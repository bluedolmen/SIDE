package com.bluexml.side.deployer.xforms.chiba;

import java.io.File;

import com.bluexml.side.util.deployer.war.WarDeployer;

public class ChibaDeployer extends WarDeployer {

	public ChibaDeployer() {
		super("com.bluexml.side.Deployer.xforms.clean", null, "xforms", "com.bluexml.side.Deployer.xforms.webappName");
	}

	public boolean check() {
		return true;
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// TODO Auto-generated method stub

	}

}
