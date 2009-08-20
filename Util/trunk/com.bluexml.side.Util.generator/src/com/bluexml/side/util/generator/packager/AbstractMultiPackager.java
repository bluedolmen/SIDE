package com.bluexml.side.util.generator.packager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractMultiPackager {
	protected Map<String, AbstractPackager> packagers;
	protected Map<String, IFile> packageFiles;
	protected String workingdir;
	protected IFolder IworkingDir; // generated folder
	protected Properties moduleProperties;
	protected String technoV;

	public AbstractMultiPackager(IFolder folder, Properties moduleProperties, String technoV) {
		this.IworkingDir = folder;
		this.workingdir = IFileHelper.convertIRessourceToSystemString(folder);
		this.moduleProperties = moduleProperties;
		this.technoV = technoV;
	}

	public Map<String, IFile> buildPackages(boolean doClean) throws Exception {
		Map<String, IFile> packageFiles_ = new HashMap<String, IFile>();
		for (Map.Entry<String, AbstractPackager> p : packagers.entrySet()) {
			if (p.getValue().getFolderToPackage().exists()) {
				IFile pp = p.getValue().buildPackage(false);
				packageFiles_.put(p.getKey(), pp);
			} else {
				// INFO : packager skipped
				if (p.getValue().getPackageFile().exists()) {
					// delete empty package if exist
					p.getValue().getPackageFile().delete();
				}
			}
		}
		if (doClean) {
			FileHelper.deleteFile(getWorkingFolder());
		}
		return packageFiles_;
	}

	protected Map<String, IFile> getPackageFiles() {
		return packageFiles;
	}
	
	public String getWorkingdir() {
		return workingdir;
	}
	
	protected File getWorkingFolder() {
		return new File(getWorkingdir());
	}

}
