package com.bluexml.side.side.view.edit.ui.utils.outline.service;

import org.eclipse.emf.ecore.EObject;

public class OutlineViewService {
	
	protected static String nameOfSelectedForm = "";

	public static String getNameOfSelectedView(EObject element) {
		return nameOfSelectedForm;
	}
	
	public static void setNameOfSelectedView(String p_nameOfSelectedForm) {
		nameOfSelectedForm = p_nameOfSelectedForm;
	}
}
