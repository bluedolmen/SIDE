package com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService;

import java.util.Collection;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public interface NodeService {

	/*
	 * Create a node in the database given the properties of the parameter NodeRef.
	 * The NodeRef has to be valid, and the node has to exist in the Alfresco repository.
	 */
	public void create(NodeRef nodeRef) ;

	/*
	 * Create a node in the database. This method is necessary in particular cases where some associations
	 * are already defined on a node on creation; notably when an archived node is restored, all the previous
	 * removed associations have to be restored. This method is less efficient than the previous one and may
	 * lead to duplicate associations if not used properly.
	 * The NodeRef has to be valid, and the node has to exist in the Alfresco repository.
	 */
	public void createWithAssociations(NodeRef nodeRef);
	
	/*
	 * Remove the node in the database. Associations related to this node are also removed (due to database integrity constraint) 
	 * The NodeRef has to be valid, and the node must still exist in the Alfresco repository. 
	 */
	public void delete(NodeRef nodeRef) ;
	
	/*
	 * Update the properties of a node given the names of the properties that change.
	 */
	public void updateProperties(NodeRef nodeRef, Collection<QName> changes); 
	
	/*
	 * Create an association between a sourceRef and a targetRef NodeRefs given the type name (typeQName)
	 * of the association.
	 */
	public void createAssociation(NodeRef sourceRef, NodeRef targetRef, QName typeQName); 
	
	/*
	 * Remove an association between a sourceRef and a targetRef NodeRefs given the type name (typeQName)
	 * of the association.
	 */
	public void deleteAssociation(NodeRef sourceRef, NodeRef targetRef, QName typeQName);
	
}
