package com.bluexml.side.deployer.alfresco;

import java.io.File;

import com.bluexml.side.util.deployer.AbstractMultiDeployer;

public class AlfrescoDeployer extends AbstractMultiDeployer {

	public AlfrescoDeployer() {
		this.cleanKey = "com.bluexml.side.deployer.alfresco.clean";
		this.logChanges = "com.bluexml.side.deployer.alfresco.logChanges";

		AMPDeployer ampDep = new AMPDeployer();
		ampDep.setWebappName("alfresco");
		addDeployer(ampDep);

		ShareDeployer shareDep = new ShareDeployer();
		shareDep.setWebappName("share");
		addDeployer(shareDep);

	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean check() {
		return true;
	}
}
