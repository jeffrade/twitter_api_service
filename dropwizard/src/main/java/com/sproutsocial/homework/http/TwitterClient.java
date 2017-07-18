package com.sproutsocial.homework.http;

import com.sproutsocial.homework.model.Tweet;
import com.sproutsocial.homework.model.Timeline;
import com.sproutsocial.homework.json.TimelineListMapper;
import com.sproutsocial.homework.json.TweetMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.exceptions.OAuthException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.ArrayList;
import java.net.URLEncoder;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class TwitterClient {

  private static final Logger LOG = LoggerFactory.getLogger(TwitterClient.class);

  private static final String TIMELINE_BASE_URL = "https://api.twitter.com/1.1/statuses/home_timeline.json?";

  private static final String TWEET_BASE_URL = "https://api.twitter.com/1.1/statuses/update.json?";

  private OAuth10aService twitterOAuthService;

  private String apiKey;
  private String apiSecret;

  public TwitterClient(final String apiKey, final String apiSecret) {
    super();
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
  }

  public List<Timeline> getHomeTimelineRequest(
      final String oAuthToken,
      final String oAuthSecret,
      final String count,
      final String sinceId,
      final String maxId
  ) throws OAuthException, IOException, InterruptedException, ExecutionException, Exception {
    LOG.debug("entering...");
    OAuth10aService twitterOAuthService = createTwitterOAuthService();

    final String timelineUrl = buildTimelineUrl(count, sinceId, maxId);
    OAuthRequest homeTimelineRequest = new OAuthRequest(Verb.GET, timelineUrl);
    twitterOAuthService.signRequest(new OAuth1AccessToken(oAuthToken, oAuthSecret), homeTimelineRequest);

    com.github.scribejava.core.model.Response response = twitterOAuthService.execute(homeTimelineRequest);
    return checkForErrorsAndHandleTimelineResponse(response.getBody());
  }

  public Tweet postTweetRequest(
      final String oAuthToken,
      final String oAuthSecret,
      final Tweet tweet
  ) throws UnsupportedEncodingException, OAuthException, IOException, InterruptedException, ExecutionException, Exception {
    LOG.debug("entering...");
    OAuth10aService twitterOAuthService = createTwitterOAuthService();

    final String tweetUrl = buildTweetUrl(tweet.getText());
    OAuthRequest tweetRequest = new OAuthRequest(Verb.POST, tweetUrl);
    twitterOAuthService.signRequest(new OAuth1AccessToken(oAuthToken, oAuthSecret), tweetRequest);

    com.github.scribejava.core.model.Response response = twitterOAuthService.execute(tweetRequest);
    return checkForErrorsAndHandleTweetResponse(response.getBody());
  }

  private List<Timeline> checkForErrorsAndHandleTimelineResponse(final String responseBody) throws Exception {
    checkForErrors(responseBody);
    return new TimelineListMapper(responseBody).parse();
  }

  private Tweet checkForErrorsAndHandleTweetResponse(final String responseBody) throws Exception {
    checkForErrors(responseBody);
    return new TweetMapper(responseBody).parse();
  }

  private void checkForErrors(final String responseBody) throws Exception {
    if(responseBody.indexOf("{\"errors\":") == 0) {//FIXME I don't like this way of checking if errors exist
      JSONObject errorObject = (JSONObject) new JSONTokener(responseBody).nextValue();
      if(errorObject != null && errorObject.getJSONArray("errors") != null) {
        throw new Exception(errorObject.getJSONArray("errors").toString());
      }
    }
  }

  private OAuth10aService createTwitterOAuthService() {
    return new ServiceBuilder(this.apiKey)
      .apiSecret(this.apiSecret)
      .build(TwitterApi.instance());
  }

  private String buildTimelineUrl(final String count, final String sinceId, final String maxId) {
    final StringBuilder sb = new StringBuilder(TIMELINE_BASE_URL);
    if(count != null && !"".equals(count.trim())) {
      sb.append("count=");
      sb.append(count);
      sb.append("&");
    }
    if(sinceId != null && !"".equals(sinceId.trim())) {
      sb.append("since_id=");
      sb.append(sinceId);
      sb.append("&");
    }
    if(maxId != null && !"".equals(maxId.trim())) {
      sb.append("max_id=");
      sb.append(maxId);
      sb.append("&");
    }
    return sb.toString();
  }

  private String buildTweetUrl(final String text) throws UnsupportedEncodingException {
    final StringBuilder sb = new StringBuilder(TWEET_BASE_URL);
    sb.append("status=");
    sb.append(URLEncoder.encode(text, "UTF-8"));
    sb.append("&enable_dm_commands=false");
    return sb.toString();
  }

}
