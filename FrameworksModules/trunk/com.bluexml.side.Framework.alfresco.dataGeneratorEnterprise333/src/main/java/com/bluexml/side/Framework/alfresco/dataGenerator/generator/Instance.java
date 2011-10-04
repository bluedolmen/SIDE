/**
 * This class allows instanciation of model's types
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.util.Collection;
import java.util.Map;

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
	NativeInstance nativeInstance;
	InstanceServices services;
	
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
	public NativeInstance getNativeInstance() {
		return nativeInstance;
	}


	/**
	 * @param nativeInstance the nativeInstance to set
	 */
	public void setNativeInstance(NativeInstance nativeInstance) {
		this.nativeInstance = nativeInstance;
	}
	
	public InstanceServices getServices() {
		return services;
	}
	
	public void setServices(InstanceServices services) {
		this.services = services;
	}

	/**
	 * 
	 * @param type
	 * @return the instance node of the given type
	 * @throws Exception
	 */
	public INode instanciation(Object type) throws Exception{
		INode nodeInstance = new AlfrescoNode();
		Collection<PropertyDefinition> properties = ((AlfrescoModelStructure) structure).getProperties().get(type);
		Map<PropertyDefinition,Object> sideDataProperties = ((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDatasProperties((TypeDefinition)type,properties);
		Collection<AspectDefinition> aspects = ((AlfrescoModelStructure) structure).getAspects().get(type);
		Map<AspectDefinition,Map<PropertyDefinition,Object>> sideAspectDataProperties = ((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDataAspect((TypeDefinition)type,aspects);
		// As aspects's properties and types's properties are separated in the model's structure,
		// if unicity module is used we have to check this unicity on both 
		while (services.checkUnicity((TypeDefinition)type, sideDataProperties, sideAspectDataProperties)){
			sideDataProperties = ((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDatasProperties((TypeDefinition)type,properties);
			sideAspectDataProperties = ((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDataAspect((TypeDefinition)type,aspects);
		}
		((AlfrescoNode) nodeInstance).setDatasProperties(sideDataProperties);
		((AlfrescoNode) nodeInstance).setDataAspects(sideAspectDataProperties);
		((AlfrescoNode) nodeInstance).setNativeNode(((NativeInstance) nativeInstance).instanciation((TypeDefinition)type));
		((AlfrescoNode) nodeInstance).setTypeDefinition((TypeDefinition)type);
		
		return nodeInstance;
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @param associationDefinition
	 * @return arc instance of the association 
	 */
	public IArc instanciation(INode source, INode target, Object associationDefinition){
		IArc arcInstance = new AlfrescoArc();
		((AlfrescoArc) arcInstance).setSource((AlfrescoNode) source);
		((AlfrescoArc) arcInstance).setTarget((AlfrescoNode) target);
		((AlfrescoArc) arcInstance).setTypeAssociation((AssociationDefinition)associationDefinition);
		return arcInstance;
	}
	
	
}
