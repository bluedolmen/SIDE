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

		List<String> messageBundles = repoAdminService.getMessageBundles();
		for (String string : messageBundles) {
			System.out.println("MessageBundleDeployerWebscript.execute() bundle :" + string);
		}

		Set<String> registeredBundles = messageService.getRegisteredBundles();
		for (String string : registeredBundles) {
			System.out.println("MessageBundleDeployerWebscript.execute() registered :"+string);
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
		logger.debug("result :" + response);
		outputStream.write(bytes);
	}

}
