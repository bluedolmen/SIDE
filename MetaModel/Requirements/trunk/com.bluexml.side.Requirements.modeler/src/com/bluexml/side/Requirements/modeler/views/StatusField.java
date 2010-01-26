package com.bluexml.side.Requirements.modeler.views;

import com.bluexml.side.Requirements.modeler.views.internal.AbstractField;
import com.bluexml.side.requirements.Annotation;

public class StatusField extends AbstractField {

	public String getColumnHeaderText() {
		return "Status";
	}

	public String getDescription() {
		return "Status";
	}

	public int getPreferredWidth() {
		return 100;
	}

	public String getValue(Object obj) {
		if (obj instanceof Annotation) {
			Annotation a = (Annotation) obj;
			return a.getStatus().getLiteral();
		}
		return "Not defined";
	}


}
