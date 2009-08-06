package com.bluexml.side.workflow.generator.alfresco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.XMLConflictResolver;
import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class WorkflowGenerator extends AbstractAlfrescoGenerator {
	
	public static String ALFRESCO_URL_KEY = "alfresco.url";
	public static String GENERATOR_CODE = "CODE_GED_G_W_ALFRESCO_3";
	XMLConflictResolver xmlresolver = null;

	public XMLConflictResolver getXmlresolver() {
		if (xmlresolver == null) {
			xmlresolver = new XMLConflictResolver(this.getCresolver());
		}
		return xmlresolver;
	}

	protected Properties moduleProperties;

	@Override
	protected String getMetamodelURI() {
		return "http://www.kerblue.org/workflow/1.0";
	}

	@Override
	protected List<String> getTemplates() {
		List<String> result = new ArrayList<String>();
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_context.mt");
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_jpdl.mt");
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_model.mt");
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_properties.mt");
		result.add("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_web_client_config.mt");
		return result;
	}

	public Properties buildModuleProperties(String rootPackage) {
		Properties props = new Properties();
		props.put("module.id", "SIDE_WorkflowExtension_" + rootPackage);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "S-IDE workflow extension");
		props.put("module.description", "this module contains S-IDE generated extension to add new workflow");

		return props;
	}

	public String getVersioNumber() {
		String vn = getGenerationParameter("com.bluexml.side.Workflow.generator.alfresco.module.version");
		if (vn == null || vn.equals("")) {
			vn = "1.0";
		}
		return vn;
	}

	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id");
	}

	/**
	 * This method check if the user have the license to use this generator.
	 * 
	 * @return true if the generator can be used.
	 */
	public boolean check() {
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}
	
	@Override
	public Collection<IFile> complete() throws Exception {
		String alfrescoUrl = generationParameters.get(ALFRESCO_URL_KEY);
		if (!alfrescoUrl.endsWith("/"))
			alfrescoUrl += "/";
		String uri = alfrescoUrl + "faces/jsp/admin/workflow-console.jsp";
		addServiceLog("Workflow Console","The Workflow Console is an administrator/developer tool for interacting with workflows.", uri);

		return super.complete();
	}

}
