/**
 * This class represents the result of generation, i.e. types and associations instances (filled with data) 
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.data;

import java.util.Collection;

import com.bluexml.side.Framework.alfresco.dataGenerator.graph.IArc;
import com.bluexml.side.Framework.alfresco.dataGenerator.graph.INode;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoModelData implements IData {
	
	private Collection<INode> generatedTypesInstances;
	private Collection<IArc> generatedAssociationsInstances;
	
	/**
	 * @return the generatedTypesInstances
	 */
	public Collection<INode> getGeneratedTypesInstances() {
		return generatedTypesInstances;
	}
	/**
	 * @param generatedTypesInstances the generatedTypesInstances to set
	 */
	public void setGeneratedTypesInstances(Collection<INode> generatedTypesInstances) {
		this.generatedTypesInstances = generatedTypesInstances;
	}
	/**
	 * @return the generatedAssociations
	 */
	public Collection<IArc> getGeneratedAssociationsInstances() {
		return generatedAssociationsInstances;
	}
	/**
	 * @param generatedAssociations the generatedAssociations to set
	 */
	public void setGeneratedAssociationsInstances(Collection<IArc> generatedAssociations) {
		this.generatedAssociationsInstances = generatedAssociations;
	}
		
}
