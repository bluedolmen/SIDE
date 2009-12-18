package com.bluexml.side.framework.alfresco.cmisFacetMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.alfresco.repo.security.authentication.AuthenticationException;
import org.alfresco.repo.security.authentication.TicketComponent;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.web.scripts.*;
import org.apache.log4j.Logger;

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
		logger.info("Super webscript loaded");
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
