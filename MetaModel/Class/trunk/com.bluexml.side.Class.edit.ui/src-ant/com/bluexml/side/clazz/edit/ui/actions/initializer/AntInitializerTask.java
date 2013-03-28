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
package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.libs.IFileHelper;

public class AntInitializerTask extends Task {
	File tomcatHome;
	String model;

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	String alfrescoVersion;

	/**
	 * @return the tomcatHome
	 */
	public File getTomcatHome() {
		return tomcatHome;
	}

	/**
	 * @param tomcatHome
	 *            the tomcatHome to set
	 */
	public void setTomcatHome(File tomcatHome) {
		this.tomcatHome = tomcatHome;
	}

	/**
	 * @return the alfrescoVersion
	 */
	public String getAlfrescoVersion() {
		return alfrescoVersion;
	}

	/**
	 * @param alfrescoVersion
	 *            the alfrescoVersion to set
	 */
	public void setAlfrescoVersion(String alfrescoVersion) {
		this.alfrescoVersion = alfrescoVersion;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		if (!tomcatHome.exists() || tomcatHome.isFile()) {
			throw new BuildException("Bad tomcat home, please to give the absolute path");
		}
		try {
			IFile classModelIfile = IFileHelper.getIFile(model);
			InitializerRegister initializerRegister = InitializerRegister.getInitializerRegisterFromClassModel(classModelIfile, tomcatHome.getAbsolutePath(), alfrescoVersion);
			initializerRegister.initialize();
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

}
