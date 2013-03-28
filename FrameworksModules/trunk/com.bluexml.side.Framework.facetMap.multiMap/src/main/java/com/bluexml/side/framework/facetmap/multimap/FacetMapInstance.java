/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
