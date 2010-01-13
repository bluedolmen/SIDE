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
package com.bluexml.side.clazz.generator.alfresco.services;
 
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.ClassModelElement;
import com.bluexml.side.clazz.Clazz;



public class AssociationServices {

	/**
	 * Private methode returning the top-package of a class
	 */
	private static EObject getTopPackage(EObject o) {
		if (o.eContainer() == null) {
			return o;
		} else {
			return getTopPackage(o.eContainer());
		}
	}

	/**
	 * Return true if the extremities of the association are in the same
	 * top-package Used for determining if an association should be generated
	 * (e.g. in the SQL statements)
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isInnerAssociation(Association a) {
		return getTopPackage(a.getFirstEnd().getLinkedClass()) == getTopPackage(a.getSecondEnd().getLinkedClass());
	}

	/**
	 * Return true if the association endings are Clazzs It enables one to
	 * remove the generation of associations which are between tasks and Clazzs
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isAssociationBetweenClazzs(Association a) {
		return a.getFirstEnd().getLinkedClass().eClass().getName() == "Clazz" && a.getSecondEnd().getLinkedClass().eClass().getName() == "Clazz";
	}

	public static boolean isContainment(Association a) {
		return (a.getAssociationType() == AssociationType.COMPOSITION);
	}

	/**
	 * Return true if the association "a" is navigable from "elt"
	 * 
	 * @param a
	 * @param elt
	 * @return
	 */
	public static boolean isSource(Association a, Clazz elt) {
		return elt.isSource(a);		
	}

	public boolean isMandatorySrc(Association a, ClassModelElement elt) {
		if (a.getSecondEnd().isNavigable() && a.getFirstEnd().getLinkedClass() == elt) {
			return Integer.valueOf(a.getFirstEnd().getCardMin()) > 0;
		} else if (a.getFirstEnd().isNavigable() && a.getSecondEnd().getLinkedClass() == elt) {
			return Integer.valueOf(a.getSecondEnd().getCardMin()) > 0;
		}
		return false;
	}

	public boolean isManySrc(Association a, ClassModelElement elt) {
		if (a.getSecondEnd().isNavigable() && a.getFirstEnd().getLinkedClass() == elt) {
			return Integer.valueOf(a.getFirstEnd().getCardMax()) > 1 || Integer.valueOf(a.getFirstEnd().getCardMax()) == -1;
		} else if (a.getFirstEnd().isNavigable() && a.getSecondEnd().getLinkedClass() == elt) {
			return Integer.valueOf(a.getSecondEnd().getCardMax()) > 1 || Integer.valueOf(a.getSecondEnd().getCardMax()) == -1;
		}
		return false;
	}

	public boolean isMandatoryTarget(Association a, ClassModelElement elt) {
		if (a.getSecondEnd().isNavigable() && a.getFirstEnd().getLinkedClass() == elt) {
			return Integer.valueOf(a.getSecondEnd().getCardMin()) > 0;
		} else if (a.getFirstEnd().isNavigable() && a.getSecondEnd().getLinkedClass() == elt) {
			return Integer.valueOf(a.getFirstEnd().getCardMin()) > 0;
		}
		return false;
	}

	public boolean isManyTarget(Association a, ClassModelElement elt) {
		if (a.getFirstEnd().getLinkedClass() == elt) {
			return Integer.valueOf(a.getSecondEnd().getCardMax()) > 1 || Integer.valueOf(a.getSecondEnd().getCardMax()) == -1;
		} else if (a.getSecondEnd().getLinkedClass() == elt) {
			return Integer.valueOf(a.getFirstEnd().getCardMax()) > 1 || Integer.valueOf(a.getFirstEnd().getCardMax()) == -1;
		}
		return false;
	}

	/**
	 * 
	 * @param a
	 * @param elt
	 * @return
	 */
	public AbstractClass getTarget(Association a, ClassModelElement elt) throws Exception {
		if (a.getFirstEnd().getLinkedClass().equals(elt)) {
			if (a.getSecondEnd().getLinkedClass() instanceof Aspect) {
				Aspect aspect = (Aspect) a.getSecondEnd().getLinkedClass();
				return aspect;
			}
			if (a.getSecondEnd().getLinkedClass() instanceof Clazz) {
				Clazz Clazz = (Clazz) a.getSecondEnd().getLinkedClass();
				return Clazz;
			}
		} else if (a.getSecondEnd().getLinkedClass().equals(elt)) {
			if (a.getFirstEnd().getLinkedClass() instanceof Aspect) {
				Aspect aspect = (Aspect) a.getFirstEnd().getLinkedClass();
				return aspect;
			}
			if (a.getFirstEnd().getLinkedClass() instanceof Clazz) {
				Clazz Clazz = (Clazz) a.getFirstEnd().getLinkedClass();
				return Clazz;
			}
		} else {
			if (elt instanceof Clazz) {
				// maybe Association is defined in superClass
				// TODO : check if multi-Generalizations is available in
				// Alfresco
				Clazz Clazz = ((Clazz) elt);				
				EList<Clazz> parents = Clazz.getGeneralizations();
				if (parents.size() > 0) {
					return getTarget(a, parents.get(0));
				}
			}
			throw new Exception("bad ClassModelElement, must be source or destination");
		}
		return null;
	}

