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

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Class.modeler.diagram.dialogs.ViewDataStructure;
import com.bluexml.side.Class.modeler.diagram.dialogs.ViewEditDialog;
import com.bluexml.side.Class.modeler.diagram.dialogs.view.ViewAttribute;
import com.bluexml.side.clazz.View;

public class ViewUpdateCommand extends Command {

	/** The association currently updated */
	private View view;

	/** Map containing new association data */
	private Map<String,Object> newAssociationData;
	
	/**
	 * Create a command for updating parameters on a given operation
	 * 
	 * @param assoc
	 *            the association to update
	 * @param prop
	 *            Map containing association data
	 */
	public ViewUpdateCommand(View view, Map<String,Object> data) {
		this.view = view;
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
	@SuppressWarnings("unchecked")
	public void redo() {
		// Update the class properties
		view.setName((String) newAssociationData
				.get(ViewEditDialog.VIEW_NAME));
		
		view.setDescription((String) newAssociationData
				.get(ViewEditDialog.VIEW_DESCRIPTION));
		
		view.getAttributes().clear();
		ViewDataStructure data = (ViewDataStructure) newAssociationData.get(ViewEditDialog.VIEW_ATTRIBUTES);
		Collection<ViewAttribute> allAttributes = data.getData();
		for (ViewAttribute ao : allAttributes) {
			view.getAttributes().add(ao.toViewItem());
		}
	}
}
