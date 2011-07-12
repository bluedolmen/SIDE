package com.bluexml.side.clazz.edit.ui.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.application.Application;
import com.bluexml.side.application.Model;
import com.bluexml.side.application.ModelElement;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;

public class InitializeModels implements IObjectActionDelegate {

	private IStructuredSelection _selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	/**
	 * action that create/replace/update Models from dt and workflow models
	 */
	public void run(IAction action) {
		IFile applicationModel = (IFile) _selection.getFirstElement();
		try {
			Application app = (Application) openModel(applicationModel);

			// search for  dt models and workflow models
			List<Model> dts = new ArrayList<Model>();
			List<Model> wks = new ArrayList<Model>();

			EList<ModelElement> elements = app.getElements();
			for (ModelElement modelElement : elements) {
				if (modelElement instanceof Model) {
					Model model = (Model) modelElement;
					String replaceFirst = model.getFile().replaceFirst("/.*(\\..*)", "$1");
					if (replaceFirst.equals(".dt")) {
						dts.add(model);
					} else if (replaceFirst.equals(".workflow")) {
						wks.add(model);
					}
				}
			}
			InitializerRegister initializerRegister = InitializerRegister.getInitializerRegister(dts, wks);
			initializerRegister.initialize();

			applicationModel.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}

	public static EObject openModel(IFile classModel) throws IOException {
		EList<EObject> l = ModelInitializationUtils.openModel(classModel);
		return l.get(0);
	}

}
