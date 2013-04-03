package com.alfea.alfresco.repo.web.scripts.activity.stats;

import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.admin.RepoServerMgmt;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.AuthorityService;
import org.alfresco.service.cmr.security.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

/**
 * Java-backed WebScript to retrieve number of connected users
 * @author gwillems
 *
 */
public class ConnectedUsersWebScript extends DeclarativeWebScript {
	 private static final Log logger = LogFactory.getLog(ConnectedUsersWebScript.class);
	 protected static final String CONNECTED_USERS_COUNT_KEY = "connectedUsersCount";
	 protected static final String CONNECTED_USERS_KEY = "connectedUsers";
	 protected RepoServerMgmt repoServerMgmt;
	 protected AuthorityService authorityService;
	 protected PersonService personService;
	 protected NodeService nodeService;
	 
	 /* (non-Javadoc)
	  * @see org.alfresco.web.scripts.DeclarativeWebScript#executeImpl(org.alfresco.web.scripts.WebScriptRequest, org.alfresco.web.scripts.Status, org.alfresco.web.scripts.Cache)
	  */
	 @Override
	 protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache){
		 Map<String, Object> model = new HashMap<String, Object>();
		 
		 // Get Conected user
		 model.put(CONNECTED_USERS_COUNT_KEY, repoServerMgmt.getUserCountNonExpired());
		 if(logger.isDebugEnabled()){
			 logger.debug("Connected users: " + model.get(CONNECTED_USERS_COUNT_KEY));
		 }
		 
		 //If user is admin, get user list
		 Map<String,String> userMap = new HashMap<String, String>();
		 if (authorityService.hasAdminAuthority()){
			 String[] userList = repoServerMgmt.listUserNamesNonExpired();
			 for (int i = 0 ; i < userList.length ; i++){
				 if(personService.personExists(userList[i])){
					 NodeRef user = personService.getPerson(userList[i]);
					 String email = (String)nodeService.getProperty(user, ContentModel.PROP_EMAIL);
					 userMap.put(userList[i], email);
					 if(logger.isDebugEnabled()){
						 logger.debug("Connected user: " + userList[i] + " (" + email + ")");
					 }
				 }
			 }
		 }
		 
		 model.put(CONNECTED_USERS_KEY, userMap);
		 return model;
	}

	/**
	 * @param repoServerMgmt the repoServerMgmt to set
	 */
	public void setRepoServerMgmt(RepoServerMgmt repoServerMgmt) {
		this.repoServerMgmt = repoServerMgmt;
	}

	/**
	 * @param authorityService the authorityService to set
	 */
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	/**
	 * @param personService the personService to set
	 */
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	/**
	 * @param nodeService the nodeService to set
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
}