	/**
	 * get the Clazz source where the Clazz have the associations the source
	 * for A->B is A but beware a.getFirstEnd().getLinkedClass() is the start of the line draw
	 * between Clazz not the source ! must use navigation to avoid mistake
	 * 
	 * @param a
	 * @param elt
	 *            the source association
	 * @return
	 * @throws Exception
	 */
	public static AbstractClass getRealSource(Association a, ClassModelElement elt) throws Exception {
		if (a.getFirstEnd().getLinkedClass().equals(elt) && a.getSecondEnd().getLinkedClass().equals(elt)) {
			if (a.getFirstEnd().isNavigable() || a.getSecondEnd().isNavigable()) {
				return (AbstractClass) elt;
			}
		} else if (a.getFirstEnd().getLinkedClass().equals(elt)) {
			if (a.getSecondEnd().isNavigable()) {
				// elt is the source
				return (AbstractClass) elt;
			}
		} else if (a.getSecondEnd().getLinkedClass().equals(elt)) {
			if (a.getFirstEnd().isNavigable()) {
				return (AbstractClass) elt;
			}
		} else {
			if (elt instanceof Clazz) {
				// maybe Association is defined in superClass
				// TODO : check if multi-Generalizations is available in
				// Alfresco
				Clazz Clazz = ((Clazz) elt);
				EList<Clazz> parents = Clazz.getGeneralizations();
				if (parents.size() > 0) {
					return getRealSource(a, parents.get(0));
				}
			}
			throw new Exception("bad ClassModelElement, must be source or destination");
		}
		return null;
	}

	public boolean isSourceOfAssociation(ClassModelElement elt) {
		if (elt instanceof Clazz) {
			Clazz c = (Clazz) elt;			
			return c.getAllSourceAssociations().size() >0;			
		}
		return false;
	}
	/**
	 * Return the associaton name.
	 * 
	 * @param a
	 * @param e
	 * @return
	 */
	public static String getAssociationName(Association a, ClassModelElement e) {
		return getName(a, e, false);
	}

	public static String getQualifiedNameReverse(Association a, ClassModelElement e) {
		return getName(a, e, true);
	}

	public static String getName(Association a, ClassModelElement e, boolean reverse) {
		String associationName = "";
		if (e instanceof Clazz) {
			Clazz c = (Clazz) e;
			// Check if association is on this class or is inherited :
			if (a.getFirstEnd().getLinkedClass() == c || a.getSecondEnd().getLinkedClass() == c) {
				associationName = constructAssociationName(a, c, reverse);
			} else {
				// We must find the parent class
				Collection<Clazz> s = c.getInheritedClasses();
				for (Clazz Clazz : s) {
					if (a.getFirstEnd().getLinkedClass() == Clazz || a.getSecondEnd().getLinkedClass() == Clazz) {
						associationName = constructAssociationName(a, Clazz, reverse);
					}
				}
			}
		}
		return associationName;
	}

	/**
	 * Construct normal association name
	 * ClazzSource_AssociationName_[roleName_]ClazzTarget
	 * 
	 * @param a
	 * @param c
	 * @return
	 */
	public static String constructAssociationName(Association a, Clazz c, boolean reverse) {
		String associationName = "";

		associationName = c.getFullName().replace(".", "_") + "_" + a.getName();
		if (a.getSecondEnd().getLinkedClass() == c && !reverse) {
			if (a.getSecondEnd().getName() != null && !"".equalsIgnoreCase(a.getSecondEnd().getName())) {
				associationName += "_" + a.getSecondEnd().getName();
			}
			associationName += "_" + ((Clazz) a.getFirstEnd().getLinkedClass()).getFullName().replace(".", "_");
		} else {
			if (a.getFirstEnd().getName() != null && !"".equalsIgnoreCase(a.getFirstEnd().getName())) {
				associationName += "_" + a.getFirstEnd().getName();
			}
			associationName += "_" + ((Clazz) a.getSecondEnd().getLinkedClass()).getFullName().replace(".", "_");
		}

		return associationName;
	}

	public String getNameForClassAssociationAC(Clazz c, Association assoc, Clazz a) {
		return getNameForClassAssociationAC(c,assoc, a, false);
	}
	public String getNameForClassAssociationAC(Association assoc,Clazz c, Clazz a) {
		return getNameForClassAssociationAC(c,assoc, a, false);
	}
	
	
	
	public String getNameForClassAssociationACReverse(Clazz c, Association assoc, Clazz a) {
		return getNameForClassAssociationAC(c,assoc, a, true);
	}
	public String getNameForClassAssociationACReverse(Association assoc,Clazz c, Clazz a) {
		return getNameForClassAssociationAC(c,assoc, a, true);
	}
	
