package com.bluexml.side.Framework.alfresco.hotdeployer;

import java.io.IOException;
import java.io.OutputStream;

import org.alfresco.repo.admin.RepoAdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

public class MessageBundleDeployerWebscript extends AbstractWebScript {
	static Log logger = LogFactory.getLog(MessageBundleDeployerWebscript.class);
	private RepoAdminService repoAdminService;

	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		String bundleBasePath = req.getParameter("bundleBasePath");

		String bundleBaseName = repoAdminService.deployMessageBundle(bundleBasePath);

		String response = "<res>";
		String result = bundleBaseName;
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
