package com.bluexml.side.framework.alfresco.webscriptExtension;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.security.authentication.TicketComponent;
import org.alfresco.service.ServiceRegistry;
import org.apache.log4j.Logger;

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

	private Logger logger = Logger.getLogger(getClass());

	public String getCurrentTicket() {
		return serviceRegistry.getAuthenticationService().getCurrentTicket();
	}

	// public String getCurrentUser() {
	//		
	// }
}
