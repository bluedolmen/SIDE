package com.bluexml.side.Integration.alfresco.sql.synchronisation.schemaManagement;

import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronisation.common.SqlCommon.TableType;

public class CustomActionManager implements CustomAction {

	Logger logger = Logger.getLogger(getClass());
	
	public void doInContentReplication(NodeRef nodeRef) {
		logger.debug("Calling custom action content replication on node " + nodeRef);
		for (CustomAction createCustomAction : createCustomActions) {
			createCustomAction.doInContentReplication(nodeRef);
		}		
	}

	public void doInCreateAssociation(QName associationName, CreateStatement currentCreateStatement) {
		logger.debug("Calling custom action create association on association " + associationName);
		for (CustomAction createCustomAction : createCustomActions) {
			createCustomAction.doInCreateAssociation(associationName, currentCreateStatement);
		}				
	}

	public void doInCreateType(QName nodeName, CreateStatement currentCreateStatement) {
		logger.debug("Calling custom action create type on type " + nodeName);
		for (CustomAction createCustomAction : createCustomActions) {
			createCustomAction.doInCreateType(nodeName, currentCreateStatement);
		}				
		
	}

	public TableStatus doInSchemaChecking(Map<String, Integer> columnDefinition, TableStatus tableStatus, TableType tableType) {
		logger.debug("Calling custom action schema checking");
		for (CustomAction createCustomAction : createCustomActions) {
			tableStatus = createCustomAction.doInSchemaChecking(columnDefinition, tableStatus, tableType);
		}
		return tableStatus;
	}

	
	/*
	 * Spring IoC/DI material
	 */
	
	private List<CustomAction> createCustomActions;
	
	public void setCustomActions(List<CustomAction> createCustomActions_) {
		for (CustomAction createCustomAction : createCustomActions_) {
			logger.debug("Registering custom action " + createCustomAction);
		}
		
		createCustomActions = createCustomActions_;
	}


	
}
