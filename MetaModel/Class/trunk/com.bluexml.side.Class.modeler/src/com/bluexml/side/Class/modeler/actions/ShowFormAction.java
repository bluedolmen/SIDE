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
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.presentation.FormEditor;

public class ShowFormAction extends AbstractModelerOpenLinkedObject {
	static public String ID = "com.bluexml.side.Class.modeler.actions.showForm"; //$NON-NLS-1$

	public ShowFormAction(IWorkbenchPart part) {
		super(part, "form", FormCollection.class);
	}

	@Override
	protected boolean calculateEnabled() {
		ClazzEditor editor = (ClazzEditor) getWorkbenchPart();
		setSelection(editor.getSelection());
		return (selectedObject != null);
	}

	@Override
	protected void init() {
		setId(ID);
		setText(ClazzPlugin.Messages.getString("ShowFormAction.5")); //$NON-NLS-1$
		setImageDescriptor(ClazzImageRegistry.getImageDescriptor("FORMMODEL")); //$NON-NLS-1$
	}

	@Override
	protected List<EObject> searchEObject() {
		Clazz clazz = (Clazz) selectedObject;
		List<EObject> roots = searchRootElements();
		List<EObject> result = new ArrayList<EObject>();
		for (EObject wfc_ : roots) {
			FormCollection wfc = (FormCollection) wfc_;
			for (FormContainer fc : wfc.getForms()) {
				if (fc instanceof ClassReference) {
					ClassReference fw = (ClassReference) fc;

					Clazz c = fw.getReal_class();
					if (c.getFullName().equals(clazz.getFullName())) {
						result.add(fw);
						System.out.println("match ! record :"+fw);
					}
				} else {
					System.err.println("no matches :" + fc);
				}
			}
		}

		return result;
	}

	@Override
	protected void selectEObject(IEditorPart editorPart, List<EObject> elements) {
		ArrayList<EObject> l = new ArrayList<EObject>();
		if (editorPart instanceof FormEditor) {
			FormEditor editor = (FormEditor) editorPart;

			for (EObject eObject : elements) {
				ClassReference foundedFc = (ClassReference) eObject;

				Clazz clazz = foundedFc.getReal_class();
				TreeViewer treeViewer = (TreeViewer) editor.getViewer();
				TreeItem item = treeViewer.getTree().getItem(0);
				item.setExpanded(true);
				treeViewer.refresh();
				FormCollection wfc = (FormCollection) item.getItem(0).getData();

				for (FormContainer fc : wfc.getForms()) {
					if (fc instanceof ClassReference) {
						ClassReference fw = (ClassReference) fc;
						Clazz c = fw.getReal_class();

						if (c.getFullName().equals(clazz.getFullName())) {
							l.add(fw);
						}
					}
				}
			}
			editor.setSelectionToViewer(l);
		}
	}

	@Override
	protected void setMessages() {
		message1 = ClazzPlugin.Messages.getString("ShowFormAction.1");
		message2 = ClazzPlugin.Messages.getString("ShowFormAction.2", project.getName());
	}

}
