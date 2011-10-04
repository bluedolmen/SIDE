package com.bluexml.side.view.generator.facetmap34d;

import java.util.HashMap;
import java.util.Map;

import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator;

public class FacetMapViewGenerator extends ViewFacetmapGenerator {

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected Map<String, String> getTemplatesSubstitution() {
		Map<String, String> templatesSubstitution = new HashMap<String, String>();
		templatesSubstitution.put("/com.bluexml.side.View.generator.facetmap/templates/facetmap-facets-cmis2xfml-generation.mt", "/com.bluexml.side.View.generator.facetmap34d/com/bluexml/side/view/generator/facetmap34d/templates/facetmap-facets-cmis2xfml-generation.mt");
		return templatesSubstitution;
	}

}
