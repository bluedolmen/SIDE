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
package com.bluexml.side.framework.alfresco.webscriptExtension;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.security.authentication.TicketComponent;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.namespace.QName;

public class TicketScriptExtension extends BaseScopableProcessorExtension {

	/**
	 * @return the serviceRegistry
	 */
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	/**
	 * @param serviceRegistry
	 *            the serviceRegistry to set
	 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	ServiceRegistry serviceRegistry;
	TicketComponent ticketComponent;

	/**
	 * @return the ticketComponent
	 */
	public TicketComponent getTicketComponent() {
		return ticketComponent;
	}

	/**
	 * @param ticketComponent
	 *            the ticketComponent to set
	 */
	public void setTicketComponent(TicketComponent ticketComponent) {
		this.ticketComponent = ticketComponent;
	}

	public String getCurrentTicket() {
		return serviceRegistry.getAuthenticationService().getCurrentTicket();
	}

	/**
	 * setType change the node type without check anythings this allow to avoid
	 * limitation of ScriptNode.specialiseType
	 * 
	 * @param node
	 * @param type
	 * @return new instance of the ScriptNode to be sure to have updated
	 *         ScriptNode because getType call nodeService.getType only one time
	 */
	public ScriptNode setType(ScriptNode node, String type) {
		serviceRegistry.getNodeService().setType(node.getNodeRef(), QName.createQName(type, serviceRegistry.getNamespaceService()));
		return new ScriptNode(node.getNodeRef(), serviceRegistry);
	}

}
