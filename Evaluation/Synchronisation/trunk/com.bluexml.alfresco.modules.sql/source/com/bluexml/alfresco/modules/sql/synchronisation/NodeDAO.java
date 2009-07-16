package com.bluexml.alfresco.modules.sql.synchronisation;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public interface NodeDAO {

	public void create(NodeRef nodeRef);
	
	public void delete(NodeRef nodeRef);
	
	public void update(NodeRef nodeRef, Map<QName, Serializable> values);
	
	public void addAssociation(AssociationRef associationRef);
	
	public void removeAssociation(AssociationRef associationRef);
	
	public void addChildAssociation(ChildAssociationRef associationRef);
	
	public void removeChildAssociation(ChildAssociationRef associationRef);

}
