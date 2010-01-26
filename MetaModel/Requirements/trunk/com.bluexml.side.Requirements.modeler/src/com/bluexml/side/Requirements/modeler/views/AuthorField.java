package com.bluexml.side.Requirements.modeler.views;

import com.bluexml.side.Requirements.modeler.views.internal.AbstractField;
import com.bluexml.side.requirements.Annotation;

public class AuthorField extends AbstractField {

	public String getColumnHeaderText() {
		return "Author";
	}

	public String getDescription() {
		return "Annotation's author";
	}

	public int getPreferredWidth() {
		return 250;
	}

	public String getValue(Object obj) {
		if (obj instanceof Annotation) {
			Annotation a = (Annotation) obj;
			return a.getAuthor();
		}
		return "Not defined";
	}


}
