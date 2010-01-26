package com.bluexml.side.Requirements.modeler.views;

import com.bluexml.side.Requirements.modeler.views.internal.AbstractField;
import com.bluexml.side.requirements.AnnotableElement;
import com.bluexml.side.requirements.Annotation;

public class AnnotatedObjectField extends AbstractField {

	public String getColumnHeaderText() {
		return "Annotated Object";
	}

	public String getDescription() {
		return "Annotated Object";
	}

	public int getPreferredWidth() {
		return 600;
	}

	public String getValue(Object obj) {
		if (obj instanceof Annotation) {
			Annotation a = (Annotation) obj;
			AnnotableElement elt = (AnnotableElement) a.eContainer();
			return "["+elt.eClass().getName()+"] "+elt.getName();
		}
		return "Not defined";
	}
}
