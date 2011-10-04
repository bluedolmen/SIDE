package com.bluexml.side.workflow.generator.alfresco.enterprise;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class WorkflowGenerator extends com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator {
	
	public static String GENERATOR_CODE = "CODE_GED_G_W_ALFRESCO_3";

	public Properties buildModuleProperties(String rootPackage) {
		Properties props = new Properties();
		props.put("module.id", "SIDE_WorkflowExtension_" + rootPackage);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "SIDE workflow extension");
		props.put("module.description", "this module contains SIDE generated extension to add new workflow");
		return props;
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
	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String idMetamodel) throws Exception {
		if (!check()) {
			throw new Exception("Bad, please to enter your licence number in preferencies page");
		}
		return super.generate(modelsInfo, idMetamodel);
	}
}
