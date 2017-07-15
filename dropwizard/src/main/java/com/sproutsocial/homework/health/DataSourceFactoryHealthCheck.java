package com.sproutsocial.homework.health;

import io.dropwizard.db.DataSourceFactory;

import com.codahale.metrics.health.HealthCheck;

public class DataSourceFactoryHealthCheck extends HealthCheck {

  private DataSourceFactory dataSourceFactory;

  public DataSourceFactoryHealthCheck(DataSourceFactory dataSourceFactory) {
    this.dataSourceFactory = dataSourceFactory;
  }

  @Override
  protected Result check() throws Exception {
    if (dataSourceFactory == null) {
      return Result.unhealthy("dataSourceFactory is null");
    }
    return Result.healthy();
  }

}
