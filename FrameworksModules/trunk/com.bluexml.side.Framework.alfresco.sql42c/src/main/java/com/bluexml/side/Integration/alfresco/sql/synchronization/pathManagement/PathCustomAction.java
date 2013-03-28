/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
