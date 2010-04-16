package com.bluexml.side.util.generator.packager;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.libs.zip.ZipManager;

public class JavaProjectPackager extends AbstractPackager {

	static String techVersion = "javaProjects";
	String projectName;
	String modelID;

	public JavaProjectPackager(IFolder folder, String technoV, String modelID, String projectName) {
		super(folder, technoV + File.separator + techVersion);
		this.projectName = projectName;
		this.modelID = modelID;
	}

	public IFile buildPackage() throws Exception {
		File packageFile = getPackageFile();
		packageFile.createNewFile();
		ZipManager.zip(getFolderToPackage(), packageFile, false);
		// if (doClean) {
		// FileHelper.deleteFile(getWorkingFolder());
		// }
		IFile packageIFile = getPackageIFile();
		return packageIFile;
	}

	@Override
	protected String getPackageFileName() {
		return "eclipseProject." + modelID + "." + projectName + ".zip";
	}

	protected File getFolderToPackage() {
		return new File(getWorkingdir() + File.separator + techVersion + File.separator + projectName);
	}

}
