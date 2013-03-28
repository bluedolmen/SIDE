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
package com.bluexml.side.form.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.form.ClassFormCollection;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.clazz.utils.SynchronizeWithClass;
import com.bluexml.side.form.workflow.utils.SynchronizeWorkflowFormWithClass;
import com.bluexml.side.util.libs.eclipse.AbstractIFileJob;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class SynchronizeFormJob extends AbstractIFileJob {
	public SynchronizeFormJob(IFile iFile) {
		super("Synchronize Form", iFile);
	}

	public void execute() throws Exception {
		EObject fc = EResourceUtils.openModel(iFile).get(0);
		if (fc instanceof ClassFormCollection) {
			SynchronizeWithClass swc = new SynchronizeWithClass();
			swc.synchronize((FormCollection) fc);
		} else if (fc instanceof WorkflowFormCollection) {
			SynchronizeWorkflowFormWithClass sw = new SynchronizeWorkflowFormWithClass();
			sw.synchronize((WorkflowFormCollection) fc);
		}
		EResourceUtils.saveModel(iFile, fc);
	}
}
