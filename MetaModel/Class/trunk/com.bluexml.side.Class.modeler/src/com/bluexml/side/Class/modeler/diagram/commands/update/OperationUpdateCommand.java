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

import com.bluexml.side.Class.modeler.diagram.dialogs.ConstraintsDataStructure;
import com.bluexml.side.Class.modeler.diagram.dialogs.OperationDataStructure;
import com.bluexml.side.Class.modeler.diagram.dialogs.OperationEditDialog;
import com.bluexml.side.Class.modeler.diagram.dialogs.ConstraintsDataStructure.ConstraintObject;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblOperationMetaInfo;
import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.Operation;
import com.bluexml.side.common.Parameter;
import com.bluexml.side.common.Visibility;

/**
 * Class that create a command in order to update operation parameters <br>
 * creation : 8 avr. 2005
 */
public class OperationUpdateCommand extends Command {
	/** Current operation */
	private Operation operation;

	/** Old values */
	private String oldName;

	private String oldReturnType;

	private Visibility oldVisibility;

	private OperationDataStructure oldInputTypes;
	
	private String oldDoc;
	
	private boolean oldStatic;
	
	private String oldBody;

	/** New values */
	private String name;

	private String returnType;

	private Visibility visibility;

	private OperationDataStructure inputTypes;

	private ConstraintsDataStructure metainfo;
	
	private String doc;
	
	private boolean _static;
	
	private String body;

	/**
	 * Create a command to update parameters, name, etc... on a given operation
	 * 
	 * @param op
	 *            the Operation to update
	 * @param data
	 *            the map containing the new values
	 */
	public OperationUpdateCommand(Operation op, Map data) {
		operation = op;

		// Store new data
		name = (String) data.get(OperationEditDialog.OPERATION_NAME);
		returnType = (String) data
				.get(OperationEditDialog.OPERATION_RETURN_TYPE);
		inputTypes = (OperationDataStructure) data
				.get(OperationEditDialog.OPERATION_INPUTS);
		metainfo = ((ConstraintsDataStructure) data
				.get(OperationEditDialog.OPERATION_CONSTRAINTS));
		visibility = Visibility.getByName(data.get(
				OperationEditDialog.OPERATION_VISIBILITY).toString());
		doc = (String) data.get(OperationEditDialog.OPERATION_DOCUMENTATION);
		body = (String) data.get(OperationEditDialog.OPERATION_BODY);
		_static = (Boolean) data.get(OperationEditDialog.OPERATION_STATIC);
	}
	
	/**
	 * Get the old values
	 */
	protected void getOldValues() {
		// Store old data
		oldName = operation.getName();
		oldReturnType = operation.getReturnType().toString();
		oldInputTypes = new OperationDataStructure(operation);
		oldVisibility = operation.getVisibility();
		oldDoc = operation.getDocumentation();
		oldBody = operation.getBody();
		oldStatic = operation.isStatic();
	}

	/**
	 * Set the values on the Operation
	 */
	protected void setValues() {
		// Perform update for main data
		operation.setName(name);

		operation.setVisibility(visibility);

		// Perform update for return String data
		operation.setReturnType(DataType.getByName(returnType));

		// Perform update for input parameters
		List newParameters = new ArrayList();
		Iterator iterator = inputTypes.getData().iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			String displayName = inputTypes.getDisplayName(object);
			String string = inputTypes.getType(object);
			Parameter parameter = operation.getParameter(displayName);
			if (parameter == null) {
				parameter = CommonFactory.eINSTANCE.createParameter();
				parameter.setName(displayName);
			}
			parameter.setValueType(DataType.getByName(string));
			newParameters.add(parameter);
		}
		operation.getParameters().clear();
		operation.getParameters().addAll(newParameters);

		// Metainfo
		operation.getMetainfo().clear();

		for (Object o : metainfo.getData()) {
			ConstraintObject co = (ConstraintObject) o;
			MetaInfo c = (new OblOperationMetaInfo()).getMetaInfo(co.getKey());

			boolean valid = true;
			if (co.getValue() instanceof String) {
				valid = (((String) co.getValue()).length() > 0);
			}

			if (valid) {
				c.setValue(co.getValue());
				c.setConstraintType(null);
				c.setValueSet(null);
				c.setValueType(null);
				operation.getMetainfo().add(c);
			}
		}
		
		//Doc
		operation.setDocumentation(doc);
		operation.setBody(body);
		operation.setStatic(_static);
	}

	/**
	 * Switch the old and new values
	 */
	protected void switchValues() {
		// Reverse old and new value for name
		String tempName = name;
		name = oldName;
		oldName = tempName;

		// Reverse old and new value for visibility
		Visibility tempV = visibility;
		visibility = oldVisibility;
		oldVisibility = tempV;

		// Reverse old and new values for String
		String tempType = returnType;
		returnType = oldReturnType;
		oldReturnType = tempType;

		// Reverse old and new values for inputType
		OperationDataStructure tempInputTypes = inputTypes;
		inputTypes = oldInputTypes;
		oldInputTypes = tempInputTypes;
		
		//Doc
		String tempDoc = doc;
		doc = oldDoc;
		oldDoc = tempDoc;
		
		//Body
		String tempBody = body;
		body = oldBody;
		oldBody = tempBody;
		
		//Static
		boolean tempStatic = _static;
		_static = oldStatic;
		oldStatic = tempStatic;
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
