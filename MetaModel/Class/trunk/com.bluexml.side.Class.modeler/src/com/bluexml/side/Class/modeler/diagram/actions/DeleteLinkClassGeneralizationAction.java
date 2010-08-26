package com.bluexml.side.Class.modeler.diagram.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.topcased.modeler.commands.DeleteGraphElementCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.edit.ClazzEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.GeneralizationEditPart;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.util.libs.ui.UIUtils;

public class DeleteLinkClassGeneralizationAction extends WorkbenchPartAction implements ISelectionChangedListener
{
	public static String ID = "Unlink Generalization";
	
    /**
     * The selected EditPart object
     */
	private ISelection selection;

    /**
     * @param part
     */
    public DeleteLinkClassGeneralizationAction(IWorkbenchPart part)
    {
        super(part);
    }
    
    protected void init()
    {
        setId(ID);
        setText("Delete From Model");
     // load Eclipse icon
		ImageDescriptor img = UIUtils.getImage("org.topcased.modeler", "/icons/deleteFromModel.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		setImageDescriptor(img);
    }

    public void run()
    {
    	StructuredSelection ss = (StructuredSelection) this.selection;
    	for (Object o : ss.toList()) {
    		if (o instanceof GeneralizationEditPart) {
				//Get edit part and graph element
    			GeneralizationEditPart editPart = (GeneralizationEditPart) o;
				GraphEdge eo = (GraphEdge) editPart.getModel();
				
				//Get source and target edit part
				ClazzEditPart sp = (ClazzEditPart) editPart.getSource();
				ClazzEditPart tp = (ClazzEditPart) editPart.getTarget();
				
				//Get source and target model element
				Clazz cs = (Clazz) Utils.getElement((GraphElement) sp.getModel());
				Clazz ct = (Clazz) Utils.getElement((GraphElement) tp.getModel());
				
				//Remove aspect from class
				cs.getGeneralizations().remove(ct);
				
				//Create delete command and execute it
				DeleteGraphElementCommand cmd = new DeleteGraphElementCommand(eo,true);
				editPart.getViewer().getEditDomain().getCommandStack().execute(cmd);
			}
    	}
    }

    /**
     * Determine if the action must appear in the context menu
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled()
    {
        return true;
    }

    /**
     * Sets the selected EditPart and refreshes the enabled state of this action.
     * 
     * @param event
     * 
     * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event)
    {
        this.selection = event.getSelection();
    }

}
