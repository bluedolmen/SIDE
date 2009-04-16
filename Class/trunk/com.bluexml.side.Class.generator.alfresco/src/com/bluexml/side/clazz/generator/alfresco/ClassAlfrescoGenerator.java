package com.bluexml.side.clazz.generator.alfresco;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.application.generator.AbstractGenerator;
import com.bluexml.side.application.generator.XMLConflictResolver;
import com.bluexml.side.application.generator.alfresco.AbstractAlfrescoGenerator;

public class ClassAlfrescoGenerator extends AbstractAlfrescoGenerator {

	/*
	 * final fields used in generation too
	 */

	
	public static String GENERATOR_OPTIONS_DATAMODEL = "alfresco.dataModel";
	public static String GENERATOR_OPTIONS_SHARE_EXTENSION = "alfresco.share.extension";

	

	XMLConflictResolver xmlresolver = null;

	public XMLConflictResolver getXmlresolver() {
		if (xmlresolver == null) {
			xmlresolver = new XMLConflictResolver(this.getCresolver());
		}
		return xmlresolver;
	}

	public static String MMUri = "http://www.kerblue.org/class/1.0";

	public List<String> classTemplates = null;

	public static String wClientTmpFile = TEMP_FOLDER + "/shared/classes/alfresco/extension/web-client-config-custom.xml";

	public List<String> getClassTemplates() {
		if (classTemplates == null) {
			List<String> result = new ArrayList<String>();
			if (getGeneratorOptionValue(GENERATOR_OPTIONS_DATAMODEL)) {
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/Model/alfrescoGenerator_model.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/Model/alfrescoGenerator_model_properties.mt");

				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webClient/alfrescoGenerator_web_client_config.mt");

				result.add("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoGenerator_context.mt");
			}

			if (getGeneratorOptionValue(GENERATOR_OPTIONS_SHARE_EXTENSION)) {
				// only to extends alfresco share Forms
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/CustomConfiguration/web-framework-config-custom.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/SiteWebscripts/AlfrescoFileUploadPatch/file-upload-js-get-lib.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/SiteWebscripts/AlfrescoFileUploadPatch/file-upload-response-get-patch.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/SiteWebscripts/AlfrescoFileUploadPatch/flash-upload-js-get-patch.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/SiteWebscripts/AlfrescoFileUploadPatch/html-upload-js-get-patch.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/Webscripts/bluexml-upload-js-post.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/Webscripts/bluexml-upload-post-desc.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/Webscripts/bluexml-upload-response-html-post.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/Webscripts/bluexml-upload-response-json-post.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/Webscripts/bluexml-upload-response-status-get.mt");
			}
			classTemplates = result;
		}
		return classTemplates;
	}

	public void setClassTemplates(List<String> classTemplates) {
		this.classTemplates = classTemplates;
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		return getClassTemplates();
	}

	public boolean check() {
		return true;
	}

	public Collection<IFile> complete() throws Exception {
		//solveConflict();
		IFile ampIFile = buildAMPPackage();
		generatedFiles.add(ampIFile);

		return generatedFiles;
	}

	
	
	
	public Properties buildModuleProperties() {
		Properties props = new Properties();
		props.put("module.id", "SIDE_ModelExtension");
		props.put("module.version", "1.0");
		props.put("module.title", "S-IDE model extension");
		props.put("module.description", "this module contains S-IDE generated extension to extends Alfresco Data Model");
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

/*
	private void solveConflict() throws Exception {
		// manage file that can be in conflict
		List<IFile> conflict = searchForConflict();
		List<IFile> unresolvedconflict = new ArrayList<IFile>();
		boolean allresolved = true;
		for (IFile f : conflict) {
			if (f.getFullPath().toString().indexOf("/shared/classes/alfresco/extension/web-client-config-custom.xml") != -1) {
				// known conflict, apply specific process

				getXmlresolver().resolveByMerging(f);
				System.out.println("resolved conflict on :" + f.getFullPath());
			} else {
				// unknown conflict
				allresolved = false;
				System.err.println("Unknow conflict detected on :" + f.getFullPath());
			}
		}
		if (!allresolved) {
			throw new UnresolvedConflictException(unresolvedconflict);
		}
		// conflicts are resolved
		getCresolver().copyToFinalFolder();
	}
*/
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
