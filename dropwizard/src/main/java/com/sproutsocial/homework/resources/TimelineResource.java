package com.sproutsocial.homework.resources;

import com.sproutsocial.homework.model.Timeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.jersey.caching.CacheControl;

import com.codahale.metrics.annotation.Timed;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/sprout/v1/twitter/account/{twitterAccountId}/timeline")
@Produces(MediaType.APPLICATION_JSON)
public class TimelineResource {

  private static final Logger LOG = LoggerFactory.getLogger(TimelineResource.class);

  /**
  * Retrieves Twitter {@code Timeline} for a given twitter account id
  * @param twitterAccountId String holding Twitter account id
  * @return a JSON representation of a {@code Timeline} object
  */
  @GET
  @CacheControl(maxAge = 30, maxAgeUnit = TimeUnit.SECONDS)
  @Timed
  public Timeline getTimeline(@PathParam("twitterAccountId") final String twitterAccountId) {
    LOG.info("getTimeline: entering... [twitterAccountId]=" + twitterAccountId);
    return new Timeline("dumpyFrumpy");
  }

}
