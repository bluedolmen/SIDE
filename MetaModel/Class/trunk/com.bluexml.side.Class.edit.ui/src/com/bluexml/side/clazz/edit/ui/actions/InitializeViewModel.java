package com.bluexml.side.clazz.edit.ui.actions;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ViewModelInitializer;
import com.bluexml.side.clazz.edit.ui.actions.initializer.ModelCreator.ASK_USER;
import com.bluexml.side.util.libs.eclipse.AbstractIFileJob;

public class InitializeViewModel implements IObjectActionDelegate {

	private IStructuredSelection _selection;

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IFile classModel = (IFile) _selection.getFirstElement();
		Job j = new Job(classModel);
		j.schedule();
	}

	class Job extends AbstractIFileJob {
		public Job(IFile iFile) {
			super("Initialize View Model", iFile);
		}

		@Override
		protected void execute() throws Exception {
			ClassPackage cp = openModel(iFile);
			InitializerRegister initilizerList = InitializerRegister.getDefaultInitializerRegister(iFile, cp, ASK_USER.ASK);
			for (ModelCreator initializer : initilizerList.getInitializers(ViewModelInitializer.class).values()) {
				initializer.initialize();
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		_selection = (IStructuredSelection) selection;
	}

	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		//Nothing
	}

	private static ClassPackage openModel(IFile classModel) throws IOException {
		EList<?> l = ModelInitializationUtils.openModel(classModel);
		return (ClassPackage) l.get(0);
	}
}
