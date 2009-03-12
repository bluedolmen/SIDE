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

import com.bluexml.side.Class.modeler.diagram.dialogs.ConstraintsDataStructure;
import com.bluexml.side.Class.modeler.diagram.dialogs.ConstraintsDataStructure.ConstraintObject;
import com.bluexml.side.Class.modeler.diagram.utils.AssociationHelper;
import com.bluexml.side.Class.modeler.diagram.utils.metainfo.OblAssociationMetaInfo;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.MetaInfo;

public class AssociationUpdateCommand extends Command {

	/** The association currently updated */
	private Association association;

	/** Map containing new association data */
	private Map newAssociationData;

	/** Map containing old association data */
	private Map oldAssociationData;

	private ConstraintsDataStructure constraints;

	/**
	 * Create a command for updating parameters on a given operation
	 * 
	 * @param assoc
	 *            the association to update
	 * @param prop
	 *            Map containing association data
	 */
	public AssociationUpdateCommand(Association assoc, Map prop) {
		this.association = assoc;
		this.newAssociationData = prop;
		
		constraints = ((ConstraintsDataStructure) prop
				.get(AssociationHelper.META_INFO));
	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldAssociationData = AssociationHelper
				.extractAssociationData(association);

		redo();
	}

	/**
	 * Set the new values
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		// Update the Association properties
		association.setName((String) newAssociationData
				.get(AssociationHelper.ASSOCIATION_NAME));
		association.setTitle((String) newAssociationData
				.get(AssociationHelper.ASSOCIATION_TITLE));
		association.setDescription((String) newAssociationData
				.get(AssociationHelper.ASSOCIATION_DESCRIPTION));
		association.setName((String) newAssociationData
				.get(AssociationHelper.ASSOCIATION_NAME));
		association.setAssociationType((AssociationType) newAssociationData
				.get(AssociationHelper.ASSOCIATION_TYPE));
		association.setRoleSrc((String) newAssociationData
				.get(AssociationHelper.ASSOCIATION_ROLE_SRC));
		association.setRoleTarget((String) newAssociationData
				.get(AssociationHelper.ASSOCIATION_ROLE_TARGET));
		
		association.setRoleSrcTitle((String) newAssociationData
				.get(AssociationHelper.ASSOCIATION_ROLE_SRC_TITLE));
		association.setRoleTargetTitle((String) newAssociationData
				.get(AssociationHelper.ASSOCIATION_ROLE_TARGET_TITLE));
		

		association.setIsNavigableSRC(((Boolean) newAssociationData
				.get(AssociationHelper.FIRST_END_IS_NAVIGABLE)).booleanValue());
		association.setMinSRC((String) newAssociationData
				.get(AssociationHelper.FIRST_END_LOWER_BOUND));
		association.setMaxSRC((String) newAssociationData
				.get(AssociationHelper.FIRST_END_UPPER_BOUND));

		association
				.setIsNavigableTARGET(((Boolean) newAssociationData
						.get(AssociationHelper.SECOND_END_IS_NAVIGABLE))
						.booleanValue());
		association.setMinTARGET((String) newAssociationData
				.get(AssociationHelper.SECOND_END_LOWER_BOUND));
		association.setMaxTARGET((String) newAssociationData
				.get(AssociationHelper.SECOND_END_UPPER_BOUND));
		association.getMetainfo().clear();
		
		for (Object o : constraints.getData()) {
			ConstraintObject co = (ConstraintObject) o;
			MetaInfo c = (new OblAssociationMetaInfo()).getMetaInfo(co.getKey());

			c.setValue(co.getValue());
			c.setConstraintType(null);
			c.setValueSet(null);
			c.setValueType(null);
			association.getMetainfo().add(c);
		}
	}

	/**
	 * Set the old values
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		association.setName((String) oldAssociationData
				.get(AssociationHelper.ASSOCIATION_NAME));
		association.setTitle((String) oldAssociationData
				.get(AssociationHelper.ASSOCIATION_TITLE));
		association.setDescription((String) oldAssociationData
				.get(AssociationHelper.ASSOCIATION_DESCRIPTION));
		association.setAssociationType((AssociationType) oldAssociationData
				.get(AssociationHelper.ASSOCIATION_TYPE));
		association.setRoleSrc((String) oldAssociationData
				.get(AssociationHelper.ASSOCIATION_ROLE_SRC));
		association.setRoleTarget((String) oldAssociationData
				.get(AssociationHelper.ASSOCIATION_ROLE_TARGET));

		association.setIsNavigableSRC(((Boolean) oldAssociationData
				.get(AssociationHelper.FIRST_END_IS_NAVIGABLE)).booleanValue());
		association.setMinSRC((String) oldAssociationData
				.get(AssociationHelper.FIRST_END_LOWER_BOUND));
		association.setMaxSRC((String) oldAssociationData
				.get(AssociationHelper.FIRST_END_UPPER_BOUND));

		association
				.setIsNavigableTARGET(((Boolean) oldAssociationData
						.get(AssociationHelper.SECOND_END_IS_NAVIGABLE))
						.booleanValue());
		association.setMinTARGET((String) oldAssociationData
				.get(AssociationHelper.SECOND_END_LOWER_BOUND));
		association.setMaxTARGET((String) oldAssociationData
				.get(AssociationHelper.SECOND_END_UPPER_BOUND));
	}
}
