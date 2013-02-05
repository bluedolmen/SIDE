/**
 * This class allows useful methods to Instance class
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;

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
	public boolean checkUnicity(ClassDefinition type, Map<PropertyDefinition, Object> sideDataProperties, Map<AspectDefinition, Map<PropertyDefinition, Object>> sideAspectDataProperties) throws Exception {
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