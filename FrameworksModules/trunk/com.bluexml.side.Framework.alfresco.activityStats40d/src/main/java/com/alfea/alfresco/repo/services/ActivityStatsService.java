package com.alfea.alfresco.repo.services;

import java.text.ParseException;
import java.util.List;

import org.json.JSONException;

import com.alfea.alfresco.repo.model.ActivityStat;

public interface ActivityStatsService {

	/**
	 * Get User Feed Entries
	 * @return
	 */
	public abstract List<String> getUserFeedEntries(String person);

	/**
	 * Get Site Feed Entries
	 * @return
	 */
	public abstract List<String> getSiteFeedEntries(List<String> sites);

	/**
	 * Get an activity from an activity feed
	 * @param activityFeed
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	public abstract ActivityStat parseActivityFeed(String activityFeed)
			throws JSONException, ParseException;
}