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
package com.bluexml.xforms.generator.forms.renderable.forms.field;

import org.apache.commons.lang.StringUtils;

import com.bluexml.side.form.CharField;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;

public class RenderableRegexInput<CF extends CharField> extends RenderableSimpleInput<CF> {

	protected static final String MAIL_REGEXP = "^(|[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+)$";

	protected static final String URL_REGEXP = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";

	private String regexp;

	public RenderableRegexInput(XFormsGenerator generationManager, FormElement parent,
			CF formElement, String regexp) {
		super(generationManager, parent, formElement, "string");
		this.regexp = StringUtils.trimToNull(regexp);
	}

	@Override
	protected void applyConstraints(ModelElementBindSimple meb) {
		super.applyConstraints(meb);
		if (regexp != null) {
			setConstraintRegexp(meb, regexp);
		}

	}

}
