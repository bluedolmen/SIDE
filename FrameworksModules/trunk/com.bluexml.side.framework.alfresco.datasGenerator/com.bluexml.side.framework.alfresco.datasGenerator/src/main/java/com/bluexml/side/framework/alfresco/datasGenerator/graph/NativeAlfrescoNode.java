/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.graph;

import java.util.Map;

import org.alfresco.service.namespace.QNamePattern;

/**
 * @author davidchevrier
 *
 */
public class NativeAlfrescoNode implements INode {
	
	private Map<QNamePattern,Object> nativeDatasProperties;

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
	
}
