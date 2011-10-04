/**
 * Arc is equivalent to Association
 */
package com.bluexml.side.Framework.alfresco.dataGenerator.graph;

import org.alfresco.service.cmr.dictionary.AssociationDefinition;

/**
 * @author davidchevrier
 *
 */
public class AlfrescoArc implements IArc {
	
	private INode source;
	private INode target;
	private AssociationDefinition typeAssociation;
	/**
	 * @return the source
	 */
	public INode getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(INode source) {
		this.source = source;
	}
	/**
	 * @return the target
	 */
	public INode getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(INode target) {
		this.target = target;
	}
	/**
	 * @return the typeAssociation
	 */
	public AssociationDefinition getTypeAssociation() {
		return typeAssociation;
	}
	/**
	 * @param typeAssociation the typeAssociation to set
	 */
	public void setTypeAssociation(AssociationDefinition typeAssociation) {
		this.typeAssociation = typeAssociation;
	}
		
}
