/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.structure;

import java.util.Collection;
import java.util.Map;

import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoModelStructure implements IStructure {
	
	private Collection<TypeDefinition> types;
	private Map<TypeDefinition,Collection<PropertyDefinition>> properties;
	private Collection<AssociationDefinition> associations;
	private IStructure nativeStructure;
	/**
	 * @return the types
	 */
	public Collection<TypeDefinition> getTypes() {
		return types;
	}
	/**
	 * @param types the types to set
	 */
	public void setTypes(Collection<TypeDefinition> types) {
		this.types = types;
	}
	/**
	 * @return the properties
	 */
	public Map<TypeDefinition, Collection<PropertyDefinition>> getProperties() {
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Map<TypeDefinition, Collection<PropertyDefinition>> properties) {
		this.properties = properties;
	}
	/**
	 * @return the associations
	 */
	public Collection<AssociationDefinition> getAssociations() {
		return associations;
	}
	/**
	 * @param associations the associations to set
	 */
	public void setAssociations(Collection<AssociationDefinition> associations) {
		this.associations = associations;
	}
	/**
	 * @return the nativeStructure
	 */
	public IStructure getNativeStructure() {
		return nativeStructure;
	}
	/**
	 * @param nativeStructure the nativeStructure to set
	 */
	public void setNativeStructure(IStructure nativeStructure) {
		this.nativeStructure = nativeStructure;
	}
	
	
}
