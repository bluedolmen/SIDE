package com.alfea.alfresco.repo.web.scripts.activity.stats;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.security.AuthorityService;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import com.alfea.alfresco.repo.model.ActivityStat;
import com.alfea.alfresco.repo.model.Chart;
import com.alfea.alfresco.repo.services.ActivityStatsService;
import com.alfea.alfresco.repo.services.ChartService;

/**
 * Java-backed WebScript to retrieve activity stats
 * 
 * @author gwillems
 * 
 */
public class ActivityStatsWebScript extends DeclarativeWebScript {
	public static final String PARAM_TYPES = "types";
	public static final String PARAM_PERIOD = "period";
	public static final String PARAM_SITES = "sites";
	public static final String PARAM_PERSON = "person";
	public static final String ALL_TYPES = "all";
	public static final String ALL_SITES = "all";
	
	private static final Log logger = LogFactory.getLog(ActivityStatsWebScript.class);

    protected ActivityStatsService activityStatsService;
    protected ChartService chartService;
    protected SiteService siteService;
    protected AuthorityService authorityService;
        

	private List<String> types = new ArrayList<String>();
	private String period;
	private List<String> sites = new ArrayList<String>();
	private String person;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.alfresco.web.scripts.DeclarativeWebScript#executeImpl(org.alfresco
	 * .web.scripts.WebScriptRequest, org.alfresco.web.scripts.Status,
	 * org.alfresco.web.scripts.Cache)
	 */
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req,	Status status, Cache cache) {
		logParams(req);
		retrieveParams(req);
		boolean isAdmin = authorityService.isAdminAuthority(AuthenticationUtil.getFullyAuthenticatedUser());
		Map<String, Object> model = new HashMap<String, Object>();
		
		//Get initial chart from sites
		Map<String,Chart> charts = getChartsFromSites();

		//G et feed entries
		List<String> feedEntries;
		// If not admin, get user feed entries
		if(!isAdmin){
			feedEntries= activityStatsService.getUserFeedEntries(this.person);
		}
		else{
			// If admin and contains ALL_SITES, get feed entries for all available sites
			if(this.sites.contains(ALL_SITES)){
				List<SiteInfo> allSitesInfo = siteService.listSites(null, null);
				List<String> allSites = new ArrayList<String>();
				for(SiteInfo site : allSitesInfo){
					allSites.add(site.getShortName());
				}
				feedEntries = activityStatsService.getSiteFeedEntries(allSites);
			}
			// Else, get feed entries for resquested sites
			else{
				feedEntries = activityStatsService.getSiteFeedEntries(this.sites);
			}
		}
		
		for (String feed : feedEntries){
			if (logger.isDebugEnabled()) {
				logger.debug("Feed:" + feed);
			}
			try {
				ActivityStat activityStat = activityStatsService.parseActivityFeed(feed);
				if (this.types.contains(ALL_TYPES) || this.types.contains(activityStat.getType())){
					chartService.incrementChart(charts, activityStat.getSite(), activityStat.getDate());
				}
			} catch (JSONException e) {
				e.printStackTrace();
				status.setException(e);
			} catch (ParseException e) {
				e.printStackTrace();
				status.setException(e);
			}
		}
		model.put("charts", chartService.chartsToJSONList(charts));
		return model;
	}

	/**
	 * Retrive parameters from request
	 * @param req
	 */
	protected void retrieveParams(final WebScriptRequest req){
		if(req.getParameter(PARAM_TYPES) != null && req.getParameter(PARAM_TYPES).length()>0){
			this.types = new ArrayList<String>(Arrays.asList(req.getParameter(PARAM_TYPES).split(",")));
		}
		if(req.getParameter(PARAM_SITES) != null && req.getParameter(PARAM_SITES).length()>0){
			this.sites = new ArrayList<String>(Arrays.asList(req.getParameter(PARAM_SITES).split(",")));
		}
		else{
			//default value is all sites
			this.sites.add("all");
		}
		this.period = req.getParameter(PARAM_PERIOD);
		this.person = req.getParameter(PARAM_PERSON);
	}

	/**
	 * Get chart list from sites
	 * @return
	 */
	public Map<String,Chart> getChartsFromSites(){
		Map<String,Chart> charts = new HashMap<String,Chart>();
		// For each site, initialize a chart
		for (String siteName : sites){
			if(ChartService.ALL.equals(siteName)){
				charts.put(siteName, chartService.getInitialChart(siteName, null, period));
			}
			else{
				SiteInfo site = siteService.getSite(siteName);
				charts.put(siteName, chartService.getInitialChart(siteName, site.getTitle(), period));
			}
		}
		return charts;
	}
	
	/**
	 * Log parameters from request
	 * @param req
	 */
	protected void logParams(final WebScriptRequest req){
		if (logger.isDebugEnabled()) {
			logger.debug("PARAM TYPES value: " + req.getParameter(PARAM_TYPES));
			logger.debug("PARAM PERIOD value: "	+ req.getParameter(PARAM_PERIOD));
			logger.debug("PARAM SITES value: "	+ req.getParameter(PARAM_SITES));
			logger.debug("PARAM PERSON value: "	+ req.getParameter(PARAM_PERSON));
		}
	}

	/**
	 * @return the activityStatsService
	 */
	public ActivityStatsService getActivityStatsService() {
		return activityStatsService;
	}

	/**
	 * @param activityStatsService the activityStatsService to set
	 */
	public void setActivityStatsService(ActivityStatsService activityStatsService) {
		this.activityStatsService = activityStatsService;
	}

	/**
	 * @param chartService the chartService to set
	 */
	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}

	/**
	 * @return the chartService
	 */
	public ChartService getChartService() {
		return chartService;
	}

	/**
	 * @return the siteService
	 */
	public SiteService getSiteService() {
		return siteService;
	}

	/**
	 * @param siteService the siteService to set
	 */
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	/**
	 * @param authorityService the authorityService to set
	 */
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

}
