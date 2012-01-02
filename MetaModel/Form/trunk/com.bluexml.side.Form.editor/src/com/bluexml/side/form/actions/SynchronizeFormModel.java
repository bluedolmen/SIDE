package com.bluexml.side.form.actions;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.util.libs.eclipse.AbstractObjectActionDelegate;

public class SynchronizeFormModel extends AbstractObjectActionDelegate {

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}

	@Override
	protected WorkspaceJob getJob() {
		return new SynchronizeFormJob(getFirstSelection());
	}

}
