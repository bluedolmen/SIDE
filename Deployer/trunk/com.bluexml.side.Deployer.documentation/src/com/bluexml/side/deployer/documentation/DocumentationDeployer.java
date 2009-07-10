package com.bluexml.side.deployer.documentation;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IResource;

import com.bluexml.side.application.StaticConfigurationParameters;
import com.bluexml.side.util.deployer.Deployer;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;

public class DocumentationDeployer extends Deployer {

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		FileUtils.deleteDirectory(fileToDeploy);
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		File src = fileToDeploy;
		// TODO : Get configuration name
		IResource destIFile = IFileHelper.createFolder(getConfigurationParameters().get(StaticConfigurationParameters.GENERATIONOPTIONSLOG_PATH.getLiteral()) + File.separator + getConfigurationParameters().get("configurationName") + File.separator + "doc");
		File dest = IFileHelper.convertIRessourceToFile(destIFile);
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

