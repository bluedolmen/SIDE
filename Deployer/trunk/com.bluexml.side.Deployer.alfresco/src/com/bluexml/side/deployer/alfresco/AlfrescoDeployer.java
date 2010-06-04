package com.bluexml.side.deployer.alfresco;

import java.io.File;

import com.bluexml.side.util.deployer.AbstractMultiDeployer;

public class AlfrescoDeployer extends AbstractMultiDeployer {

	public AlfrescoDeployer() {
		super("com.bluexml.side.deployer.alfresco.clean", "com.bluexml.side.deployer.alfresco.logChanges");

		AMPDeployer ampDep = new AMPDeployer();
		addDeployer(ampDep);

		ShareDeployer shareDep = new ShareDeployer();
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
	
}
