/**
 * 
 */
package com.bluexml.xforms.controller.beans;

/**
 * @author Amenel
 * 
 */
public class EditNodeBean {
	private String dataId;
	private String dataType;

	/**
	 * @param dataId
	 * @param dataType
	 */
	public EditNodeBean(String dataId, String dataType) {
		super();
		this.dataId = dataId;
		this.dataType = dataType;
	}

	/**
	 * @return the dataId
	 */
	public String getDataId() {
		return dataId;
	}

	/**
	 * @return the datatType
	 */
	public String getDataType() {
		return dataType;
	}

}
