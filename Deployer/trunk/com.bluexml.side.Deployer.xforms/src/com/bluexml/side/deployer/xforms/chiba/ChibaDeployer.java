package com.bluexml.side.deployer.xforms.chiba;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import com.bluexml.side.util.deployer.Deployer;

public class ChibaDeployer extends Deployer {
	
	private String TARGET_ARTIFACT = "xforms";;
	private String DESTFOLDER_PARAM_ID = "war.folder";
	private File projectFolder;
	private File warFile;
	
	public ChibaDeployer() {
		super();
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		FileUtils.deleteDirectory(fileToDeploy);
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		projectFolder = new File(fileToDeploy, TARGET_ARTIFACT);
		warFile = new File(projectFolder, "target/" + TARGET_ARTIFACT + ".war");
		
		String folderPath = getGenerationParameters().get(DESTFOLDER_PARAM_ID);
		if (folderPath != null && folderPath.length() > 0) {
			File destFile = new File(folderPath);
			if (destFile.exists()) {
				FileUtils.copyFileToDirectory(warFile, destFile, false);
			} else {
				throw new Exception("Folder " + folderPath + " doesn't exist for Chiba XForms Deployer.");
			}
		} else {
			throw new Exception("No webapp destination folder for Chiba XForms Deployer set.");
		}
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
		// TODO Auto-generated method stub
		return true;
	}

}
