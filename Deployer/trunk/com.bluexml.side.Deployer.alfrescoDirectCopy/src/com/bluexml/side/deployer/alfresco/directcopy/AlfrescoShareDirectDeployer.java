package com.bluexml.side.deployer.alfresco.directcopy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.bluexml.side.util.deployer.war.DirectWebAppsDeployer;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public class AlfrescoShareDirectDeployer extends DirectWebAppsDeployer {

	public AlfrescoShareDirectDeployer() {
		this.webappName = "share";
		packageExt = "zip";
		tzh = new TrueZipHelper(packageExt);
	}

	

	@Override
	public Map<String, File> createMapper(File packageDirectory) {
		File packageFolder = getDeployedWebbAppFolder();

		Map<String, File> mapper = new HashMap<String, File>();
		// all files just copied in same directory organization, so filter is on
		// packageDirectory
		addToMap(mapper, packageDirectory.getAbsolutePath(), packageFolder, "/");
		return mapper;
	}
}
