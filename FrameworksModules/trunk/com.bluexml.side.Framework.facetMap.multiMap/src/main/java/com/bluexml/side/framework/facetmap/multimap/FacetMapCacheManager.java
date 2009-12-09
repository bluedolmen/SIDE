package com.bluexml.side.framework.facetmap.multimap;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bluexml.side.framework.facetmap.alfrescoConnector.Helper;

public class FacetMapCacheManager {
	private Logger logger = Logger.getLogger(getClass());
	
	
	protected Map<String, String> availableFacetMap = new HashMap<String, String>();

	public FacetMapCacheManager() {
		System.out.println("instanciate cache mananger");
		try {
			updateAvailableMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateAvailableMap() throws Exception {
		URL cache = this.getClass().getResource(Helper.cacheRep);
		File fileCacheRep = new File(cache.toURI().getPath());
		if (fileCacheRep.exists()) {
			File[] l = fileCacheRep.listFiles();
			availableFacetMap.clear();
			for (File file : l) {
				availableFacetMap.put(getMapKey(file), file.getAbsolutePath());
			}
		}
	}
	
	public URL getFacetMap(String groupId) throws Exception {
		if (!availableFacetMap.containsKey(groupId)) {
			updateAvailableMap();
			if (!availableFacetMap.containsKey(groupId)) {
				// update do not found required file
				logger.warn("required map not ready, must run update");
				return null;
			}
		}
		
		return new File(availableFacetMap.get(groupId)).toURL();
	}

	private String getMapKey(File file) {
		return file.getName().replaceFirst("map_([^\\.]*)\\.xml", "$1");
	}
	
}
