/**
 * 
 */
package com.alfea.alfresco.repo.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gwillems
 *
 */
public class ChartPoint {
	
	private Date start;
	private Date end;
	private long value;

	/**
	 * Constructor
	 */
	public ChartPoint(){
		
	}

	/**
	 * Constructor
	 * @param start
	 * @param end
	 */
	public ChartPoint(Date start, Date end) {
		this.setStart(start);
		this.setEnd(end);
		this.setValue(0);
	}
	
	/**
	 * Increment Value
	 */
	public void incrementValue() {
		this.value++;
		
	}
	
	/**
	 * ChartPoint to JSON String
	 * @return
	 */
	public String toJSONString(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		stringBuilder.append("\"start\":").append("\"").append(df.format(start)).append("\"").append(",");
		stringBuilder.append("\"end\":").append("\"").append(df.format(end)).append("\"").append(",");
		stringBuilder.append("\"value\":").append("\"").append(value).append("\"");
		stringBuilder.append("}");
		return stringBuilder.toString();
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public long getValue() {
		return value;
	}
}
