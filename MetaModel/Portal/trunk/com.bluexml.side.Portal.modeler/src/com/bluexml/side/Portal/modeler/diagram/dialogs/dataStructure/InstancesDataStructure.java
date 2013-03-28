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
package com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletAttributeInstance;
import com.bluexml.side.util.libs.eclipse.RessourcesSelection.RESOURCE_TYPE;
import com.bluexml.side.util.libs.ecore.ResourceTableCellData;

public class InstancesDataStructure {

	public class InstancesObject implements ResourceTableCellData {
		private String key;
		private String value;
		private String label;
		private String dataType;

		public InstancesObject(String key, String value, String label, String dataType) {
			this.key = key;
			this.value = value;
			this.label = label;
			this.dataType = dataType;
		}

		public InstancesObject(String key, String value) {
			this.key = key;
			this.value = value;
			this.label = key;
			this.dataType = RESOURCE_TYPE.RESOURCE_TYPE_STRING.toString();
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getDataType() {
			return dataType;
		}

		public void setDataType(String dataType) {
			this.dataType = dataType;
		}

	}

	private List<InstancesObject> list = new ArrayList<InstancesObject>();

	public InstancesDataStructure(EList<PortletAttributeInstance> p_instances, EList<PortletAttribute> p_attributes) {
		// Add all existing instances
		addAll(p_instances);
		// Add attributes that hasn't been added :
		addAttributes(p_attributes);
	}

	public List<InstancesObject> getData() {
		return list;
	}

	private void addAll(EList<PortletAttributeInstance> p_instances) {
		Iterator<PortletAttributeInstance> itInst = p_instances.iterator();
		while (itInst.hasNext()) {
			add(itInst.next());
		}
	}

	private void addAttributes(EList<PortletAttribute> p_attributes) {
		Iterator<PortletAttribute> itAttr = p_attributes.iterator();
		while (itAttr.hasNext()) {
			PortletAttribute attr = (PortletAttribute) itAttr.next();
			String name = attr.getName();

			// If attribute ID isn't in list we add it :
			if (!attributeAlreadyIn(name)) {
				add(attr.getName());
			}
		}
	}

	private boolean attributeAlreadyIn(String p_name) {
		boolean isIn = false;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size() && !isIn; i++) {
				if (list.get(i).getKey().equalsIgnoreCase(p_name)) {
					isIn = true;
				}
			}
		}
		return isIn;
	}

	public void add(String p_name) {
		list.add(new InstancesObject(p_name, ""));
	}

	public void add(PortletAttributeInstance inst) {
		PortletAttribute instanceOf = inst.getInstanceOf();
		String name = instanceOf.getName();
		list.add(new InstancesObject(name, inst.getValue(), name, instanceOf.getType().getLiteral()));
	}

	public void remove(Object object) {
		list.remove(object);
	}
}
