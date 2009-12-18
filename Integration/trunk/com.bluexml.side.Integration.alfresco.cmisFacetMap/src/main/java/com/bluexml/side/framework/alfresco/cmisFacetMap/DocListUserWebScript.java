package com.bluexml.side.framework.alfresco.cmisFacetMap;
import java.util.Map;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.web.scripts.*;
import org.apache.log4j.Logger;
public class DocListUserWebScript extends DeclarativeWebScript {
	ServiceRegistry serviceRegistry;
	private Logger logger = Logger.getLogger(getClass());
	/* (non-Javadoc)
	 * @see org.alfresco.web.scripts.DeclarativeWebScript#executeImpl(org.alfresco.web.scripts.WebScriptRequest, org.alfresco.web.scripts.Status, org.alfresco.web.scripts.Cache)
	 */
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		logger.info("Super webscript loaded");
		return super.executeImpl(req, status, cache);
	}

	/**
	 * @return the serviceRegistry
	 */
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	/**
	 * @param serviceRegistry the serviceRegistry to set
	 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	

	
}
