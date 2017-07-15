package com.sproutsocial.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;

public class Tweet {

  @List({
    @Length(min = 1, message = "A tweet must be at least 1 character"),
    @Length(max = 140, message = "A tweet must not be greater than 140 characters")
  })
  private String text;

  public Tweet() {
    super();
  }

  public Tweet(@JsonProperty("text") final String text) {
    super();
    this.text = text;
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
  *
  @JsonProperty
  public void setText(String text) {
    this.text = text;
  }*/

	/**
	* Create string representation of Tweet for printing
	* @return this {@code Tweet} object as a String
	*/
	@Override
	public String toString() {
		return "Tweet [text=" + text + "]";
	}
}
