package com.bluexml.side.deployer.alfrescoshare;

import com.bluexml.side.util.deployer.war.WarDeployer;

public class ShareDeployer extends WarDeployer {

	public ShareDeployer() {
		this.webappName = "share";
		this.cleanKey = "com.bluexml.side.Application.deployer.alfrescoshare.clean";
	}

	
	@Override
	protected void postProcess(java.io.File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub
	}

	public boolean check() {
		return true;
	}

	

}
