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
 * This class allows access to native properties of instances defined in Alfresco
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.graph;

import java.util.Map;

import org.alfresco.service.namespace.QNamePattern;

/**
 * @author davidchevrier
 *
 */
public class NativeAlfrescoNode {
	
	private Map<QNamePattern,Object> nativeDatasProperties;
	private Map<QNamePattern,Object> nativeDatasAspects;

	/**
	 * @return the nativeDatasProperties
	 */
	public Map<QNamePattern, Object> getNativeDatasProperties() {
		return nativeDatasProperties;
	}

	/**
	 * @param nativeDatasProperties the nativeDatasProperties to set
	 */
	public void setNativeDatasProperties(
			Map<QNamePattern, Object> nativeDatasProperties) {
		this.nativeDatasProperties = nativeDatasProperties;
	}

	/**
	 * @return the nativeDatasAspects
	 */
	public Map<QNamePattern, Object> getNativeDatasAspects() {
		return nativeDatasAspects;
	}

	/**
	 * @param nativeDatasAspects the nativeDatasAspects to set
	 */
	public void setNativeDatasAspects(Map<QNamePattern, Object> nativeDatasAspects) {
		this.nativeDatasAspects = nativeDatasAspects;
	}
	
}
