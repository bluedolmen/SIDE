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
