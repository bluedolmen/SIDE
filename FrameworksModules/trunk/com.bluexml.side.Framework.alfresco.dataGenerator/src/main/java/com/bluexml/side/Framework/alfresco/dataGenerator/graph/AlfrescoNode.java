/**
 * Node is equivalent to an instance of an Alfresco type
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.graph;

import java.util.Map;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoNode implements INode {
	
	private TypeDefinition typeDefinition;
	private Map<PropertyDefinition,Object> datasProperties;
	private Map<AspectDefinition,Map<PropertyDefinition,Object>> dataAspects;
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
	 * @return the dataAspects
	 */
	public Map<AspectDefinition, Map<PropertyDefinition, Object>> getDataAspects() {
		return dataAspects;
	}
	/**
	 * @param dataAspects the dataAspects to set
	 */
	public void setDataAspects(
			Map<AspectDefinition, Map<PropertyDefinition, Object>> dataAspects) {
		this.dataAspects = dataAspects;
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
