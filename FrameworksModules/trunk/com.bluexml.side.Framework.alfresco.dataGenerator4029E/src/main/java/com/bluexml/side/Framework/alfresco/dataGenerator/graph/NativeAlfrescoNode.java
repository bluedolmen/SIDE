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
