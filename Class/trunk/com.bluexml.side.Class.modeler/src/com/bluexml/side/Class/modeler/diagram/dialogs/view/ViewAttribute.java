package com.bluexml.side.Class.modeler.diagram.dialogs.view;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.ViewItem;



public class ViewAttribute {

	private ViewContainer viewContainer;
	private ViewAttributeType attributeType;
	private Attribute attribute = null;
	private Aspect aspect = null;
	private String role = null;

	public static ViewAttribute getDirectViewAttribute(ViewContainer directContainer, Attribute attribute) {
		ViewAttribute viewAttribute = new ViewAttribute();
		viewAttribute.attributeType = ViewAttributeType.CLASS_ATTRIBUTE;
		viewAttribute.viewContainer = directContainer;
		viewAttribute.attribute = attribute;
		return viewAttribute;
	}

	public static ViewAttribute getDirectAspectViewAttribute(ViewContainer directContainer, Aspect aspect, Attribute attribute) {
		ViewAttribute viewAttribute = new ViewAttribute();
		viewAttribute.attributeType = ViewAttributeType.CLASS_ASPECT_ATTRIBUTE;
		viewAttribute.viewContainer = directContainer;
		viewAttribute.attribute = attribute;
		viewAttribute.aspect = aspect;
		return viewAttribute;
	}

	public ViewAttribute() {
		// private constructor
	}

	public ViewAttribute(ViewAttribute toCopy) {
		copyFrom(toCopy);
	}

	public ViewAttribute(Clazz source, ViewContainer viewContainer, ViewItem a) {
		this.viewContainer = viewContainer;
		role = a.getRole();		
		this.attribute = a.getAttribute();
		if (a.getAspect() == null) {
			attributeType = ViewAttributeType.CLASS_ATTRIBUTE;
		} else {
			aspect = a.getAspect();
			attributeType = ViewAttributeType.CLASS_ASPECT_ATTRIBUTE;
		}
		
		if (role !=null) {
			this.viewContainer.setRole(role);
		}
	}

	public String toString() {		
		if (viewContainer != null) {
			return viewContainer.getContainerName() + "@" + getAttributeName();
		} else {
			return getAttributeName();
		}
	}

	public ViewItem toViewItem() {
		ViewItem vi = ClazzFactory.eINSTANCE.createViewItem();
		vi.setAssociation(viewContainer.getAssociation());
		vi.setAttribute(getAttribute());
		vi.setClasse(viewContainer.getClasse());
		vi.setAspect(getAspect());
		if (viewContainer.getAssociation() != null) {
			vi.setRole(viewContainer.getAssociationViewRole());
		}
		return vi;
	}

	Attribute getAttribute() {
		return attribute;
	}

	Aspect getAspect() {
		if (attributeType == ViewAttributeType.CLASS_ASPECT_ATTRIBUTE) {
			return aspect;
		} else {
			return null;
		}
	}

	public String getAttributeName() {
		if (getAspect() == null) {
			return getAttribute().getName();
		} else {
			return getAspect().getName() + "." + getAttribute().getName();
		}
	}

	public ViewContainer getViewContainer() {
		return viewContainer;
	}

	public void setViewContainer(ViewContainer viewContainer) {
		this.viewContainer = viewContainer;
	}

	public void copyFrom(ViewAttribute viewAttribute) {
		this.viewContainer = viewAttribute.viewContainer;
		this.attributeType = viewAttribute.attributeType;
		this.attribute = viewAttribute.attribute;
		this.aspect = viewAttribute.aspect;
		this.role = viewAttribute.role;
	}

}