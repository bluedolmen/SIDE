package com.bluexml.side.requirements.generator.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.requirements.generator.Activator;
import com.bluexml.side.requirements.generator.TransformModel;

public class ExportSIDEModels implements IObjectActionDelegate {

	private ISelection _selection;
	
	private static String ASM_FILE = "/interpretation/side/transformation/Reqs2SIDE.asm";
	
	/**
	 * Constructor for Action1.
	 */
	public ExportSIDEModels() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (_selection instanceof StructuredSelection) {
			StructuredSelection sSelection = (StructuredSelection) _selection;
			IFile file = (IFile) sSelection.getFirstElement();
			
			TransformModel t = new TransformModel();
			t.setASMFile(ASM_FILE);
			t.addInputModel("IN", "Reqs", file.getRawLocation().toString(), "/com.bluexml.side.Requirements/model/requirements.ecore");
			t.addOutputModel("OUTData", "Data", file.getParent().getFullPath().toString()+"/models/data.dt", "/com.bluexml.side.Class/model/Class.ecore");
			t.addOutputModel("OUTDataForm", "DataForm", file.getParent().getFullPath().toString()+"/models/dataForm.form", "/com.bluexml.side.Form/model/Forms.ecore");
			t.addOutputModel("OUTWfForm", "WorkflowForm", file.getParent().getFullPath().toString()+"/models/workflowForm.form", "/com.bluexml.side.Form/model/Forms.ecore");
			t.addOutputModel("OUTWorkflow", "Workflow", file.getParent().getFullPath().toString()+"/models/allWorkflows.workflow", "/com.bluexml.side.Workflow/model/Workflow.ecore");
			t.setContributor(Activator.getDefault().getBundle().getSymbolicName());
			
			try {
				t.execute();
			} catch (Exception e) {
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

}
