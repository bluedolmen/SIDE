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
