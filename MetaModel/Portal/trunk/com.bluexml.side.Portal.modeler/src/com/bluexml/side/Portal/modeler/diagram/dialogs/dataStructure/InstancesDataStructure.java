package com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletAttributeInstance;

public class InstancesDataStructure {
	
	public class InstancesObject {				
		
		private String attributeName;
		
		private String value;		

		public InstancesObject(String attributeName, String value) {			
			
			this.attributeName = attributeName;
			this.value = value;
		}		

		public String getAttributeName() {
			return attributeName;
		}

		public void setAttributeName(String attributeName) {
			this.attributeName = attributeName;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
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
	
	private void addAttributes(EList<PortletAttribute> p_attributes){
		Iterator<PortletAttribute> itAttr = p_attributes.iterator();
        while (itAttr.hasNext())
        {
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
			for (int i=0; i <list.size() && !isIn; i++) {
				if (list.get(i).getAttributeName().equalsIgnoreCase(p_name)) {
					isIn = true;
				}
			}				
		}
		return isIn;
	}

	public void add(String p_name) {		
		list.add(new InstancesObject(p_name,""));
	}

	public void add(PortletAttributeInstance inst) {		
		list.add(new InstancesObject(inst.getInstanceOf().getName(),inst.getValue()));	
	}
	
	public void remove(Object object) {				
		list.remove(object);
	}
}
