package com.bluexml.side.integration.m2.zipPackage;

import org.apache.maven.plugin.logging.Log;
import org.apache.tools.ant.DefaultLogger;

public class AntLogger  extends DefaultLogger {
	
	Log logger;
	
	public AntLogger(Log mavenlogger) {
		logger = mavenlogger;
	}

	@Override
	protected void log(String message) {
		super.log(message);
		logger.info(message);
	}
	
}
