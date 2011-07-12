package com.bluexml.side.clazz.edit.ui.actions.initializer.creator;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.Model;
import com.bluexml.side.clazz.edit.ui.actions.initializer.InitializerRegister;

public class ClassModelCreator extends ModelAndDiagramCreator {
	private static final String ClASS_DIARGAM_ID = "com.bluexml.side.Class.modeler.diagram";
	private static final String Class_EDITOR_ID = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.clazz.presentation.ClazzEditorID"); //$NON-NLS-1$
	protected String packages;

	public ClassModelCreator(IProject project, InitializerRegister register, ASK_USER ask, String formModelFileName, String packages) throws IOException {
		super(project, Class_EDITOR_ID, "data", register, ask, formModelFileName, ClASS_DIARGAM_ID);
		this.packages = packages;
	}

	@Override
	protected void createRootObject() {
		Model createModel = ClazzFactory.eINSTANCE.createModel();
		createModel.setName(getModelName());
		newRootObject = createModel;
	}

	@Override
	protected void headLessInitialize() throws Exception {
		// create packages
		String[] split = packages.split("/");
		com.bluexml.side.common.Package parent = (Model) newRootObject;
		for (String string : split) {
			ClassPackage createClassPackage = ClazzFactory.eINSTANCE.createClassPackage();
			createClassPackage.setName(string);
			parent.getPackageSet().add(createClassPackage);
			parent = createClassPackage;
		}
		rootDiagram = parent;
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		CompoundCommand cc = new CompoundCommand();
		headLessInitialize();
		return cc;
	}

}
