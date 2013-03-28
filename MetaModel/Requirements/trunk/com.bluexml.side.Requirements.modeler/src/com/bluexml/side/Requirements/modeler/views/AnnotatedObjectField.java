/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
