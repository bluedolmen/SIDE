package com.bluexml.side.workflow.edit.ui.actions;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.presentation.FormEditor;
import com.bluexml.side.form.workflow.utils.WorkflowInitialization;
import com.bluexml.side.workflow.Process;

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

		String formModelFileName = workflowModel.getFullPath().lastSegment();
		formModelFileName = formModelFileName.replaceAll(".workflow", ".form");
		IPath formModelPath = workflowModel.getRawLocation()
				.removeLastSegments(1).append(formModelFileName);

		if (formModelPath.toFile().exists())
			MessageDialog.openError(null, "File already existing !",
					"The file "
							+ formModelFileName
									.replaceAll(".workflow", ".form")
							+ " already exists.");
		else {
			try {
				Process p = openWorkflowModel(workflowModel);
				WorkflowFormCollection wfc = FormFactory.eINSTANCE
						.createWorkflowFormCollection();
				wfc.setLinked_process(p);

				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot workspaceRoot = workspace.getRoot();
				IFile formModelFile = (IFile) workspaceRoot
						.getFileForLocation(formModelPath);

				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IEditorDescriptor desc = PlatformUI.getWorkbench()
						.getEditorRegistry().getDefaultEditor(
								formModelPath.toFile().getName());
				IEditorPart editorPart = page.openEditor(new FileEditorInput(
						formModelFile), desc.getId());
				FormEditor editor = (FormEditor) editorPart;
				editor.getEditingDomain().getCommandStack().execute(
						WorkflowInitialization.initialize(wfc, editor
								.getEditingDomain()));

				page.closeEditor(editor, false);
				saveFormModel(formModelPath.toFile(), wfc);
				page.openEditor(new FileEditorInput(
						formModelFile), desc.getId());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					workflowModel.getParent().refreshLocal(1,
							new NullProgressMonitor());
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void saveFormModel(File file, WorkflowFormCollection wfc) throws IOException {
		ModelInitializationUtils.saveModel(file, (EObject) wfc);
	}

	private Process openWorkflowModel(IFile workflowModel) throws IOException {
		EList<?> l = ModelInitializationUtils.openModel(workflowModel);
		return (Process) l.get(0);
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
