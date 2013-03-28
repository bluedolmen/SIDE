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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.bluexml.side.util.libs.ecore.ResourceTableCellData;

public class DialogResourceCellEditor extends AbstractCellEditor {

	public DialogResourceCellEditor(Table table) {
		super(table);
	}

	public AbstractDialogCellEditor createResourceSelector(Composite parent) {
		return new RessourcesSelection(parent.getShell());
	}

	public ResourceTableCellData getSelectedCellData() {
		ResourceTableCellData data = null;
		Composite parent = this.getControl().getParent();
		if (parent instanceof Table) {
			TableItem[] it = ((Table) parent).getSelection();
			for (TableItem tableItem : it) {
				Object selected = tableItem.getData();
				if (selected != null && selected instanceof ResourceTableCellData) {
					data = (ResourceTableCellData) selected;
					break;
				}
			}
		}
		return data;
	}

}
