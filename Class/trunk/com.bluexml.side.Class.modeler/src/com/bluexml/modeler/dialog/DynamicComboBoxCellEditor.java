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
package com.bluexml.modeler.dialog;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class DynamicComboBoxCellEditor extends ComboBoxCellEditor {

	private Table table;

	public DynamicComboBoxCellEditor(Table table, Object dataStructure) {

	}

	

	@Override
	public void addListener(ICellEditorListener listener) {
		super.addListener(listener);
	}

	@Override
	protected Control createControl(Composite parent) {
		Control c = super.createControl(parent);
		if (c instanceof CCombo) {
			CCombo combo = (CCombo) c;
			combo.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					super.focusGained(e);
					int i = table.getSelectionIndex();
					try {
						TableItem ti = table.getItem(i);
						Object o = ti.getData();
						
					} catch (Exception ex) {
						setItems(new String[0]);
					}
				}
			});
		}
		return c;
	}

}
