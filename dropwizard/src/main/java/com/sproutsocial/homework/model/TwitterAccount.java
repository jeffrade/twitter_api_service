package com.sproutsocial.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitterAccount {

  private long id;

  private long twitterAccountId;

  private String oAuthToken;

  private String oAuthSecret;

  public TwitterAccount() {
    super();
  }

  /**
  * Returns value of id
  * @return database row id
  */
  public long getId() {
    return id;
  }

  /**
  * Sets new value of id
  * @param id database row id
  */
  public void setId(long id) {
    this.id = id;
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

  /**
  * Returns value of oAuthToken
  * @return returns OAuth token
  */
  public String getOAuthToken() {
    return oAuthToken;
  }

  /**
  * Sets new value of oAuthToken
  * @param oAuthToken OAuth token
  */
  public void setOAuthToken(String oAuthToken) {
    this.oAuthToken = oAuthToken;
  }

  /**
  * Returns value of oAuthSecret
  * @return returns OAuth secret key
  */
  public String getOAuthSecret() {
    return oAuthSecret;
  }

  /**
  * Sets new value of oAuthSecret
  * @param oAuthSecret OAuth secret key
  */
  public void setOAuthSecret(String oAuthSecret) {
    this.oAuthSecret = oAuthSecret;
  }

  /**
  * Create string representation of TwitterAccount for printing
  * @return returns String representation of this object
  */
  @Override
  public String toString() {
    return "TwitterAccount [id=" + id + ", twitterAccountId=" + twitterAccountId + ", oAuthToken=" + oAuthToken + "]";
  }
}
