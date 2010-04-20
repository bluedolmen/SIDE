package com.bluexml.side.util.generator.packager;

import java.io.File;

import org.eclipse.core.resources.IFolder;

public class WarPatchPackager extends ZipPackager {
	protected String webappName;

	public WarPatchPackager(IFolder folder, String moduleId, IFolder technoV, String webappName) {
		super(folder, technoV, "module." + moduleId + ".zip");
		this.webappName = webappName;
	}

	protected File getFolderToPackage() {
		return new File(getWorkingdir() + File.separator + "webapps" + File.separator + webappName);
	}
}
