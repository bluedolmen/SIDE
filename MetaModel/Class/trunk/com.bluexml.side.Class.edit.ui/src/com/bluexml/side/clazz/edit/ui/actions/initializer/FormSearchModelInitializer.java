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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormSearch;
import com.bluexml.side.form.SearchFormCollection;
import com.bluexml.side.form.search.utils.SearchInitialization;

public class FormSearchModelInitializer extends ModelInitializer {
	private static final String FORM_EDITOR_ID = ModelInitializationUtils.getExtensionForEditorId("com.bluexml.side.form.presentation.formEditorID"); //$NON-NLS-1$

	public FormSearchModelInitializer(IFile classModel, EObject root, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, root, FORM_EDITOR_ID, "form", register, ask, formModelFileName);
	}

	@Override
	protected void headLessInitialize() throws Exception {
		for (Clazz c : ((ClassPackage) root).getAllClasses()) {
			FormSearch form = createFormClass(c);
			SearchInitialization.initializeFormSearch(form);
		}
	}

	private FormSearch createFormClass(Clazz c) {
		FormSearch fc = FormFactory.eINSTANCE.createFormSearch();
		fc.setReal_class(c);
		// set parent before initialize because initialize need it
		((SearchFormCollection) newRootObject).getForms().add(fc);
		return fc;
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) throws Exception {
		CompoundCommand cc = new CompoundCommand();

		for (Clazz c : ((ClassPackage) root).getAllClasses()) {
			FormSearch form = createFormClass(c);
			cc.append(SearchInitialization.initializeFormSearch(form, editingDomain));
		}
		return cc;
	}

	@Override
	protected void createRootObject() {
		SearchFormCollection createSearchFormCollection = FormFactory.eINSTANCE.createSearchFormCollection();
		createSearchFormCollection.setName(getModelName() + "-search");
		newRootObject = createSearchFormCollection;
	}

}
