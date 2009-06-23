package com.bluexml.side.form.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.VirtualField;

public class FormDiagramUtils {

	/**
	 * Return the parent form class
	 */
	public static FormContainer getParentFormContainer(FormElement fe) {
		FormContainer fc = null;
		if (fe != null && fe instanceof FormContainer) {
			return (FormContainer) fe;
		} else {
			if (fe != null) {
				return getParentFormContainer((FormElement) fe.eContainer());
			}
		}
		return fc;
	}

	/**
	 * Return the parent form class
	 */
	public static FormClass getParentFormClass(FormElement fe) {
		FormContainer fc = getParentFormContainer(fe);
		FormClass result = null;
		if (fc instanceof FormClass) {
			result = (FormClass) fc;
		}
		return result;
	}

	/**
	 * Get the parent forme
	 * 
	 * @param fe
	 * @return
	 */
	public static FormContainer getParentForm(FormElement fe) {
		FormContainer f = null;
		if (fe.eContainer() != null && fe.eContainer() instanceof FormContainer) {
			f = (FormContainer) fe.eContainer();
		} else {
			if (fe.eContainer() != null) {
				f = getParentForm((FormElement) fe.eContainer());
			}
		}
		return f;
	}

	/**
	 * Get the parent form collection
	 * 
	 * @param eo
	 * @return
	 */
	public static FormCollection getParentFormCollection(EObject eo) {
		FormCollection fc = null;
		if (eo.eContainer() != null
				&& eo.eContainer() instanceof FormCollection) {
			fc = (FormCollection) eo.eContainer();
		} else {
			if (eo.eContainer() != null) {
				fc = getParentFormCollection(eo.eContainer());
			}
		}
		return fc;
	}

	private static List<FormClass> visited;
	private static List<Reference> path;

	/**
	 * Return true if o have a reference to t.
	 * 
	 * @param o
	 * @param t
	 * @return
	 */
	public static boolean haveReferenceTo(FormClass o, FormGroup t) {
		visited = new ArrayList<FormClass>();
		path = new ArrayList<Reference>();
		boolean referenceBetween = haveReferenceBetween(o, t);
		return referenceBetween;
	}

	/**
	 * Return true if o have a reference to t.
	 * 
	 * @param o
	 * @param t
	 * @return
	 */
	private static boolean haveReferenceBetween(FormClass o, FormGroup t) {
		boolean haveReference = false;

		if (!visited.contains(o)) {
			visited.add(o);
			// For each form element of the formclass, formgroup, aspect, ...
			for (FormElement fe : t.getChildren()) {
				if (!haveReference) {
					// If we have a reference
					if (fe instanceof Reference) {
						Reference ref = (Reference) fe;
						// Does reference the origin formclass?
						if (ref.getTarget().contains(o)) {
							haveReference = true;
							path.add(ref);
						} else {
							for (FormGroup fc : ref.getTarget()) {
								haveReference = haveReferenceTo(o, fc);
								if (haveReference) {
									path.add(ref);
								}
							}
						}
					} else if (fe instanceof FormGroup
							&& !(fe instanceof FormClass)) {
						haveReference = haveReferenceTo(o, (FormGroup) fe);
					}
				}
			}
		}
		return haveReference;
	}

	/**
	 * Return the list of reference between a FormClass and a FormGroup
	 * 
	 * @param o
	 * @param t
	 * @return
	 */
	public static List<Reference> getReferenceBetween(FormClass o, FormGroup t) {
		if (path == null || path.size() == 0) {
			haveReferenceTo(o, t);
		}
		return path;
	}

	/**
	 * Return all the Field and FormAspect of a FormGroup in a map id => Form
	 * Element
	 * 
	 * @param fc
	 * @return
	 */
	public static HashMap<String, FormElement> getFormChild(FormGroup fc) {
		HashMap<String, FormElement> listFormElem = new HashMap<String, FormElement>();
		if (fc != null) {
			for (FormElement fe : fc.getChildren()) {
				if (fe instanceof FormGroup) {
					if (fe instanceof FormAspect) {
						listFormElem.put(fe.getId(), fe);
					}
					listFormElem.putAll(getFormChild((FormGroup) fe));
				} else {
					listFormElem.put(fe.getId(), fe);
				}
			}
			for (FormElement fe : fc.getDisabled()) {
				if (fe instanceof FormGroup) {
					if (fe instanceof FormAspect) {
						listFormElem.put(fe.getId(), fe);
					}
					listFormElem.putAll(getFormChild((FormGroup) fe));
				} else {
					listFormElem.put(fe.getId(), fe);
				}
			}
		}
		return listFormElem;
	}

	/**
	 * Will return true is the field have been virtualized in any other form.
	 * 
	 * @return
	 */
	public static boolean IsVirtualized(Field f) {
		return (getVirtualizedFields(f).size() > 0 ? true : false);
	}

	/**
	 * Return the list of virtual field of the given field in the given
	 * formgroup
	 * 
	 * @param form
	 * @param f
	 * @return
	 */
	public static List<VirtualField> getVirtualizedFieldsForGroup(
			FormGroup form, Field f) {
		List<VirtualField> lvf = new ArrayList<VirtualField>();
		for (FormElement fe : form.getChildren()) {
			if (fe instanceof FormGroup) {
				lvf.addAll(getVirtualizedFieldsForGroup((FormGroup) fe, f));
			} else if (fe instanceof VirtualField) {
				if (((VirtualField) fe).getLink().equals(f)) {
					lvf.add((VirtualField) fe);
				}
			}
		}
		return lvf;
	}

	/**
	 * Return the list of virtual field of the given field
	 * 
	 * @param f
	 * @return
	 */
	public static List<VirtualField> getVirtualizedFields(Field f) {
		List<VirtualField> lvf = new ArrayList<VirtualField>();
		FormCollection fc = getParentFormCollection((EObject) f);
		for (FormContainer form : fc.getForms()) {
			lvf.addAll(getVirtualizedFieldsForGroup((FormGroup) form, f));
		}
		return lvf;
	}
}
