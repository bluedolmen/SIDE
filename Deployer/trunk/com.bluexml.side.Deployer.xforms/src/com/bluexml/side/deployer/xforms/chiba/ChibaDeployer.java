package com.bluexml.side.deployer.xforms.chiba;

import java.io.File;

import com.bluexml.side.util.deployer.war.WarDeployer;

public class ChibaDeployer extends WarDeployer {
	
	public ChibaDeployer() {
		super("xforms");
	}

	
	public boolean check() {
		return true;
	}


	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		// nothing to do
		
	}

}
