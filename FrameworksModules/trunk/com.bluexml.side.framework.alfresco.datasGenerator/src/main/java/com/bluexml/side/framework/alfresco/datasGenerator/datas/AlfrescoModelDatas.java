/**
 * 
 */
package com.bluexml.side.framework.alfresco.datasGenerator.datas;

import java.util.Collection;
import java.util.Map;

import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;

import com.bluexml.side.framework.alfresco.datasGenerator.graph.IArc;
import com.bluexml.side.framework.alfresco.datasGenerator.graph.INode;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoModelDatas implements IDatas {

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
