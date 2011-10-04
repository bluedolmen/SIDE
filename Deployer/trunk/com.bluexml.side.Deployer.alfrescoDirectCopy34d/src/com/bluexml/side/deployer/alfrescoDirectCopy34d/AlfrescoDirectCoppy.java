package com.bluexml.side.deployer.alfrescoDirectCopy34d;

import com.bluexml.side.deployer.alfresco.directcopy.AlfrescoHotDeployer;

public class AlfrescoDirectCoppy extends AlfrescoHotDeployer {

	public AlfrescoDirectCoppy() {
		super();
		this.cleanKey = "com.bluexml.side.Deployer.alfrescoDirectCopy34d.clean";
		this.logChangesKey = "";
	}


	@Override
	public boolean check() {
		return true;
	}
}
