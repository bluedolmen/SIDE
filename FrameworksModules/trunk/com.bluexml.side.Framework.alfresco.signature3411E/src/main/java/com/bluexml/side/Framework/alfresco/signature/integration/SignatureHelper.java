package com.bluexml.side.Framework.alfresco.signature.integration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SignatureHelper {
	
	private static Log logger = LogFactory
            .getLog(SignatureHelper.class);
	protected ServiceRegistry serviceRegistry;
	protected NodeService nodeService;
	
	/** This function sign all the pdf describe by myFileToSign and all pdf linked to file into myFileToSign (test.doc is into myFileToSign, if test.pdf exist it will be sign).
	 *  This pdf need to be child of parapheur or document.
	 *  WARNING exception are passed to the action by reference because serviceRegistry.getActionService().executeAction() doesn't throw exception.
	 * @param props all the properties needed to sign
	 * @param entree the folder where are the files to sign.
	 * @param myFileToSign the list of file(s) to diffuse.
	 * @param destination The folder where the signed file are created. If null signed file are brother of the original file.
	 * @return exception if there are
	 **/
	public ArrayList<String> execute(Properties props, NodeRef entree, List<AssociationRef> myFileToSign, NodeRef destination) {
		ArrayList<String> exception = new ArrayList<String>();
		NodeRef key = null;
		Map<String, Serializable> params = null;
		
		//Get the keystore.
		List<NodeRef> assocTarget = null;
		NodeRef root = nodeService
				.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		assocTarget = serviceRegistry
				.getSearchService()
				.query(root.getStoreRef(),
						SearchService.LANGUAGE_LUCENE,
						"PATH:\"" + props.get("Service.signature.keystore.path") + "cm:" + props.get("Service.signature.keystore.name") + "\"")
				.getNodeRefs();
		if (assocTarget.size() > 0) {
			key = assocTarget.get(0);
		} else {
			logger.error("Keystore not found at " + props.get("Service.signature.keystore.path") + props.get("Service.signature.keystore.name"));
			exception.add("Keystore not found at " + props.get("Service.signature.keystore.path") + props.get("Service.signature.keystore.name"));
			return exception;
		}
		
		//test if properties are correct.
		exception = testinit(props, entree);
		if (!exception.isEmpty()) {
			return exception;
		} else {
			//Initialize parameters.
			params = initParams(props, key, exception);
		}
		
		/* Parse myFileToSign, if it is a pdf => sign it
		 * if not test if a pdf file exist with the same cm:name less the extension.
		 */
		for(AssociationRef child : myFileToSign) {
			ContentReader myReader = serviceRegistry.getContentService().getReader(child.getTargetRef(), ContentModel.PROP_CONTENT);
			if (myReader.getMimetype().equals("application/pdf")) {
				logger.debug("Start signature of document  " + nodeService.getProperty(child.getTargetRef(), ContentModel.PROP_NAME));
				//Create Sign pdf action.
				if (destination != null && nodeService.exists(destination)) {
					params.put("destination-folder", destination);
				} else {
					params.put("destination-folder", nodeService.getPrimaryParent(child.getTargetRef()).getParentRef());
				}
				Action signPDF = serviceRegistry.getActionService().createAction("pdf-signature", params);
				serviceRegistry.getActionService().executeAction(signPDF, child.getTargetRef());
			} else {
				//Get Name without extension
				String name = (String) nodeService.getProperty(child.getTargetRef(), ContentModel.PROP_NAME);
				String[] splitedName = name.split("\\.");
				name = getNameWithoutExtension(splitedName);
				//Get all files under parapheurDocuments
				List<ChildAssociationRef> myFiles = nodeService.getChildAssocs(entree);
				for(ChildAssociationRef mychild : myFiles) {
					if(nodeService.getType(mychild.getChildRef()) == ContentModel.TYPE_CONTENT) {
						// Test if names matche 
						if (nodeService.getProperty(mychild.getChildRef(), ContentModel.PROP_NAME).equals(name + ".pdf")) {
							logger.debug("Start signature of document  " + nodeService.getProperty(mychild.getChildRef(), ContentModel.PROP_NAME));
							if (destination != null && nodeService.exists(destination)) {
								params.put("destination-folder", destination);
							} else {
								params.put("destination-folder", nodeService.getPrimaryParent(mychild.getParentRef()));
							}
							Action signPDF = serviceRegistry.getActionService().createAction("pdf-signature", params);
							serviceRegistry.getActionService().executeAction(signPDF, mychild.getChildRef());
							break;
						}
					} else if(nodeService.getType(mychild.getChildRef()) == ContentModel.TYPE_FOLDER) {
						if (searchFilesIntoFolders(mychild.getChildRef(), name + ".pdf", params, destination)) {
							break;
						}
					}
				}
			}
		}
		return exception;
	}

	private boolean searchFilesIntoFolders(NodeRef node, String name, Map<String, Serializable> params, NodeRef destination) {
		List<ChildAssociationRef> myFiles = nodeService.getChildAssocs(node);
		for(ChildAssociationRef mychild : myFiles) {
			if(nodeService.getType(mychild.getChildRef()) == ContentModel.TYPE_CONTENT) {
				if (nodeService.getProperty(mychild.getChildRef(), ContentModel.PROP_NAME).equals(name)) {
					logger.debug("Start signature of document  " + nodeService.getProperty(mychild.getChildRef(), ContentModel.PROP_NAME));
					if (destination != null && nodeService.exists(destination)) {
						params.put("destination-folder", destination);
					} else {
						params.put("destination-folder", nodeService.getPrimaryParent(mychild.getParentRef()));
					}
					Action signPDF = serviceRegistry.getActionService().createAction("pdf-signature", params);
					serviceRegistry.getActionService().executeAction(signPDF, mychild.getChildRef());
					return true;
				}
			} else if(nodeService.getType(mychild.getChildRef()) == ContentModel.TYPE_FOLDER) {
				if (searchFilesIntoFolders(mychild.getChildRef(), name, params, destination)) {
					return true;
				}
			}
		}
		return false;
	}

	private String getNameWithoutExtension(String[] splitedName) {
		String name = "";
		if (splitedName.length == 2) {
			return name = splitedName[0];
		} else {
			for(int i = splitedName.length - 2; i >= 0; i--) {
	    		if (name.equals("")) {
	    			name =  splitedName[i];
	    		} else {
	    			name =  splitedName[i] + "." + name;
	    		}
	    	}
			return name;
		}
	}

	ArrayList<String> testinit(Properties props, NodeRef entree) {
		ArrayList<String> exception = new ArrayList<String>();
		if (props == null || entree == null) {
			logger.error("Wrong initialisation of signature helper. This document(s) cannot be signed");
			exception.add("Wrong initialisation of signature helper. This document(s) cannot be signed");
			return exception;
		} else {
			if (testProps(props)) {
				logger.error("Wrong initialisation of signature helper. This document(s) cannot be signed");
				exception.add("Wrong initialisation of signature helper. This document(s) cannot be signed");
				return exception;
			} else {
					logger.debug("Start signature of document(s)");
				}
			}
		return exception;
	}

	private Map<String, Serializable> initParams(Properties props, NodeRef key, ArrayList<String> myException) {
		Map<String, Serializable> params = new HashMap<String, Serializable>();
		params.put("exception", myException);
		params.put("private-key",key);
		params.put("key-type", (Serializable) props.get("Service.signature.key.type"));
		params.put("key-password", (Serializable) props.get("Service.signature.key.password"));
		params.put("store-password", (Serializable) props.get("Service.signature.keystore.password"));
		params.put("alias", (Serializable) props.get("Service.signature.key.alias"));
		params.put("visibility", (Serializable) props.get("Service.signature.visibility"));
		params.put("location-x", (Serializable) props.get("Service.signature.position.x"));
		params.put("location-y", (Serializable) props.get("Service.signature.position.y"));
		params.put("height", (Serializable) props.get("Service.signature.heigth"));
		params.put("width", (Serializable) props.get("Service.signature.width"));
		params.put("reason", (Serializable) props.get("Service.signature.location"));
		params.put("location", (Serializable) props.get("Service.signature.location"));
		params.put("signed-name", (Serializable) props.get("Service.signature.signed.file.name"));
		return params;
	}

	private boolean testProps(Properties props) {
		if (props.get("Service.signature.location") == null) {
			props.setProperty("Service.signature.reason", "");
		}
		if (props.get("Service.signature.location") == null) {
			props.setProperty("Service.signature.location", "");
		}
		if (props.get("Service.signature.key.password") == null || props.get("Service.signature.key.password").equals("") ||
					props.get("Service.signature.keystore.path") == null || props.get("Service.signature.keystore.path").equals("") ||
					props.get("Service.signature.key.alias") == null || props.get("Service.signature.key.alias").equals("") ||
					props.get("Service.signature.keystore.name") == null || props.get("Service.signature.keystore.name").equals("") ||
					props.get("Service.signature.keystore.password") == null || props.get("Service.signature.keystore.password").equals("") ||
					props.get("Service.signature.visibility") == null || props.get("Service.signature.visibility").equals("") ||
					props.get("Service.signature.key.type") == null || props.get("Service.signature.key.type").equals("") ||
					props.get("Service.signature.width") == null || props.get("Service.signature.width").equals("") ||
					props.get("Service.signature.heigth") == null || props.get("Service.signature.heigth").equals("") ||
					props.get("Service.signature.position.x") == null || props.get("Service.signature.position.x").equals("") ||
					props.get("Service.signature.signed.file.name") == null || props.get("Service.signature.signed.file.name").equals("") ||
					props.get("Service.signature.position.y") == null || props.get("Service.signature.position.y").equals("")) {
			return true;
		} else {
			return false;
		}
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
