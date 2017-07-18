package com.sproutsocial.homework.resources;

import com.sproutsocial.homework.model.Response;
import com.sproutsocial.homework.model.Error;
import com.sproutsocial.homework.model.Timeline;
import com.sproutsocial.homework.model.TwitterAccount;
import com.sproutsocial.homework.db.TwitterAccountDAO;
import com.sproutsocial.homework.http.TwitterClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.jersey.caching.CacheControl;
import com.github.scribejava.core.exceptions.OAuthException;

import com.codahale.metrics.annotation.Timed;

import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

@Path("/api/sprout/v1/twitter/account/{twitterAccountId}/timeline")
@Produces(MediaType.APPLICATION_JSON)
public class TimelineResource {

  private static final Logger LOG = LoggerFactory.getLogger(TimelineResource.class);

  private TwitterAccountDAO twitterAccountDAO;

  private TwitterClient twitterClient;

  public TimelineResource(TwitterAccountDAO twitterAccountDAO, TwitterClient twitterClient) {
    super();
    this.twitterAccountDAO = twitterAccountDAO;
    this.twitterClient = twitterClient;
  }

  /**
  * Retrieves Twitter {@code Timeline} for a given twitter account id
  * @param twitterAccountId String holding Twitter account id
  * @param count String holding count of tweets
  * @param sinceId String holding since_id of tweet wanted
  * @param maxId String holding max_id of Tweet wanted
  * @return a JSON representation of a {@code Timeline} object
  */
  @GET
  @CacheControl(maxAge = 30, maxAgeUnit = TimeUnit.SECONDS)
  @Timed
  public Response<?> getTimeline(
      @PathParam("twitterAccountId") final String twitterAccountId,
      @QueryParam("count") final String count,
      @QueryParam("since_id") final String sinceId,
      @QueryParam("max_id") final String maxId
  ) {
    LOG.debug("entering...");
    final TwitterAccount twitterAccount = twitterAccountDAO.findTwitterAccountByAccountId(twitterAccountId);
    if(twitterAccount == null) {
      LOG.error("Encountered problem looking for account in database");
      Error errorResponse = new Error(Status.BAD_REQUEST, "account not found");
      return new Response<Error>(errorResponse);
    }
    List<Timeline> timelineList = null;

    try{
      timelineList = twitterClient.getHomeTimelineRequest(
          twitterAccount.getOAuthToken(),
          twitterAccount.getOAuthSecret(),
          count,
          sinceId,
          maxId
      );
    } catch(OAuthException e) {
      LOG.error("Encountered an OAuth error:", e);
      Error errorResponse = new Error(Status.INTERNAL_SERVER_ERROR, "encountered an OAuth exception");
      return new Response<Error>(errorResponse);
    } catch(IOException e) {
      LOG.error("Encountered an IO error:", e);
      Error errorResponse = new Error(Status.INTERNAL_SERVER_ERROR, "encountered an IO exception");
      return new Response<Error>(errorResponse);
    } catch(InterruptedException e) {
      LOG.error("Encountered an thread interupted error:", e);
      Error errorResponse = new Error(Status.INTERNAL_SERVER_ERROR, "thread interupted error");
      return new Response<Error>(errorResponse);
    } catch(ExecutionException e) {
      LOG.error("Encountered an thread execution error:", e);
      Error errorResponse = new Error(Status.INTERNAL_SERVER_ERROR, "thread execution error");
      return new Response<Error>(errorResponse);
    } catch(Exception e) {
      LOG.error("Encountered problem while getting a response", e);
      Error errorResponse = new Error(Status.INTERNAL_SERVER_ERROR, e.getMessage());
      return new Response<Error>(errorResponse);
    }

    return new Response<List<Timeline>>(timelineList);
  }

}
