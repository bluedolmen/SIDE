package com.bluexml.side.clazz.edit.ui.actions;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.actions.initializer.FormModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.FormSearchModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator.ASK_USER;
import com.bluexml.side.util.libs.eclipse.AbstractIFileJob;
import com.bluexml.side.util.libs.ecore.EResourceUtils;

public class InitializeFormClassModel implements IObjectActionDelegate {

	private IStructuredSelection _selection;

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IFile classModel = (IFile) _selection.getFirstElement();
		try {
			Job j = new Job(classModel);
			j.schedule();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}

	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		//Nothing
	}

	static ClassPackage openModel(IFile classModel) throws IOException {
		EList<?> l = EResourceUtils.openModel(classModel);
		return (ClassPackage) l.get(0);
	}

	class Job extends AbstractIFileJob {

		public Job(IFile iFile) {
			super("Initialize Models From dt model", iFile);
		}

		@Override
		public void execute() throws Exception {
			ClassPackage cp = (ClassPackage) EResourceUtils.openModel(iFile).get(0);

			InitializerRegister initilizerList = InitializerRegister.getDefaultInitializerRegister(iFile, cp, ASK_USER.ASK);
			ArrayList<ModelCreator> initializers = new ArrayList<ModelCreator>();
			initializers.addAll(initilizerList.getInitializers(FormModelInitializer.class).values());
			initializers.addAll(initilizerList.getInitializers(FormSearchModelInitializer.class).values());
			
			for (ModelCreator initializer : initializers) {
				initializer.initialize();
			}
		}

	}
}
