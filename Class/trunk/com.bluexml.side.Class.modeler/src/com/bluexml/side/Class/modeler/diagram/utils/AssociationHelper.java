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

import com.bluexml.side.Class.modeler.diagram.dialogs.view.ViewContainerType;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Clazz;


public class AssociationHelper {
	public static final String	ASSOCIATION_NAME			= "association name";
	
	public static final String	ASSOCIATION_TITLE			= "association title";
	
	public static final String	ASSOCIATION_DESCRIPTION		= "association description";
	
	public static final String	ASSOCIATION_TYPE			= "association type";
	
	public static final String	FIRST_END_CLASS				= "first end class";
	
	public static final String	FIRST_END_ROLE				= "first end role";
	
	public static final String	FIRST_END_IS_NAVIGABLE		= "first end is navigable";
	
	public static final String	FIRST_END_IS_ORDERED		= "first end is ordered";
	
	public static final String	FIRST_END_VISIBILITY_KIND	= "first end visibility kind";
	
	public static final String	FIRST_END_LOWER_BOUND		= "first end lower bound";
	
	public static final String	FIRST_END_UPPER_BOUND		= "first end upper bound";
	
	public static final String	SECOND_END_CLASS			= "second end class";
	
	public static final String	SECOND_END_ROLE				= "second end role";
	
	public static final String	SECOND_END_IS_NAVIGABLE		= "second end is navigable";
	
	public static final String	SECOND_END_IS_ORDERED		= "second end is ordered";
	
	public static final String	SECOND_END_VISIBILITY_KIND	= "second end visibility kind";
	
	public static final String	SECOND_END_LOWER_BOUND		= "second end lower bound";
	
	public static final String	SECOND_END_UPPER_BOUND		= "second end upper bound";
	
	public static final String	META_INFO					= "meta info";
	
	public static final String	ASSOCIATION_ROLE_SRC		= "association role source";
	
	public static final String	ASSOCIATION_ROLE_TARGET		= "association role target";
	
	public static final String	ASSOCIATION_ROLE_SRC_TITLE		= "association role source title";
	
	public static final String	ASSOCIATION_ROLE_TARGET_TITLE		= "association role target title";
	
	/**
	 * Return all the informations concerning the Association
	 * 
	 * @return Map
	 */
	public static Map<String, Object> extractAssociationData(Association asso) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put(AssociationHelper.ASSOCIATION_NAME, asso.getName());
		resultMap.put(AssociationHelper.ASSOCIATION_TYPE, asso.getAssociationType());
		
		resultMap.put(AssociationHelper.FIRST_END_IS_NAVIGABLE, new Boolean(asso.isIsNavigableSRC()));
		resultMap.put(AssociationHelper.FIRST_END_LOWER_BOUND, asso.getMinSRC());
		resultMap.put(AssociationHelper.FIRST_END_UPPER_BOUND, asso.getMaxSRC());
		
		resultMap.put(AssociationHelper.SECOND_END_IS_NAVIGABLE, new Boolean(asso.isIsNavigableTARGET()));
		resultMap.put(AssociationHelper.SECOND_END_LOWER_BOUND, asso.getMinSRC());
		resultMap.put(AssociationHelper.SECOND_END_UPPER_BOUND, asso.getMaxSRC());
		
		return resultMap;
	}
	
	public static String getRoleTargetOrTitle(Association association) {
		String role = "";
		if (association.getRoleTargetTitle() != null && association.getRoleTargetTitle().length() > 0) {
			role = association.getRoleTargetTitle();
		} else if (association.getRoleTarget() != null && association.getRoleTarget().length() > 0) {
			role = association.getRoleTarget();
		}
		return role;
	}

	
	public static String getViewItemContainerName(Clazz cl,Association association, ViewContainerType viewContainerType,String role_) {
		if (association == null) {
			return cl.getName();
		} else {
			String name = cl.getName() + "[" + association.getName() + "]";
			String associationViewRole = AssociationHelper.getAssociationViewRole(cl, association, viewContainerType, role_);
			if (!associationViewRole.equals("")) {
				name += "(" + associationViewRole + ")";
			}
			
			return name;
		}
	}
	
	public static String getAssociationViewRole(Clazz cl,Association association, ViewContainerType viewContainerType,String role_) {
		String role = "";
		
		if ((viewContainerType.equals(ViewContainerType.ASSOCIATION_RECUR) || viewContainerType.equals(ViewContainerType.ASSOCIATION_CLASS_RECUR)) && role_ !=null) {
			if (AssociationHelper.getRoleTargetOrTitle(association).equals(role_)) {
				role = AssociationHelper.getRoleTargetOrTitle(association);
			} else if (AssociationHelper.getRoleSrcOrTitle(association).equals(role_)) {
				role = AssociationHelper.getRoleSrcOrTitle(association);
			}
		} else if (cl.equals(association.getDestination())) {
			role = AssociationHelper.getRoleTargetOrTitle(association);
		} else {
			role = AssociationHelper.getRoleSrcOrTitle(association);
		}
		
		return role;
	}
	
	public static String getRoleSrcOrTitle(Association association) {
		String role = "";
		if (association.getRoleSrcTitle() != null && association.getRoleSrcTitle().length() > 0) {
			role = association.getRoleSrcTitle();
		} else if (association.getRoleSrc() != null && association.getRoleSrc().length() > 0) {
			role = association.getRoleSrc();
		}
		return role;
	}
}
