package com.bluexml.side.Requirements.modeler.views;

import com.bluexml.side.Requirements.modeler.views.internal.AbstractField;
import com.bluexml.side.requirements.Annotation;

public class DateField extends AbstractField {

	public String getColumnHeaderText() {
		return "Date";
	}

	public String getDescription() {
		return "Annotation's date";
	}

	public int getPreferredWidth() {
		return 200;
	}

	public String getValue(Object obj) {
		if (obj instanceof Annotation) {
			Annotation a = (Annotation) obj;
			return a.getDate().toLocaleString();
		}
		return "Not defined";
	}


}
