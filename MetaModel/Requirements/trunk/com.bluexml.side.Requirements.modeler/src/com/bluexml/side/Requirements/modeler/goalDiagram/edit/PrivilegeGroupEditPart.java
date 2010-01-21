/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.edit;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.RequirementsPlugin;
import com.bluexml.side.Requirements.modeler.dialogs.PrivilegeDialog;
import com.bluexml.side.Requirements.modeler.goalDiagram.commands.update.PrivilegeNullUpdateCommand;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.decoration.ImageDecoration;
import com.bluexml.side.Requirements.modeler.goalDiagram.figures.CommentFigure;
import com.bluexml.side.Requirements.modeler.goalDiagram.figures.PrivilegeGroupFigure;
import com.bluexml.side.Requirements.modeler.goalDiagram.preferences.ReqDiagramPreferenceConstants;
import com.bluexml.side.requirements.BasicElement;
import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.Privilege;
import com.bluexml.side.requirements.PrivilegeGroup;
import com.bluexml.side.requirements.PrivilegeNature;

/**
 * PrivilegeGroup controller
 * 
 * @generated
 */
public class PrivilegeGroupEditPart extends EMFGraphEdgeEditPart {

	/**
	 * Constructor
	 * 
	 * @param model
	 *            the graph object
	 * @generated
	 */
	public PrivilegeGroupEditPart(GraphEdge model) {
		super(model);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FONT_EDITPOLICY,
				null);

	}

	/**
	 * @return the Figure
	 * @generated
	 */
	protected IFigure createFigure() {
		PrivilegeGroupFigure connection = new PrivilegeGroupFigure();

		return connection;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
	 * 
	 * @generated
	 */
	protected String getPreferenceDefaultRouter() {
		return getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_ROUTER);
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
	 * 
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String preferenceForeground = getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_FOREGROUND_COLOR);
		if (preferenceForeground.length() != 0) {
			return Utils.getColor(preferenceForeground);
		}
		return null;

	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultFont()
	 * 
	 * @generated
	 */
	protected Font getPreferenceDefaultFont() {
		String preferenceFont = getPreferenceStore().getString(
				ReqDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}

	@Override
	public void performRequest(Request request) {
		PrivilegeGroup g = (PrivilegeGroup) Utils.getElement(getGraphEdge());
		Modeler editor = ((ModelerGraphicalViewer) getViewer()).getModelerEditor();

		if (request.getType() == RequestConstants.REQ_OPEN) {
			if (g.getEntryPoint() != null) {
				PrivilegeDialog dialog = new PrivilegeDialog(ModelerPlugin
						.getActiveWorkbenchShell(), g,editor);
				if (dialog.open() == Window.OK) {
					PrivilegeNullUpdateCommand command = new PrivilegeNullUpdateCommand();
					getViewer().getEditDomain().getCommandStack().execute(
							command);
					refresh();
				}
			} else
				MessageDialog.openError(null, "No entry point!",
						"No entry point has been defined ! Check your model !");
		} else {
			super.performRequest(request);
		}
	}

	@Override
	protected void refreshEdgeObjects() {
		super.refreshEdgeObjects();

		PrivilegeGroup pGroup = (PrivilegeGroup) Utils
				.getElement(getGraphEdge());

		Map<BasicElement, Set<Privilege>> s = filterPrivileges(pGroup
				.getPrivileges());
		List<BasicElement> keys = sortPrivileges(s.keySet());

		String value = "";
		char nl = '\r';
		for (BasicElement elt : keys) {
			if (elt != null) {
				value += nl;
				String label = elt.getName();
				if (!(elt instanceof Entity))
					value += "   ";
				value += label + " (";

				Set<Privilege> ps = s.get(elt);
				boolean[] rules = {false, false, false, false};
				for (Privilege p : ps) {
					if (p.getCategory().equals(PrivilegeNature.CREATE))
						rules[0] = true;
					if (p.getCategory().equals(PrivilegeNature.READ))
						rules[1] = true;
					if (p.getCategory().equals(PrivilegeNature.UPDATE))
						rules[2] = true;
					if (p.getCategory().equals(PrivilegeNature.DELETE))
						rules[3] = true;
				}
				
				int begin,end;
				if (elt instanceof Entity) {
					begin = 0;
					end = 3;
				} else {
					begin = 1;
					end = 2;
				}
				
				for (int i=begin;i<=end;++i) {
					if (rules[i]) {
						switch (i) {
						case 0:
							value += "C";
							break;
						case 1:
							value += "R";
							break;
						case 2:
							value += "U";
							break;
						case 3:
							value += "D";
							break;
						default:
							break;
						}
					}
				}
				value+=")";
			}
		}

		PrivilegeGroupFigure fig = (PrivilegeGroupFigure) getFigure();
		CommentFigure lbl = (CommentFigure) fig.getmiddleNameEdgeObjectFigure();
		lbl.setText(value);

		//Set decoration

		URL url = null;
		Image image;
		try {
			url = new URL(RequirementsPlugin.getDefault().getDescriptor()
					.getInstallURL(), "icons/StrategyDecoration_Entity.png");
			image = ImageDescriptor.createFromURL(url).createImage();
			((PolylineConnection) figure)
					.setTargetDecoration(new ImageDecoration(image));

			url = new URL(RequirementsPlugin.getDefault().getDescriptor()
					.getInstallURL(), "icons/StrategyDecoration_Goal.png");
			image = ImageDescriptor.createFromURL(url).createImage();
			((PolylineConnection) figure)
					.setSourceDecoration(new ImageDecoration(image));
		} catch (MalformedURLException e) {
		}
	}

	private List<BasicElement> sortPrivileges(Set<BasicElement> set) {
		List<BasicElement> result = new ArrayList<BasicElement>();

		// List all entities
		for (BasicElement elt : set) {
			if (elt instanceof Entity) {
				result.add(elt);
			}
		}

		//List all attributes
		int i = 0;
		List<BasicElement> tmpList = new ArrayList<BasicElement>(result);
		for (BasicElement entity : tmpList) {
			i++;
			for (BasicElement elt : set) {
				if (elt != null && elt.eContainer() != null
						&& elt.eContainer().equals(entity)) {
					result.add(i, elt);
					i++;
				}
			}
		}

		for (BasicElement elt : set) {
			if (!result.contains(elt)) {
				result.add(elt);
			}
		}

		return result;
	}

	// Classify all privileges by model elements
	private Map<BasicElement, Set<Privilege>> filterPrivileges(
			EList<Privilege> privileges) {
		Map<BasicElement, Set<Privilege>> result = new HashMap<BasicElement, Set<Privilege>>();
		for (Privilege p : privileges) {
			if (result.containsKey(p.getElement())) {
				Set<Privilege> s = result.get(p.getElement());
				s.add(p);
			} else {
				Set<Privilege> s = new HashSet<Privilege>();
				s.add(p);
				result.put(p.getElement(), s);
			}
		}

		return result;
	}

}