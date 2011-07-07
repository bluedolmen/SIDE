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
	private static final String FORM_EDITOR_ID = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.form.presentation.formEditorID"); //$NON-NLS-1$

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
