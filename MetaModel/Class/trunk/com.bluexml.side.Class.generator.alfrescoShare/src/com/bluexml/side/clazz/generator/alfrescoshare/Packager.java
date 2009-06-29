package com.bluexml.side.clazz.generator.alfrescoshare;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.zip.ZipManager;

public class Packager {
	private String workingdir;
	private IFolder IworkingDir; // generated folder
	private Properties moduleProperties;
	private String technoV;

	public Packager(IFolder folder, Properties moduleProperties, String technoV) {
		this.IworkingDir = folder;
		this.workingdir = IFileHelper.convertIRessourceToSystemString(folder);
		this.moduleProperties = moduleProperties;
		this.technoV = technoV;
	}

	public IFile buildPackage(List<IFile> generatedFiles, boolean doClean) throws Exception {
		if (generatedFiles == null) {
			// must use all generated files
			generatedFiles = IFileHelper.getAllFiles(IworkingDir);
		}

		File ampFile = getPackageFile();
		ampFile.createNewFile();
		ZipManager.zip(getFolderToPackage(), ampFile, false);
		if (doClean) {
			FileHelper.deleteFile(getWorkingFolder());
		}
		IFile ampIFile = getPackageIFile();
		return ampIFile;
	}

	public String getWorkingdir() {
		return workingdir;
	}

	private String getPackagePath() {
		// get technoV from extension point tree
		return getTechnoPath() + File.separator + "module." + moduleProperties.getProperty("module.id") + ".zip";
	}

	private String getTechnoPath() {
		return File.separator + ".." + File.separator + ".." + File.separator + technoV;
	}

	private File getPackageFile() {
		File container = new File(getWorkingdir() + getTechnoPath());
		container.mkdirs();
		return new File(getWorkingdir() + getPackagePath());
	}

	private IFile getPackageIFile() {
		return IFileHelper.getIFile(this.IworkingDir.toString().replaceFirst("[^/]*/", "/") + getPackagePath());
	}

	private File getWorkingFolder() {
		return new File(getWorkingdir());
	}

	private File getFolderToPackage() {
		return new File(getWorkingdir() + File.separator + "webapps" + File.separator + "share");
	}

}
