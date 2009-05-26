package com.bluexml.side.Class.modeler.diagram.dialogs.view;

import com.bluexml.side.Class.modeler.diagram.utils.AssociationHelper;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ViewItem;


public class ViewContainer {

	private ViewContainerType viewContainerType;
	private Clazz source = null;
	private Association association = null;
	private Clazz target = null;
	private String role = null;

	public static ViewContainer getDirectViewContainer(Clazz source) {
		ViewContainer viewContainer = new ViewContainer();
		viewContainer.viewContainerType = ViewContainerType.CLASS;
		viewContainer.source = source;
		return viewContainer;
	}

	

	public static ViewContainer getAssociationViewContainer(Clazz source, Association association, Clazz targetClass, ViewContainerType containertype,String role) {
		ViewContainer viewContainer = new ViewContainer();
		viewContainer.viewContainerType =containertype;		
		viewContainer.role=role;
		viewContainer.source = source;
		viewContainer.association = association;
		viewContainer.target = targetClass;
		return viewContainer;
	}

	private ViewContainer() {
		// private constructor
	}

	public ViewContainer(Clazz source, ViewItem a) {
		this.source = source;
		if (a.getAssociation() == null) {
			viewContainerType = ViewContainerType.CLASS;
		} else {
			association = a.getAssociation();
			target = a.getClasse();
			role = a.getRole();
			if (a.getAssociation().getAssociationsClass().contains(a.getClasse())) {
				if (a.getAssociation().getFirstEnd().getLinkedClass().equals(a.getAssociation().getSecondEnd().getLinkedClass())) {
					viewContainerType = ViewContainerType.ASSOCIATION_CLASS_RECUR;
				} else {
					viewContainerType = ViewContainerType.ASSOCIATION_CLASS;
				}
				
			} else {
				if (source.equals(target)) {
					viewContainerType = ViewContainerType.ASSOCIATION_RECUR;
				} else {
					viewContainerType = ViewContainerType.ASSOCIATION_TARGET;
				}
			}
		}
	}

	public String toString() {
		return getContainerName();
	}

	Association getAssociation() {
		if (viewContainerType == ViewContainerType.CLASS) {
			return null;
		} else {
			return association;
		}
	}

	Clazz getClasse() {
		if (viewContainerType == ViewContainerType.CLASS) {
			return source;
		} else {
			return target;
		}
	}
	
	public String getContainerName() {
		return AssociationHelper.getViewItemContainerName(getClasse(), getAssociation(), this.viewContainerType, this.role);		
	}

	
	public String getAssociationViewRole() {
		return AssociationHelper.getAssociationViewRole(getClasse(), getAssociation(), this.viewContainerType, this.role);		
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((association == null) ? 0 : association.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		result = prime * result + ((viewContainerType == null) ? 0 : viewContainerType.hashCode());
		return result;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
