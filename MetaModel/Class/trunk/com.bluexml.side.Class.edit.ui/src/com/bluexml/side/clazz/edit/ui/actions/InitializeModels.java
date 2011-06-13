package com.bluexml.side.clazz.edit.ui.actions;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.Messages;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelInitializer.ASK_USER;
import com.bluexml.side.util.libs.ui.UIUtils;

public class InitializeModels implements IObjectActionDelegate {

	private IStructuredSelection _selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	public void run(IAction action) {
		IFile classModel = (IFile) _selection.getFirstElement();
		try {
			ClassPackage cp = openModel(classModel);

			InitializerRegister initilizerList = InitializerRegister.getDefaultInitializerRegister(classModel, cp, ASK_USER.OVERRIDE);

			// search for existing files
			boolean exists = false;

			for (ModelInitializer initializer : initilizerList.getViewInitializer().values()) {
				IPath path = initializer.getNewModelPath();
				exists |= ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path).exists();
			}
			for (ModelInitializer initializer : initilizerList.getFormInitializer().values()) {
				IPath path = initializer.getNewModelPath();
				exists |= ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path).exists();
			}
			for (ModelInitializer initializer : initilizerList.getPortalInitializer().values()) {
				IPath path = initializer.getNewModelPath();
				exists |= ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path).exists();
			}

			boolean override = false;
			if (exists) {
				override = UIUtils.showConfirmation(Messages.InitializeModels_0, Messages.InitializeModels_1);
			}

			if (!exists || override) {
				for (ModelInitializer initializer : initilizerList.getViewInitializer().values()) {
					initializer.initialize();
				}
				for (ModelInitializer initializer : initilizerList.getFormInitializer().values()) {
					initializer.initialize();
				}
				for (ModelInitializer initializer : initilizerList.getPortalInitializer().values()) {
					initializer.initialize();
				}

			}
			classModel.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}

	public static ClassPackage openModel(IFile classModel) throws IOException {
		EList<?> l = ModelInitializationUtils.openModel(classModel);
		return (ClassPackage) l.get(0);
	}

}
