package com.bluexml.side.Framework.alfresco.notification;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.action.executer.MailActionExecuter.URLHelper;
import org.alfresco.repo.admin.SysAdminParams;
import org.alfresco.repo.admin.SysAdminParamsImpl;
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

		SysAdminParams sysadmin = new SysAdminParamsImpl();
		model.put("url", new URLHelper(sysadmin));

		return model;
	}

	public void doAction(List<String> recipicents, String subject, NodeRef doc, NodeRef templateRef, Map<String, Object> model) {
		logger.debug("email recipicents :" + recipicents);
		String text = serviceRegistry.getTemplateService().processTemplate("freemarker", templateRef.toString(), model);
		logger.debug("email subject :" + subject);
		logger.debug("email body :" + "\n" + text);
	}
}
