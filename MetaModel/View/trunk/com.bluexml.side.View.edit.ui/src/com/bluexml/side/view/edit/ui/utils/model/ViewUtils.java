package com.bluexml.side.view.edit.ui.utils.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.DataList;
import com.bluexml.side.view.DataTable;
import com.bluexml.side.view.FacetMap;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldContainer;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.Tree;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;
import com.bluexml.side.view.edit.ui.utils.InternalModification;

public class ViewUtils {
	/**
	 * Return the top view element for the given EObject, or null if no view found.
	 * @param f
	 * @return
	 */
	public static AbstractView getViewForElement(EObject f) {
		AbstractView v = null;
		if (f != null) {
			if (f instanceof AbstractView) {
				v = (AbstractView)f;
			} else {
				v = getViewForElement(f.eContainer());
			}
		}
		return v;
	}
	
	/**
	 * If move to disabled set to true will move deleted field to the disabled list of the view container
	 * @param domain
	 * @param owner
	 * @param collection
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static CompoundCommand createRemoveCommand(EditingDomain domain, EObject owner, Collection collection) {
		CompoundCommand cmd = new CompoundCommand();
		// First we check if we synchronize
		if (InternalModification.getMoveToDisabled()) {
			for (Object o : collection) {
				if (o instanceof FieldContainer) {
					FieldContainer fg = (FieldContainer) EcoreUtil.copy((FieldContainer)o);
					Command createCmd = AddCommand.create(domain, ViewUtils.getViewForElement(owner), ViewPackage.eINSTANCE.getFieldContainer_Disabled(), fg);
					cmd.append(createCmd);
				} else if (o instanceof Field) {
					Field f = (Field) o;
					if (f.getPath().size() == 0) {
						Field fcpy = (Field) EcoreUtil.copy(f);
						Command createCmd = AddCommand.create(domain, ViewUtils.getViewForElement(owner), ViewPackage.eINSTANCE.getFieldContainer_Disabled(), fcpy);
						cmd.append(createCmd);
					}
				}
			}
		}
		return cmd;
	}
	
	/**
	 * Return the command for merging the given cols
	 * @param selectedObject
	 * @return
	 */
	public static Command getCommandForColMerge(List<Col> cols, FieldContainer fg, EditingDomain domain) {
		CompoundCommand cmd = new CompoundCommand();
		if (cols.size() > 0) {
			Col newCol = ViewFactory.eINSTANCE.createCol();
			String newName = "";
			List<FieldElement> childrens = new ArrayList<FieldElement>();
			for (Col c : cols) {
				newName += (newName.length() ==0 ? c.getName() : "+" + c.getName());
				for (FieldElement fe : c.getChildren()) {
					childrens.add((FieldElement)EcoreUtil.copy(fe));
				}
			}
			newCol.setName(newName);
			newCol.getChildren().addAll(childrens);
			// Create the new col :
			cmd.append(AddCommand.create(domain, fg, ViewPackage.eINSTANCE.getFieldContainer_Children(), newCol));
			// Delete all old cols :
			cmd.append(DeleteCommand.create(domain, cols));
		}
		return cmd;
	}

	/**
	 * Return the name as string for a given View
	 * @param av
	 * @return
	 */
	public static String getTypeAsString(AbstractView av) {
		String name = "";
		if (av instanceof DataList) {
			name = "DataList";
		} else if (av instanceof Tree) {
			name = "Tree";
		} else if (av instanceof FacetMap) {
			name = "FacetMap";
		} else if (av instanceof DataTable) {
			name = "DataTable";
		} else if (av instanceof ComposedView) {
			name = "ComposedView";
		}
		return name;
	}
}
