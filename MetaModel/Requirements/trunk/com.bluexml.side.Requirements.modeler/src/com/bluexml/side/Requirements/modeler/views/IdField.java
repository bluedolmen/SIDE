package com.bluexml.side.Requirements.modeler.views;

import com.bluexml.side.Requirements.modeler.views.internal.AbstractField;
import com.bluexml.side.requirements.Annotation;

public class IdField extends AbstractField {

	public String getColumnHeaderText() {
		return "ID";
	}

	public String getDescription() {
		return "ID";
	}

	public int getPreferredWidth() {
		return 20;
	}

	public String getValue(Object obj) {
		if (obj instanceof Annotation) {
			Annotation a = (Annotation) obj;
			return a.getId();
		}
		return "Not defined";
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		if (obj1 instanceof Annotation && obj2 instanceof Annotation) {
			Annotation a1 = (Annotation) obj1;
			Annotation a2 = (Annotation) obj2;
			
			return Integer.valueOf(a1.getId()).compareTo(Integer.valueOf(a2.getId()));
		}
		return super.compare(obj1, obj2);
	}

}
