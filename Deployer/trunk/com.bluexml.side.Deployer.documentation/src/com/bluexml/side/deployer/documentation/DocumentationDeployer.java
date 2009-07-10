package com.bluexml.side.deployer.documentation;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.libs.FileHelper;

public class DocumentationDeployer extends Deployer {

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		FileUtils.deleteDirectory(fileToDeploy);
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		File src = fileToDeploy;
		File dest = new File(getConfigurationParameters().get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()));
		FileHelper.copyFiles(src, dest, true);
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
		return true;
	}

}
