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
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.presentation.ViewEditor;

public class ShowViewAction extends AbstractModelerOpenLinkedObject {

	static public String ID = "com.bluexml.side.Portal.modeler.actions.showView"; //$NON-NLS-1$

	public ShowViewAction(IWorkbenchPart part) {
		super(part, "view", ViewCollection.class);
	}

	@Override
	protected void init() {
		setId(ID);
		setText(PortalPlugin.Messages.getString("ShowViewAction.5")); //$NON-NLS-1$
		setImageDescriptor(PortalImageRegistry.getImageDescriptor("VIEWMODEL")); //$NON-NLS-1$
	}

	@Override
	protected boolean calculateEnabled() {
		return ModelerContextMenuProvider.checkAllElements(selection, PortletInternalEditPart.class) && ((PortletInternal) selectedObject).getView() != null;
	}

	@Override
	protected void setMessages() {
		message1 = PortalPlugin.Messages.getString("ShowViewAction.1");
		message2 = PortalPlugin.Messages.getString("ShowViewAction.2", project.getName());
	}

	@Override
	protected List<EObject> searchEObject() {
		List<EObject> item2open = new ArrayList<EObject>();
		if (selectedObject instanceof PortletInternal) {
			// ok
			PortletInternal portletInternal = (PortletInternal) selectedObject;
			List<EObject> roots = searchRootElements();

			for (EObject eObject : roots) {
				ViewCollection viewCollection = (ViewCollection) eObject;
				for (AbstractView fc : viewCollection.getAllViewsAndSubViews()) {
					if (compareView(fc, portletInternal.getView())) {
						item2open.add(fc);
					}
				}
			}
		}

		return item2open;
	}

	@Override
	protected void selectEObject(IEditorPart editorPart, List<EObject> elements) {
		ArrayList<EObject> l = new ArrayList<EObject>();
		if (editorPart instanceof ViewEditor) {
			ViewEditor editor = (ViewEditor) editorPart;
			for (EObject eObject : elements) {
				AbstractView view = (AbstractView) eObject;
				TreeViewer treeViewer = (TreeViewer) editor.getViewer();
				TreeItem item = treeViewer.getTree().getItem(0);
				item.setExpanded(true);
				treeViewer.refresh();
				ViewCollection wfc = (ViewCollection) item.getItem(0).getData();
				for (AbstractView fc : wfc.getAllViewsAndSubViews()) {
					if (fc instanceof AbstractView) {
						AbstractView fw = (AbstractView) fc;
						if (compareView(view, fw)) {
							l.add(fw);
						}
					}
				}
			}
			editor.setSelectionToViewer(l);
		}
	}

	/**
	 * @param selected
	 * @param toCompare
	 * @return
	 */
	private boolean compareView(AbstractView selected, AbstractView toCompare) {
		String toCompareName = toCompare.getName();
		String selectedName = selected.getName();
		return (toCompareName != null) && (selectedName != null) && toCompareName.equals(selectedName);
	}

}
