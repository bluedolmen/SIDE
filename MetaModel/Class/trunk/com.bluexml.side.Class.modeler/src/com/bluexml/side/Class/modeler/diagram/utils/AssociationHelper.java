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
package com.bluexml.side.Class.modeler.diagram.utils;

import java.util.HashMap;
import java.util.Map;

import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.edit.AssociationEditPart;
import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.ClazzFactory;

public class AssociationHelper {
	public static final String ASSOCIATION_NAME = "association name";

	public static final String ASSOCIATION_TITLE = "association title";

	public static final String ASSOCIATION_DESCRIPTION = "association description";
	
	public static final String ASSOCIATION_DOCUMENTATION = "association documentation";

	public static final String ASSOCIATION_TYPE = "association type";

	public static final String FIRST_END_CLASS = "first end class";

	public static final String FIRST_END_ROLE = "first end role";

	public static final String FIRST_END_IS_NAVIGABLE = "first end is navigable";

	public static final String FIRST_END_IS_ORDERED = "first end is ordered";

	public static final String FIRST_END_VISIBILITY_KIND = "first end visibility kind";

	public static final String FIRST_END_LOWER_BOUND = "first end lower bound";

	public static final String FIRST_END_UPPER_BOUND = "first end upper bound";

	public static final String SECOND_END_CLASS = "second end class";

	public static final String SECOND_END_ROLE = "second end role";

	public static final String SECOND_END_IS_NAVIGABLE = "second end is navigable";

	public static final String SECOND_END_IS_ORDERED = "second end is ordered";

	public static final String SECOND_END_VISIBILITY_KIND = "second end visibility kind";

	public static final String SECOND_END_LOWER_BOUND = "second end lower bound";

	public static final String SECOND_END_UPPER_BOUND = "second end upper bound";

	public static final String META_INFO = "meta info";

	public static final String ASSOCIATION_ROLE_SRC = "association role source";

	public static final String ASSOCIATION_ROLE_TARGET = "association role target";

	public static final String ASSOCIATION_ROLE_SRC_TITLE = "association role source title";

	public static final String ASSOCIATION_ROLE_TARGET_TITLE = "association role target title";

	/**
	 * Return all the informations concerning the Association
	 * 
	 * @return Map
	 */
	public static Map<String, Object> extractAssociationData(Association asso) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put(AssociationHelper.ASSOCIATION_NAME, asso.getName());
		resultMap.put(AssociationHelper.ASSOCIATION_TYPE, asso.getAssociationType());

		resultMap.put(AssociationHelper.FIRST_END_IS_NAVIGABLE, new Boolean(asso.getFirstEnd().isNavigable()));
		resultMap.put(AssociationHelper.FIRST_END_LOWER_BOUND, asso.getFirstEnd().getCardMin());
		resultMap.put(AssociationHelper.FIRST_END_UPPER_BOUND, asso.getFirstEnd().getCardMax());

		resultMap.put(AssociationHelper.SECOND_END_IS_NAVIGABLE, new Boolean(asso.getSecondEnd().isNavigable()));
		resultMap.put(AssociationHelper.SECOND_END_LOWER_BOUND, asso.getFirstEnd().getCardMin());
		resultMap.put(AssociationHelper.SECOND_END_UPPER_BOUND, asso.getFirstEnd().getCardMax());

		return resultMap;
	}

	public static String getRoleTargetOrTitle(Association association) {
		String role = "";
		if (association.getSecondEnd().getTitle() != null && association.getSecondEnd().getTitle().length() > 0) {
			role = association.getSecondEnd().getTitle();
		} else if (association.getSecondEnd().getName() != null && association.getSecondEnd().getName().length() > 0) {
			role = association.getSecondEnd().getName();
		}
		return role;
	}

	

	

	public static String getRoleSrcOrTitle(Association association) {
		String role = "";
		if (association.getFirstEnd().getTitle() != null && association.getFirstEnd().getTitle().length() > 0) {
			role = association.getFirstEnd().getTitle();
		} else if (association.getFirstEnd().getName() != null && association.getFirstEnd().getName().length() > 0) {
			role = association.getFirstEnd().getName();
		}
		return role;
	}

	/**
	 * Create FirstEnd and SecondEnd Object, link AssociationEnd with Clazz
	 * and register AssociationEnds to the association
	 * @param epart
	 * @param asso
	 */
	public static void createAssociationsEnds(AssociationEditPart epart, Association asso) {
		// get the association Edges
		GraphElement src = Utils.getSource((GraphEdge) epart.getModel());
		GraphElement trg = Utils.getTarget((GraphEdge) epart.getModel());

		AbstractClass sourceObject = (AbstractClass) Utils.getElement(src);
		AbstractClass targetObject = (AbstractClass) Utils.getElement(trg);
		
		// set firstEnd
		AssociationEnd fe = asso.getFirstEnd();		
		if (fe == null) {
			fe = ClazzFactory.eINSTANCE.createAssociationEnd();
			asso.setFirstEnd(fe);
		}
		fe.setLinkedClass(sourceObject);
		if (fe.getCardMax() == null) {
			fe.setCardMax("1");
		}
		if (fe.getCardMin() == null) {
			fe.setCardMin("0");
		}
		if (fe.getName() == null) {
			fe.setName("");
		}
		if (fe.getTitle() == null) {
			fe.setTitle("");
		}

		// setSecondEnd
		AssociationEnd se = asso.getSecondEnd();
		if (se == null) {
			se = ClazzFactory.eINSTANCE.createAssociationEnd();
			asso.setSecondEnd(se);
			se.setNavigable(true);
		}
		se.setLinkedClass(targetObject);
		if (se.getCardMax() == null) {
			se.setCardMax("1");
		}
		if (se.getCardMin() == null) {
			se.setCardMin("0");
		}
		if (se.getName() == null) {
			se.setName("");
		}
		if (se.getTitle() == null) {
			se.setTitle("");
		}
	}
}
