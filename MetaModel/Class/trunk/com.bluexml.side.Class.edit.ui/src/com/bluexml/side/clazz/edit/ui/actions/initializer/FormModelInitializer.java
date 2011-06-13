package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.ClassFormCollection;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.clazz.utils.ClassInitialization;

public class FormModelInitializer extends ModelInitializer {
	private static final String FORM_EDITOR_ID = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.form.presentation.formEditorID");

	public FormModelInitializer(IFile classModel, ClassPackage root, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, root, FORM_EDITOR_ID, "form", register, ask, formModelFileName);
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) {
		ClassFormCollection createClassFormCollection = FormFactory.eINSTANCE.createClassFormCollection();
		createClassFormCollection.setName(getModelName());
		newRootObject = createClassFormCollection;
		CompoundCommand cc = new CompoundCommand();
		for (Clazz c : root.getAllClasses()) {
			FormClass fc = FormFactory.eINSTANCE.createFormClass();
			fc.setReal_class(c);
			// set parent before initialize because initialize need it
			((ClassFormCollection) newRootObject).getForms().add(fc);

			Command cmd = ClassInitialization.initializeClass(fc, editingDomain);
			cc.append(cmd);

		}

		return cc;
	}

}
