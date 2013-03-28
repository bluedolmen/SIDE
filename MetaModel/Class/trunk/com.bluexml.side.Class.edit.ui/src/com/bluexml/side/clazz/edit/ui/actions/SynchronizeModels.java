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
package com.bluexml.side.clazz.edit.ui.actions;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.application.Application;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;
import com.bluexml.side.form.actions.SynchronizeFormJob;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.eclipse.AbstractIFileJob;
import com.bluexml.side.util.libs.eclipse.AbstractObjectActionDelegate;

public class SynchronizeModels extends AbstractObjectActionDelegate {

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}

	@Override
	protected WorkspaceJob getJob() {
		return new AbstractIFileJob("", getApplication()) {

			@Override
			public void execute() throws Exception {
				// get application
				List<String> modelsPathFromApplication = ApplicationUtil.getModelsPathFromApplication((Application) openModel(getApplication()));
				for (String string : modelsPathFromApplication) {
					IFile iFile = IFileHelper.getIFile(string);

					if (iFile.getFileExtension().equals("form")) {
						// synchonizeForms
						SynchronizeFormJob sfj = new SynchronizeFormJob(iFile);
						sfj.execute();
					} else if (iFile.getFileExtension().equals("view")) {

					}

				}

			}
		};
	}

	protected IFile getApplication() {
		return getFirstSelection();
	}

}
