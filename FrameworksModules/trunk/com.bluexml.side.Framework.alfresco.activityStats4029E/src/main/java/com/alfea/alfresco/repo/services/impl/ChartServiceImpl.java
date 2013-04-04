/**
 * 
 */
package com.alfea.alfresco.repo.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alfea.alfresco.repo.enumeration.PeriodType;
import com.alfea.alfresco.repo.model.Chart;
import com.alfea.alfresco.repo.model.ChartPoint;
import com.alfea.alfresco.repo.services.ChartService;

/**
 * @author gwillems
 *
 */
public class ChartServiceImpl implements ChartService{
	private static final Log logger = LogFactory.getLog(ChartServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.alfea.alfresco.repo.services.ChartService#chartsToJSONList(java.util.Map)
	 */
	public List<String> chartsToJSONList(Map<String,Chart> charts){
		List<String> jsonCharts = new ArrayList<String>();
		
		for(Chart chart: charts.values()){
			jsonCharts.add(chart.toJSONString());
		}
		return jsonCharts;
	}
	
	/* (non-Javadoc)
	 * @see com.alfea.alfresco.repo.services.ChartService#addActivity(java.util.Map, java.lang.String, java.util.Date)
	 */
	public void incrementChart(Map<String, Chart> charts, String chartName, Date date){
		if(charts.containsKey(chartName)){
			charts.get(chartName).incrementChart(date);	
		}
		if(charts.containsKey(ALL)){
			charts.get(ALL).incrementChart(date);
		}
	}

	/* (non-Javadoc)
	 * @see com.alfea.alfresco.repo.services.ChartService#getInitialChart(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Chart getInitialChart(String chartName, String displayName, String period) {
		Chart chart = new Chart(chartName, displayName);
		
		Calendar firstDate = Calendar.getInstance();
		int incField = Calendar.HOUR;
		Calendar now = Calendar.getInstance();
		switch(PeriodType.toPeriod(period)){
		case TODAY:
			incField = Calendar.HOUR;
			firstDate.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
			break;
		case WEEK:
			incField = Calendar.DATE;
			firstDate.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
			firstDate.add(Calendar.DATE, -7);
			break;
		case MONTH:
			incField = Calendar.DATE;
			firstDate.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1, 0, 0, 0);
			break;
		case YEAR:
			incField = Calendar.MONTH;
			firstDate.set(now.get(Calendar.YEAR), 0, 1, 0, 0, 0);
			break;
		default: //TODAY
			incField = Calendar.HOUR;
			firstDate.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
			break;
		}
		// Create Chart Point list
		while(firstDate.before(now)){
			Date start = firstDate.getTime();
			firstDate.add(incField, 1);
			Date end = firstDate.getTime();
			chart.add(new ChartPoint(start, end));
		}
		if(logger.isDebugEnabled()){
			logger.debug("Chart " + chart.getDisplayName() + "(" + chart.getName() + ") created with " + chart.size() + " points.");
		}
		return chart;
	}
}
