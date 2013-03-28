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
package com.bluexml.side.workflow.generator.alfresco;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.XMLConflictResolver;
import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class WorkflowGenerator extends AbstractAlfrescoGenerator {

	public static String ALFRESCO_URL_KEY = "alfresco.url";
	XMLConflictResolver xmlresolver = null;

	@Override
	protected String getMetamodelURI() {
		return "http://www.kerblue.org/workflow/1.0";
	}

	@Override
	protected List<String> getMainTemplates() {
		// Compute services
		String alfrescoUrl = generationParameters.get(ALFRESCO_URL_KEY);
		if (alfrescoUrl != null && alfrescoUrl.length() > 0) {
			if (!alfrescoUrl.endsWith("/"))
				alfrescoUrl += "/";
			String uri = alfrescoUrl + "faces/jsp/admin/workflow-console.jsp";
			monitor.getLog().addServiceLog("Workflow Console", "The Workflow Console is an administrator/developer tool for interacting with workflows.", uri);
		}

		List<String> result = new ArrayList<String>();
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_context.mt");
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_jpdl.mt");
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_model.mt");
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_properties.mt");
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_web_client_config.mt");
		return result;
	}

	@Override
	public Properties buildModuleProperties(String rootPackage) {
		Properties props = new Properties();
		props.put("module.id", "SIDE_WorkflowExtension_" + rootPackage);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "SIDE workflow extension");
		props.put("module.description", "this module contains SIDE generated extension to add new workflow");

		return props;
	}

	@Override
	public String getVersioNumber() {
		return getGenerationParameter("com.bluexml.side.Workflow.generator.alfresco.module.version");
	}

	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id");
	}

	/**
	 * This method check if the user have the license to use this generator.
	 * 
	 * @return true if the generator can be used.
	 */
	@Override
	public boolean check() {
		return true;
	}

}
