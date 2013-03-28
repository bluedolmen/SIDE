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
package com.bluexml.side.Framework.alfresco.notification;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.action.executer.MailActionExecuter.URLHelper;
import org.alfresco.repo.template.DateCompareMethod;
import org.alfresco.repo.template.HasAspectMethod;
import org.alfresco.repo.template.I18NMessageMethod;
import org.alfresco.repo.template.TemplateNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmailTest {
	private static Log logger = LogFactory.getLog(EmailTest.class);
	ServiceRegistry serviceRegistry;

    private static final String REPO_REMOTE_URL = "http://localhost:8080/alfresco";

	public EmailTest(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	
	public Map<String, Object> createEmailTemplateModel(NodeRef ref) {
		Map<String, Object> model = new HashMap<String, Object>(8, 1.0f);

		NodeRef person = serviceRegistry.getPersonService().getPerson(serviceRegistry.getAuthenticationService().getCurrentUserName());
		model.put("person", new TemplateNode(person, serviceRegistry, null));
		model.put("document", new TemplateNode(ref, serviceRegistry, null));
		NodeRef parent = serviceRegistry.getNodeService().getPrimaryParent(ref).getParentRef();
		model.put("space", new TemplateNode(parent, serviceRegistry, null));

		// current date/time is useful to have and isn't supplied by FreeMarker by default
		model.put("date", new Date());

		// add custom method objects
		model.put("hasAspect", new HasAspectMethod());
		model.put("message", new I18NMessageMethod());
		model.put("dateCompare", new DateCompareMethod());

	    model.put("url", new URLHelper(REPO_REMOTE_URL));

		return model;
	}

	public void doAction(List<String> recipicents, String subject, NodeRef doc, NodeRef templateRef, Map<String, Object> model) {
		logger.debug("email recipicents :" + recipicents);
		String text = serviceRegistry.getTemplateService().processTemplate("freemarker", templateRef.toString(), model);
		logger.debug("email subject :" + subject);
		logger.debug("email body :" + "\n" + text);
	}

}
