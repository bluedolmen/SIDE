package com.bluexml.side.Integration.alfresco.sql.synchronisation.schemaManagement;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Integration.alfresco.sql.synchronisation.dialects.SynchronisationDialect;
import com.bluexml.side.Integration.alfresco.sql.synchronisation.nodeService.NodeService;

public abstract class AbstractCustomAction implements CustomAction {

	public void doInContentReplication(NodeRef nodeRef) {
		throw new UnsupportedOperationException();

	}

	public void doInCreateAssociation(QName associationName,
			CreateStatement currentCreateStatement) {
		throw new UnsupportedOperationException();

	}

	public void doInCreateType(QName nodeName,
			CreateStatement currenCreateStatement) {
		throw new UnsupportedOperationException();

	}

	/*
	 * Spring IoC/DI Material
	 */
	
	protected SynchronisationDialect synchronisationDialect;
	protected NodeService synchronisationNodeService;
	
	public void setSynchronisationDialect (SynchronisationDialect synchronisationDialect_) {
		synchronisationDialect = synchronisationDialect_;
	}

	public void setSynchronisationNodeService(NodeService synchronisationNodeService_) {
		synchronisationNodeService = synchronisationNodeService_;
	}
}
