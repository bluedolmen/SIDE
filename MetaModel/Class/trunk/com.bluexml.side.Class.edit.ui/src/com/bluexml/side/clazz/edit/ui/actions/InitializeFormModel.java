package com.bluexml.side.clazz.edit.ui.actions;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
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
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.Model;
import com.bluexml.side.form.ClassFormCollection;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.clazz.utils.ClassInitialization;
import com.bluexml.side.form.presentation.FormEditor;

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
		IFile classModel = (IFile) ss.getFirstElement();

		String formModelFileName = classModel.getFullPath().lastSegment();
		String classExt = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.clazz.presentation.ClazzEditorID");
		String formExt = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.form.presentation.formEditorID");

		formModelFileName = formModelFileName.replaceAll(classExt, formExt);
		IPath formModelPath = classModel.getRawLocation()
				.removeLastSegments(1).append(formModelFileName);
		// Check on file
		if (formModelPath.toFile().exists())
			MessageDialog.openError(null, "File already existing !",
					"The file "
							+ formModelFileName
									.replaceAll(classExt, formExt)
							+ " already exists.");
		else {
			try {
				// Create editor and launch init
				ClassPackage cp = openClassModel(classModel);
				ClassFormCollection cfc = FormFactory.eINSTANCE
						.createClassFormCollection();

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
				// Init :
				Command creationCommand = initFormCollection(cp, cfc, editor.getEditingDomain());
				editor.getEditingDomain().getCommandStack().execute(creationCommand);
				page.closeEditor(editor, false);
				saveFormModel(formModelPath.toFile(), cfc);
				page.openEditor(new FileEditorInput(
						formModelFile), desc.getId());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					classModel.getParent().refreshLocal(1,
							new NullProgressMonitor());
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Command initFormCollection(ClassPackage cp, ClassFormCollection cfc, EditingDomain domain) {
		CompoundCommand cc = new CompoundCommand();
		for (Clazz c : cp.getAllClasses()) {
			FormClass fc = FormFactory.eINSTANCE.createFormClass();
			fc.setReal_class(c);
			Command cmd = ClassInitialization.initializeClass(fc, domain);
			cc.append(cmd);
			cfc.getForms().add(fc);
		}
		return cc;
	}

	private void saveFormModel(File file, ClassFormCollection cfc) throws IOException {
		ModelInitializationUtils.saveModel(file, (EObject) cfc);
	}

	private Model openClassModel(IFile classModel) throws IOException {
		EList<?> l = ModelInitializationUtils.openModel(classModel);
		return (Model) l.get(0);
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
