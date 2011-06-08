package com.bluexml.side.clazz.edit.ui.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer.ASK_USER;

public class InitializeViewModel implements IObjectActionDelegate {

	private IStructuredSelection _selection;

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IFile classModel = (IFile) _selection.getFirstElement();
		try {
			ClassPackage cp = InitializeModels.openModel(classModel);

			InitializerRegister initilizerList = InitializerRegister.getDefaultInitializerRegister(classModel, cp, ASK_USER.ASK);

			for (ModelInitializer initializer : initilizerList.getViewInitializer().values()) {
				initializer.initialize();
			}

			classModel.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}

	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		//Nothing
	}

}
