package com.bluexml.side.view.edit.ui.utils;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.Filtering;
import com.bluexml.side.view.Sorting;
import com.bluexml.side.view.Styling;
import com.bluexml.side.view.ViewPackage;

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
		model = toCopy;
	}

	/**
	 * Will paste the col configuration (styling, filtering, operation,
	 * operation group, Sorting, isEditable, isMovable, isHidden) of the already set col
	 * to the given col.
	 * 
	 * @param domain
	 * @return
	 */
	public static Command paste(Col toEdit, EditingDomain domain) {
		CompoundCommand cmd = new CompoundCommand();
		if (model != null) {
			// Attributes
			cmd.append(SetCommand.create(domain, toEdit, ViewPackage.eINSTANCE.getMovable_Movable(), model.isMovable()));
			cmd.append(SetCommand.create(domain, toEdit, ViewPackage.eINSTANCE.getEditable_Editable(), model.isEditable()));
			cmd.append(SetCommand.create(domain, toEdit, ViewPackage.eINSTANCE.getFieldElement_Hidden(), model.isHidden()));
			// Children :
			if (model.getStyling() != null) {
				Styling style = (Styling) EcoreUtil.copy(model.getStyling());
				cmd.append(SetCommand.create(domain, toEdit, ViewPackage.eINSTANCE.getStylable_Styling(), style));
			}
			if (model.getFiltering() != null) {
				Filtering filter = (Filtering) EcoreUtil.copy(model.getFiltering());
				cmd.append(SetCommand.create(domain, toEdit, ViewPackage.eINSTANCE.getFilterable_Filtering(), filter));
			}
			if (model.getOperations() != null) {
				OperationComponent operation = (OperationComponent) EcoreUtil.copy(model.getOperations());
				cmd.append(SetCommand.create(domain, toEdit, ViewPackage.eINSTANCE.getActionable_Operations(), operation));
			}
			if (model.getSorting() != null) {
				Sorting sort = (Sorting) EcoreUtil.copy(model.getSorting());
				cmd.append(SetCommand.create(domain, toEdit, ViewPackage.eINSTANCE.getSortable_Sorting(), sort));
			}
		}
		return cmd;
	}
}
