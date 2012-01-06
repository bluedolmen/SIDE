package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public interface Filterer {

	public boolean acceptQName(QName qname);
	
	public boolean acceptModelQName(QName qname);
	
	public boolean acceptTypeQName(QName qname);
	
	public boolean acceptAssociationQName(QName qname);
	
	public boolean acceptPropertyQName(QName qname);
	
	public boolean accept(NodeRef nodeRef);
	
	public boolean accept(AssociationRef associationRef);
	
	public boolean accept(ChildAssociationRef childAssociationRef);
	
}
