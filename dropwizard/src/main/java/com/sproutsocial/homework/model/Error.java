package com.sproutsocial.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.ws.rs.core.Response.Status;

public class Error {

  private String status;

  private String message;

  public Error(final Status status, final String message) {
    super();
    this.status = status.toString();
    this.message = message;
  }

  @JsonProperty
  public String getStatus() {
    return status;
  }

  @JsonProperty
  public String getMessage() {
    return message;
  }
}
