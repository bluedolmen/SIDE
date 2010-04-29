package com.bluexml.side.deployer.alfresco;

import com.bluexml.side.util.deployer.war.Activator;
import com.bluexml.side.util.deployer.war.WarDeployer;

public class ShareDeployer extends WarDeployer {

	public ShareDeployer() {
		super("com.bluexml.side.Application.deployer.alfrescoshare.clean", "com.bluexml.side.Application.deployer.alfrescoshare.logChanges", "share", "deployer.webappName.alfrescoshare");
	}

	@Override
	protected void postProcess(java.io.File fileToDeploy) throws Exception {

	}

	public boolean check() {
		return true;
	}

	@Override
	public void deploy() throws Exception {
		try {
			super.deploy();
		} catch (Exception e) {
			monitor.addWarningTextAndLog(Activator.Messages.getString("ShareDeployer.01"), null);
		}
	}

}
