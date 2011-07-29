package com.bluexml.side.application.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.util.libs.ui.UIUtils;

public class CreateGenerationConfiguration implements IObjectActionDelegate {

	protected static List<String> inUse = new ArrayList<String>();

	protected ISelection selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) selection;
			if (iss.getFirstElement() instanceof IFile) {
				final IFile rwm_model = (IFile) iss.getFirstElement();
				// We open a dialog only if no dialog already open on it
				if (!inUse(rwm_model)) {
					Shell shell = new Shell();

					ApplicationDialog dialog = new ApplicationDialog(shell, rwm_model);
					addFileUnUse(rwm_model);
					dialog.open();
					removeFileUnUse(rwm_model);
				}
			} else {
				UIUtils.showError(Activator.Messages.getString("Erreur.Title.1"), Activator.Messages.getString("Erreur.Msg.1")); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}

	protected boolean inUse(IFile model) {
		return inUse.contains(model.getLocation().toOSString());
	}

	protected void addFileUnUse(IFile model) {
		inUse.add(model.getLocation().toOSString());
	}

	protected void removeFileUnUse(IFile model) {
		inUse.remove(model.getLocation().toOSString());
	}

	public void selectionChanged(IAction action, ISelection _selection) {
		selection = _selection;
	}

}
