package com.alfea.alfresco.repo.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alfea.alfresco.repo.model.Chart;

public interface ChartService {
	
	public static final String ALL = "all";
	
	/**
	 * Convert Charts Map to a JSON List
	 * @param charts
	 * @return
	 */
	public abstract List<String> chartsToJSONList(Map<String, Chart> charts);

	/**
	 * Add activity to a chart map
	 * @param charts
	 * @param chartName
	 * @param date
	 */
	public abstract void incrementChart(Map<String, Chart> charts, String chartName, Date date);
	
	/**
	 * Initialize a chart depending the period
	 * @param chartName 
	 * @param displayName 
	 * @param period 
	 * @return
	 */
	public abstract Chart getInitialChart(String chartName, String displayName, String period);
}
