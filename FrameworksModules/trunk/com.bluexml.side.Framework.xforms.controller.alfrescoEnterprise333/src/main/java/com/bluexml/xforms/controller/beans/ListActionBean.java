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
	/** Namespaceprefix'ed content type to search, e.g. "wagramLeadsMgmt:Contact" or "cm:person" */
	private String dataType;

	/** Search keywords string */
	private String query;

	/** Max number of items allowed in the result set */
	private String maxResults;

	/** Pattern for formatting labels of the list items */
	private String format;

	/** Length at which labels computed using the format are truncated (if not "0") */
	private String maxLength;

	/**
	 * Local name of a property whose value will be used as the id of results. If <code>null</code>,
	 * the noderef id is used. If non-<code>null</code>, SHOULD be: 1- an actual property of the
	 * data type being searched, 2- an actual identifier guaranteed to be unique in the value set.
	 * e.g.: for "cm:person", an identifier field may be "userName".
	 */
	private String identifier;

	/**
	 * The association by which the list is filtered server-side. May be non-<code>null</code>
	 * depending on the cardinalities in the model. If <code>null</code>, no filtering will occur.
	 * See FormsGeneratorManager.isAssociationFilterable for an example.
	 */
	private String filterAssoc;

	/**
	 * Whether the filering association is a composition, in which case, the value is "1".
	 * Otherwise, "0".
	 */
	private String compositionStatus;

	/**
	 * Whether the action is for a selection widget in "search" mode. Which means the list item
	 * should be empty at load time (a time when there's no query string). If so, contains "1".
	 */
	private String searchMode;

	/** The user-defined query to replace the query that the webscript creates automatically. */
	private String luceneQuery;

	/** the URI to use instead of the default Xform Webscript uri */
	private String dataSourceURI;
	
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
			String maxLength, String identifier, String filterAssoc, String compositionStatus,
			String searchMode, String luceneQuery,String dataSourceURI) {
		this.dataType = dataType;
		this.query = query;
		this.maxResults = maxResults;
		this.format = format;
		this.maxLength = maxLength;
		this.identifier = identifier;
		this.filterAssoc = filterAssoc;
		this.compositionStatus = compositionStatus;
		this.searchMode = searchMode;
		this.luceneQuery = luceneQuery;
		this.dataSourceURI = dataSourceURI;
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

	/**
	 * @return the searchMode
	 */
	public String getSearchMode() {
		return searchMode;
	}

	/**
	 * @return the luceneQuery
	 */
	public String getLuceneQuery() {
		return luceneQuery;
	}

	public void setDataSourceURI(String dataSourceURI) {
		this.dataSourceURI = dataSourceURI;
	}

	public String getDataSourceURI() {
		return dataSourceURI;
	}
}
