package com.bluexml.side.util.generator.packager;

import java.io.File;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.zip.ZipManager;

public class WarPatchPackager extends AbstractPackager {
	protected String webappName;

	public WarPatchPackager(IFolder folder, Properties moduleProperties, String technoV, String webappName) {
		super(folder, moduleProperties, technoV);
		this.webappName = webappName;
	}

	public IFile buildPackage(boolean doClean) throws Exception {
		File ampFile = getPackageFile();
		ampFile.createNewFile();
		ZipManager.zip(getFolderToPackage(), ampFile, false);
		if (doClean) {
			FileHelper.deleteFile(getWorkingFolder());
		}
		IFile ampIFile = getPackageIFile();
		return ampIFile;
	}

	@Override
	protected String getPackageFileName() {
		return "module." + moduleProperties.getProperty("module.id") + ".zip";
	}

	protected File getFolderToPackage() {
		return new File(getWorkingdir() + File.separator + "webapps" + File.separator + webappName);
	}
}
