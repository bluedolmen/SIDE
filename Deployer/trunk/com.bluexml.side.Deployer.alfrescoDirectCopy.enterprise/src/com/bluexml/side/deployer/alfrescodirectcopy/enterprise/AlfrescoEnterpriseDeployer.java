package com.bluexml.side.deployer.alfrescodirectcopy.enterprise;

import com.bluexml.side.deployer.alfresco.directcopy.AlfrescoDeployer;

public class AlfrescoEnterpriseDeployer extends AlfrescoDeployer {

	public AlfrescoEnterpriseDeployer() {
		super();
		this.cleanKey = "com.bluexml.side.deployerDirect.alfresco.enterprise.clean";
		this.logChangesKey = "";
	}


	public boolean check() {
		return true;
	}

}
