package com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.portal.PortletAttribute;

public class PortletTypeAttributeDataStructure {
	public class PortletTypeAttributeObject{
		private String name;
		
		private int type;
		
		private boolean required;					

		private boolean isMultiValued;
		
		public PortletTypeAttributeObject(String p_name, int p_type, boolean p_required, boolean p_isMultiValued) {
			name = p_name;
			type = p_type;
			required = p_required;			
			isMultiValued = p_isMultiValued;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}
		
		public String getTypeName() {
			return attributesType[type];
		}

		public boolean isRequired() {
			return required;
		}

		public void setRequired(boolean required) {
			this.required = required;
		}

		public boolean isMultiValued() {
			return isMultiValued;
		}

		public void setMultiValued(boolean isMultiValued) {
			this.isMultiValued = isMultiValued;
		}		
				
	}
	
	private List<PortletTypeAttributeObject> listAttr = new ArrayList<PortletTypeAttributeObject>();
	
	protected String[] attributesType;
		
			
	
	public PortletTypeAttributeDataStructure(EList<PortletAttribute> p_attrList, String[] p_attributesType) {		
		Iterator<PortletAttribute> itAttr = p_attrList.iterator();
		while (itAttr.hasNext()) {
			PortletAttribute abAttr = itAttr.next();				
			PortletAttribute attr = (PortletAttribute) abAttr;
			add(attr);									
		}
		attributesType = p_attributesType;
	}				
		
	
	
	public void add(String p_name, int p_type, boolean p_required, boolean p_isMultiValued) {
		listAttr.add(new  PortletTypeAttributeObject(p_name, p_type, p_required, p_isMultiValued));
	}

	public void add(PortletAttribute p_attr) {
		listAttr.add(new PortletTypeAttributeObject(p_attr.getName(),p_attr.getType().getValue(),p_attr.isRequired(), p_attr.isMultiValued()));		
	}
	
	public void remove(Object object)
    {
		listAttr.remove(object);
    }
	
	public List<PortletTypeAttributeObject> getData() {
		return listAttr;
	}
}
