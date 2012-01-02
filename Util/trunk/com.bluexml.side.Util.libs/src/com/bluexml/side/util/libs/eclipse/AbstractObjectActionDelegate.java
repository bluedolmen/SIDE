package com.bluexml.side.util.libs.eclipse;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;

import com.bluexml.side.util.libs.ecore.EResourceUtils;

public abstract class AbstractObjectActionDelegate implements IObjectActionDelegate {

	protected IStructuredSelection _selection;

	public void run(IAction action) {
		try {

			WorkspaceJob job = getJob();
			job.schedule();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}

	protected abstract WorkspaceJob getJob();

	protected static EObject openModel(IFile classModel) throws IOException {
		EList<?> l = EResourceUtils.openModel(classModel);
		return (EObject) l.get(0);
	}

	public IFile getFirstSelection() {
		return (IFile) _selection.getFirstElement();
	}

}
