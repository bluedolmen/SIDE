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

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.workflow.utils.WorkflowInitialization;

public class FormWorkflowModelInitializer extends ModelInitializer {
	private static final String FORM_EDITOR_ID = ModelInitializationUtils.getExtensionForEditorId("com.bluexml.side.form.presentation.formEditorID"); //$NON-NLS-1$

	public FormWorkflowModelInitializer(IFile classModel, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, FORM_EDITOR_ID, "form", register, ask, formModelFileName);
	}

	@Override
	protected void headLessInitialize() throws Exception {
		WorkflowInitialization.headLessInitialize((WorkflowFormCollection) newRootObject);
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		return WorkflowInitialization.initialize((WorkflowFormCollection) newRootObject, editingDomain);
	}

	@Override
	protected void createRootObject() {
		newRootObject = FormFactory.eINSTANCE.createWorkflowFormCollection();
		((WorkflowFormCollection) newRootObject).setLinked_process((com.bluexml.side.workflow.Process) root);
	}

}
