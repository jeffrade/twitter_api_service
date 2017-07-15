package com.sproutsocial.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitterAccount {

  private long twitterAccountId;

  public TwitterAccount() {
    super();
  }

  /**
  * Returns value of twitterAccountId
  * @return long containing user's Twitter account id
  */
  public long getTwitterAccountId() {
    return twitterAccountId;
  }

  /**
  * Sets new value of twitterAccountId
  * @param twitterAccountId a long containing user's Twitter account id
  */
  public void setTwitterAccountId(final long twitterAccountId) {
    this.twitterAccountId = twitterAccountId;
  }
}
