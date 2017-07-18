package com.sproutsocial.homework.json;

import com.sproutsocial.homework.model.Timeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * FIXME I'm sure there is an easier solution (e.g. Using annotations in Timeline.java) that does this.
 */
public class TimelineListMapper {

  private static final Logger LOG = LoggerFactory.getLogger(TimelineListMapper.class);

  private static final SimpleDateFormat SDF = new SimpleDateFormat("EEE MMM dd HH:MM:ss Z yyyy");

  private String json;

  public TimelineListMapper(final String json) {
    this.json = json;
  }

  public List<Timeline> parse() throws Exception {
    List<Timeline> timelineList = new ArrayList<Timeline>();

    JSONArray timelineArray = (JSONArray) new JSONTokener(this.json).nextValue();

    for (int i = 0; i < timelineArray.length(); i++) {
      final JSONObject timelineObject = timelineArray.getJSONObject(i);
      final JSONObject user = (JSONObject) timelineObject.getJSONObject("user");
      timelineList.add(new Timeline(
        timelineObject.getString("id"),
        user.getString("screen_name"),
        timelineObject.getString("text"),
        parseDateStringToUnixTimestamp(timelineObject.getString("created_at")),
        user.getString("profile_image_url")
      ));
    }

    return timelineList;
  }

  private long parseDateStringToUnixTimestamp(final String dateTimeString) throws ParseException{
    long timestamp;

    try {
      Date date = SDF.parse(dateTimeString);
      timestamp = date.getTime();
    } catch(ParseException e) {
      LOG.error("error parsing dateTimeString " + dateTimeString, e);
      timestamp = -1l;//FIXME Need a fallback mechanism (e.g. regex) to attempt parsing if ParseException thrown
    }

    return timestamp;
  }

}
