/**
 * 
 */
package com.bluexml.xforms.controller.beans;

/**
 * Bean for passing parameters from the list action to the controller.
 * 
 * @author Amenel
 * 
 */
public class ListActionBean {
	private String dataType;
	private String query;
	private String maxResults;
	private String format;
	private String maxLength;
	private String identifier;
	private String filterAssoc;
	private String compositionStatus;

	/**
	 * @param dataType
	 * @param query
	 * @param maxResults
	 * @param format
	 * @param maxLength
	 * @param identifier
	 * @param filterAssoc
	 * @param compositionStatus
	 */
	public ListActionBean(String dataType, String query, String maxResults, String format,
			String maxLength, String identifier, String filterAssoc, String compositionStatus) {
		this.dataType = dataType;
		this.query = query;
		this.maxResults = maxResults;
		this.format = format;
		this.maxLength = maxLength;
		this.identifier = identifier;
		this.filterAssoc = filterAssoc;
		this.compositionStatus = compositionStatus;
	}

	/**
	 * @return the compositionStatus
	 */
	public String getCompositionStatus() {
		return compositionStatus;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @return the maxResults
	 */
	public String getMaxResults() {
		return maxResults;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @return the maxLength
	 */
	public String getMaxLength() {
		return maxLength;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @return the filterAssoc
	 */
	public String getFilterAssoc() {
		return filterAssoc;
	}
}
