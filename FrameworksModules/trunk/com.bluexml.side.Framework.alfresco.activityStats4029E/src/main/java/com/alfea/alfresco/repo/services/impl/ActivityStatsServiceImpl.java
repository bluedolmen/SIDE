/**
 * 
 */
package com.alfea.alfresco.repo.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.activities.ActivityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.alfea.alfresco.repo.enumeration.PersonType;
import com.alfea.alfresco.repo.model.ActivityStat;
import com.alfea.alfresco.repo.services.ActivityStatsService;

/**
 * @author gwillems
 *
 */
public class ActivityStatsServiceImpl implements ActivityStatsService {
	private static final Log logger = LogFactory.getLog(ActivityStatsServiceImpl.class);
	public static final String FORMAT = "json";
	private ActivityService activityService;

	
	
	/* (non-Javadoc)
	 * @see com.alfea.alfresco.repo.services.ActivityStatsService#getUserFeedEntries(java.lang.String)
	 */
	public List<String> getUserFeedEntries(String person){
		String userId = AuthenticationUtil.getFullyAuthenticatedUser();
		String siteId = null;
		boolean exclThisUser = PersonType.toPerson(person.toUpperCase()).equals(PersonType.OTHERS)?true:false;
		boolean exclOtherUsers = PersonType.toPerson(person.toUpperCase()).equals(PersonType.MINE)?true:false;
		if (logger.isDebugEnabled()) {
			logger.debug("Get user feed entries with :" + userId + ", " + FORMAT + "," + siteId + "," + exclThisUser + "," + exclOtherUsers);
		}
		return activityService.getUserFeedEntries(userId, FORMAT, siteId, exclThisUser, exclOtherUsers);
	}
	
	/* (non-Javadoc)
	 * @see com.alfea.alfresco.repo.services.ActivityStatsService#getSiteFeedEntries(java.util.List)
	 */
	public List<String> getSiteFeedEntries(List<String> sites){
		List<String> feedEntries = new ArrayList<String>();
		for(int i = 0 ; i < sites.size() ; i++){
			feedEntries.addAll(activityService.getSiteFeedEntries(sites.get(i), FORMAT));
		}
		return feedEntries;
	}
	
	/* (non-Javadoc)
	 * @see com.alfea.alfresco.repo.services.ActivityStatsService#parseActivityFeed(java.lang.String)
	 */
	public ActivityStat parseActivityFeed(String activityFeed) throws JSONException, ParseException{
		ActivityStat activityStat = new ActivityStat();
		JSONObject joFeed = new JSONObject(new JSONTokener(activityFeed));
		activityStat.setUserId(joFeed.getString("postUserId"));
		activityStat.setType(joFeed.getString("activityType"));
		activityStat.setSite(joFeed.getString("siteNetwork"));
		String postDateStr = joFeed.getString("postDate");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
		activityStat.setDate(simpleDateFormat.parse(postDateStr));
		return activityStat;
	}

	/**
	 * @return the activityService
	 */
	public ActivityService getActivityService() {
		return activityService;
	}

	/**
	 * @param activityService the activityService to set
	 */
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
}
