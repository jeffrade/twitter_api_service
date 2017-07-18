package com.sproutsocial.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Timeline {

  private String id;

  private String screenName;

  private String text;

  private long timestamp;

  private String profileImageUrl;

  public Timeline() {
    super();
  }

  public Timeline(final String id, final String screenName, final String text, final long timestamp, final String profileImageUrl) {
    super();
    this.id = id;
    this.screenName = screenName;
    this.text = text;
    this.timestamp = timestamp;
    this.profileImageUrl = profileImageUrl;
  }

  /**
  * Returns value of id
  * @return id
  */
  @JsonProperty
  public String getId() {
    return id;
  }

  /**
  * Sets new value of id
  * @param id id of the Tweet
  */
  @JsonProperty
  public void setId(String id) {
    this.id = id;
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
  @JsonProperty
  public void setScreenName(final String screenName) {
    this.screenName = screenName;
  }

  /**
  * Returns value of text
  * @return text
  */
  @JsonProperty
  public String getText() {
    return text;
  }

  /**
  * Sets new value of text
  * @param text the text of the Tweet
  */
  @JsonProperty
  public void setText(String text) {
    this.text = text;
  }

  /**
  * Returns value of timestamp
  * @return timestamp
  */
  @JsonProperty
  public long getTimestamp() {
    return timestamp;
  }

  /**
  * Sets new value of timestamp
  * @param timestamp time when Tweet was posted
  */
  @JsonProperty
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  /**
  * Returns value of profileImageUrl
  * @return profileImageUrl profile image of Tweet author
  */
  @JsonProperty
  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  /**
  * Sets new value of profileImageUrl
  * @param profileImageUrl profile image of Tweet author
  */
  @JsonProperty
  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }
}
