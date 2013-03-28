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
