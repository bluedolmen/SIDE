package com.bluexml.side.deployer.alfresco.directcopy;

import java.io.File;

import com.bluexml.side.util.deployer.AbstractMultiDeployer;

public class AlfrescoDeployer extends AbstractMultiDeployer {

	public AlfrescoDeployer() {
		this.cleanKey = "com.bluexml.side.deployer.alfresco.clean";
		this.logChanges = "com.bluexml.side.deployer.alfresco.logChanges";

		AlfrescoAmpDirectDeployer ampDep = new AlfrescoAmpDirectDeployer();
		ampDep.setWebappName("alfresco");
		addDeployer(ampDep);

		AlfrescoShareDirectDeployer shareDep = new AlfrescoShareDirectDeployer();
		shareDep.setWebappName("share");
		addDeployer(shareDep);
	}
	
	@Override
	protected void clean(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean check() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
