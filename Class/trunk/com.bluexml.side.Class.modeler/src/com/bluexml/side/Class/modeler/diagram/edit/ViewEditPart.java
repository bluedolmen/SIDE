/*******************************************************************************
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.CdEditPolicyConstants;
import com.bluexml.side.Class.modeler.diagram.commands.ViewRestoreConnectionCommand;
import com.bluexml.side.Class.modeler.diagram.commands.update.ViewUpdateCommand;
import com.bluexml.side.Class.modeler.diagram.dialogs.ViewEditDialog;
import com.bluexml.side.Class.modeler.diagram.dialogs.view.ViewContainerType;
import com.bluexml.side.Class.modeler.diagram.figures.ViewFigure;
import com.bluexml.side.Class.modeler.diagram.policies.hasViewEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.utils.AssociationHelper;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.View;
import com.bluexml.side.clazz.ViewItem;

/**
 * The View object controller
 * 
 * @generated
 */
public class ViewEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public ViewEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 * 
	 * @generated
	 */
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(CdEditPolicyConstants.HASVIEW_EDITPOLICY, new hasViewEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy() {
			@Override
			protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request) {
				return new ViewRestoreConnectionCommand(getHost());
			}
		});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_BACKGROUND_COLOR_EDITPOLICY, null);
		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY, null);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	@Override
	protected IFigure createFigure() {

		return new ViewFigure();
	}

	@Override
	public void refresh() {
		super.refresh();
		FontData[] fData = Display.getDefault().getSystemFont().getFontData();
		fData[0].setStyle(SWT.BOLD);
		JFaceResources.getFontRegistry().put("BoldFont", fData);

		ComposedLabel l = (ComposedLabel) getLabel();
		View v = (View) Utils.getElement(getGraphNode());
		if (v.getName() != null) {
			l.setPrefix(v.getName());
		} else {
			l.setPrefix("NONE");
		}

		String t = "";
		for (Object o : v.getAttributes()) {
			if (o instanceof ViewItem) {
				ViewItem vi = (ViewItem) o;
				String viewItemName = "";
				String containerName = null;
				String attributeName = null;

				Attribute attr = vi.getAttribute();
				if (attr != null) {
					attributeName = attr.getName();

					EObject container = attr.eContainer();
					if (container instanceof Aspect) {
						containerName = ((Aspect) container).getName();
					}
					if (container instanceof Clazz) {
						containerName = ((Clazz) container).getName();
					}
				}
				
				Association association = vi.getAssociation();
				if (association != null) {
					ViewContainerType containertype = ViewContainerType.ASSOCIATION_TARGET;
					ViewContainerType asso_classCT = ViewContainerType.ASSOCIATION_CLASS;
					if (association != null && association.getSource().equals(association.getDestination())) {
						containertype = ViewContainerType.ASSOCIATION_RECUR;
						asso_classCT = ViewContainerType.ASSOCIATION_CLASS_RECUR;
					}
					ViewContainerType viewContainerType;
					if (association != null && association.getAssociationsClass().indexOf(vi.getClasse()) != -1) {
						// attribute from associated classe
						viewContainerType = asso_classCT;
					} else {
						viewContainerType = containertype;
					}
					viewItemName = AssociationHelper.getViewItemContainerName(vi.getClasse(), vi.getAssociation(), viewContainerType, vi.getRole());
				} else {
					if (containerName != null) {
						viewItemName += containerName;
					}
				}
				if (attributeName != null) {
					viewItemName += "@" + attributeName;
				}
				
				t += viewItemName + "\n";
			}
		}
		l.setFont(JFaceResources.getFontRegistry().get("BoldFont"));
		l.setMain(t);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			View view = (View) Utils.getElement(getGraphNode());

			ViewEditDialog dlg = new ViewEditDialog(view, ModelerPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				ViewUpdateCommand command = new ViewUpdateCommand(view, dlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);

				refresh();
			}
		} else {
			super.performRequest(request);
		}
	}
}