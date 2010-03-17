/**
 * This class allows serialization of data by property and type 
 * to manage unicity of some attributes defined in the model 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.io.Serializable;

public class Serial implements Serializable {

	private static final long serialVersionUID = 4980443534822014010L;
	
	private String type;
	private String property;
	private Object data;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	
	public Serial(String type,String property,Object data){
		this.type = type;
		this.property = property;
		this.data = data;
	}
	
}
