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
			e.printStackTrace();
			String string = Activator.Messages.getString("AlfrescoShareDirectDeployer.1") + "\n";
			string += e.getStackTrace();
			monitor.addWarningTextAndLog(string, null);
		}
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}
}
