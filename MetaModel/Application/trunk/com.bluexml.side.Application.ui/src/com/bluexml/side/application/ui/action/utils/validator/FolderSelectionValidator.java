package com.bluexml.side.application.ui.action.utils.validator;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

/**
 * Simple validator class for container selection dialog
 *
 */
public class FolderSelectionValidator implements ISelectionStatusValidator {

	public IStatus validate(Object[] selection) {
		IStatus result = Status.OK_STATUS;

		if (selection.length == 0) {
			result = new Status(IStatus.ERROR, "not_used", 0, "No Selection" , null);
		} else {
			if (!(selection[0] instanceof Folder)) {
				result = new Status(IStatus.ERROR, "not_used", 0, "You Must Select a Folder" , null);
			}
		}

		return result;
	}
}
