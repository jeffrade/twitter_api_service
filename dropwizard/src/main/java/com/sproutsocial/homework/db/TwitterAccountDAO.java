package com.sproutsocial.homework.db;

import com.sproutsocial.homework.model.TwitterAccount;
import com.sproutsocial.homework.mapper.TwitterAccountMapper;

import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface TwitterAccountDAO {

  @SqlQuery("select id, twitter_id, oauth_token, oauth_secret " +
            "from twitter_accounts " +
            "where twitter_id = :twitterAccountId")
  @Mapper(TwitterAccountMapper.class)
  TwitterAccount findTwitterAccountByAccountId(@Bind("twitterAccountId") String twitterAccountId);

}
