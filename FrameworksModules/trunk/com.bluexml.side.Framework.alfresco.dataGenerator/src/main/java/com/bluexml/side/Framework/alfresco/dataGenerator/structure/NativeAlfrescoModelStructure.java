/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.structure;

import java.util.ArrayList;
import java.util.Collection;

import org.alfresco.model.ContentModel;
import org.alfresco.service.namespace.QNamePattern;

/**
 * @author davidchevrier
 *
 */
public class NativeAlfrescoModelStructure implements IStructure {
	
	private Collection<QNamePattern> nativeMandatoryProperties;
	private Collection<QNamePattern> nativeAspects;
	
	/**
	 * @return the nativeMandatoryProperties
	 */
	public Collection<QNamePattern> getNativeMandatoryProperties() {
		return nativeMandatoryProperties;
	}

	/**
	 * @param nativeMandatoryProperties the nativeMandatoryProperties to set
	 */
	public void setNativeMandatoryProperties(
			Collection<QNamePattern> nativeMandatoryProperties) {
		this.nativeMandatoryProperties = nativeMandatoryProperties;
	}

	/**
	 * @return the nativeAspects
	 */
	public Collection<QNamePattern> getNativeAspects() {
		return nativeAspects;
	}

	/**
	 * @param nativeAspects the nativeAspects to set
	 */
	public void setNativeAspects(Collection<QNamePattern> nativeAspects) {
		this.nativeAspects = nativeAspects;
	}

	public void init(){
		nativeMandatoryProperties = fillNativeMandatoryProperties();
		nativeAspects = fillNativeAspects();
	}

	private Collection<QNamePattern> fillNativeAspects() {
		Collection<QNamePattern> nativeAspects = new ArrayList<QNamePattern>();
		nativeAspects.add(ContentModel.ASPECT_TEMPORARY);
		return nativeAspects;
	}

	private Collection<QNamePattern> fillNativeMandatoryProperties() {
		Collection<QNamePattern> nativeProperties = new ArrayList<QNamePattern>();
		nativeProperties.add(ContentModel.PROP_CONTENT);
		nativeProperties.add(ContentModel.PROP_NAME);
		nativeProperties.add(ContentModel.PROP_TITLE);
		return nativeProperties;
	}
	
	

}
