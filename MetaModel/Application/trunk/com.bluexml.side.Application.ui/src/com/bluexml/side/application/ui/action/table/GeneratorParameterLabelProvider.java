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
