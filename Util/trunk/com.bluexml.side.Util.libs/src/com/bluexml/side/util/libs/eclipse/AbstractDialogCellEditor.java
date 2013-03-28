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
package com.bluexml.side.util.libs.eclipse;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractDialogCellEditor extends Dialog {

	protected AbstractDialogCellEditor(Shell parentShell) {
		super(parentShell);
	}

	public AbstractDialogCellEditor(IShellProvider parentShell) {
		super(parentShell);
	}
	
	public abstract String getResourcePath();

	public abstract void addResourceSelectionListener(ResourceSelectionListener listener);
	
	public abstract void init(String locationLabel, String initialValue, String resource_type);
}
