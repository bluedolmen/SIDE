package com.bluexml.side.Framework.alfresco.hotdeployer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import org.alfresco.repo.i18n.MessageService;
import org.alfresco.service.cmr.admin.RepoAdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
/**
 * SIDE Extension
 * Service to reload i18n messages
 * @author davidabad
 *
 */
public class MessageBundleDeployerWebscript extends AbstractWebScript {
	static Log logger = LogFactory.getLog(MessageBundleDeployerWebscript.class);
	private RepoAdminService repoAdminService;
	private MessageService messageService;

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public RepoAdminService getRepoAdminService() {
		return repoAdminService;
	}

	public void setRepoAdminService(RepoAdminService repoAdminService) {
		this.repoAdminService = repoAdminService;
	}

	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		String bundleBasePath = req.getParameter("bundleBasePath");

		if (logger.isDebugEnabled()) {
			List<String> messageBundles = repoAdminService.getMessageBundles();
			for (String string : messageBundles) {
				logger.debug("MessageBundleDeployerWebscript.execute() bundle :" + string);
			}

			Set<String> registeredBundles = messageService.getRegisteredBundles();
			for (String string : registeredBundles) {
				logger.debug("MessageBundleDeployerWebscript.execute() registered :" + string);
			}
		}

		String bundleBaseName = repoAdminService.deployMessageBundle(bundleBasePath);
		String result = bundleBaseName;

		String response = "<res>";
		response += result;
		response += "</res>";
		// response
		res.setContentType("text/xml");
		String contentEncoding = "UTF-8";
		res.setContentEncoding(contentEncoding);
		OutputStream outputStream = res.getOutputStream();
		byte[] bytes = response.getBytes("UTF-8");
		if (logger.isDebugEnabled()) {
			logger.debug("result :" + response);
		}
		outputStream.write(bytes);
	}

}
