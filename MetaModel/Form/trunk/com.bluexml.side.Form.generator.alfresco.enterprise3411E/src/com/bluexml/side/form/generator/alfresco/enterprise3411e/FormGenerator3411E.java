package com.bluexml.side.form.generator.alfresco.enterprise3411e;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.form.generator.alfresco34d.FormGenerator;

public class FormGenerator3411E extends FormGenerator {

	public FormGenerator3411E() {
		versionProperty = "com.bluexml.side.Form.generator.alfresco3411E.module.version";
	}

	// acceleo services 
	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id"); //$NON-NLS-1$
	}
}
