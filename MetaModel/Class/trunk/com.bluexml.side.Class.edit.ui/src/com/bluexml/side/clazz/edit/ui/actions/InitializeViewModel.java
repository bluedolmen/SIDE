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
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.edit.ui.utils.InitView;
import com.bluexml.side.view.presentation.ViewEditor;

public class InitializeViewModel implements IObjectActionDelegate {

	private ISelection _selection;

	/**
	 * Constructor for Action1.
	 */
	public InitializeViewModel() {
		super();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IStructuredSelection ss = (IStructuredSelection) _selection;
		IFile classModel = (IFile) ss.getFirstElement();

		String viewModelFileName = classModel.getFullPath().lastSegment();
		String classExt = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.clazz.presentation.ClazzEditorID");
		String viewExt = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.view.presentation.ViewEditorID");

		viewModelFileName = viewModelFileName.replaceAll(classExt, viewExt);
		IPath modelPath = classModel.getRawLocation()
				.removeLastSegments(1).append(viewModelFileName);
		// Check on file
		if (modelPath.toFile().exists())
			MessageDialog.openError(null, "File already existing !",
					"The file "
							+ viewModelFileName
									.replaceAll(classExt, viewExt)
							+ " already exists.");
		else {
			try {
				// Create editor and launch init
				ClassPackage cp = openModel(classModel);
				ViewCollection vc = ViewFactory.eINSTANCE.createViewCollection();

				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot workspaceRoot = workspace.getRoot();
				IFile formModelFile = (IFile) workspaceRoot
						.getFileForLocation(modelPath);

				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IEditorDescriptor desc = PlatformUI.getWorkbench()
						.getEditorRegistry().getDefaultEditor(
								modelPath.toFile().getName());
				IEditorPart editorPart = page.openEditor(new FileEditorInput(
						formModelFile), desc.getId());
				ViewEditor editor = (ViewEditor) editorPart;
				// Init :
				Command creationCommand = initCollection(cp, vc, editor.getEditingDomain());
				editor.getEditingDomain().getCommandStack().execute(creationCommand);
				page.closeEditor(editor, false);
				saveModel(modelPath.toFile(), vc);
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

	private Command initCollection(ClassPackage cp, ViewCollection vc, EditingDomain domain) {
		CompoundCommand cc = new CompoundCommand();
		for (Clazz c : cp.getAllClasses()) {
			ComposedView cv = ViewFactory.eINSTANCE.createComposedView();

			// Initialize tree, DataList and DataTable views
			AbstractViewOf tree = createView(ViewFactory.eINSTANCE.createTree(), c);
			cv.getChildren().add(tree);
			cc.append(InitView.init(tree, domain));

			AbstractViewOf dataList = createView(ViewFactory.eINSTANCE.createDataList(), c);
			cv.getChildren().add(dataList);
			cc.append(InitView.init(dataList, domain));

			AbstractViewOf dataTable = createView(ViewFactory.eINSTANCE.createDataTable(), c);
			cv.getChildren().add(dataTable);
			cc.append(InitView.init(dataTable, domain));

			vc.getComposedViews().add(cv);
		}
		return cc;
	}

	private AbstractViewOf createView(AbstractViewOf v, Clazz c) {
		v.setViewOf(c);
		return v;
	}

	private void saveModel(File file, ViewCollection vc) throws IOException {
		ModelInitializationUtils.saveModel(file, (EObject) vc);
	}

	private ClassPackage openModel(IFile classModel) throws IOException {
		EList<?> l = ModelInitializationUtils.openModel(classModel);
		return (ClassPackage) l.get(0);
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
