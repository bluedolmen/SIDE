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

import org.eclipse.core.runtime.IConfigurationElement;

import com.bluexml.side.util.libs.ecore.ResourceTableCellData;

public class GeneratorParameter implements Comparable<GeneratorParameter>, ResourceTableCellData {
	private String key;
	private String value;
	private String label;
	private String documentation;
	private String dataType;

	public GeneratorParameter(String key, String label, String value, String documentation, String dataType) {
		this.key = key;
		this.label = label;
		this.value = value;
		this.documentation = documentation;
		this.dataType = dataType;
	}

	public GeneratorParameter(IConfigurationElement confParam) {
		this.key = confParam.getAttribute("key");
		this.label = confParam.getAttribute("label");
		String defaultValueAtt = confParam.getAttribute("defaultValue");
		if (defaultValueAtt != null && !defaultValueAtt.equals("")) {
			this.value = defaultValueAtt;
		} else {
			this.value = null;
		}

		this.documentation = confParam.getAttribute("documentation");
		this.dataType = confParam.getAttribute("dataType");

	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.application.ui.action.table.ResourceTableCellData#getKey
	 * ()
	 */
	public String getKey() {
		return key;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.application.ui.action.table.ResourceTableCellData#setKey
	 * (java.lang.String)
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.application.ui.action.table.ResourceTableCellData#getValue
	 * ()
	 */
	public String getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.application.ui.action.table.ResourceTableCellData#setValue
	 * (java.lang.String)
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.application.ui.action.table.ResourceTableCellData#getLabel
	 * ()
	 */
	public String getLabel() {
		return label;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.bluexml.side.application.ui.action.table.ResourceTableCellData#setLabel
	 * (java.lang.String)
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataType() {
		return dataType;
	}

	public int compareTo(GeneratorParameter o) {
		return label.compareTo(o.getLabel());
	}

	@Override
	public String toString() {
		return "{key :" + this.key + ", value :" + this.value + "}";
	}

}
