package com.bluexml.side.Integration.alfresco.sql.synchronisation.nodeService;

import java.util.Collection;

import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public interface NodeService {

	public void create(NodeRef nodeRef) ;
	
	public void delete(NodeRef nodeRef) ;
	
	public void updateProperties(NodeRef nodeRef, Collection<QName> changes); //, Map<QName, Serializable> values) ;
	
	public void addAssociation(AssociationRef associationRef) ;
	
	public void removeAssociation(AssociationRef associationRef) ;
	
	public void addChildAssociation(ChildAssociationRef associationRef) ;
	
	public void removeChildAssociation(ChildAssociationRef associationRef) ;

}
