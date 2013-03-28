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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.resources.IFile;

import com.bluexml.side.util.libs.IFileHelper;

public class InitializeFormWorkflow extends Task {
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

	/*
	 * (non-Javadoc)
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		IFile ifile = IFileHelper.getIFile(model);
		try {
			InitializerRegister formWorkFlowInitializerRegister = InitializerRegister.getFormWorkFlowInitializerRegister(ifile, ModelInitializer.ASK_USER.OVERRIDE);
			formWorkFlowInitializerRegister.initialize();
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.apache.tools.ant.Task#init()
	 */
	@Override
	public void init() throws BuildException {
		super.init();
		log("init is never called !!!");
	}
	
	

}
