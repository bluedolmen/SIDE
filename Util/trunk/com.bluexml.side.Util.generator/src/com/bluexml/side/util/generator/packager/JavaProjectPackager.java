/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.util.generator.packager;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.bluexml.side.util.libs.zip.ZipManager;

public class JavaProjectPackager extends AbstractPackager {

	static String techVersion = "javaProjects";
	String projectName;
	String modelID;

	public JavaProjectPackager(IFolder folder, IFolder technoV, String modelID, String projectName) {
		super(folder, technoV);
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
