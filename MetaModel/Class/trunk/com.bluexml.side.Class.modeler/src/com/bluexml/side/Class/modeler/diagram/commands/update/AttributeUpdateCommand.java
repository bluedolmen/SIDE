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

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Class.modeler.diagram.dialogs.AttributeEditDialog;
import com.bluexml.side.Class.modeler.diagram.dialogs.ConstraintsDataStructure;
import com.bluexml.side.Class.modeler.diagram.dialogs.ConstraintsDataStructure.ConstraintObject;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblAttributeMetaInfo;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.Visibility;

public class AttributeUpdateCommand extends Command {
	/** Current property */
	private Attribute Attribute;

	/** Old values */
	private String oldName;

	private String oldType;

	private Visibility oldVisibility;

	private String oldDoc;

	private String oldTitle;

	private String oldDescription;

	private Enumeration oldEnumeration;

	private boolean oldUnique;

	/** New values */
	private String name;

	private String typ;

	private Visibility visibility;

	private String doc;

	private String title;

	private String description;

	private Enumeration enumeration;

	private boolean unique;

	private ConstraintsDataStructure constraints;

	/**
	 * Create a command for updating parameters on a given Attribute
	 * 
	 * @param prop
	 *            the Attribute to update
	 * @param data
	 *            the datas to set
	 */
	public AttributeUpdateCommand(Attribute prop, Map data) {
		Attribute = prop;
		name = (String) data.get(AttributeEditDialog.PROPERTY_NAME);
		typ = ((DataType) data.get(AttributeEditDialog.PROPERTY_TYPE)).toString();
		// bug http://bugs.bluexml.net/show_bug.cgi?id=1281
		// visibility = Visibility.getByName(data.get(AttributeEditDialog.PROPERTY_VISIBILITY).toString());
		constraints = ((ConstraintsDataStructure) data.get(AttributeEditDialog.PROPERTY_CONSTRAINTS));
		doc = (String) data.get(AttributeEditDialog.PROPERTY_DOCUMENTATION);
		title = (String) data.get(AttributeEditDialog.PROPERTY_TITLE);
		description = (String) data.get(AttributeEditDialog.PROPERTY_DESCRIPTION);
		enumeration = (Enumeration) data.get(AttributeEditDialog.PROPERTY_VALUELIST);
		unique = (Boolean) data.get(AttributeEditDialog.PROPERTY_UNIQUE);
	}

	/**
	 * Get the old values
	 */
	protected void getOldValues() {
		oldName = Attribute.getName();
		oldType = Attribute.getTyp().toString();
		oldVisibility = Attribute.getVisibility();
		oldDoc = Attribute.getDocumentation();
		oldTitle = Attribute.getTitle();
		oldDescription = Attribute.getDescription();
		oldEnumeration = Attribute.getValueList();
		oldUnique = Attribute.isUnique();
	}

	/**
	 * Set the values on the Operation
	 */
	protected void setValues() {
		// Perform update
		Attribute.setName(name);
		Attribute.setTyp(DataType.getByName(typ));
		Attribute.setVisibility(visibility);
		Attribute.setDocumentation(doc);
		Attribute.setDescription(description);
		Attribute.setTitle(title);
		Attribute.setValueList(enumeration);
		Attribute.setUnique(unique);
		Attribute.getMetainfo().clear();

		for (Object o : constraints.getData()) {
			ConstraintObject co = (ConstraintObject) o;
			MetaInfo c = (new OblAttributeMetaInfo()).getMetaInfo(co.getKey());

			c.setValue(co.getValue());
			c.setConstraintType(null);
			c.setValueSet(null);
			c.setValueType(null);
			Attribute.getMetainfo().add(c);
		}
	}

	/**
	 * Switch the old and new values
	 */
	protected void switchValues() {
		// Reverse old and new value for name
		String tempString = name;
		name = oldName;
		oldName = tempString;

		// Reverse old and new value for String
		String tempType = typ;
		typ = oldType;
		oldType = tempType;

		// Reverse old and new value for String
		Visibility tempVisibility = visibility;
		visibility = oldVisibility;
		oldVisibility = tempVisibility;

		String tempDoc = doc;
		doc = oldDoc;
		oldDoc = tempDoc;

		String tempTitle = title;
		title = oldTitle;
		oldTitle = tempTitle;

		boolean tempUnique = unique;
		unique = oldUnique;
		oldUnique = tempUnique;

		String tempDescription = description;
		description = oldDescription;
		oldDescription = tempDescription;

		Enumeration tempEnum = enumeration;
		enumeration = oldEnumeration;
		oldEnumeration = tempEnum;
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
