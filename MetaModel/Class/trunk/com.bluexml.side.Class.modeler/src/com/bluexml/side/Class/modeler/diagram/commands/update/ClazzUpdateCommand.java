/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
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
package com.bluexml.side.Class.modeler.diagram.commands.update;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.gef.commands.Command;

import com.bluexml.side.Class.modeler.diagram.dialogs.ClazzEditDialog;
import com.bluexml.side.Class.modeler.diagram.dialogs.ConstraintsDataStructure;
import com.bluexml.side.Class.modeler.diagram.dialogs.ConstraintsDataStructure.ConstraintObject;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblClassMetaInfo;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.impl.CommonFactoryImpl;

public class ClazzUpdateCommand extends Command {

	/** The association currently updated */
	private Clazz classe;

	/** Map containing new association data */
	private Map newAssociationData;

	private ConstraintsDataStructure metainfo;

	/**
	 * Create a command for updating parameters on a given operation
	 * 
	 * @param assoc
	 *            the association to update
	 * @param prop
	 *            Map containing association data
	 */
	public ClazzUpdateCommand(Clazz classe, Map data) {
		this.classe = classe;
		this.newAssociationData = data;
		this.metainfo = ((ConstraintsDataStructure) data.get(ClazzEditDialog.CLASSE_METAINFO));
	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}

	/**
	 * Set the new values
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		// Update the class properties
		classe.setName((String) newAssociationData.get(ClazzEditDialog.CLASSE_NAME));

		classe.setTitle((String) newAssociationData.get(ClazzEditDialog.CLASSE_TITLE));

		classe.setDescription((String) newAssociationData.get(ClazzEditDialog.CLASSE_DESCRIPTION));

		classe.setDocumentation((String) newAssociationData.get(ClazzEditDialog.CLASSE_DOCUMENTATION));

		classe.setAbstract((Boolean) newAssociationData.get(ClazzEditDialog.CLASSE_ISABSTRACT));

		classe.setDeprecated((Boolean) newAssociationData.get(ClazzEditDialog.CLASSE_ISDEPRECATED));

		Comment layout = null, view = null;

		for (Object o : classe.getComments()) {
			if (o instanceof Comment) {
				Comment c = (Comment) o;
				if (isStereotyped(c, "view")) {
					view = c;
				}
				if (isStereotyped(c, "layout")) {
					layout = c;
				}
			}
		}

		String _view = (String) newAssociationData.get(ClazzEditDialog.CLASSE_VIEW);
		// String _layout = (String)
		// newAssociationData.get(ClazzEditDialog.CLASSE_LAYOUT);

		// if (layout == null) {
		// if (_layout.length() > 0) {
		// layout = CommonFactoryImpl.init().createComment();
		// layout.setValue(_layout);
		// classe.getComments().add(layout);
		// Stereotype s = searchStereotype("layout");
		//
		// if (s == null) {
		// s = CommonFactoryImpl.init().createStereotype();
		// s.setName("layout");
		// ((ClassPackage) classe.eContainer()).getStereotypeSet().add(s);
		// }
		// layout.getStereotypes().add(s);
		// }
		// } else {
		// if (_layout.length() > 0) {
		// layout.setValue(_layout);
		// } else {
		// // We delete the comments
		// classe.getComments().remove(layout);
		// }
		// }

		if (view == null) {
			if (_view != null && _view.length() > 0) {
				view = CommonFactoryImpl.init().createComment();
				view.setValue(_view);
				classe.getComments().add(view);
				Stereotype s = searchStereotype("view");

				if (s == null) {
					s = CommonFactoryImpl.init().createStereotype();
					s.setName("view");
					((ClassPackage) classe.eContainer()).getStereotypeSet().add(s);
				}
				view.getStereotypes().add(s);
			}
		} else {
			if (_view.length() > 0) {
				view.setValue(_view);
			} else {
				// We delete the comments
				classe.getComments().remove(view);
			}
		}

		// Metainfo
		classe.getMetainfo().clear();

		if (metainfo != null) {
			for (Object o : metainfo.getData()) {
				ConstraintObject co = (ConstraintObject) o;
				MetaInfo c = (new OblClassMetaInfo()).getMetaInfo(co.getKey());
				c.setValue(co.getValue());
				c.setConstraintType(null);
				c.setValueSet(null);
				c.setValueType(null);
				classe.getMetainfo().add(c);
			}
		}

	}

	private boolean isStereotyped(Comment c, String stereotype) {
		boolean result = false;

		for (Object obj : c.getStereotypes()) {
			if (obj instanceof Stereotype) {
				Stereotype s = (Stereotype) obj;
				if (s.getName().equalsIgnoreCase(stereotype)) {
					result = true;
				}
			}
		}
		return result;
	}

	private Stereotype searchStereotype(String value) {
		Collection reachableStereotypes = ItemPropertyDescriptor.getReachableObjectsOfType(classe, CommonPackage.eINSTANCE.getStereotype());
		Stereotype result = null;

		for (Object o : reachableStereotypes) {
			if (o instanceof Stereotype) {
				Stereotype s = (Stereotype) o;
				if (s.getName().equalsIgnoreCase(value)) {
					result = s;
				}
			}
		}

		return result;
	}
}
