package com.bluexml.side.portal.generator.alfresco346e;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.portal.generator.alfresco34d.PortalGenerator;

public class PortalGenerator346e extends PortalGenerator {

	@Override
	public String getXFORMURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_XFORMURL);
	}

	@Override
	public String getSHAREURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_SHAREURL);
	}

	@Override
	public String getFacetMapURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_FACETMAPURL);
	}
}
