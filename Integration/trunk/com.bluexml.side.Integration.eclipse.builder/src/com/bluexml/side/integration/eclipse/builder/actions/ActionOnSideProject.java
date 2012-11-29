package com.bluexml.side.integration.eclipse.builder.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.integration.eclipse.builder.SIDEBuilderUtil;

public abstract class ActionOnSideProject implements IObjectActionDelegate {
	protected ISelection selection;
	protected Shell currentShell;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		currentShell = targetPart.getSite().getShell();
	}

	public final void run(IAction action) {
		System.out.println("action start " + selection);
		if (selection instanceof IStructuredSelection) {
			List<IProject> projects = new ArrayList<IProject>();
			for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
				}
				if (project != null && SIDEBuilderUtil.isSIDEProject(project)) {
					System.out.println("selection resolved as Project :" + project);
					projects.add(project);
				}
			}

			if(projects.size() == 1) {
				run(projects.get(0));
			}
		}

	}

	abstract public void run(IProject projects) ;

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
