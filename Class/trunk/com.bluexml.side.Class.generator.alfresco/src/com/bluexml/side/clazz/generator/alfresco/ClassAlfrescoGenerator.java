package com.bluexml.side.clazz.generator.alfresco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.application.generator.ConflitResolverHelper;
import com.bluexml.side.application.generator.UnresolvedConflictException;
import com.bluexml.side.application.generator.XMLConflictResolver;
import com.bluexml.side.application.generator.acceleo.AbstractAcceleoGenerator;

public class ClassAlfrescoGenerator extends AbstractAcceleoGenerator {

	/*
	 * final fields used in generation too
	 */
	
	public static String CONFIGURATION_PARAMETER_CATALINA_HOME = "CATALINA_HOME";
	public static String GENERATOR_OPTIONS_DATAMODEL = "alfresco.dataModel";
	public static String GENERATOR_OPTIONS_SHARE_EXTENSION = "alfresco.share.extension";
	
	public static String getTEMP_FOLDER(EObject node) {
		return TEMP_FOLDER;
	}
	
	
	XMLConflictResolver xmlresolver = null;
	public XMLConflictResolver getXmlresolver() {
		if (xmlresolver == null) {
			xmlresolver = new XMLConflictResolver(new ConflitResolverHelper(getTargetPath(), getTemporaryFolder()));
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
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webClient/alfrescoGenerator_web_client_properties.mt");
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
		// TODO Auto-generated method stub
		return true;
	}

	public Collection<IFile> complete() throws Exception {
		// register additional generated files :

		// manage all post process
		// manage file that can be in conflict
		List<IFile> conflict = searchForConflict();
		List<IFile> unresolvedconflict= new ArrayList<IFile>();
		boolean allresolved =true;
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
		// delete temporary folder
		ConflitResolverHelper.deleteFolder(getTemporaryFolder());
		
		return generatedFiles;
	}
	

	

	

}
