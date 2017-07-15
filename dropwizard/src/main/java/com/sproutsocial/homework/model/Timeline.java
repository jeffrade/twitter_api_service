package com.sproutsocial.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * - Extract the Screen Name, Text, Date, and Profile Image from each object
 * - Transform the date into a Unix Timestamp
 */
public class Timeline {

  private String screenName;

  public Timeline() {
    super();
  }

  public Timeline(final String screenName) {
    super();
    this.screenName = screenName;
  }

  /**
  * Returns value of user's screen name
  * @return a {@code String} containing user's screen name
  */
  @JsonProperty
  public String getScreenName() {
    return screenName;
  }

  /**
  * Sets new value of screenName
  * @param screenName user's Twitter screen name or "handle"
  */
  public void setScreenName(final String screenName) {
    this.screenName = screenName;
  }
}
