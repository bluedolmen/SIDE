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
package com.bluexml.side.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.runtime.internal.adaptor.ContextFinder;

import com.bluexml.side.application.Application;
import com.bluexml.side.integration.standalone.ApplicationModelJob;
import com.bluexml.side.integration.standalone.GenerateModelHelper;
import com.bluexml.side.util.libs.eclipse.EclipseUtils;

public abstract class SideApplicationTask extends Task {
	private ClassLoader oldContext = null;
	private static final String WORKSPACE = "workspace://";
	private File applicationFile = null;

	public void setApplicationFile(String applicationFile) {
		if (applicationFile.startsWith(WORKSPACE)) {
			applicationFile = applicationFile.substring(WORKSPACE.length());
			this.applicationFile = PropertiesUtil.getFileFromWorkspace(applicationFile);
		} else {
			// resolve as file
			this.applicationFile = new File(applicationFile);
		}
	}

	public File getApplicationFile() {
		return applicationFile;
	}

	public void init() {
		this.oldContext = Thread.currentThread().getContextClassLoader();
		if (!(oldContext instanceof ContextFinder)) {
			// replace with Eclipse ContextFinderClassLoader this is required to have access to all the Eclipse classPath
			Thread.currentThread().setContextClassLoader(EclipseUtils.getContextFinderClassLoader());
		}
	}

	private void end() {
		// restore classLoader
		Thread.currentThread().setContextClassLoader(this.oldContext);
	}

	public final void execute() {
		// TODO : remove this workaround, in RCP init method is not called ...
		init();
		executeImp();
		end();
	}

	abstract void executeImp();
		
	protected final Application getInitApplicationModel() {
		Application applicationModel = GenerateModelHelper.getApplication(getApplicationFile(), true);
		
		if (applicationModel == null) {
			throw new BuildException("Cannot get the application model referenced by '" + getApplicationFile().getAbsolutePath() + "'");
		}
		
		return applicationModel;
	}

	
}
