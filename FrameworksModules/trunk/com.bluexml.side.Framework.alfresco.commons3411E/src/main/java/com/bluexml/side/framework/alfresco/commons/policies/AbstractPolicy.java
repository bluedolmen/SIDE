package com.bluexml.side.framework.alfresco.commons.policies;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.AuthenticationService;

public abstract class AbstractPolicy {

	private AuthenticationService authenticationService;

	protected boolean isValideNodeAndContext(NodeRef node) {
		String currentUserName = getAuthenticationService().getCurrentUserName();
		// sometime nodeRef that can't be founded by nodeService
		return currentUserName != null && !currentUserName.equals("null") && getNodeService().exists(node) && !getNodeService().hasAspect(node, ContentModel.ASPECT_WORKING_COPY);
	}

	public abstract NodeService getNodeService();

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

}
