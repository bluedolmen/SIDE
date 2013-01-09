package com.bluexml.side.Framework.alfresco.signature.integration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SignatureHelper {
	
	private static Log logger = LogFactory
            .getLog(SignatureHelper.class);
	protected ServiceRegistry serviceRegistry;
	protected NodeService nodeService;
	
	public void execute(String keyPass, String storePass, String alias, NodeRef key, NodeRef destination, NodeRef document) throws Exception {
		
		if (keyPass.equals("") || keyPass == null || storePass.equals("") || storePass == null || alias.equals("") || alias == null ||
				key == null || destination == null || document == null ) {
			logger.error("Wrong initialisation of signature helper. This document cannot be signed");
			return;
		} else {
			ContentReader myReader = serviceRegistry.getContentService().getReader(document, ContentModel.PROP_CONTENT);
			if (myReader.getMimetype().equals("application/pdf")) {
				logger.debug("Start signature of document " + nodeService.getProperty(document, ContentModel.PROP_NAME));
			} else {
				logger.debug("The document " + nodeService.getProperty(document, ContentModel.PROP_NAME) + " is not a PDF. It cannot be signed.");
				return;
			}
		}
		
		Map<String, Serializable> params = new HashMap<String, Serializable>();
		params.put("destination-folder",destination);
		params.put("private-key",key);
		params.put("key-type","Default");
		params.put("key-password",keyPass);
		params.put("store-password",storePass);
		params.put("alias",alias);
		params.put("visibility","Hidden");
		params.put("location-x","1");
		params.put("location-y","1");
		params.put("height","1");
		params.put("width","1");
		params.put("reason","");
		params.put("location","");
		Action signPDF = serviceRegistry.getActionService().createAction("pdf-signature", params);
		serviceRegistry.getActionService().executeAction(signPDF, document);
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public NodeService getNodeService() {
		return nodeService;
	}
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
}
