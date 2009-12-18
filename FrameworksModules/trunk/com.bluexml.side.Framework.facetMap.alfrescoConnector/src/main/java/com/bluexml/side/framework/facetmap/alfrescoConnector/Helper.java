package com.bluexml.side.framework.facetmap.alfrescoConnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.tools.ant.Project;

import com.bluexml.side.framework.facetmap.multimap.FacetMapNotAvailableException;

public class Helper {
	public static String cacheRep = "/multimap/cache";
	public static String alfrescoConnector = "/alfrescoConnector";

	public Properties getProperties(String mapId) throws FacetMapNotAvailableException {
		Properties p = new Properties();
		String fileName = "/build." + mapId + ".properties";
		if (mapId ==null) {
			fileName = "/build.properties";
		}
		
		String path = alfrescoConnector + fileName;
		try {
			File f = getFileFromClassPath(path);
			// InputStream in = this.getClass().getResourceAsStream(path);
			InputStream in = new FileInputStream(f);

			p.load(in);
		} catch (Exception e) {
			throw new FacetMapNotAvailableException("Error occure while updating facetmap, check facet name :" + mapId, e);
		}
		return p;
	}

	public File getBuildFile() throws Exception {
		return getFileFromClassPath(alfrescoConnector + "/build.xml");
	}

	public File getFileFromClassPath(String path) throws Exception {
		URL url = this.getClass().getResource(path);
		File file = new File(url.toURI().getPath());
		return file;
	}

	public void setProperties(Project ant, Properties p) {
		for (Map.Entry<Object, Object> it : p.entrySet()) {
			ant.setProperty(it.getKey().toString(), it.getValue().toString());
		}
	}

	public File getMapFile(String mapId, String community) throws Exception {
		return new File(new Helper().getFileFromClassPath(Helper.cacheRep), getFacetId(mapId, community));
	}

	public String getFacetId(String mapId, String community) {
		return "map_" + mapId + "_" + community + ".xml";
	}

	public boolean isCommunityAdmin(HttpServletRequest req) {
		String community = req.getParameter("community");
		List<String> auths = (List<String>) req.getSession().getAttribute("userRight");
		String authToSearch = "GROUP_site_" + community + "_SiteManager";
		return auths.contains(authToSearch);
	}
	
	public boolean isAdmin(List<String> auths) {
		String authToSearch = "ROLE_ADMINISTRATOR";
		return auths.contains(authToSearch);
	}
}
