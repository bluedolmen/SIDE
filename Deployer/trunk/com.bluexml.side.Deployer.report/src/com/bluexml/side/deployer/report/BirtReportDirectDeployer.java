package com.bluexml.side.deployer.report;

import com.bluexml.side.util.deployer.war.DirectWebAppsDeployer;

public class BirtReportDirectDeployer extends DirectWebAppsDeployer {

	public BirtReportDirectDeployer() {
		super("com.bluexml.side.Deployer.birtReport.clean", "birt", "com.bluexml.side.Deployer.birtReport.webappName");
	}

}
