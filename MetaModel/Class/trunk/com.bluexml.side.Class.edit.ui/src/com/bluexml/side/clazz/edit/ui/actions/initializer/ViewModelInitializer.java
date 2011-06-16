package com.bluexml.side.clazz.edit.ui.actions.initializer;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.Util.ecore.ModelInitializationUtils;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.edit.ui.utils.InitView;

public class ViewModelInitializer extends ModelInitializer {

	private static final String VIEW_EDITOR_ID = ModelInitializationUtils.getExtensionForExtensionId("com.bluexml.side.view.presentation.ViewEditorID"); //$NON-NLS-1$

	public ViewModelInitializer(IFile classModel, ClassPackage root, InitializerRegister register, ASK_USER ask, String formModelFileName) throws IOException {
		super(classModel, root, VIEW_EDITOR_ID, "view", register, ask, formModelFileName); //$NON-NLS-1$
	}

	@Override
	protected Command initialize(EditingDomain editingDomain) {
		ViewCollection createViewCollection = ViewFactory.eINSTANCE.createViewCollection();
		createViewCollection.setName(newModelPath.lastSegment().replace(newModelExt, "")); //$NON-NLS-1$
		newRootObject = createViewCollection;

		CompoundCommand cc = new CompoundCommand();

		createComposedView(editingDomain, cc, "groupedViewsDocLib"); //$NON-NLS-1$
		createComposedView(editingDomain, cc, "groupedViewsDocDetails"); //$NON-NLS-1$

		return cc;
	}

	private void createComposedView(EditingDomain editingDomain, CompoundCommand cc, String value) {
		ComposedView cv = ViewFactory.eINSTANCE.createComposedView();
		((ViewCollection) newRootObject).getComposedViews().add(cv);
		cv.setName(value);

		for (Clazz c : root.getAllClasses()) {
			AbstractViewOf dataList = createView(ViewFactory.eINSTANCE.createDataList(), c);
			cv.getChildren().add(dataList);
			cc.append(InitView.init(dataList, editingDomain));
		}
	}

	private AbstractViewOf createView(AbstractViewOf v, Clazz c) {
		v.setViewOf(c);
		return v;
	}

}
