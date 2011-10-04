package com.bluexml.side.deployer.alfrescoDirectCopy.enterprise333;

import com.bluexml.side.deployer.alfresco.directcopy.AlfrescoDeployer;

public class AlfrescoEnterpriseDirectCoppy extends AlfrescoDeployer {

	public AlfrescoEnterpriseDirectCoppy() {
		super();
		this.cleanKey = "com.bluexml.side.Deployer.alfrescoDirectCopy.enterprise333.clean";
		this.logChangesKey = "";
	}


	public boolean check() {
		return true;
	}
}
