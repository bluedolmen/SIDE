package com.bluexml.side.Class.modeler.diagram.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.dialogs.ConfirmationDialog;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.diagram.commands.delete.DeleteLinkClassAspectCommand;
import com.bluexml.side.Class.modeler.diagram.edit.AspectEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.ClazzEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.hasAspectEditPart;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.util.libs.ui.UIUtils;

public class DeleteLinkClassAspectAction extends WorkbenchPartAction implements ISelectionChangedListener {
	public static String ID = "Unlink Aspect";

	/**
	 * The selected EditPart object
	 */
	private ISelection selection;

	/**
	 * @param part
	 */
	public DeleteLinkClassAspectAction(IWorkbenchPart part) {
		super(part);
		//setImageDescriptor(OblPlugin.getImageDescriptor("icons/actions/add.gif"));
	}

	protected void init() {
		setId(ID);
		setText("Delete From Model");
		// load Eclipse icon
		ImageDescriptor img = UIUtils.getImage("org.topcased.modeler", "/icons/deleteFromModel.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		setImageDescriptor(img);
	}

	public void run() {
		Modeler modeler = (Modeler)getWorkbenchPart();
		ConfirmationDialog dialog = new ConfirmationDialog(ClazzPlugin.getActiveWorkbenchShell(), "Delete From Model", "Are you sure you want to delete these model elements ?", modeler.getPreferenceStore(), "deleteModelActionConfirm");
        int result = dialog.open();
        if(result != 0) {
        	return;
        }
        
		StructuredSelection ss = (StructuredSelection) this.selection;
		for (Object o : ss.toList()) {
			if (o instanceof hasAspectEditPart) {
				//Get edit part and graph element
				hasAspectEditPart editPart = (hasAspectEditPart) o;
				GraphEdge eo = (GraphEdge) editPart.getModel();

				//Get source and target edit part
				ClazzEditPart cep = (ClazzEditPart) editPart.getSource();
				AspectEditPart aep = (AspectEditPart) editPart.getTarget();

				//Get source and target model element
				Clazz c = (Clazz) Utils.getElement((GraphElement) cep.getModel());
				Aspect a = (Aspect) Utils.getElement((GraphElement) aep.getModel());

				DeleteLinkClassAspectCommand dlcac = new DeleteLinkClassAspectCommand(editPart, c, a, eo);
				editPart.getViewer().getEditDomain().getCommandStack().execute(dlcac);
			}
		}
	}

	/**
	 * Determine if the action must appear in the context menu
	 * 
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		return true;
	}

	/**
	 * Sets the selected EditPart and refreshes the enabled state of this
	 * action.
	 * 
	 * @param event
	 * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		this.selection = event.getSelection();
	}

}
