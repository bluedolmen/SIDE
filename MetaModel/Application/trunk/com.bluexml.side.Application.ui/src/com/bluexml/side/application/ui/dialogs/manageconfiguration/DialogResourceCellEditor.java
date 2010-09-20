package com.bluexml.side.application.ui.dialogs.manageconfiguration;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.dialogs.RessourcesSelection;

public class DialogResourceCellEditor extends CellEditor {
	private RessourcesSelection resourceSelector;
	private Table table;

	public DialogResourceCellEditor(Table table) {
		this.table = table;
	}

	@Override
	protected Control createControl(Composite parent) {
		return parent;
	}

	@Override
	protected Object doGetValue() {
		return resourceSelector.getResourcePath();
	}

	@Override
	protected void doSetFocus() {
		resourceSelector.getLocationField().setFocus();
	}

	@Override
	protected void doSetValue(Object value) {
		resourceSelector = new RessourcesSelection(table.getShell(), Activator.Messages.getString("DialogResourceCellEditor.1"), (String) value);
		resourceSelector.open();
		
		fireApplyEditorValue();
	}

}
