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

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class GeneratorParameterContentProvider implements IStructuredContentProvider {

	private GeneratorParameterDataStructure dataStructure;

	public GeneratorParameterContentProvider(GeneratorParameterDataStructure p_dataStructure) {
		dataStructure = p_dataStructure;
	}

	public Object[] getElements(Object inputElement) {
		if (dataStructure != null && dataStructure.getData() != null) {
			return dataStructure.getData().toArray();
		} else {
			return null;
		}

	}

	public void setDataStructure(GeneratorParameterDataStructure dataStructure) {
		this.dataStructure = dataStructure;
	}

	public void dispose() {
		// nothing to do
		System.out.println("GeneratorParameterContentProvider.dispose()");
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// nothing to do
		System.out.println("GeneratorParameterContentProvider.inputChanged() oldInput" + oldInput + " newInput" + newInput);
	}

}
