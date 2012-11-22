package com.bluexml.side.Integration.alfresco.sql.synchronization.pathManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.SqlCommon.TableType;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.CreateTableStatement;
import com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement.AbstractCustomAction;
import com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement.TableStatus;

public class PathCustomAction extends AbstractCustomAction {

	public void doInContentReplication(NodeRef nodeRef) {
		/*
		 * Obsolete since 1.0.9 where sql NodeService also defines policies
		 */
		//Path path = nodeService.getPath(nodeRef);	
		//pathService.updatePath(nodeRef);
	}

	public void doInCreateAssociation(QName associationName, CreateTableStatement.Builder currentBuilder) {
		// DO nothing more
	}

	public void doInCreateType(QName nodeName, CreateTableStatement.Builder currentBuilder) {
		// add a new column named path
		Map<String, List<String>> newColumns = new HashMap<String, List<String>>();
		
		List<String> definition = new ArrayList<String>();
		String sqlType = synchronizationDialect.getSQLMapping(DataTypeDefinition.PATH);
		definition.add(sqlType);
		
		newColumns.put(PathManagementCommon.PATH_COLUMN_NAME, definition);
		
		currentBuilder.columns(newColumns);
	}

	public TableStatus doInSchemaChecking(Map<String, Integer> columnDefinition, TableStatus tableStatus, TableType tableType) {
		if (TableType.TABLE_CLASS.equals(tableType) ) {
			if (!columnDefinition.containsKey(PathManagementCommon.PATH_COLUMN_NAME)) {
				// if the column cannot be found, then the table exists but the path column does not exist => unmatched
				// This checking is only performed on tables that are relative to classes
				tableStatus = TableStatus.EXISTS_UNMATCHED;
			}
		}
		return tableStatus;
	}

}
