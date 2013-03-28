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
package com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public class PropertyDoesNotExist extends NodeServiceFailureException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6247506898280470790L;
	private QName failingProperty;
	private NodeRef nodeRef;
		
	public PropertyDoesNotExist(QName propertyName, NodeRef nodeRef_) {
		failingProperty = propertyName;
		nodeRef = nodeRef_;
	}

	public String toString() {
		return "Cannot find property " + failingProperty + " in the node " + nodeRef;
	}
}
