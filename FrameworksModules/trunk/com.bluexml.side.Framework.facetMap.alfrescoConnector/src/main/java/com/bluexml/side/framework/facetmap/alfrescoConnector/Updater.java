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
package com.bluexml.side.framework.facetmap.alfrescoConnector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.bluexml.side.framework.facetmap.multimap.FacetMapAlfrescoServlet;
import com.bluexml.side.framework.facetmap.multimap.FacetMapInstanceManager;
import com.bluexml.side.framework.facetmap.multimap.FacetMapNotAvailableException;

public class Updater {

	public static String getDocBase(String community) {
		return "Sites/"+community+"/documentLibrary";
	}
	
	public static String update(HttpServletRequest httpservletrequest) throws Exception {
		// extract parameters
		String host=httpservletrequest.getHeader("host");
		String facetName=httpservletrequest.getParameter("facetName");
		String community=httpservletrequest.getParameter("community");
		
		
		
		return doUpdate(host, facetName, community);
		
	}

	private static String doUpdate(String host, String facetName, String community) throws FacetMapNotAvailableException, Exception {
		// Update code
		Project ant = new Project();

		// add a listener to see ant's log
		org.apache.tools.ant.DefaultLogger log = new org.apache.tools.ant.DefaultLogger();
		log.setOutputPrintStream(System.out);
		log.setMessageOutputLevel(Project.MSG_INFO);

		ant.addBuildListener(log);

		Helper help = new Helper();
		Properties p = help.getProperties(facetName);

		// building ant script

		ant.init();
		help.setProperties(ant, p);
		ant.setProperty("ant.host", host);
		
		String path= getDocBase(community);
		path=path.replaceAll(" ", "%20");
		ant.setProperty("ant.community", path);
		
		Helper h =new Helper(); 
		File f = h.getMapFile(facetName, community);
		ant.setProperty("ant.Mapname", f.getAbsolutePath());
		
		ProjectHelper.configureProject(ant, help.getBuildFile());
		ant.executeTarget("main");
		
		return h.getFacetId(facetName, community);
	}
	
	public static List<String> updateAll(FacetMapAlfrescoServlet srv, HttpServletRequest httpservletrequest) throws Exception {
		List<String> updated= new ArrayList<String>();
		FacetMapInstanceManager facetMapInstanceManager = srv.getFacetMapInstanceManager();
		
		Properties prop =new Helper().getProperties(null);
		
		AlfrescoCommunicator conn = new AlfrescoCommunicator(prop);
		List<String> lsite = conn.getSitesCollection();
		for (String community : lsite) {
			Set<String> keys = facetMapInstanceManager.getFacetDisplayPropsCacheManager().getPropertiesCache().keySet();
			for (String facetName : keys) {
				String host=httpservletrequest.getHeader("host");
				String uo =doUpdate(host, facetName, community);
				updated.add(uo);
			}
		}		
		// files are up to date so we can update manager
		srv.updateFacets();
		return updated;
	}
}
