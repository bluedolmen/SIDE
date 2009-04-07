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

public class WorkflowGenerator extends AbstractAlfrescoGenerator {

	XMLConflictResolver xmlresolver = null;

	public XMLConflictResolver getXmlresolver() {
		if (xmlresolver == null) {
			xmlresolver = new XMLConflictResolver(this.getCresolver());
		}
		return xmlresolver;
	}

	protected Properties moduleProperties;

	

	public boolean check() {
		// Already usable
		return true;
	}

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
		props.put("module.version", "1.0");
		props.put("module.title", "S-IDE workflow extension");
		props.put("module.description", "this module contains S-IDE generated extension to add new workflow");
		/*
		 * props.put("module.id",getGenerationParameter(
		 * "com.bluexml.side.Class.generator.alfresco.module.id"));
		 * props.put("module.version",getGenerationParameter(
		 * "com.bluexml.side.Class.generator.alfresco.module.version"));
		 * props.put("module.title",getGenerationParameter(
		 * "com.bluexml.side.Class.generator.alfresco.module.title"));
		 * props.put("module.description",getGenerationParameter(
		 * "com.bluexml.side.Class.generator.alfresco.module.description"));
		 */
		return props;
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

}
