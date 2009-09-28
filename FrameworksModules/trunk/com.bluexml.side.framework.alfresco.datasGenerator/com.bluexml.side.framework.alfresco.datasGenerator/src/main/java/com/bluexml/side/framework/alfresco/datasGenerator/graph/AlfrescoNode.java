/**
 * Node is equivalent to an instance of an Alfresco type
 */
package com.bluexml.side.framework.alfresco.datasGenerator.graph;

import java.util.Collection;
import java.util.Map;

import org.alfresco.repo.template.TemplateProperties;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoNode implements INode {
	
	private TypeDefinition typeDefinition;
	private Map<PropertyDefinition,Object> datasProperties;
	private INode nativeNode;
	/**
	 * @return the type
	 */
	public TypeDefinition getTypeDefinition() {
		return typeDefinition;
	}
	/**
	 * @param type the type to set
	 */
	public void setTypeDefinition(TypeDefinition type) {
		this.typeDefinition = type;
	}
	/**
	 * @return the datasProperties
	 */
	public Map<PropertyDefinition, Object> getDatasProperties() {
		return datasProperties;
	}
	/**
	 * @param datasProperties the datasProperties to set
	 */
	public void setDatasProperties(
			Map<PropertyDefinition, Object> datasProperties) {
		this.datasProperties = datasProperties;
	}
	/**
	 * @return the nativeNode
	 */
	public INode getNativeNode() {
		return nativeNode;
	}
	/**
	 * @param nativeNode the nativeNode to set
	 */
	public void setNativeNode(INode nativeNode) {
		this.nativeNode = nativeNode;
	}
	
}
