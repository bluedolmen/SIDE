package com.bluexml.side.deployer.alfrescoshare;

import com.bluexml.side.util.deployer.war.WarDeployer;

public class ShareDeployer extends WarDeployer {

	
	public ShareDeployer() {
		this.webappName = "share";
		this.cleanKey = "com.bluexml.side.Application.deployer.alfrescoshare.clean";
		this.logChanges ="com.bluexml.side.Application.deployer.alfrescoshare.logChanges";
	}

	
	@Override
	protected void postProcess(java.io.File fileToDeploy) throws Exception {
		
	}

	public boolean check() {
		return true;
	}

	

}
