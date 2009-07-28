package com.bluexml.alfresco.modules.sql.synchronisation.pathManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import com.bluexml.alfresco.modules.sql.synchronisation.common.SqlCommon.TableType;
import com.bluexml.alfresco.modules.sql.synchronisation.schemaManagement.AbstractCustomAction;
import com.bluexml.alfresco.modules.sql.synchronisation.schemaManagement.CreateStatement;
import com.bluexml.alfresco.modules.sql.synchronisation.schemaManagement.TableStatus;

public class PathCustomAction extends AbstractCustomAction {

	public void doInContentReplication(NodeRef nodeRef) {
		//Path path = nodeService.getPath(nodeRef);
				
		pathService.updatePath(nodeRef);
	}

	public void doInCreateAssociation(QName associationName, CreateStatement currentCreateStatement) {
		// DO nothing more
	}

	public void doInCreateType(QName nodeName, CreateStatement currenCreateStatement) {
		// add a new column named path
		Map<String, List<String>> newColumns = new HashMap<String, List<String>>();
		
		List<String> definition = new ArrayList<String>();
		String sqlType = synchronisationDialect.getSQLMapping(DataTypeDefinition.PATH);
		definition.add(sqlType);
		
		newColumns.put(PathManagementCommon.PATH_COLUMN_NAME, definition);
		
		currenCreateStatement.addColumns(newColumns);
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

	/*
	 * Spring IoC/DI material
	 */

	private PathService pathService;
	
	public void setPathService(PathService pathService_) {
		pathService = pathService_;
	}

}
