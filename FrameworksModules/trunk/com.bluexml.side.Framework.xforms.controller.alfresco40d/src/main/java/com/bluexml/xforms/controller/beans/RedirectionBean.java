/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * 
 */
package com.bluexml.xforms.controller.beans;

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
