package com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.CreateTableStatement;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.SynchronizationDialect;
import com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService.NodeService;

public abstract class AbstractCustomAction implements CustomAction {

	public void doInContentReplication(NodeRef nodeRef) {
		throw new UnsupportedOperationException();

	}

	public void doInCreateAssociation(QName associationName,
			CreateTableStatement currentCreateStatement) {
		throw new UnsupportedOperationException();

	}

	public void doInCreateType(QName nodeName,
			CreateTableStatement currenCreateStatement) {
		throw new UnsupportedOperationException();

	}

	/*
	 * Spring IoC/DI Material
	 */
	
	protected SynchronizationDialect synchronizationDialect;
	protected NodeService synchronizationNodeService;
	
	public void setSynchronizationDialect (SynchronizationDialect synchronizationDialect_) {
		synchronizationDialect = synchronizationDialect_;
	}

	public void setSynchronizationNodeService(NodeService synchronizationNodeService_) {
		synchronizationNodeService = synchronizationNodeService_;
	}
}
