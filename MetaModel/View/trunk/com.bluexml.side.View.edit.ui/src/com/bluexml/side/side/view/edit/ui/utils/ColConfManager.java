package com.bluexml.side.side.view.edit.ui.utils;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.view.Col;

public class ColConfManager {

	protected static Col model = null;
	
	/**
	 * Return the given actual copied col, null if no col have been copied
	 * @return
	 */
	public static Col getActualCopiedCol() {
		return model;
	}
	

	/**
	 * Put a copy of the given col in the static field.
	 * 
	 * @param toCopy
	 * @param domain
	 */
	public static void copy(Col toCopy, EditingDomain domain) {
		model = (Col) EcoreUtil.copy(toCopy);
	}

	/**
	 * Will paste the col configuration (styling, paging, filtering, operation,
	 * operation group, isEditable, isMovable, isHidden) of the already set col
	 * to the given col.
	 * 
	 * @param domain
	 * @return
	 */
	public static Command paste(Col toEdit, EditingDomain domain) {
		CompoundCommand cmd = new CompoundCommand();
		if (model != null) {

		}
		return cmd;
	}
}
