package com.bluexml.side.view.generator.alfresco346e;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.view.generator.alfresco34d.ViewGenerator;

public class ViewGenerator346e extends ViewGenerator {

	// acceleo services
		public String getModuleIdService(EObject ob, String modelId) throws Exception {
			return buildModuleProperties(modelId).getProperty("module.id"); //$NON-NLS-1$
		}
}
