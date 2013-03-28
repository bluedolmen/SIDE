/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
	private static final String FORM_EDITOR_ID = ModelInitializationUtils.getExtensionForEditorId("com.bluexml.side.form.presentation.formEditorID"); //$NON-NLS-1$

	public FormModelInitializer(IFile classModel, ClassPackage root, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, root, FORM_EDITOR_ID, "form", register, ask, formModelFileName); //$NON-NLS-1$
	}

	@Override
	protected void headLessInitialize() throws Exception {
		for (Clazz c : ((ClassPackage) root).getAllClasses()) {
			FormClass fc = createFormClass(c);
			ClassInitialization.headLessInitializeClass(fc);
		}
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) {
		CompoundCommand cc = new CompoundCommand();

		for (Clazz c : ((ClassPackage) root).getAllClasses()) {
			if (!c.isAbstract()) {
				FormClass fc = createFormClass(c);
				Command cmd = ClassInitialization.initializeClass(fc, editingDomain);
				cc.append(cmd);
			}
		}
		return cc;
	}

	private FormClass createFormClass(Clazz c) {
		FormClass fc = FormFactory.eINSTANCE.createFormClass();
		fc.setReal_class(c);
		// set parent before initialize because initialize need it
		((ClassFormCollection) newRootObject).getForms().add(fc);
		return fc;
	}

	@Override
	protected void createRootObject() {
		ClassFormCollection createClassFormCollection = FormFactory.eINSTANCE.createClassFormCollection();
		createClassFormCollection.setName(getModelName());
		newRootObject = createClassFormCollection;
	}

}
