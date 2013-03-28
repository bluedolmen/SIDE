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

import com.bluexml.side.util.libs.IFileHelper;

public abstract class AbstractPackager {
	protected String workingdir;
	protected IFolder IworkingDir; // generated folder
	protected String technoVPath;
	protected IFolder ItechnoVPath;
	
	

	public AbstractPackager(IFolder folder, IFolder ItechnoVPath) {
		this.IworkingDir = folder;
		this.workingdir = IFileHelper.convertIRessourceToSystemString(folder);
		this.ItechnoVPath = ItechnoVPath;
		this.technoVPath = IFileHelper.convertIRessourceToSystemString(ItechnoVPath);
	}

	abstract public IFile buildPackage() throws Exception;

	abstract protected String getPackageFileName();

	protected String getPackagePath() {
		// get technoV from extension point tree
		return getTechnoPath() + File.separator + getPackageFileName();
	}

	protected String getTechnoPath() {
		return technoVPath;
	}

	public String getWorkingdir() {
		return workingdir;
	}

	protected File getPackageFile() {
		System.out.println("AbstractPackager.getPackageFile()");
		System.out.println(getTechnoPath());
		File container = new File(getTechnoPath());
		container.mkdirs();
		return new File(getPackagePath());
	}

	protected IFile getPackageIFile() {
		return ItechnoVPath.getFile(getPackageFileName());
//		return IFileHelper.getIFile(this.IworkingDir.toString().replaceFirst("[^/]*/", "/") + getPackagePath());
	}

	protected File getWorkingFolder() {
		return new File(getWorkingdir());
	}

	abstract protected File getFolderToPackage();

}
