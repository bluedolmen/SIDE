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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Class.modeler.diagram.dialogs.EnumerationEditDialog;
import com.bluexml.side.Class.modeler.diagram.dialogs.EnumerationLiteralDataStructure;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;

/**
 * Class that create a command in order to update operation parameters <br>
 * creation : 8 avr. 2005
 */
public class EnumerationUpdateCommand extends Command {
	/** Current operation */
	private Enumeration enumeration;

	/** Old values */
	private String oldName;
	
	private boolean oldIsDynamic;

	private EnumerationLiteralDataStructure oldInputTypes;

	/** New values */
	private String name;
	
	private boolean isDynamic;

	private EnumerationLiteralDataStructure inputTypes;

	/**
	 * Create a command to update parameters, name, etc... on a given operation
	 * 
	 * @param op
	 *            the Operation to update
	 * @param data
	 *            the map containing the new values
	 */
	public EnumerationUpdateCommand(Enumeration op, Map data) {
		enumeration = op;

		// Store new data
		name = (String) data.get(EnumerationEditDialog.ENUMERATION_NAME);
//		isDynamic = (Boolean) data.get(EnumerationEditDialog.ENUMERATION_ISDYNAMIC);
		
		inputTypes = (EnumerationLiteralDataStructure) data
				.get(EnumerationEditDialog.ENUMERATION_LITERALS);
	}

	/**
	 * Get the old values
	 */
	protected void getOldValues() {
		// Store old data
		oldName = enumeration.getName();
		oldIsDynamic = enumeration.getDynamic();
		oldInputTypes = new EnumerationLiteralDataStructure(enumeration);
	}

	/**
	 * Set the values on the Operation
	 */
	protected void setValues() {
		// Perform update for main data
		enumeration.setName(name);
		enumeration.setDynamic(isDynamic);
		// Perform update for input parameters
		List newParameters = new ArrayList();
		Iterator iterator = inputTypes.getData().iterator();

		while (iterator.hasNext()) {
			Object object = iterator.next();
			String displayName = inputTypes.getDisplayName(object);
			EnumerationLiteral literal = ClazzFactory.eINSTANCE
					.createEnumerationLiteral();
			literal.setName(displayName);
			newParameters.add(literal);
		}
		enumeration.getLiterals().clear();
		enumeration.getLiterals().addAll(newParameters);

	}

	/**
	 * Switch the old and new values
	 */
	protected void switchValues() {
		// Reverse old and new value for name
		String tempName = name;
		name = oldName;
		oldName = tempName;

		// Reverse old and new values for inputType
		EnumerationLiteralDataStructure tempInputTypes = inputTypes;
		inputTypes = oldInputTypes;
		oldInputTypes = tempInputTypes;

	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		getOldValues();
		setValues();
	}

	/**
	 * Set the new values
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		switchValues();
		setValues();
	}

	/**
	 * Set the old values
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		switchValues();
		setValues();
	}

}
