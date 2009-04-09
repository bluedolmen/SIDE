package com.bluexml.side.form.editor.views.service;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.utils.FormDiagramUtils;

public class OutlineViewService {
	
	protected static String nameOfSelectedForm = "";

	public static String getNameOfSelectedForm(EObject element) {
		return nameOfSelectedForm;
	}
	
	public static void setNameOfSelectedForm(String p_nameOfSelectedForm) {
		nameOfSelectedForm = p_nameOfSelectedForm;
	}
	
	/**
	 * Will search the field to know if this field have been virtualized
	 * @param element
	 */
	public static boolean isVirtualized(EObject element) {
		boolean isVirtualized = false;
		if (element instanceof Field) {
			Field f = (Field) element;
			isVirtualized = FormDiagramUtils.IsVirtualized(f);
		}
		return isVirtualized;
	}
}
