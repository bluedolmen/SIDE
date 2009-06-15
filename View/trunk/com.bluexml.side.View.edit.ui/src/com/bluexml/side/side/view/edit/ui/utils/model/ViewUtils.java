package com.bluexml.side.side.view.edit.ui.utils.model;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.view.AbstractView;

public class ViewUtils {
	public static AbstractView getViewForElement(EObject f) {
		AbstractView v = null;
		if (f instanceof AbstractView) {
			v = (AbstractView)f;
		} else {
			v = getViewForElement(f.eContainer());
		}
		return v;
	}
}
