package com.bluexml.side.deployer.alfresco;

import java.io.File;

import com.bluexml.side.util.deployer.war.MultiWarDeployer;

public class AlfrescoDeployer extends MultiWarDeployer {

	
	public AlfrescoDeployer() {
		this.cleanKey = "com.bluexml.side.deployer.alfresco.clean";
		this.logChanges = "com.bluexml.side.deployer.alfresco.logChanges";
		
		AMPDeployer ampDep = new AMPDeployer();
		addWarDeployer("alfresco", ampDep);
		
		ShareDeployer shareDep = new ShareDeployer();
		addWarDeployer("share",shareDep);
		
	}
	
	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean check() {
		return true;
	}

}
