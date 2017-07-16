package com.sproutsocial.homework.resources;

import com.sproutsocial.homework.model.Tweet;
import com.sproutsocial.homework.model.TwitterAccount;
import com.sproutsocial.homework.db.TwitterAccountDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import javax.validation.constraints.NotNull;
import javax.validation.Valid;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Path("/api/sprout/v1/twitter/account/{twitterAccountId}/tweet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TweetResource {

  private static final Logger LOG = LoggerFactory.getLogger(TweetResource.class);

  private TwitterAccountDAO twitterAccountDAO;

  public TweetResource(TwitterAccountDAO twitterAccountDAO) {
    super();
    this.twitterAccountDAO = twitterAccountDAO;
  }

  /**
  * Posts a new tweet for given twitter account id.
  * @param twitterAccountId String holding Twitter account id
  * @param tweet {@code Tweet} object holding the tweet to post
  * @return a Tweet object that was just posted
  */
  @POST
  @Timed
  public Tweet postTweet(
      @PathParam("twitterAccountId") final String twitterAccountId,
      @Valid @NotNull final Tweet tweet) {
    LOG.info("postTweet: entering... [twitterAccountId, tweet]=" + twitterAccountId + ", " + tweet.toString());
    TwitterAccount twitterAccount = twitterAccountDAO.findTwitterAccountByAccountId(twitterAccountId);
    LOG.info("getTimeline: twitterAccount=" + twitterAccount.toString());
    return tweet;
  }

}
