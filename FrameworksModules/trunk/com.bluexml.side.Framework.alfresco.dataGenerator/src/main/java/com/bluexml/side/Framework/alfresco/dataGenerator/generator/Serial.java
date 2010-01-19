package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.io.Serializable;

public class Serial implements Serializable {

	private static final long serialVersionUID = 4980443534822014010L;
	
	private String property;
	private Object data;
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public Serial(String property,Object data){
		this.property = property;
		this.data = data;
	}
	
}
