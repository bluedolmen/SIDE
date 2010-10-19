package com.bluexml.side.Class.modeler.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.Class.modeler.ClazzImageRegistry;
import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.editor.ClazzEditor;
import com.bluexml.side.Util.ecore.modeler.AbstractModelerOpenLinkedObject;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.Container;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.presentation.ViewEditor;

public class ShowViewAction extends AbstractModelerOpenLinkedObject {

	public ShowViewAction(IWorkbenchPart part) {
		super(part, "view", ViewCollection.class);
	}

	static public String ID = "com.bluexml.side.Class.modeler.actions.showView"; //$NON-NLS-1$

	@Override
	protected boolean calculateEnabled() {
		ClazzEditor editor = (ClazzEditor) getWorkbenchPart();
		setSelection(editor.getSelection());
		return (selectedObject != null);
	}

	protected void init() {
		setId(ID);
		setText(ClazzPlugin.Messages.getString("ShowViewAction.5")); //$NON-NLS-1$
		setImageDescriptor(ClazzImageRegistry.getImageDescriptor("VIEWMODEL")); //$NON-NLS-1$
	}

	@Override
	protected List<EObject> searchEObject() {
		Clazz clazz = (Clazz) selectedObject;
		List<EObject> roots = searchRootElements();
		List<EObject> result = new ArrayList<EObject>();
		for (EObject wfc_ : roots) {
			ViewCollection wfc = (ViewCollection) wfc_;
			for (AbstractView fc : wfc.getAllViewsAndSubViews()) {
				if (fc instanceof AbstractViewOf) {
					AbstractViewOf fw = (AbstractViewOf) fc;
					Container co = fw.getViewOf();
					if (co instanceof Clazz) {
						Clazz c = (Clazz) co;
						if (c.getFullName().equals(clazz.getFullName()))
							result.add(fw);
					}
				}
			}
		}
		return result;
	}

	@Override
	protected void selectEObject(IEditorPart editorPart, List<EObject> elements) {
		ArrayList<EObject> l = new ArrayList<EObject>();
		if (editorPart instanceof ViewEditor) {
			ViewEditor editor = (ViewEditor) editorPart;
			for (EObject eObject : elements) {
				AbstractViewOf view = (AbstractViewOf) eObject;

				TreeViewer treeViewer = (TreeViewer) editor.getViewer();
				TreeItem item = treeViewer.getTree().getItem(0);
				item.setExpanded(true);
				treeViewer.refresh();
				ViewCollection wfc = (ViewCollection) item.getItem(0).getData();
				for (AbstractView fc : wfc.getAllViewsAndSubViews()) {
					if (fc instanceof AbstractViewOf) {
						AbstractViewOf fw = (AbstractViewOf) fc;
						if ((view.getName() == null && fw.getName() == null) || (view.getName().equals(fw.getName()) && view.getViewOf().eClass().equals(fw.getViewOf().eClass()))) {
							Container co = fw.getViewOf();
							if (co instanceof Clazz && view.getViewOf() instanceof Clazz) {
								Clazz c = (Clazz) co;
								if (c.getFullName().equals(((Clazz) view.getViewOf()).getFullName())) {
									l.add(fw);
								}
							}
						}
					}
				}
			}
			editor.setSelectionToViewer(l);
		}

	}

	@Override
	protected void setMessages() {
		message1 = ClazzPlugin.Messages.getString("ShowViewAction.1");
		message2 = ClazzPlugin.Messages.getString("ShowViewAction.2", project.getName());

	}

}
