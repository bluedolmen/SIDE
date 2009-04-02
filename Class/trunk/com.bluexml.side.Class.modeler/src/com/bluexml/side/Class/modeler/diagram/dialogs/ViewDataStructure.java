/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import com.bluexml.side.Class.modeler.diagram.dialogs.view.ViewAttribute;
import com.bluexml.side.Class.modeler.diagram.dialogs.view.ViewContainer;
import com.bluexml.side.Class.modeler.diagram.dialogs.view.ViewContainerType;
import com.bluexml.side.Class.modeler.diagram.utils.AssociationHelper;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.View;
import com.bluexml.side.clazz.ViewItem;

public class ViewDataStructure {

	/** A collection for ViewAttribute objects */
	private List<ViewAttribute> data;

	private Map<ViewContainer, List<ViewAttribute>> possibleDataMap = new HashMap<ViewContainer, List<ViewAttribute>>();
	private List<ViewContainer> possibleContainer = new ArrayList<ViewContainer>();
	private List<ViewAttribute> possibleAttribute = new ArrayList<ViewAttribute>();

	private Clazz source = null;

	private View view = null;

	/**
	 * The constructor
	 * 
	 * @param operation
	 *            the Operation
	 */
	@SuppressWarnings("unchecked")
	public ViewDataStructure(View v) {
		this.view = v;
		if (view.eContainer() instanceof Clazz) {
			source = (Clazz) view.eContainer();
		}
		data = new ArrayList<ViewAttribute>();
		buildChoices();
		if (view != null) {
			addAll(view.getAttributes());
		}
	}

	private ViewAttribute getFirstPossibleData() {
		if (possibleAttribute.size() > 0) {
			return possibleAttribute.iterator().next();
		}
		return null;
	}

	/**
	 * Adds a new empty ViewItem.
	 */
	public void add() {
		data.add(new ViewAttribute(getFirstPossibleData()));
	}

	/**
	 * Add a parameter to the structure
	 * 
	 * @param parameter
	 *            the parameter to add
	 */
	public void add(ViewItem a) {
		data.add(createViewItem(a));
	}

	private ViewAttribute createViewItem(ViewItem a) {
		ViewContainer newContainer = new ViewContainer(source, a);
		ViewAttribute newAttribute = new ViewAttribute(source, newContainer, a);
		return newAttribute;
	}

	/**
	 * Remove a parameter or the name or etc..; from the structure
	 * 
	 * @param object
	 *            the object to remove
	 */
	public void remove(Object object) {
		data.remove(object);
	}

	/**
	 * Add a collection of parameters to the operation
	 * 
	 * @param list
	 *            the collection of parameters to add
	 */
	public void addAll(EList<ViewItem> list) {
		for (ViewItem viewItem : list) {
			add(viewItem);
		}
	}

	/**
	 * Get the datas
	 * 
	 * @return a Collection of
	 */
	public Collection<ViewAttribute> getData() {
		return data;
	}

	public String getContainerName(Object element) {
		ViewAttribute ao = ((ViewAttribute) element);
		return ao.getViewContainer().getContainerName();
	}

	public String getAttributeName(Object element) {
		ViewAttribute ao = ((ViewAttribute) element);
		return ao.getAttributeName();
	}

	public void setContainer(Object element, Integer val) {
		ViewAttribute ao = (ViewAttribute) element;
		ViewContainer container = possibleContainer.get(val);
		if (!container.getContainerName().equals(ao.getViewContainer().getContainerName())) {
			ao.setViewContainer(container);
			ao.copyFrom(possibleDataMap.get(container).get(0));
		}
	}

	public void setAttribute(Object element, Integer val) {
		ViewAttribute ao = (ViewAttribute) element;
		ViewContainer container = possibleContainer.get(findContainerIndex(ao));
		List<ViewAttribute> attributes = possibleDataMap.get(container);
		ao.copyFrom(attributes.get(val));
	}

	public List<ViewContainer> getContainers() {
		return possibleContainer;
	}

	private void getAllClasses(Clazz classe, List<Clazz> result) {
		result.add(classe);
		if (classe != null) {
			EList<Clazz> generalizations = classe.getGeneralizations();
			for (Clazz cl : generalizations) {
				getAllClasses( cl, result);
			}
		}
	}

	private void buildAttributes(ViewContainer container, Clazz classe) {
		EList<Attribute> attributes = classe.getAttributes();
		for (Attribute attribute : attributes) {
			ViewAttribute directAttribute = ViewAttribute.getDirectViewAttribute(container, attribute);
			possibleAttribute.add(directAttribute);
		}
		EList<Aspect> aspects = classe.getAspects();
		for (Aspect aspect : aspects) {
			EList<Attribute> aspectAttributes = aspect.getAttributes();
			for (Attribute aspectAttribute : aspectAttributes) {
				ViewAttribute directAttribute = ViewAttribute.getDirectAspectViewAttribute(container, aspect, aspectAttribute);
				possibleAttribute.add(directAttribute);
			}
		}
	}

	private void buildAllAttributes(ViewContainer container, Clazz classe) {
		possibleContainer.add(container);

		List<Clazz> directClasses = new ArrayList<Clazz>();
		getAllClasses(classe, directClasses);
		for (Clazz subClasse : directClasses) {
			buildAttributes(container, subClasse);
		}
	}

