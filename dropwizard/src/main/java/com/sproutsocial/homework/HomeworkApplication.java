package com.sproutsocial.homework;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sproutsocial.homework.health.DataSourceFactoryHealthCheck;
import com.sproutsocial.homework.resources.TimelineResource;
import com.sproutsocial.homework.resources.TweetResource;
import com.sproutsocial.homework.db.TwitterAccountDAO;
import com.sproutsocial.homework.http.TwitterClient;

public class HomeworkApplication extends Application<HomeworkConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(HomeworkApplication.class);

    public static void main(final String[] args) throws Exception {
        new HomeworkApplication().run(args);
    }

    @Override
    public String getName() {
        return "Homework";
    }

    @Override
    public void initialize(final Bootstrap<HomeworkConfiguration> bootstrap) {
    }

    @Override
    public void run(final HomeworkConfiguration configuration, final Environment environment) {
        LOG.info("run: entering...");
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "sqlite");
        final TwitterAccountDAO twitterAccountDAO = jdbi.onDemand(TwitterAccountDAO.class);
        final TwitterClient twitterClient = new TwitterClient(configuration.getTwitterConsumerKey(), configuration.getTwitterConsumerSecret());

        final DataSourceFactoryHealthCheck healthCheck = new DataSourceFactoryHealthCheck(configuration.getDataSourceFactory());
        environment.healthChecks().register("dataSourceFactory-health-check", healthCheck);

        environment.jersey().register(new TimelineResource(twitterAccountDAO, twitterClient));
        environment.jersey().register(new TweetResource(twitterAccountDAO));
        LOG.info("run: exiting");
    }

}
