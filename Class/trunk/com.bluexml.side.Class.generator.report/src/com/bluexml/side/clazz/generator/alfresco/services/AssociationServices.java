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
import java.util.HashSet;

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
		return getTopPackage(a.getSource()) == getTopPackage(a.getDestination());
	}

	/**
	 * Return true if the association endings are Clazzs It enables one to
	 * remove the generation of associations which are between tasks and Clazzs
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isAssociationBetweenClazzs(Association a) {
		return a.getSource().eClass().getName() == "Clazz" && a.getDestination().eClass().getName() == "Clazz";
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
	public static boolean isSource(Association a, ClassModelElement elt) {

		Collection<ClassModelElement> allInheritedClazzs = new HashSet<ClassModelElement>();
		if (elt instanceof Clazz) {
			Clazz cl = (Clazz) elt;
			allInheritedClazzs.addAll(cl.getInheritedClasses());
		}
		allInheritedClazzs.add(elt);

		if (!(elt.equals(a.getSource()) || elt.equals(a.getDestination())))
			if (allInheritedClazzs.contains(a.getSource()))
				elt = a.getSource();
			else if (allInheritedClazzs.contains(a.getDestination()))
				elt = a.getDestination();

		if (a.isIsNavigableTARGET() && a.getSource() == elt) {
			return true;
		} else if (a.isIsNavigableSRC() && a.getDestination() == elt) {
			return true;
		}
		return false;
	}

	public boolean isMandatorySrc(Association a, ClassModelElement elt) {
		if (a.isIsNavigableTARGET() && a.getSource() == elt) {
			return Integer.valueOf(a.getMinSRC()) > 0;
		} else if (a.isIsNavigableSRC() && a.getDestination() == elt) {
			return Integer.valueOf(a.getMinTARGET()) > 0;
		}
		return false;
	}

	public boolean isManySrc(Association a, ClassModelElement elt) {
		if (a.isIsNavigableTARGET() && a.getSource() == elt) {
			return Integer.valueOf(a.getMaxSRC()) > 1 || Integer.valueOf(a.getMaxSRC()) == -1;
		} else if (a.isIsNavigableSRC() && a.getDestination() == elt) {
			return Integer.valueOf(a.getMaxTARGET()) > 1 || Integer.valueOf(a.getMaxTARGET()) == -1;
		}
		return false;
	}

	public boolean isMandatoryTarget(Association a, ClassModelElement elt) {
		if (a.isIsNavigableTARGET() && a.getSource() == elt) {
			return Integer.valueOf(a.getMinTARGET()) > 0;
		} else if (a.isIsNavigableSRC() && a.getDestination() == elt) {
			return Integer.valueOf(a.getMinSRC()) > 0;
		}
		return false;
	}

	public boolean isManyTarget(Association a, ClassModelElement elt) {
		if (a.getSource() == elt) {
			return Integer.valueOf(a.getMaxTARGET()) > 1 || Integer.valueOf(a.getMaxTARGET()) == -1;
		} else if (a.getDestination() == elt) {
			return Integer.valueOf(a.getMaxSRC()) > 1 || Integer.valueOf(a.getMaxSRC()) == -1;
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
		if (a.getSource().equals(elt)) {
			if (a.getDestination() instanceof Aspect) {
				Aspect aspect = (Aspect) a.getDestination();
				return aspect;
			}
			if (a.getDestination() instanceof Clazz) {
				Clazz Clazz = (Clazz) a.getDestination();
				return Clazz;
			}
		} else if (a.getDestination().equals(elt)) {
			if (a.getSource() instanceof Aspect) {
				Aspect aspect = (Aspect) a.getSource();
				return aspect;
			}
			if (a.getSource() instanceof Clazz) {
				Clazz Clazz = (Clazz) a.getSource();
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
	 * for A->B is A but beware a.getSource() is the start of the line draw
	 * between Clazz not the source ! must use navigation to avoid mistake
	 * 
	 * @param a
	 * @param elt
	 *            the source association
	 * @return
	 * @throws Exception
	 */
	public static AbstractClass getRealSource(Association a, ClassModelElement elt) throws Exception {
		if (a.getSource().equals(elt) && a.getDestination().equals(elt)) {
			if (a.isIsNavigableSRC() || a.isIsNavigableTARGET()) {
				return (AbstractClass) elt;
			}
		} else if (a.getSource().equals(elt)) {
			if (a.isIsNavigableTARGET()) {
				// elt is the source
				return (AbstractClass) elt;
			}
		} else if (a.getDestination().equals(elt)) {
			if (a.isIsNavigableSRC()) {
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
			for (Object obj : c.getAssociations()) {
				if (obj instanceof Association) {
					Association asso = (Association) obj;
					if (asso.isIsNavigableTARGET() && asso.getSource() == c) {
						return true;
					} else if (asso.isIsNavigableSRC() && asso.getDestination() == c) {
						return true;
					}
				}
			}
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
			if (a.getSource() == c || a.getDestination() == c) {
				associationName = constructAssociationName(a, c, reverse);
			} else {
				// We must find the parent class
				Collection<Clazz> s = c.getInheritedClasses();
				for (Clazz Clazz : s) {
					if (a.getSource() == Clazz || a.getDestination() == Clazz) {
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
		if (a.getDestination() == c && !reverse) {
			if (a.getRoleTarget() != null && !"".equalsIgnoreCase(a.getRoleTarget())) {
				associationName += "_" + a.getRoleTarget();
			}
			associationName += "_" + ((Clazz) a.getSource()).getFullName().replace(".", "_");
		} else {
			if (a.getRoleSrc() != null && !"".equalsIgnoreCase(a.getRoleSrc())) {
				associationName += "_" + a.getRoleSrc();
			}
			associationName += "_" + ((Clazz) a.getDestination()).getFullName().replace(".", "_");
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

		 if (assoc.getRoleTarget() != null && !"".equalsIgnoreCase(assoc.getRoleTarget()) && !reverse) {
			associationName += "_" + assoc.getRoleTarget();
		 } else {
			 if (assoc.getRoleSrc() != null && !"".equalsIgnoreCase(assoc.getRoleSrc())) {
				 associationName += "_" + assoc.getRoleSrc();
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

		if (assoc.getRoleTarget() != null && !reverse && !"".equalsIgnoreCase(assoc.getRoleTarget())) {
			associationName += "_" + assoc.getRoleTarget();
		} else if (assoc.getRoleTarget() != null && reverse && !"".equalsIgnoreCase(assoc.getRoleTarget())) {
			associationName += "_" + assoc.getRoleSrc();
		}

		associationName += "_" + b.getFullName().replace(".", "_");

		// When we have a class associated to itself with a class association to
		// itself too
		if (c == b) {
			associationName += "_" + assoc.getRoleSrc();
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
		if (a.getDestination() == e || a.getSource() == e) {
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
					if (a.getSource() == Clazz || a.getDestination() == Clazz) {
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
		if (a.getDestination() == c && reverse) {
			if (a.getRoleSrcTitle() != null) {
				title = a.getRoleSrcTitle();
			} else {
				if (a.getTitle() != null) {
					title = a.getTitle();
				} else {
					title = a.getRoleSrc();
				}
			}
			// If e is target, check if there is a role title
		} else if (a.getSource() == c) {
			if (a.getRoleTargetTitle() != null) {
				title = a.getRoleTargetTitle();
			} else {
				if (a.getTitle() != null) {
					title = a.getTitle();
				} else {
					title = a.getRoleTarget();
				}
			}
		}
		return title;
	}

	public String getRole(Association a, ClassModelElement e) throws Exception {
		if (a.getSource().equals(e)) {
			return a.getRoleSrc();
		} else if (a.getDestination().equals(e)) {
			return a.getRoleTarget();
		} else {
			throw new Exception("Bad ClassModelElement, not found in this association :" + a);
		}
	}

	public Clazz getAssociationClass(Association a) {
		return a.getAssociationsClass().get(0);
	}
}