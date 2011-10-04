package com.bluexml.side.integration.eclipse.builder.actions;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.integration.eclipse.builder.nature.SIDENature;
import com.bluexml.side.integration.eclipse.builder.nature.SIDENatureWithBuilder;
import com.bluexml.side.util.libs.eclipse.ProjectNatureHelper;

public class ToggleNatureAction implements IObjectActionDelegate {

	private ISelection selection;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
				}
				if (project != null) {
					toggleNature(project);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action
	 * .IAction,
	 * org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.
	 * action.IAction,
	 * org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * Toggles sample nature on a project
	 * 
	 * @param project
	 *            to have sample nature added or removed
	 */
	private void toggleNature(IProject project) {
		if (!ProjectNatureHelper.hasNature(project, SIDENature.NATURE_ID) && !ProjectNatureHelper.hasNature(project, SIDENatureWithBuilder.NATURE_ID)) {
			ProjectNatureHelper.addNature(project, SIDENatureWithBuilder.NATURE_ID);
		} else if (ProjectNatureHelper.hasNature(project, SIDENature.NATURE_ID)) {
			ProjectNatureHelper.removeNature(project, SIDENature.NATURE_ID);
			ProjectNatureHelper.addNature(project, SIDENatureWithBuilder.NATURE_ID);
		} else if (ProjectNatureHelper.hasNature(project, SIDENatureWithBuilder.NATURE_ID)) {
			ProjectNatureHelper.removeNature(project, SIDENatureWithBuilder.NATURE_ID);
			ProjectNatureHelper.addNature(project, SIDENature.NATURE_ID);
		}
	}

}
