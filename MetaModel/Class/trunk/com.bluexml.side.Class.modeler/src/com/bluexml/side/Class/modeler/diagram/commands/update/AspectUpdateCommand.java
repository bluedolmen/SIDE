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

import com.bluexml.side.Class.modeler.diagram.dialogs.AspectEditDialog;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.impl.CommonFactoryImpl;

public class AspectUpdateCommand extends Command {

	/** The association currently updated */
	private Aspect aspect;

	/** Map containing new association data */
	private Map newAssociationData;
	
	/**
	 * Create a command for updating parameters on a given operation
	 * 
	 * @param assoc
	 *            the association to update
	 * @param prop
	 *            Map containing association data
	 */
	public AspectUpdateCommand(Aspect aspect, Map data) {
		this.aspect = aspect;
		this.newAssociationData = data;
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
		aspect.setName((String) newAssociationData
				.get(AspectEditDialog.ASPECT_NAME));
		
		aspect.setTitle((String) newAssociationData
				.get(AspectEditDialog.ASPECT_TITLE));
		
		aspect.setDescription((String) newAssociationData
				.get(AspectEditDialog.ASPECT_DESCRIPTION));		
		
		Comment layout = null;

		for (Object o : aspect.getComments()) {
			if (o instanceof Comment) {
				Comment c = (Comment) o;
				if (isStereotyped(c, "layout")) {
					layout = c;
				}
			}
		}

		String _layout = (String) newAssociationData
				.get(AspectEditDialog.ASPECT_LAYOUT);

		if (layout == null) {
			if (_layout.length() > 0) {
				layout = CommonFactoryImpl.init().createComment();
				layout.setValue(_layout);
				aspect.getComments().add(layout);
				Stereotype s = searchStereotype("layout");
				
				if (s == null) {
					s = CommonFactoryImpl.init().createStereotype();
					s.setName("layout");
					((ClassPackage) aspect.eContainer()).getStereotypeSet().add(s);
				}
				layout.getStereotypes().add(s);
			}
		} else {
			if (_layout.length() > 0) {
				layout.setValue(_layout);
			} else {
				//We delete the comments
				aspect.getComments().remove(layout);
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
		Collection reachableStereotypes = ItemPropertyDescriptor
				.getReachableObjectsOfType(aspect, CommonPackage.eINSTANCE
						.getStereotype());
		Stereotype result = null;
		
		for (Object  o : reachableStereotypes) {
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
