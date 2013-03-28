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
 * This class allows useful methods to Instance class
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;

/**
 * @author dchevrier
 *
 */
public class InstanceServices {
	
	IRandomGenerator alfrescoModelRandomGenerator;
	
	/**
	 * 
	 * @return the alfrescoModelRandomGenerator instance
	 */
	public IRandomGenerator getAlfrescoModelRandomGenerator() {
		return alfrescoModelRandomGenerator;
	}

	public void setAlfrescoModelRandomGenerator(
			IRandomGenerator alfrescoModelRandomGenerator) {
		this.alfrescoModelRandomGenerator = alfrescoModelRandomGenerator;
	}

	/**
	 * 
	 * @param type
	 * @param sideDataProperties
	 * @param sideAspectDataProperties
	 * @return true if unicity of aspects's properties and types's properties is checked
	 * @throws Exception
	 */
	public boolean checkUnicity(TypeDefinition type, Map<PropertyDefinition, Object> sideDataProperties, Map<AspectDefinition, Map<PropertyDefinition, Object>> sideAspectDataProperties) throws Exception {
		boolean result = false;
		Set<PropertyDefinition> SIDEProperties = sideDataProperties.keySet();
		Collection<PropertyDefinition> unicitySIDEProperties = ((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).getGeneratorServices().getUnicityProperties(type,SIDEProperties);
		
		Collection<PropertyDefinition> unicityAspectsProperties = new ArrayList<PropertyDefinition>();
		
		Collection<Map<PropertyDefinition, Object>> aspectDataProperties = sideAspectDataProperties.values();
		
		Collection<Serial> serialProperties = new ArrayList<Serial>();
		
		for (Map<PropertyDefinition, Object> dataProperties : aspectDataProperties){
			Set<PropertyDefinition> properties = dataProperties.keySet();
			unicityAspectsProperties.addAll(((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).getGeneratorServices().getUnicityProperties(type,properties));
			for(PropertyDefinition property : unicityAspectsProperties){
				serialProperties.add(new Serial(type.getName().toString(),property.getName().toString(),dataProperties.get(property).toString()));
			}
		}
		for (PropertyDefinition property : unicitySIDEProperties){
			serialProperties.add(new Serial(type.getName().toString(),property.getName().toString(),sideDataProperties.get(property).toString()));
		}
		
		Collection<Serial> serializedData = new ArrayList<Serial>();
		if (unicitySIDEProperties.size() > 0 && unicityAspectsProperties.size() > 0){
			serializedData = ((AlfrescoModelRandomDataGenerator)alfrescoModelRandomGenerator).getSerializedData();
			boolean same = false;
			if (serializedData.size() > 0 && serialProperties.size() > 0){
				same = true;
				for (Serial dataProperty : serialProperties){
					boolean contains = false;
					for (Serial serializedDataProperty : serializedData){
						if (dataProperty.getType().equals(serializedDataProperty.getType())
							&& dataProperty.getProperty().equals(serializedDataProperty.getProperty())
							&& dataProperty.getData().toString().equals(serializedDataProperty.getData().toString())){
							contains = true;
						}
					}
					same &= contains;
				}
			}
			result = same;
		}
		else{
			result = false;
		}
		
		if (result == false){
			Collection<Serial> temp = new ArrayList<Serial>();
			for (Serial dataProperty : serialProperties){
				boolean contains = false;
				for (Serial serializedDataProperty : serializedData){
					if (dataProperty.getType().equals(serializedDataProperty.getType())
						&& dataProperty.getProperty().equals(serializedDataProperty.getProperty()) 
						&& dataProperty.getData().toString().equals(serializedDataProperty.getData().toString())){
						contains = true;
					}
				}
				if (!contains){
					temp.add(dataProperty);
				}
			}
			if (temp.size() > 0){
				serializedData.addAll(temp);
				((AlfrescoModelRandomDataGenerator)alfrescoModelRandomGenerator).setSerializedData((ArrayList<Serial>)serializedData);
			}
		}
		
		return result;
	}

}
