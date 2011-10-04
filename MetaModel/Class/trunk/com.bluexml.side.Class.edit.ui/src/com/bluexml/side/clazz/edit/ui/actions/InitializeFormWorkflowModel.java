package com.bluexml.side.clazz.edit.ui.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer;
import com.bluexml.side.util.libs.eclipse.AbstractIFileJob;

public class InitializeFormWorkflowModel implements IObjectActionDelegate {

	private ISelection _selection;

	/**
	 * Constructor for Action1.
	 */
	public InitializeFormWorkflowModel() {
		super();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IStructuredSelection ss = (IStructuredSelection) _selection;
		IFile workflowModel = (IFile) ss.getFirstElement();
		Job j = new Job(workflowModel);
		j.schedule();
	}

	class Job extends AbstractIFileJob {
		public Job(IFile iFile) {
			super("Initilize Form Workflow model", iFile);
		}

		@Override
		protected void execute() throws Exception {
			InitializerRegister reg = InitializerRegister.getFormWorkFlowInitializerRegister(iFile, ModelInitializer.ASK_USER.ASK);
			reg.initialize();
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
