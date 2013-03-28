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
