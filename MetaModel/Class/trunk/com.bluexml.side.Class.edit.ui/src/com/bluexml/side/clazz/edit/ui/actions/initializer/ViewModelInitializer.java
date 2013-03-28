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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.util.metaModel.validate.OCLextension.OCLEvaluator;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.edit.ui.utils.InitView;

public class ViewModelInitializer extends ModelInitializer {

	private static final String GROUPED_VIEWS_DOC_DETAILS = "groupedViewsDocDetails";
	private static final String GROUPED_VIEWS_DOC_LIB = "groupedViewsDocLib";
	private static final String GROUPED_VIEWS_DATAGRID = "datagrid";
	private static final String VIEW_EDITOR_ID = ModelInitializationUtils.getExtensionForEditorId("com.bluexml.side.view.presentation.ViewEditorID"); //$NON-NLS-1$

	public ViewModelInitializer(IFile classModel, ClassPackage root, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, root, VIEW_EDITOR_ID, "view", register, ask, formModelFileName); //$NON-NLS-1$

	}

	@Override
	protected void headLessInitialize() throws Exception {
		headLessCreateComposedView(GROUPED_VIEWS_DOC_LIB);
		headLessCreateComposedView(GROUPED_VIEWS_DOC_DETAILS);
		EList<Clazz> eval = getDataListItemExtensions();
		if (eval.size() > 0) {
			headLessCreateComposedView(GROUPED_VIEWS_DATAGRID, eval);
		}
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) {
		CompoundCommand cc = new CompoundCommand();
		EList<Clazz> allClasses = ((ClassPackage) root).getAllClasses();
		createComposedView(editingDomain, cc, GROUPED_VIEWS_DOC_LIB, allClasses);
		createComposedView(editingDomain, cc, GROUPED_VIEWS_DOC_DETAILS, allClasses);
		try {
			EList<Clazz> eval = getDataListItemExtensions();
			if (eval.size() > 0) {
				createComposedView(editingDomain, cc, GROUPED_VIEWS_DATAGRID, eval);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cc;
	}

	private EList<Clazz> getDataListItemExtensions() throws Exception {
		return OCLEvaluator.eval(root, "self.getAllClasses() -> select(x : Clazz | x.generalizations -> select(y : AbstractClass | y.name = 'dataListItem') -> size()  = 1 )");
	}

	@Override
	protected void createRootObject() {
		ViewCollection createViewCollection = ViewFactory.eINSTANCE.createViewCollection();
		createViewCollection.setName(newModelPath.lastSegment().replace(newModelExt, "")); //$NON-NLS-1$
		newRootObject = createViewCollection;
	}

	private void createComposedView(EditingDomain editingDomain, CompoundCommand cc, String value, EList<Clazz> lc) {
		ComposedView cv = createEmptyComposedView(value);
		for (Clazz c : lc) {
			AbstractViewOf dataList = createView(ViewFactory.eINSTANCE.createDataList(), c);
			cv.getChildren().add(dataList);
			cc.append(InitView.init(dataList, editingDomain));
		}
	}

	private void headLessCreateComposedView(String value) {
		ComposedView cv = createEmptyComposedView(value);
		headlessCreateDataList(cv, ((ClassPackage) root).getAllClasses());
	}

	private ComposedView createEmptyComposedView(String value) {
		ComposedView cv = ViewFactory.eINSTANCE.createComposedView();
		((ViewCollection) newRootObject).getComposedViews().add(cv);
		cv.setName(value);
		return cv;
	}

	private void headLessCreateComposedView(String value, EList<Clazz> lc) {
		ComposedView cv = createEmptyComposedView(value);
		headlessCreateDataList(cv, lc);
	}

	private static void headlessCreateDataList(ComposedView cv, EList<Clazz> lc) {
		for (Clazz c : lc) {
			AbstractViewOf dataList = createView(ViewFactory.eINSTANCE.createDataList(), c);
			cv.getChildren().add(dataList);
			InitView.headlessInit(dataList);
		}
	}

	private static AbstractViewOf createView(AbstractViewOf v, Clazz c) {
		v.setViewOf(c);
		return v;
	}

}
