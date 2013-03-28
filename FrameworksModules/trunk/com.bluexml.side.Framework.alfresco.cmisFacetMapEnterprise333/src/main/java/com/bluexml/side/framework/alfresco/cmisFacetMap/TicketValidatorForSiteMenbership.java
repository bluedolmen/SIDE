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
package com.bluexml.side.framework.alfresco.cmisFacetMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.alfresco.repo.security.authentication.AuthenticationException;
import org.alfresco.repo.security.authentication.TicketComponent;
import org.alfresco.service.ServiceRegistry;
import org.apache.log4j.Logger;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class TicketValidatorForSiteMenbership extends DeclarativeWebScript {
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

	private Logger logger = Logger.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.alfresco.web.scripts.DeclarativeWebScript#executeImpl(org.alfresco
	 * .web.scripts.WebScriptRequest, org.alfresco.web.scripts.Status,
	 * org.alfresco.web.scripts.Cache)
	 */
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		logger.trace("TicketValidatorForSiteMenbership.executeImpl()");

		boolean ticketValide = false;
		String userName = req.getParameter("userName");
		String shortName = req.getParameter("siteName");
		String userTicket = req.getParameter("userTicket");
		String userpwd = req.getParameter("userpwd");
		String ticket = "";
		if (userpwd != null && !userpwd.equals("")) {
			// do not use ticket but check authentication using user / pwd
			serviceRegistry.getAuthenticationService().authenticate(userName, userpwd.toCharArray());
			ticket = serviceRegistry.getAuthenticationService().getCurrentTicket();
			ticketValide = true;
		} else {
			if (userTicket != null) {
				try {
					String name = ticketComponent.validateTicket(userTicket);

					if (userName.equals(name)) {
						ticket = userTicket;
						// ok
						ticketValide = true;
					}
				} catch (AuthenticationException e) {
					// ticket invalide
				}
			}
			// throw new
			// RuntimeException("Enterprise Version not Yet Supported !!");
		}
		Set<String> authorities = serviceRegistry.getAuthorityService().getAuthoritiesForUser(userName);
		// let js controller to do check on membership

		Map<String, Object> objectJsModel = new HashMap<String, Object>();
		objectJsModel.put("authorities", authorities);
		objectJsModel.put("ticket", ticket);
		objectJsModel.put("ticketValide", ticketValide);
		objectJsModel.put("shortName", shortName);

		return objectJsModel;
	}

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

}
