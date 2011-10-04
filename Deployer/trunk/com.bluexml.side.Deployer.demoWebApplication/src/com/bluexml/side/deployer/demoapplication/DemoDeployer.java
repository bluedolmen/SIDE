package com.bluexml.side.deployer.demoapplication;

import java.io.File;

import com.bluexml.side.util.deployer.war.WarDeployer;

public class DemoDeployer extends WarDeployer {
	
	public DemoDeployer() {
		super("com.bluexml.side.Deployer.demoWebApplication.clean", "", "side-demo", "demo.webapplication.name");
		
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		
	}

}
