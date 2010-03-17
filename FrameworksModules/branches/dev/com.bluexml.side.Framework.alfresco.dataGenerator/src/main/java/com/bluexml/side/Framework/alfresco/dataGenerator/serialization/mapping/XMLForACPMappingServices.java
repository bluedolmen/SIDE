/**
 * This class contains useful methods for helper's actions
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.serialization.mapping;

import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.QNamePattern;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoNode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;

/**
 * @author dchevrier
 *
 */
public class XMLForACPMappingServices {
	
	private XMLForACPMappingHelper helper;

	public XMLForACPMappingHelper getHelper() {
		return helper;
	}

	public void setHelper(XMLForACPMappingHelper helper) {
		this.helper = helper;
	}
	
	/**
	 * 
	 * @param node
	 * @return the qualified name of a given generated node
	 */
	public String getQualifiedName(INode node) {
		return ((AlfrescoNode) node).getTypeDefinition().getName().toPrefixString();
	}

	/**
	 * 
	 * @param qualifiedName
	 * @return the local name of a qualified name
	 */
	public String getName(String qualifiedName) {
		String [] decomposedQualifiedName = qualifiedName.split(Constants.SEPARATOR);
		return decomposedQualifiedName[1];
	}
	
	/**
	 * 
	 * @param qualifiedName
	 * @return the prefix representation of a qualified name
	 */
	public String getPrefix(String qualifiedName) {
		String [] decomposedQualifiedName = qualifiedName.split(Constants.SEPARATOR);
		return decomposedQualifiedName[0];
	}
	
	/**
	 * 
	 * @param node
	 * @return the SIDE namespace uri of a given generated node
	 */
	public String getSIDEUri(INode node) {
		return ((AlfrescoNode)node).getTypeDefinition().getName().getNamespaceURI();
	}
	
	/**
	 * 
	 * @param namespacePrefix
	 * @param localName
	 * @return the tag in "prefix:localName" form
	 */
	public String createTag(String namespacePrefix, String localName){
		StringBuffer tag = new StringBuffer();
		tag.append(namespacePrefix);
		tag.append(Constants.SEPARATOR);
		tag.append(localName);
		return tag.toString();
	}
	
	/**
	 * 
	 * @param nativeDataProperties
	 * @return the "cm:name" property of a given generated node 
	 * 		   by its native properties 
	 */
	public String getNodeName(Map<QNamePattern, Object> nativeDataProperties) {
		Set<QNamePattern> properties = nativeDataProperties.keySet();
		for (QNamePattern property : properties){
			if (((QName)property).equals(ContentModel.PROP_NAME)){
				return nativeDataProperties.get(property).toString();
			}
		}
		return null;
	}
	
}
