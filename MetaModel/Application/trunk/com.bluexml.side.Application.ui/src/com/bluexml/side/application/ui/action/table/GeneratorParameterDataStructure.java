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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.util.libs.ecore.ResourceTableCellData;

public class GeneratorParameterDataStructure {

	List<GeneratorParameter> data = new ArrayList<GeneratorParameter>();

	public List<GeneratorParameter> getData() {
		Collections.sort(data);
		return data;
	}

	public void addGeneratorParameter(String p_key, String p_label, String p_value, String p_documentation, String p_dataType) {
		GeneratorParameter genParam = new GeneratorParameter(p_key, p_label, p_value, p_documentation, p_dataType);
		data.add(genParam);
	}

	public void addGeneratorParameter(IConfigurationElement elt) {
		GeneratorParameter genParam = new GeneratorParameter(elt);
		data.add(genParam);
	}

	public void addGeneratorParameter(GeneratorParameter genParam) {
		data.add(genParam);
	}

	public String getLabel(Object element) {
		String result = "";
		if (element instanceof GeneratorParameter) {
			ResourceTableCellData genParam = (ResourceTableCellData) element;
			result = genParam.getLabel();
		}
		return result;
	}

	public String getValue(Object element) {
		String result = "";
		if (element instanceof GeneratorParameter) {
			ResourceTableCellData genParam = (ResourceTableCellData) element;
			result = genParam.getValue();
		}
		return result;
	}

	public void setLabel(Object element, String value) {
		if (element instanceof GeneratorParameter) {
			ResourceTableCellData genParam = (ResourceTableCellData) element;
			genParam.setKey(value);
		}
	}

	public void setValue(Object element, String value) {
		if (element instanceof GeneratorParameter) {
			ResourceTableCellData genParam = (ResourceTableCellData) element;
			genParam.setValue(value);
		}
	}

	/**
	 * Return the generatorParameter matching the given key
	 * 
	 * @param key
	 * @return
	 */
	public ResourceTableCellData getParamMatching(String key) {
		ResourceTableCellData result = null;
		int i = 0;
		int size = data.size();
		while (i < size && result == null) {
			if (data.get(i).getKey().equals(key)) {
				result = data.get(i);
			}
			i++;
		}
		return result;
	}

	@Override
	public String toString() {
		String s = "data : " + data;
		return s;
	}

}
