/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
