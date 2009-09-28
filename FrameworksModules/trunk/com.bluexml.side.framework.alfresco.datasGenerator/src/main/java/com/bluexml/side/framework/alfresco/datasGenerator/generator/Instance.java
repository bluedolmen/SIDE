/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;

import com.bluexml.side.framework.alfresco.datasGenerator.datas.AlfrescoModelDatas;
import com.bluexml.side.framework.alfresco.datasGenerator.datas.IDatas;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.AlfrescoArc;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.AlfrescoNode;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.IArc;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.INode;
import com.bluexml.side.framework.alfresco.datasGenerator.structure.AlfrescoModelStructure;
import com.bluexml.side.framework.alfresco.datasGenerator.structure.IStructure;

/**
 * @author davidchevrier
 *
 */
public class Instance implements IInstance {
	
	IStructure structure;
	IRandomGenerator alfrescoModelRandomGenerator;
	IDatas alfrescoModelDatasInstance;
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
	public IDatas getAlfrescoModelDatasInstance() {
		return alfrescoModelDatasInstance;
	}


	/**
	 * @param alfrescoModelDatasInstance the alfrescoModelDatasInstance to set
	 */
	public void setAlfrescoModelDatasInstance(IDatas alfrescoModelDatasInstance) {
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


	public INode instanciation(TypeDefinition type){
		INode nodeInstance = new AlfrescoNode();
		Collection<PropertyDefinition> properties = ((AlfrescoModelStructure) structure).getProperties().get(type);
		((AlfrescoNode) nodeInstance).setDatasProperties(((AlfrescoModelRandomDatasGenerator) alfrescoModelRandomGenerator).generateDatasProperties(properties));
		((AlfrescoNode) nodeInstance).setNativeNode(((NativeInstance) nativeInstance).instanciation(type));
		((AlfrescoNode) nodeInstance).setTypeDefinition(type);
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
