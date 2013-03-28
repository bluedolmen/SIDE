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

import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.SqlCommon.TableType;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.CreateTableStatement;

public class CustomActionManager implements CustomAction {

	Logger logger = Logger.getLogger(getClass());
	
	public void doInContentReplication(NodeRef nodeRef) {
		if (logger.isDebugEnabled())
			logger.debug("Calling custom action content replication on node " + nodeRef);
		for (CustomAction createCustomAction : createCustomActions) {
			createCustomAction.doInContentReplication(nodeRef);
		}		
	}

	public void doInCreateAssociation(QName associationName, CreateTableStatement.Builder currentBuilder) {
		if (logger.isDebugEnabled())
			logger.debug("Calling custom action create association on association " + associationName);
		for (CustomAction createCustomAction : createCustomActions) {
			createCustomAction.doInCreateAssociation(associationName, currentBuilder);
		}				
	}

	public void doInCreateType(QName nodeName, CreateTableStatement.Builder currentBuilder) {
		if (logger.isDebugEnabled())
			logger.debug("Calling custom action create type on type " + nodeName);
		for (CustomAction createCustomAction : createCustomActions) {
			createCustomAction.doInCreateType(nodeName, currentBuilder);
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
			if (logger.isDebugEnabled())
				logger.debug("Registering custom action " + createCustomAction);
		}
		
		createCustomActions = createCustomActions_;
	}


	
}
