package com.bluexml.side.framework.facetmap.alfrescoConnector;

import java.util.Properties;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;


public class Updater {

	
	public static  int update(String host,String mapId) throws Exception {
		//Update code
		Project ant = new Project();

		//add a listener to see ant's log
		org.apache.tools.ant.DefaultLogger log = new org.apache.tools.ant.DefaultLogger();  
		log.setOutputPrintStream(System.out);
		log.setMessageOutputLevel(Project.MSG_INFO);

		ant.addBuildListener(log);



		Helper help = new Helper();
		Properties p = help.getProperties(mapId);




		//building ant script

		ant.init();
		help.setProperties(ant,p);
		ant.setProperty("ant.host",host);

		ProjectHelper.configureProject(ant, help.getBuildFile());
		ant.executeTarget("main"); 
		return 0;
	}
}
