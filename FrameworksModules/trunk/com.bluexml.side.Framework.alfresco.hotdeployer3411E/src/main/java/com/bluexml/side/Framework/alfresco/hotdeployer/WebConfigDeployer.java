package com.bluexml.side.Framework.alfresco.hotdeployer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.alfresco.repo.config.xml.RepoXMLConfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.config.ConfigDeployment;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

public class WebConfigDeployer extends AbstractWebScript {
	static Log logger = LogFactory.getLog(WebConfigDeployer.class);
	RepoXMLConfigService webClientConfigService;

	public RepoXMLConfigService getWebClientConfigService() {
		return webClientConfigService;
	}

	public void setWebClientConfigService(RepoXMLConfigService webClientConfigService) {
		this.webClientConfigService = webClientConfigService;
	}

	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		// destroy and re-initialise config service
		webClientConfigService.destroy();
		List<ConfigDeployment> configDeployments = webClientConfigService.initConfig();

		String result = "";
		if (configDeployments != null) {
			for (ConfigDeployment configDeployment : configDeployments) {
				result += configDeployment.getName() + " ---> " + configDeployment.getDeploymentStatus() + "\n";
			}
			result += "Web Client config has been reloaded\n";
		} else {
			result += "No config reloaded";
		}
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
