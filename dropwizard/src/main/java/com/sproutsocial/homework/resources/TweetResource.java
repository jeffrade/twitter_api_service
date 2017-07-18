package com.sproutsocial.homework.resources;

import com.sproutsocial.homework.model.Error;
import com.sproutsocial.homework.model.Response;
import com.sproutsocial.homework.model.Tweet;
import com.sproutsocial.homework.model.TwitterAccount;
import com.sproutsocial.homework.db.TwitterAccountDAO;
import com.sproutsocial.homework.http.TwitterClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import com.github.scribejava.core.exceptions.OAuthException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import javax.validation.constraints.NotNull;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

@Path("/api/sprout/v1/twitter/account/{twitterAccountId}/tweet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TweetResource {

  private static final Logger LOG = LoggerFactory.getLogger(TweetResource.class);

  private TwitterAccountDAO twitterAccountDAO;

  private TwitterClient twitterClient;

  public TweetResource(TwitterAccountDAO twitterAccountDAO, TwitterClient twitterClient) {
    super();
    this.twitterAccountDAO = twitterAccountDAO;
    this.twitterClient = twitterClient;
  }

  /**
  * Posts a new tweet for given twitter account id. enable_dm_commands is always set to false
  * @param twitterAccountId String holding Twitter account id
  * @param tweet {@code Tweet} object holding the tweet to post
  * @return a Tweet object that was just posted
  */
  @POST
  @Timed
  public Response<?> postTweet(
      @PathParam("twitterAccountId") final String twitterAccountId,
      @Valid @NotNull final Tweet tweet) {
    LOG.debug("entering...");
    final TwitterAccount twitterAccount = twitterAccountDAO.findTwitterAccountByAccountId(twitterAccountId);
    if(twitterAccount == null) {
      LOG.error("Encountered problem looking for account in database");
      Error errorResponse = new Error(Status.BAD_REQUEST, "account not found");
      return new Response<Error>(errorResponse);
    }

    Tweet postedTweet = null;

    try {
      postedTweet = twitterClient.postTweetRequest(twitterAccount.getOAuthToken(), twitterAccount.getOAuthSecret(), tweet);
    } catch(OAuthException e) {
      LOG.error("Encountered an OAuth error:", e);
      Error errorResponse = new Error(Status.INTERNAL_SERVER_ERROR, "encountered an OAuth exception");
      return new Response<Error>(errorResponse);
    } catch(UnsupportedEncodingException e) {
      LOG.error("Encountered problem with text to Tweet", e);
      Error errorResponse = new Error(Status.INTERNAL_SERVER_ERROR, "encountered URL encoding exception");
      return new Response<Error>(errorResponse);
    } catch(Exception e) {
      LOG.error("Encountered problem while getting a response", e);
      Error errorResponse = new Error(Status.INTERNAL_SERVER_ERROR, e.getMessage());
      return new Response<Error>(errorResponse);
    }

    return new Response<Tweet>(postedTweet);
  }

}
