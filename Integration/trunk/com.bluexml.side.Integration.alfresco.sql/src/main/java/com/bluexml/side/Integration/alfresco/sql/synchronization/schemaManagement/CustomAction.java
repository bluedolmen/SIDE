package com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement;

import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.SqlCommon.TableType;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.CreateTableStatement;

public interface CustomAction {

	
	/*
	 * Action that is triggered when the structure of a new type is created in the schema
	 */
	public void doInCreateType(QName nodeName, CreateTableStatement.Builder currentBuilder);
	
	/*
	 * Action that is triggered when the structure of a new association is created in the schema
	 */
	public void doInCreateAssociation(QName associationName, CreateTableStatement.Builder currentBuilder);
	
	/*
	 * Action that is triggered when the replication of a node occurs
	 * @param nodeRef: the current node being processed
	 */
	public void doInContentReplication(NodeRef nodeRef);
	
	/*
	 * Action that is triggered when the checking of the schema occurs
	 */
	public TableStatus doInSchemaChecking(Map<String, Integer> columnDefinition, TableStatus tableStatus, TableType tableType);
}
