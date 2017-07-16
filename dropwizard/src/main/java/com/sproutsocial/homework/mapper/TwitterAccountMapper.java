package com.sproutsocial.homework.mapper;

import com.sproutsocial.homework.model.TwitterAccount;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TwitterAccountMapper implements ResultSetMapper<TwitterAccount> {

  @Override
  public TwitterAccount map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
    TwitterAccount twitterAccount = new TwitterAccount();
    twitterAccount.setId(resultSet.getLong("id"));
    twitterAccount.setTwitterAccountId(resultSet.getLong("twitter_id"));
    twitterAccount.setOAuthToken(resultSet.getString("oauth_token"));
    twitterAccount.setOAuthSecret(resultSet.getString("oauth_secret"));
    return twitterAccount;
  }
}
