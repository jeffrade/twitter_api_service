package com.sproutsocial.homework.json;

import com.sproutsocial.homework.model.Tweet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * FIXME I'm sure there is an easier solution (e.g. Using annotations in Timeline.java) that does this.
 */
public class TweetMapper {

  private static final Logger LOG = LoggerFactory.getLogger(TweetMapper.class);

  private String json;

  public TweetMapper(final String json) {
    this.json = json;
  }

  public Tweet parse() throws Exception {
    final JSONObject jsonObject = (JSONObject) new JSONTokener(this.json).nextValue();
    return new Tweet(jsonObject.getString("id"), jsonObject.getString("text"));
  }

}