	/**
	 * Return the name for an association class (C) for the association A -> C
	 * 
	 * @param c
	 * @param assoc
	 * @param a
	 * @return
	 */
	public String getNameForClassAssociationAC(Clazz c, Association assoc, Clazz a, boolean reverse) {
		String associationName = "";

		associationName = a.getFullName().replace(".", "_") + "_" + assoc.getName();

		 if (assoc.getSecondEnd().getName() != null && !"".equalsIgnoreCase(assoc.getSecondEnd().getName()) && !reverse) {
			associationName += "_" + assoc.getSecondEnd().getName();
		 } else {
			 if (assoc.getFirstEnd().getName() != null && !"".equalsIgnoreCase(assoc.getFirstEnd().getName())) {
				 associationName += "_" + assoc.getFirstEnd().getName();
		}
		 }

		associationName += "_" + c.getFullName().replace(".", "_");

		return associationName;
	}

	public String getNameForClassAssociationCB(Clazz c, Association assoc, Clazz b) {
		return getNameForClassAssociationCB(assoc, c, b, false);
	}

	
	
	public String getNameForClassAssociationCB(Association assoc, Clazz c, Clazz b) {
		return getNameForClassAssociationCB(assoc, c, b, false);
	}

	/**
	 * Give the name for the association between C and B
	 * 
	 * @param assoc
	 * @param c
	 * @param b
	 * @return
	 */
	public String getNameForClassAssociationCB(Association assoc, Clazz c, Clazz b, boolean reverse) {
		String associationName = "";

		associationName = c.getFullName().replace(".", "_") + "_" + assoc.getName() + "_CA";

		if (assoc.getSecondEnd().getName() != null && !reverse && !"".equalsIgnoreCase(assoc.getSecondEnd().getName())) {
			associationName += "_" + assoc.getSecondEnd().getName();
		} else if (assoc.getSecondEnd().getName() != null && reverse && !"".equalsIgnoreCase(assoc.getSecondEnd().getName())) {
			associationName += "_" + assoc.getFirstEnd().getName();
		}

		associationName += "_" + b.getFullName().replace(".", "_");

		// When we have a class associated to itself with a class association to
		// itself too
		if (c == b) {
			associationName += "_" + assoc.getFirstEnd().getName();
		}

		return associationName;
	}

	public String getRoleOrTitle(Association a, ClassModelElement e) throws Exception {
		return getRoleOrTitle(a, e, false);
	}

	public String getRoleOrTitleReverse(Association a, ClassModelElement e) throws Exception {
		return getRoleOrTitle(a, e, true);
	}

	/**
	 * Give the role of a class in the given association
	 * 
	 * @param a
	 * @param e,
	 *            element
	 * @return
	 */
	public String getRoleOrTitle(Association a, ClassModelElement e, boolean reverse) throws Exception {
		String title = "";
		// If e is destination, check if there is a role title
		if (a.getSecondEnd().getLinkedClass() == e || a.getFirstEnd().getLinkedClass() == e) {
			if (e instanceof Clazz) {
				Clazz c = (Clazz) e;
				title = constructTitleFromRole(a, c, reverse);
			}
		} else {
			// We must find the parent class
			if (e instanceof Clazz) {
				Clazz c = (Clazz) e;
				Collection<Clazz> s = c.getInheritedClasses();
				for (Clazz Clazz : s) {
					if (a.getFirstEnd().getLinkedClass() == Clazz || a.getSecondEnd().getLinkedClass() == Clazz) {
						title = constructTitleFromRole(a, Clazz, reverse);
					}
				}
			}
		}

		if ("".equalsIgnoreCase(title) || title == null) {
			if (a.getTitle() != null && !"".equalsIgnoreCase(a.getTitle())) {

				title = a.getTitle();
			} else {
				title = a.getName();
			}
		}

		return title;
	}

	/**
	 * Give the association title, get the role, title, ...
	 * 
	 * @param a
	 * @param c
	 * @return
	 */
	public String constructTitleFromRole(Association a, Clazz c, boolean reverse) {
		String title = "";
		if (a.getSecondEnd().getLinkedClass() == c && reverse) {
			if (a.getFirstEnd().getTitle() != null) {
				title = a.getFirstEnd().getTitle();
			} else {
				if (a.getTitle() != null) {
					title = a.getTitle();
				} else {
					title = a.getFirstEnd().getName();
				}
			}
			// If e is target, check if there is a role title
		} else if (a.getFirstEnd().getLinkedClass() == c) {
			if (a.getSecondEnd().getTitle() != null) {
				title = a.getSecondEnd().getTitle();
			} else {
				if (a.getTitle() != null) {
					title = a.getTitle();
				} else {
					title = a.getSecondEnd().getName();
				}
			}
		}
		return title;
	}

	public String getRole(Association a, ClassModelElement e) throws Exception {
		if (a.getFirstEnd().getLinkedClass().equals(e)) {
			return a.getFirstEnd().getName();
		} else if (a.getSecondEnd().getLinkedClass().equals(e)) {
			return a.getSecondEnd().getName();
		} else {
			throw new Exception("Bad ClassModelElement, not found in this association :" + a);
		}
	}

	
}