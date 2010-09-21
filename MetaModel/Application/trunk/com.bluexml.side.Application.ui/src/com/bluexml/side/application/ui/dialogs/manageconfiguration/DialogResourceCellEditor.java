package com.bluexml.side.application.ui.dialogs.manageconfiguration;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.bluexml.side.application.ui.Activator;
import com.bluexml.side.application.ui.action.table.GeneratorParameter;
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
		TableItem[] it = table.getSelection();
		String label = Activator.Messages.getString("DialogResourceCellEditor.1");
		String type = "";
		for (TableItem tableItem : it) {
			Object selected = tableItem.getData();
			if (selected != null && selected instanceof GeneratorParameter) {
				label = ((GeneratorParameter) selected).getLabel();
				type = ((GeneratorParameter) selected).getDataType();
			}
		}
		String resourceType = RessourcesSelection.RESOURCE_TYPE_STRING;
		if (type.equals("String")) {
			resourceType = RessourcesSelection.RESOURCE_TYPE_STRING;
		} else if (type.equals("File")) {
			resourceType = RessourcesSelection.RESOURCE_TYPE_FILE;
		} else if (type.equals("Directory")) {
			resourceType = RessourcesSelection.RESOURCE_TYPE_DIRECTORY;
		} else {
			System.err.println("Error ");
		}

		resourceSelector = new RessourcesSelection(table.getShell(), label, (String) value, resourceType);
		resourceSelector.open();

		fireApplyEditorValue();
	}

}
