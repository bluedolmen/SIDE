/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;

import com.bluexml.side.Framework.alfresco.dataGenerator.data.IData;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.AlfrescoNode;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.AlfrescoModelStructure;
import com.bluexml.side.Framework.alfresco.dataGenerator.structure.IStructure;

/**
 * @author davidchevrier
 *
 */
public class Instance implements IInstance {
	
	IStructure structure;
	IRandomGenerator alfrescoModelRandomGenerator;
	IData alfrescoModelDatasInstance;
	IInstance nativeInstance;
	
	/**
	 * @return the structure
	 */
	public IStructure getStructure() {
		return structure;
	}


	/**
	 * @param structure the structure to set
	 */
	public void setStructure(IStructure structure) {
		this.structure = structure;
	}


	/**
	 * @return the alfrescoModelRandomGenerator
	 */
	public IRandomGenerator getAlfrescoModelRandomGenerator() {
		return alfrescoModelRandomGenerator;
	}


	/**
	 * @param alfrescoModelRandomGenerator the alfrescoModelRandomGenerator to set
	 */
	public void setAlfrescoModelRandomGenerator(
			IRandomGenerator alfrescoModelRandomGenerator) {
		this.alfrescoModelRandomGenerator = alfrescoModelRandomGenerator;
	}


	/**
	 * @return the alfrescoModelDatasInstance
	 */
	public IData getAlfrescoModelDatasInstance() {
		return alfrescoModelDatasInstance;
	}


	/**
	 * @param alfrescoModelDatasInstance the alfrescoModelDatasInstance to set
	 */
	public void setAlfrescoModelDatasInstance(IData alfrescoModelDatasInstance) {
		this.alfrescoModelDatasInstance = alfrescoModelDatasInstance;
	}


	/**
	 * @return the nativeInstance
	 */
	public IInstance getNativeInstance() {
		return nativeInstance;
	}


	/**
	 * @param nativeInstance the nativeInstance to set
	 */
	public void setNativeInstance(IInstance nativeInstance) {
		this.nativeInstance = nativeInstance;
	}


	public INode instanciation(TypeDefinition type) throws Exception{
		INode nodeInstance = new AlfrescoNode();
		Collection<PropertyDefinition> properties = ((AlfrescoModelStructure) structure).getProperties().get(type);
		Map<PropertyDefinition,Object> sideDataProperties = ((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDatasProperties(type,properties);
		Collection<AspectDefinition> aspects = ((AlfrescoModelStructure) structure).getAspects().get(type);
		Map<AspectDefinition,Map<PropertyDefinition,Object>> sideAspectDataProperties = ((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDataAspect(type,aspects);
		while (checkUnicity(type, sideDataProperties, sideAspectDataProperties)){
			sideDataProperties = ((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDatasProperties(type,properties);
			sideAspectDataProperties = ((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDataAspect(type,aspects);
		}
		((AlfrescoNode) nodeInstance).setDatasProperties(sideDataProperties);
		((AlfrescoNode) nodeInstance).setDataAspects(sideAspectDataProperties);
		((AlfrescoNode) nodeInstance).setNativeNode(((NativeInstance) nativeInstance).instanciation(type));
		((AlfrescoNode) nodeInstance).setTypeDefinition(type);
		
		return nodeInstance;
	}

	
	private boolean checkUnicity(TypeDefinition type, Map<PropertyDefinition, Object> sideDataProperties, Map<AspectDefinition, Map<PropertyDefinition, Object>> sideAspectDataProperties) {
		Collection<Map<PropertyDefinition, Object>> aspectDataProperties = sideAspectDataProperties.values();
		Collection<Serial> serialProperties = new ArrayList<Serial>();
		for (Map<PropertyDefinition, Object> dataProperties : aspectDataProperties){
			Set<PropertyDefinition> properties = dataProperties.keySet();
			for(PropertyDefinition property : properties){
				serialProperties.add(new Serial(type.getName().toString(),property.getName().toString(),dataProperties.get(property).toString()));
			}
		}
		Set<PropertyDefinition> properties = sideDataProperties.keySet();
		for (PropertyDefinition property : properties){
			serialProperties.add(new Serial(type.getName().toString(),property.getName().toString(),sideDataProperties.get(property).toString()));
		}
		
		Collection<Serial> serializedData = ((AlfrescoModelRandomDataGenerator)alfrescoModelRandomGenerator).getSerializedData();
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
		
		return same;
	}


	public IArc instanciation(INode source, INode target, AssociationDefinition associationDefinition){
		IArc arcInstance = new AlfrescoArc();
		((AlfrescoArc) arcInstance).setSource((AlfrescoNode) source);
		((AlfrescoArc) arcInstance).setTarget((AlfrescoNode) target);
		((AlfrescoArc) arcInstance).setTypeAssociation(associationDefinition);
		return arcInstance;
	}
	
	
}
