package com.sproutsocial.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response<T> {

  private T data;

  public Response(T modelObject) {
    super();
    this.data = modelObject;
  }

  @JsonProperty
  public T getData() {
    return this.data;
  }
}
