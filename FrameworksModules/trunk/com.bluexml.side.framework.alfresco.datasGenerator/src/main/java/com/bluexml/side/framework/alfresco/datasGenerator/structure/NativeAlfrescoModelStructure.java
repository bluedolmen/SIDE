/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.structure;

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

	public void init(){
		nativeMandatoryProperties = fillNativeMandatoryProperties();
	}

	private Collection<QNamePattern> fillNativeMandatoryProperties() {
		Collection<QNamePattern> nativeProperties = new ArrayList<QNamePattern>();
		nativeProperties.add(ContentModel.PROP_CONTENT);
		nativeProperties.add(ContentModel.PROP_NAME);
		nativeProperties.add(ContentModel.PROP_CREATOR);
		nativeProperties.add(ContentModel.PROP_CREATED);
		nativeProperties.add(ContentModel.PROP_MODIFIER);
		nativeProperties.add(ContentModel.PROP_MODIFIED);
		//nativeProperties.add(ContentModel.PROP_NODE_UUID);
		//nativeProperties.add(ContentModel.PROP_NODE_DBID);
		return nativeProperties;
	}
	
	

}
