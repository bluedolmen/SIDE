package com.bluexml.side.workflow.generator.alfresco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.application.generator.AbstractGenerator;
import com.bluexml.side.application.generator.XMLConflictResolver;
import com.bluexml.side.application.generator.alfresco.AbstractAlfrescoGenerator;
import com.bluexml.side.application.security.SecurityHelper;
import com.bluexml.side.settings.SidePreferences;

public class WorkflowGenerator extends AbstractAlfrescoGenerator {
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
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_context.mt");
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_jpdl.mt");
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_model.mt");
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_properties.mt");
		result.add("/com.bluexml.side.Workflow.generator.Alfresco/templates/alfrescoGenerator_web_client_config.mt");
		return result;
	}

	public Collection<IFile> complete() throws Exception {
		// solveConflict();
		IFile ampIFile = buildAMPPackage();
		generatedFiles.add(ampIFile);

		return generatedFiles;
	}

	public Properties buildModuleProperties() {
		Properties props = new Properties();
		props.put("module.id", "SIDE_WorkflowExtension");
		props.put("module.version", getVersioNumber());
		props.put("module.title", "S-IDE workflow extension");
		props.put("module.description", "this module contains S-IDE generated extension to add new workflow");
		
		return props;
	}
	
	public String getVersioNumber() {
		String vn = getGenerationParameter("com.bluexml.side.Workflow.generator.alfresco.module.version");
		if (vn == null || vn.equals("")) {
			vn ="1.0";
		}
		return vn;
	}
	
	/**
	 * method usable as Acceleo Templates Services
	 */
	
	public String getTEMP_FOLDER(EObject node) {
		return this.getTEMP_FOLDER();
	}
	
	public String getModuleIdService(EObject node) throws Exception {
		// AbstractGenerator.printConfiguration();
		if (!AbstractGenerator.generationParameters.containsKey("com.bluexml.side.Class.generator.alfresco.module.id")) {
			return buildModuleProperties().getProperty("module.id");
		}
		return AbstractGenerator.generationParameters.get("com.bluexml.side.Class.generator.alfresco.module.id");
	}
	
	/**
	 * This method check if the user have the license to use this generator.
	 * 
	 * @return true if the generator can be used.
	 */
	public boolean check(){
		return true;//SecurityHelper.check(GENERATOR_CODE,SidePreferences.getKey());
	}
	
}
