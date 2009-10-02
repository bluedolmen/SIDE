package com.bluexml.side.util.generator.packager;

import java.io.File;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.libs.zip.ZipManager;

public class WarPatchPackager extends AbstractPackager {
	protected String webappName;

	public WarPatchPackager(IFolder folder, Properties moduleProperties, String technoV, String webappName) {
		super(folder, moduleProperties, technoV);
		this.webappName = webappName;
	}

	public IFile buildPackage() throws Exception {
		File packageFile = getPackageFile();
		packageFile.createNewFile();
		ZipManager.zip(getFolderToPackage(), packageFile, false);
//		if (doClean) {
//			FileHelper.deleteFile(getWorkingFolder());
//		}
		IFile packageIFile = getPackageIFile();
		return packageIFile;
	}

	@Override
	protected String getPackageFileName() {
		return "module." + moduleProperties.getProperty("module.id") + ".zip";
	}

	protected File getFolderToPackage() {
		return new File(getWorkingdir() + File.separator + "webapps" + File.separator + webappName);
	}
}
