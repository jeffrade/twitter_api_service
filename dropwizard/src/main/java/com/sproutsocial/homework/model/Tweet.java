package com.sproutsocial.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;

public class Tweet {

  private String id;

  @List({
    @Length(min = 1, message = "A tweet must be at least 1 character"),
    @Length(max = 140, message = "A tweet must not be greater than 140 characters")
  })
  private String text;//also known as `status` in twitter documentation

  private boolean enableDmCommands = false;

  public Tweet() {
    super();
  }

  public Tweet(@JsonProperty("text") final String text) {
    super();
    this.text = text;
  }

  public Tweet(final String id, final String text) {
    super();
    this.id = id;
    this.text = text;
  }

  /**
  * Returns id of the tweet from twitter
  * @return a {@code String} that contains the tweet id
  */
  @JsonProperty
  public String getId() {
    return id;
  }

  /**
  * Sets id of the tweet for twitter
  * @param id a {@code String} containing the id of tweet
  */
  @JsonProperty
  public void setId(String id) {
    this.id = id;
  }

  /**
  * Returns text of the tweet from twitter
  * @return a {@code String} that contains the tweet text
  */
  @JsonProperty
  public String getText() {
    return text;
  }

  /**
  * Sets new text of the tweet for twitter
  * @param text a {@code String} containing the tweet
  */
  @JsonProperty
  public void setText(String text) {
    this.text = text;
  }

  /**
  * Returns value of enableDmCommands
  * @return enableDmCommands
  */
  @JsonProperty
  public boolean isEnableDmCommands() {
    return enableDmCommands;
  }

  /**
  * Sets new value of enableDmCommands
  * @param enableDmCommands defaults to false to not DM
  */
  @JsonProperty
  public void setEnableDmCommands(boolean enableDmCommands) {
    this.enableDmCommands = enableDmCommands;
  }

  /**
  * Create string representation of Tweet for printing
  * @return this {@code Tweet} object as a String
  */
  @Override
  public String toString() {
    return "Tweet [text=" + text + "]";
  }
}
