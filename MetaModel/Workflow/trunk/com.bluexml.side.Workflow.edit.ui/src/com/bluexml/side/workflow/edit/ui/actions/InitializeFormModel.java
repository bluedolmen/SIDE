package com.bluexml.side.workflow.edit.ui.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer;

public class InitializeFormModel implements IObjectActionDelegate {

	private ISelection _selection;

	/**
	 * Constructor for Action1.
	 */
	public InitializeFormModel() {
		super();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IStructuredSelection ss = (IStructuredSelection) _selection;
		IFile workflowModel = (IFile) ss.getFirstElement();

		try {
			InitializerRegister reg = InitializerRegister.getFormWorkFlowInitializerRegister(workflowModel, ModelInitializer.ASK_USER.ASK);
			reg.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workflowModel.getParent().refreshLocal(1, new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		_selection = selection;
	}

	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		//Nothing
	}

}
