package com.bluexml.side.application.ui.action.table;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.bluexml.side.application.ApplicationFactory;
import com.bluexml.side.application.Configuration;
import com.bluexml.side.application.ConfigurationParameters;
import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.util.libs.ecore.ResourceTableCellData;

public class GeneratorParameterCellModifier implements ICellModifier {

	private String[] columnNames;
	private GeneratorParameterDataStructure dataStructure;
	private TableViewer generatorParametersViewer;

	public GeneratorParameterCellModifier(GeneratorParameterDataStructure p_dataStructure, String[] p_columnNames, TableViewer p_generatorParametersViewer) {
		columnNames = p_columnNames;
		dataStructure = p_dataStructure;
		generatorParametersViewer = p_generatorParametersViewer;
	}

	public void setDataStructure(GeneratorParameterDataStructure dataStructure) {
		this.dataStructure = dataStructure;
	}

	public boolean canModify(Object element, String property) {
		boolean result = false;
		int index = Arrays.asList(columnNames).indexOf(property);
		if (index == 1) {
			result = true;
		}
		return result;
	}

	public Object getValue(Object element, String property) {
		Object result = null;
		int index = Arrays.asList(columnNames).indexOf(property);
		switch (index) {
		case 0:
			result = dataStructure.getLabel(element);
			break;
		case 1:
			result = dataStructure.getValue(element);
			break;
		default:
			break;
		}
		return result;
	}

	public void modify(Object element, String property, Object value) {
		Object data = null;
		if (element instanceof StructuredSelection) {
			data = ((StructuredSelection) element).getFirstElement();
			
		} else if (element instanceof TableItem) {
			TableItem item = (TableItem) element;
			data = item.getData();
		}

		int index = Arrays.asList(columnNames).indexOf(property);
		if (data != null) {
			switch (index) {
			case 0:
				dataStructure.setLabel(data, (String) value);
				break;
			case 1:
				if (value != null) {
					dataStructure.setValue(data, (String) value);
				}
				break;
			default:
				break;
			}
			ApplicationDialog.modificationMade();
			updateApplication(data);
			generatorParametersViewer.update(data, null);
		} else {
			throw new RuntimeException("Error on data, selection was null");
		}
	}

	private void updateApplication(Object data) {
		if (data instanceof GeneratorParameter) {
			ResourceTableCellData modifiedParam = (ResourceTableCellData) data;
			Configuration config = ApplicationDialog.getCurrentConfiguration();
			if (config != null) {
				// Search generator parameter
				boolean modified = false;
				for (ConfigurationParameters param : config.getParameters()) {
					if (param.getKey().equals(modifiedParam.getKey())) {
						param.setValue(modifiedParam.getValue());
						modified = true;
					}
				}
				// If no modification we must create it
				if (!modified) {
					ConfigurationParameters newParam = ApplicationFactory.eINSTANCE.createConfigurationParameters();
					newParam.setKey(modifiedParam.getKey());
					newParam.setValue(modifiedParam.getValue());
					config.getParameters().add(newParam);
				}
			}
		}
	}

	public void applyDirtyValue() {
		if (generatorParametersViewer.isCellEditorActive()) {
			CellEditor ce = generatorParametersViewer.getCellEditors()[1];
			String value = ce.getValue().toString();
			TableItem ti = generatorParametersViewer.getTable().getItem(generatorParametersViewer.getControl().getLocation());
			String property = "Valeur";
			this.modify(ti, property, value);
			generatorParametersViewer.cancelEditing();
		}
	}

}
