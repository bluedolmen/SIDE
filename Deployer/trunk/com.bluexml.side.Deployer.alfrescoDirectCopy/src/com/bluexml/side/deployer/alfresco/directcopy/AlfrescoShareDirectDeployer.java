package com.bluexml.side.deployer.alfresco.directcopy;

import java.io.File;


import com.bluexml.side.util.deployer.war.DirectWebAppsDeployer;

public class AlfrescoShareDirectDeployer extends DirectWebAppsDeployer {

	public AlfrescoShareDirectDeployer() {
		super(null, "share", "deployer.webappName.alfrescoshare", "zip");
	}

	@Override
	public void deploy() throws Exception {
		try {
			super.deploy();
		} catch (Exception e) {
			monitor.addWarningTextAndLog(Activator.Messages.getString("AlfrescoShareDirectDeployer.1"), null);
		}
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}
}
