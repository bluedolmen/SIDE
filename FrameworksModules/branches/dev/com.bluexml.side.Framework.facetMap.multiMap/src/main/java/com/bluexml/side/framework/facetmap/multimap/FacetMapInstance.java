package com.bluexml.side.framework.facetmap.multimap;

import java.util.Properties;

import com.facetmap.Map;
import com.facetmap.simple.SimpleFacetMapX;

public class FacetMapInstance {
	private SimpleFacetMapX facet;
	private Properties props;
	
	public FacetMapInstance(SimpleFacetMapX facet,Properties props) {
		this.facet = facet;
		this.props=props;
		// apply properties to the facetMap
		boolean showEmptySelections = Boolean.parseBoolean(props.getProperty("showEmptySelections", "false"));
		facet.setShowEmptySelections(showEmptySelections);
		int limit = Integer.parseInt(props.getProperty("resultLimit", "5"));
		facet.setResultLimit(limit);
	}

	/**
	 * @return the facet
	 */
	public Map getFacet() {
		return facet;
	}

	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}

}
