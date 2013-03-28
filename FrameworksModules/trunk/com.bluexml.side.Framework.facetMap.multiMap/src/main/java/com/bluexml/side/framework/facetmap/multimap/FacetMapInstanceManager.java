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

import org.apache.log4j.Logger;

import com.facetmap.simple.SimpleFacetMapX;

public class FacetMapInstanceManager {
	private Logger logger = Logger.getLogger(getClass());
	private FacetDisplayPropsCacheManager facetDisplayPropsCacheManager;
	private FacetMapCacheManager facetMapCacheManager;

	/**
	 * @return the facetDisplayPropsCacheManager
	 */
	public FacetDisplayPropsCacheManager getFacetDisplayPropsCacheManager() {
		return facetDisplayPropsCacheManager;
	}

	/**
	 * @return the facetMapCacheManager
	 */
	public FacetMapCacheManager getFacetMapCacheManager() {
		return facetMapCacheManager;
	}

	public FacetMapInstanceManager() {
		try {
			facetMapCacheManager = new FacetMapCacheManager();
			facetDisplayPropsCacheManager = new FacetDisplayPropsCacheManager();
		} catch (Exception e) {
			logger.error("Initialization Error", e);
		}
	}

	public FacetMapInstance getFacetInstance(String facetName, String community) throws FacetMapNotAvailableException {
		FacetMapInstance rt = null;
		try {
			SimpleFacetMapX facet = facetMapCacheManager.getFacetMap(facetName, community);
			Properties props = facetDisplayPropsCacheManager.getProps(facetName);
			rt = new FacetMapInstance(facet, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacetMapNotAvailableException("facetMap not found, check facetName", e);
		}

		return rt;
	}

	public void update() throws Exception {
		facetDisplayPropsCacheManager.update();
		facetMapCacheManager.update();
	}

	public void save(String facetName, String comments) throws Exception {
		facetDisplayPropsCacheManager.save(facetName, comments);
	}

	
	
}
