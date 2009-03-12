package com.bluexml.modeler.dialog;


import org.eclipse.emf.ecore.plugin.EcorePlugin;
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
		//TODO : replace this class by the one in Class modeler
		EcorePlugin.INSTANCE.log("WARN : DynamicComboBoxCellEditor NOT IMPLEMENTED YET, please stay tunes for updates");
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
