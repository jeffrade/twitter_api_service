You will implement a basic REST API. Both projects are configured with a sqlite database (.db file) containing credentials for a Twitter account that can be used to access their REST API. Both projects contain configuration in the appropriate place which contains an Application Token and Secret for making authenticated requests against Twitter's API.

Note:
  - The provided sqlite database contains a single table called "twitter_accounts"
  - The skeleton applications are already configured to read from this database
  - No models/DAOs, endpoints/resources, or url configurations have been defined

Using the provided skeleton, complete the following:
1. Create a REST API endpoint that fulfills the following requirements:
  - Accept a "twitter_account.id"
  - Lookup the Twitter account by id in the sqlite database
  - Use the credentials associated with that account to fetch their home timeline from Twitter's API
  - Extract the Screen Name, Text, Date, and Profile Image from each object
  - Transform the date into a Unix Timestamp
  - Render the result as a JSON response

2. Create a second endpoint that does the following:
  - Accept a "twitter_account.id"
  - Lookup the Twitter account by id in the sqlite database
  - Accept a text parameter
  - Use the credentials associated with the twitter account to send that text as a tweet using Twitter's API


Requirements/expectations:
  - Assume this is production quality code.
  - The URLs should be laid out in a RESTful fashion
  - Error handling matters
  - etc...

### Rest API Calls:
```
GET /api/sprout/v1/twitter/account/<twitter_account.id>/timeline?count=<>&since_id=<>&max_id=<>
POST /api/sprout/v1/twitter/account/<twitter_account.id>/tweet -d "<tweet.text>"
```

### Running:
```
java -jar target/homework-0.0.1-SNAPSHOT.jar server config.yml
```

