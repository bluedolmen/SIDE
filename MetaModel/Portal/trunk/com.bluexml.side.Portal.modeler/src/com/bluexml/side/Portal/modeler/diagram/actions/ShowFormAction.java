package com.bluexml.side.Portal.modeler.diagram.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.Portal.modeler.PortalImageRegistry;
import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.diagram.edit.PortletInternalEditPart;
import com.bluexml.side.Portal.modeler.editor.ModelerContextMenuProvider;
import com.bluexml.side.Util.ecore.modeler.AbstractModelerOpenLinkedObject;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.presentation.FormEditor;
import com.bluexml.side.portal.PortletInternal;

public class ShowFormAction extends AbstractModelerOpenLinkedObject {

	static public String ID = "com.bluexml.side.Portal.modeler.actions.showForm"; //$NON-NLS-1$

	public ShowFormAction(IWorkbenchPart part) {
		super(part, "form", FormCollection.class);
	}

	@Override
	protected void init() {
		setId(ID);
		setText(PortalPlugin.Messages.getString("ShowFormAction.5")); //$NON-NLS-1$
		setImageDescriptor(PortalImageRegistry.getImageDescriptor("FORMMODEL")); //$NON-NLS-1$
	}

	@Override
	protected List<EObject> searchEObject() {
		List<EObject> result = new ArrayList<EObject>();
		List<EObject> roots = searchRootElements();
		for (EObject wfc_ : roots) {
			FormCollection wfc = (FormCollection) wfc_;
			FormCollection form = ((PortletInternal) selectedObject).getForm();
			if (compare(wfc, form)) {
				result.add(wfc);
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
				FormCollection foundedFc = (FormCollection) eObject;

				TreeViewer treeViewer = (TreeViewer) editor.getViewer();
				TreeItem item = treeViewer.getTree().getItem(0);
				item.setExpanded(true);
				treeViewer.refresh();
				FormCollection wfc = (FormCollection) item.getItem(0).getData();

				if (compare(wfc, foundedFc)) {
					l.add(wfc);
				}

			}
			editor.setSelectionToViewer(l);
		}
	}

	@Override
	protected void setMessages() {
		message1 = PortalPlugin.Messages.getString("ShowFormAction.1");
		message2 = PortalPlugin.Messages.getString("ShowFormAction.2", project.getName());
	}

	@Override
	protected boolean calculateEnabled() {
		return ModelerContextMenuProvider.checkAllElements(selection, PortletInternalEditPart.class) && ((PortletInternal) selectedObject).getForm() != null;
	}

	/**
	 * @param wfc
	 * @param form
	 * @return
	 */
	private boolean compare(FormCollection wfc, FormCollection form) {
		return wfc.eClass().getName().equals(form.eClass().getName()) && wfc.getFullName().equals(form.getFullName());
	}

}
