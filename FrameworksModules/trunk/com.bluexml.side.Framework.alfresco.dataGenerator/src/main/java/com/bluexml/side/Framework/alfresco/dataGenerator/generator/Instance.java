/**
 * 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.generator;

import java.util.Collection;

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
		((AlfrescoNode) nodeInstance).setDatasProperties(((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDatasProperties(type,properties));
		((AlfrescoNode) nodeInstance).setNativeNode(((NativeInstance) nativeInstance).instanciation(type));
		((AlfrescoNode) nodeInstance).setTypeDefinition(type);
		Collection<AspectDefinition> aspects = ((AlfrescoModelStructure) structure).getAspects().get(type);
		((AlfrescoNode) nodeInstance).setDataAspects(((AlfrescoModelRandomDataGenerator) alfrescoModelRandomGenerator).generateDataAspect(type,aspects));
		return nodeInstance;
	}

	
	public IArc instanciation(INode source, INode target, AssociationDefinition associationDefinition){
		IArc arcInstance = new AlfrescoArc();
		((AlfrescoArc) arcInstance).setSource((AlfrescoNode) source);
		((AlfrescoArc) arcInstance).setTarget((AlfrescoNode) target);
		((AlfrescoArc) arcInstance).setTypeAssociation(associationDefinition);
		return arcInstance;
	}
	
	
}
