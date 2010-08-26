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

import com.bluexml.side.Class.modeler.diagram.edit.EnumerationEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.dependsEditPart;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.util.libs.ui.UIUtils;


public class DeleteLinkEnumerationDependsAction extends WorkbenchPartAction implements ISelectionChangedListener
{
	public static String ID = "Unlink Depends Of";
	
    /**
     * The selected EditPart object
     */
	private ISelection selection;

    /**
     * @param part
     */
    public DeleteLinkEnumerationDependsAction(IWorkbenchPart part)
    {
        super(part);
        //setImageDescriptor(OblPlugin.getImageDescriptor("icons/actions/add.gif"));
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
    		if (o instanceof dependsEditPart) {
    			//Get edit part and graph element
    			dependsEditPart editPart = (dependsEditPart) o;
				GraphEdge eo = (GraphEdge) editPart.getModel();
				
				//Get source and target edit part
				EnumerationEditPart e1ep = (EnumerationEditPart) editPart.getSource();
				//EnumerationEditPart e2p = (EnumerationEditPart) editPart.getTarget();
				
				//Get source and target model element
				Enumeration e1 = (Enumeration) Utils.getElement((GraphElement) e1ep.getModel());
				//Enumeration e2 = (Enumeration) Utils.getElement((GraphElement) e2p.getModel());
				
				//Remove view from class
				e1.setDepends(null);		
				
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
