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
/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import com.bluexml.side.form.DateSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.messages.MsgId;

/**
 * The widget for DateSearchField's.
 * 
 * @author Amenel
 * 
 */
public class RenderableDateSearchField extends RenderableSearchField<DateSearchField> {

	public RenderableDateSearchField(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		super(generationManager, parent, formElement);

		RenderableSearchInput rendLo = null;
		RenderableSearchInput rendHi = null;
		// add the elements of the widget
		add(new RenderableSearchOperators(this));
		rendLo = new RenderableSearchInput(this, MsgId.INT_INSTANCE_SEARCH_VALUE_LO.getText());
		rendHi = new RenderableSearchInput(this, MsgId.INT_INSTANCE_SEARCH_VALUE_HI.getText());

		// set the appropriate data type; necessary for having the calendar on the forms
		// NOTE: force type to "date" even if "DateTime" because of incomplete support by Chiba
		String type = MsgId.INT_TYPE_XSD_DATE.getText();
		rendLo.setType(type);
		rendHi.setType(type);

		add(rendLo);
		add(rendHi);
	}

}
