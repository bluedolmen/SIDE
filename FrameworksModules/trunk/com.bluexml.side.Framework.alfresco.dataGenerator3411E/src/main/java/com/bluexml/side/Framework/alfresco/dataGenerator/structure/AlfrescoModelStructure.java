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
 * This class is a Java bean representing the loaded model in Alfresco 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.structure;

import java.util.Collection;
import java.util.Map;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoModelStructure implements IStructure {
	
	private Collection<ClassDefinition> types;
	private Map<ClassDefinition,Collection<PropertyDefinition>> properties;
	private Collection<AssociationDefinition> associations;
	private Map<ClassDefinition,Collection<AspectDefinition>> aspects;
	//This sub-structure repesents all the non SIDE model definitions,
	//i.e. the native Alfresco properties that we have to take into account
	//to create content instances
	private NativeAlfrescoModelStructure nativeStructure;
	/**
	 * @return the types
	 */
	public Collection<ClassDefinition> getTypes() {
		return types;
	}
	/**
	 * @param types the types to set
	 */
	public void setTypes(Collection<ClassDefinition> types) {
		this.types = types;
	}
	/**
	 * @return the properties
	 */
	public Map<ClassDefinition, Collection<PropertyDefinition>> getProperties() {
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Map<ClassDefinition, Collection<PropertyDefinition>> properties) {
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
	 * @return the aspects
	 */
	public Map<ClassDefinition, Collection<AspectDefinition>> getAspects() {
		return aspects;
	}
	/**
	 * @param aspects the aspects to set
	 */
	public void setAspects(Map<ClassDefinition, Collection<AspectDefinition>> aspects) {
		this.aspects = aspects;
	}
	/**
	 * @return the nativeStructure
	 */
	public NativeAlfrescoModelStructure getNativeStructure() {
		return nativeStructure;
	}
	/**
	 * @param nativeStructure the nativeStructure to set
	 */
	public void setNativeStructure(NativeAlfrescoModelStructure nativeStructure) {
		this.nativeStructure = nativeStructure;
	}
	
	
}