	public void buildChoices() {
		possibleDataMap = new HashMap<ViewContainer, List<ViewAttribute>>();

		// direct attributes
		ViewContainer directContainer = ViewContainer.getDirectViewContainer(source);
		buildAllAttributes(directContainer, source);

		EList<Association> associations = getAllAssociations(source);
		EList<Clazz> inhertedClasses = getAllInheritedClasses(source);

		for (Association association : associations) {
			ViewContainerType containertype = ViewContainerType.ASSOCIATION_TARGET;
			ViewContainerType asso_classCT = ViewContainerType.ASSOCIATION_CLASS;
			if (association.getSource().equals(association.getDestination())) {
				containertype = ViewContainerType.ASSOCIATION_RECUR;
				asso_classCT = ViewContainerType.ASSOCIATION_CLASS_RECUR;
			}
			EList<Clazz> associationsClass = association.getAssociationsClass();

			// target class attributes
			Clazz other = null;
			if (inhertedClasses.contains(association.getSource())) {
				other = (Clazz) association.getDestination();
				buildAssociationAttributes(association, containertype, asso_classCT, associationsClass, other, AssociationHelper.getRoleSrcOrTitle(association));
			}
			if (inhertedClasses.contains(association.getDestination())) {
				other = (Clazz) association.getSource();
				buildAssociationAttributes(association, containertype, asso_classCT, associationsClass, other, AssociationHelper.getRoleTargetOrTitle(association));
			}
		}

		for (ViewAttribute attribute : possibleAttribute) {
			List<ViewAttribute> list = possibleDataMap.get(attribute.getViewContainer());
			if (list == null) {
				list = new ArrayList<ViewAttribute>();
				possibleDataMap.put(attribute.getViewContainer(), list);
			}
			list.add(attribute);
		}
		Set<Entry<ViewContainer, List<ViewAttribute>>> entrySet = possibleDataMap.entrySet();
		for (Entry<ViewContainer, List<ViewAttribute>> entry : entrySet) {
			List<ViewAttribute> value = entry.getValue();
			Collections.sort(value, new Comparator<ViewAttribute>() {
				public int compare(ViewAttribute o1, ViewAttribute o2) {
					return o1.getAttributeName().compareToIgnoreCase(o2.getAttributeName());
				}
			});
		}

	}

	/**
	 * @param association
	 * @param containertype
	 * @param asso_classCT
	 * @param associationsClass
	 * @param other
	 */
	private void buildAssociationAttributes(Association association, ViewContainerType containertype, ViewContainerType asso_classCT, EList<Clazz> associationsClass, Clazz other, String role) {
		ViewContainer targetContainer = ViewContainer.getAssociationViewContainer(source, association, other, containertype, role);
		buildAllAttributes(targetContainer, other);
		for (Clazz associationClass : associationsClass) {
			// attributes of association class
			ViewContainer associationClassContainer = ViewContainer.getAssociationViewContainer(source, association, associationClass, asso_classCT, role);
			buildAllAttributes(associationClassContainer, associationClass);
		}
	}

	public EList<Association> getAllAssociations(Clazz p_clazz) {
		EList<Association> associations = p_clazz.getAssociations();
		// Add generalized associations
		for (Clazz cl : p_clazz.getGeneralizations()) {
				associations.addAll(getAllAssociations(cl));
		}
		return associations;
	}

	public EList<Clazz> getAllInheritedClasses(Clazz p_clazz) {
		EList<Clazz> classes = new BasicEList<Clazz>();
		classes.add(p_clazz);

		for (Clazz cl : p_clazz.getGeneralizations()) {
			classes.add(cl);
			classes.addAll(getAllInheritedClasses(cl));
		}

		return classes;
	}

	public Integer findContainerIndex(ViewAttribute attribute) {
		String containerName = attribute.getViewContainer().getContainerName();
		int i = 0;
		for (ViewContainer container : possibleContainer) {
			if (containerName.equals(container.getContainerName())) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Integer findAttributeIndex(ViewAttribute attribute) {
		String attributeName = attribute.getAttributeName();
		ViewContainer container = possibleContainer.get(findContainerIndex(attribute));
		List<ViewAttribute> attributes = possibleDataMap.get(container);
		int i = 0;
		for (ViewAttribute viewAttribute : attributes) {
			if (attributeName.equals(viewAttribute.getAttributeName())) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public String[] getAttributeList(ViewAttribute attribute) {
		ViewContainer container = possibleContainer.get(findContainerIndex(attribute));
		List<ViewAttribute> attributes = possibleDataMap.get(container);
		return toArray(attributes);
	}

	private String[] toArray(List<ViewAttribute> set) {
		String[] result = new String[set.size()];
		int i = 0;
		for (ViewAttribute s : set) {
			result[i] = s.getAttributeName();
			++i;
		}
		return result;
	}

	public void invert(ViewAttribute o1, ViewAttribute o2) {
		int i1 = data.lastIndexOf(o1);
		int i2 = data.lastIndexOf(o2);
		data.set(i1, o2);
		data.set(i2, o1);
	}

}
