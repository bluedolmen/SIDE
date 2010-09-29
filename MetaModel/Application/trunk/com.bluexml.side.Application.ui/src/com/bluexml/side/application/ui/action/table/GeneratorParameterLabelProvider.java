package com.bluexml.side.application.ui.action.table;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class GeneratorParameterLabelProvider extends LabelProvider implements ITableLabelProvider {

	private GeneratorParameterDataStructure dataStructure;

	public GeneratorParameterLabelProvider(GeneratorParameterDataStructure p_dataStructure) {
		dataStructure = p_dataStructure;
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public void setDataStructure(GeneratorParameterDataStructure dataStructure) {
		this.dataStructure = dataStructure;
	}

	public String getColumnText(Object element, int columnIndex) {

		String result = "";
		switch (columnIndex) {
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

}
