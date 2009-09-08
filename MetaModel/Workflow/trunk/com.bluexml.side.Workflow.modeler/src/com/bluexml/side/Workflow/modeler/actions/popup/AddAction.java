package com.bluexml.side.Workflow.modeler.actions.popup;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.topcased.modeler.di.model.GraphConnector;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Workflow.modeler.diagram.commands.ActionRestoreConnectionCommand;
import com.bluexml.side.Workflow.modeler.editor.WorkflowEditor;
import com.bluexml.side.workflow.Action;
import com.bluexml.side.workflow.Script;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.WorkflowFactory;
import com.bluexml.side.workflow.WorkflowPackage;

public class AddAction extends WorkbenchPartAction {

	static public String ID = "com.bluexml.side.Workflow.modeler.actions.addAction"; //$NON-NLS-1$
	static public int nbOfActions = 0;
	private Transition selectedObject;
	private String name;
	private String code;
	private ImageDescriptor icon;

	public AddAction(IWorkbenchPart part, String _name, String _code, ImageDescriptor _icon) {
		super(part);
		name = _name;
		code = _code;
		icon = _icon;

		setText(name); //$NON-NLS-1$
		setImageDescriptor(icon); //$NON-NLS-1$
	}

	@Override
	protected boolean calculateEnabled() {
		if (selectedObject == null) {
			WorkflowEditor editor = (WorkflowEditor) getWorkbenchPart();
			setSelection(editor.getSelection());
		}
			
		return (selectedObject != null);
	}

	@Override
	public void run() {
		Action a = WorkflowFactory.eINSTANCE.createAction();
		a.setJavaClass("org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript");
		Script s = WorkflowFactory.eINSTANCE.createScript();
		s.setExpression(code);
		a.getScript().add(s);
		Command cmd = AddCommand.create(((WorkflowEditor) getWorkbenchPart()).getEditingDomain(), selectedObject, WorkflowPackage.TRANSITION__ACTION, a); 
		((WorkflowEditor) getWorkbenchPart()).getEditingDomain().getCommandStack().execute(cmd);
		
		WorkflowEditor editor = (WorkflowEditor) getWorkbenchPart();
		ICreationUtils factory = editor.getActiveConfiguration().getCreationUtils();
		GraphElement graphElement = factory.createGraphElement(a);
		GraphElement transitionGraphElement = Utils.getGraphElement(editor.getActiveDiagram(), selectedObject);
		if (transitionGraphElement instanceof GraphEdge) {
			GraphEdge edge = (GraphEdge) transitionGraphElement;
			Point position = computePosition(edge.getAnchor());
			graphElement.setPosition(position);

			editor.getActiveDiagram().getContained().add(graphElement);
			EditPart ep = (EditPart) getViewer(editor).getEditPartRegistry().get(graphElement);
			ActionRestoreConnectionCommand restoreCmd = new ActionRestoreConnectionCommand(ep);
			restoreCmd.execute();
		}
		editor.gotoEObject(a);
	}
	
	private GraphicalViewer getViewer(WorkflowEditor editor)
    {
        return (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
    }

	private Point computePosition(EList<GraphConnector> anchor) {
		Point p = new Point();
		int xmin = -1, xmax = -1;
		int ymin = -1, ymax = -1;
		
		for (GraphConnector graphConnector : anchor) {
			Point connectionPoint = graphConnector.getGraphElement().getPosition();
			if (xmin == -1) xmin = connectionPoint.x;
			if (ymin == -1) ymin = connectionPoint.y;
			if (xmax == -1) xmax = connectionPoint.x;
			if (ymax == -1) ymax = connectionPoint.y;
			
			if (connectionPoint.x < xmin) xmin = connectionPoint.x;
			if (connectionPoint.x > xmax) xmax = connectionPoint.x;
			if (connectionPoint.y < ymin) ymin = connectionPoint.y;
			if (connectionPoint.y > ymax) ymax = connectionPoint.y;
		}
		
		p.x = xmin + ((xmax-xmin)/2);
		p.y = ymin + ((ymax-ymin)/2);
		
		return p;
	}

	protected void init() {
		setId(ID+"."+nbOfActions);
		nbOfActions++;
	}

	public void setSelection(ISelection s) {
		if (!(s instanceof IStructuredSelection)) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) s;

		selectedObject = null;
		// Recompute the command according to the current selection
		if (selection.getFirstElement() instanceof EMFGraphEdgeEditPart) {
			EMFGraphEdgeEditPart editPart = (EMFGraphEdgeEditPart) selection
					.getFirstElement();
			if (editPart.getEObject() instanceof Transition) {
				selectedObject = (Transition) editPart.getEObject();
			}
		}
	}

}
