package com.bluexml.side.clazz.edit.ui.actions;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.edit.ui.Messages;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.util.libs.eclipse.AbstractIFileJob;
import com.bluexml.side.util.libs.ecore.EResourceUtils;
import com.bluexml.side.util.libs.ui.UIUtils;

public class InitializeModels implements IObjectActionDelegate {

	protected IStructuredSelection _selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	/**
	 * action that create/replace/update Models from dt and workflow models
	 */
	public void run(IAction action) {
		IFile applicationModel = getApplication();
		try {
			boolean doWork = UIUtils.showConfirmation(Messages.InitializeModelsAction_0, Messages.InitializeModelsAction_1);
			if (doWork) {

				Job job = new Job(applicationModel);
				job.schedule();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected IFile getApplication() {
		IFile applicationModel = (IFile) _selection.getFirstElement();
		return applicationModel;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}

	public static EObject openModel(IFile classModel) throws IOException {
		EList<EObject> l = EResourceUtils.openModel(classModel);
		return l.get(0);
	}

	class Job extends AbstractIFileJob {
		public Job(IFile applicationModel) {
			super(Messages.InitializeModelsAction_2, applicationModel);
		}

		@Override
		public void execute() throws Exception {
			InitializerRegister initializerRegister = InitializerRegister.getInitializerRegister(iFile);
			initializerRegister.initialize();

		}
	}
}
