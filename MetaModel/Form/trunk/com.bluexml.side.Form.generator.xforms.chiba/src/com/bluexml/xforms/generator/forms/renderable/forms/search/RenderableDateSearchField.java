/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import java.util.Stack;

import com.bluexml.side.form.DateSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchField;
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
		String type = MsgId.INT_TYPE_XSD_DATE.getText();
		// NOTE: force type to "date" even if "DateTime" because of incomplete support by Chiba
		
		// ModelElement ref = formElement.getRef();
		// DataType attrType = ((Attribute) getFormGenerator().getRealObject(ref)).getTyp();
		// if (attrType.equals(DataType.DATE_TIME)) {
		// type = MsgId.INT_TYPE_XSD_DATETIME.getText();
		// }
		rendLo.setType(type);
		rendHi.setType(type);

		add(rendLo);
		add(rendHi);
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		Rendered rendered = new RenderedSearchField();
		return rendered;
	}

}
