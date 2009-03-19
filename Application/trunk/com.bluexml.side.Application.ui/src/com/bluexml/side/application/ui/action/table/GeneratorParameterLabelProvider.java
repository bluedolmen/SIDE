package com.bluexml.side.application.ui.action.table;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class GeneratorParameterLabelProvider extends LabelProvider implements ITableLabelProvider{

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		switch (columnIndex) {
		case 0:
			//TODO
			result = "";
			break;
		case 1:
			//TODO
			result = "";
			break;
		default:
			break;
		}
		return result;
	}

}
