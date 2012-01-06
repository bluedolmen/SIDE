package com.bluexml.side.form.generator.alfresco.enterprise346;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.form.generator.alfresco34d.FormGenerator;

public class FormGenerator346e extends FormGenerator {

	public FormGenerator346e() {
		versionProperty = "com.bluexml.side.Form.generator.alfresco346E.module.version";
	}

	// acceleo services 
	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id"); //$NON-NLS-1$
	}
}
