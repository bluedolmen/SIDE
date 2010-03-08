/**
 * 
 */
package com.bluexml.xforms.controller.alfresco;

/**
 * @author Amenel
 * 
 */
public class RedirectionBean {
	private String targetUrl;
	private boolean autoAdvance;
	private boolean addParams;

	public RedirectionBean(String targetUrl, boolean autoAdvance, boolean addParams) {
		super();
		this.targetUrl = targetUrl;
		this.autoAdvance = autoAdvance;
		this.addParams = addParams;
	}

	/**
	 * @return the targetUrl
	 */
	public String getTargetUrl() {
		return targetUrl;
	}

	/**
	 * @return the autoAdvance feature
	 */
	public boolean isAutoAdvance() {
		return autoAdvance;
	}

	/**
	 * @return the addParams
	 */
	public boolean isAddParams() {
		return addParams;
	}

}
