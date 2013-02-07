package com.bluexml.side.Integration.alfresco.model;
 
import org.alfresco.service.namespace.QName;

public interface ContentModel {

	public static final String BXCONTENT_NAMESPACE_PREFIX = "http://www.bluexml.com/model/content/";
	
	public static final String BXCONTENT_NAMESPACE_URI = BXCONTENT_NAMESPACE_PREFIX + "1.0";
	
	public static final String BXCONTENT_LOCAL_NAME = "content";
	
	public static final QName BXCONTENT_QNAME = QName.createQName(BXCONTENT_NAMESPACE_URI, BXCONTENT_LOCAL_NAME);
}
