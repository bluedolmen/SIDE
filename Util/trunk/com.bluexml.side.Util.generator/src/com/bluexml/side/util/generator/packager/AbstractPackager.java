package com.bluexml.side.util.generator.packager;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractPackager {
	protected String workingdir;
	protected IFolder IworkingDir; // generated folder
	protected String technoV;
	

	public AbstractPackager(IFolder folder, String technoV) {
		this.IworkingDir = folder;
		this.workingdir = IFileHelper.convertIRessourceToSystemString(folder);
		this.technoV = technoV;		
	}

	abstract public IFile buildPackage() throws Exception;
	abstract protected String getPackageFileName();
	
	
	protected String getPackagePath() {
		// get technoV from extension point tree
		return getTechnoPath() + File.separator + getPackageFileName();
	}

	protected String getTechnoPath() {
		return File.separator + ".." + File.separator + ".." + File.separator + technoV;
	}

	public String getWorkingdir() {
		return workingdir;
	}

	protected File getPackageFile() {
		File container = new File(getWorkingdir() + getTechnoPath());
		container.mkdirs();
		return new File(getWorkingdir() + getPackagePath());
	}

	protected IFile getPackageIFile() {
		return IFileHelper.getIFile(this.IworkingDir.toString().replaceFirst("[^/]*/", "/") + getPackagePath());
	}

	protected File getWorkingFolder() {
		return new File(getWorkingdir());
	}

	abstract protected File getFolderToPackage();
	
	
}
