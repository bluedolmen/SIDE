package com.bluexml.side.deployer.report;

import java.io.File;

import com.bluexml.side.util.deployer.war.DirectWebAppsDeployer;

public class BirtReportDirectDeployer extends DirectWebAppsDeployer {

	public BirtReportDirectDeployer() {
		super("com.bluexml.side.Deployer.birtReport.clean", "birt", "com.bluexml.side.Deployer.birtReport.webappName", "zip");
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
