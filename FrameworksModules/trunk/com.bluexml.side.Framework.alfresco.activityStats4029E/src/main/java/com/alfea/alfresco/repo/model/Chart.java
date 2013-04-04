/**
 * 
 */
package com.alfea.alfresco.repo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gwillems
 *
 */
public class Chart extends ArrayList<ChartPoint> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1923122017714643595L;
	private static final Log logger = LogFactory.getLog(Chart.class);
	private String name;
	private String displayName;
	private long sum;
	
	/**
	 * Constructor
	 * @param siteName
	 * @param displayName2
	 */
	public Chart(String name, String displayName) {
		this.name = name;
		this.displayName = displayName;
	}
	
	/**
	 * Increment chart depending the value
	 * @param postDate
	 */
	public void incrementChart(Date postDate) {
		for (ChartPoint chartPoint : this){
			if (postDate.equals(chartPoint.getStart()) || (postDate.after(chartPoint.getStart()) && postDate.before(chartPoint.getEnd()))){
				chartPoint.incrementValue();
				this.sum++;
				if(logger.isDebugEnabled()){
					logger.debug("Increment point(" + this.getName() + ", " + 
							chartPoint.getStart().toString() + ", " + 
							chartPoint.getEnd().toString() + ") " +
							"with date " + 
							postDate.toString());
				}
			}	
		}
	}
	
	/**
	 * Chart to JSON String
	 * @return
	 */
	public String toJSONString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		stringBuilder.append("\"name\":").append("\"").append(name).append("\"").append(",");
		stringBuilder.append("\"displayName\":").append("\"").append(displayName).append("\"").append(",");
		stringBuilder.append("\"sum\":").append("\"").append(sum).append("\"").append(",");
		stringBuilder.append("\"points\":[");
		Iterator<ChartPoint> pointList = this.iterator();
		while(pointList.hasNext()){
			stringBuilder.append(pointList.next().toJSONString());
			if(pointList.hasNext()){
				stringBuilder.append(",");
			}
		}
		stringBuilder.append("]");
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @return the name
	 */
	public long getSum() {
		return this.sum;
	}
	

}
