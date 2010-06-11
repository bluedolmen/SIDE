/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import com.bluexml.side.form.CharSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.messages.MsgId;

/**
 * The widget for CharSearchField's.
 * 
 * @author Amenel
 * 
 */
public class RenderableCharSearchField extends RenderableSearchField<CharSearchField> {

	public RenderableCharSearchField(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		super(generationManager, parent, formElement);

		// add the elements of the widget
		add(new RenderableSearchOperators(this));
		add(new RenderableSearchInput(this, MsgId.INT_INSTANCE_SEARCH_VALUE.getText()));
	}

}
