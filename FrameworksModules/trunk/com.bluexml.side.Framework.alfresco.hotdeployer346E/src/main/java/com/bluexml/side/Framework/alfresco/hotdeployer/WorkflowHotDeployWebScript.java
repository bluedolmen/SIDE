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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowDeployment;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

public class WorkflowHotDeployWebScript extends AbstractWebScript {
	WorkflowService workflowService;
	static Log logger = LogFactory.getLog(WorkflowHotDeployWebScript.class);

	public WorkflowService getWorkflowService() {
		return workflowService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {

		// get process definition file
		String parameter = req.getParameter("filepath");
		if (logger.isDebugEnabled()) {
			logger.debug("workflowhot deployer called for :" + parameter);
		}
		File file = null;
		try {
			file = new File(new URI(parameter));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new IOException("Error when trying to parse String to URI :" + parameter);
		}

		FileInputStream fis = new FileInputStream(file);

		String result = "";
		String workflowName = null;
		//TODO need to open the XMLDocument to read process name

		if (workflowName != null) {
			// undeploy all instance and workflow definition for the given workflow name
			List<WorkflowDefinition> defs = workflowService.getAllDefinitionsByName(workflowName);
			for (WorkflowDefinition def : defs) {
				workflowService.undeployDefinition(def.id);
			}
		}

		result = deploy(fis, result);

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
		outputStream.close();
		fis.close();
	}

	private String deploy(FileInputStream fis, String result) {
		WorkflowDeployment deployment = workflowService.deployDefinition("jbpm", fis, MimetypeMap.MIMETYPE_XML);
		WorkflowDefinition def = deployment.definition;
		result += "<defId>";
		result += def.id;
		result += "</defId>" + "\n";
		for (String problem : deployment.problems) {
			result += "<problem>";
			result += problem;
			result += "</problem>" + "\n";
		}
		return result;
	}

}
