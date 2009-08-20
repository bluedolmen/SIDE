package com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService;

import java.util.Collection;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public interface NodeService {

	public void create(NodeRef nodeRef) ;
	
	public void delete(NodeRef nodeRef) ;
	
	public void updateProperties(NodeRef nodeRef, Collection<QName> changes); 
	
	public void createAssociation(NodeRef sourceRef, NodeRef targetRef, QName typeQName); 
	
	public void deleteAssociation(NodeRef sourceRef, NodeRef targetRef, QName typeQName);
	

}
