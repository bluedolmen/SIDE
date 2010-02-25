/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import com.bluexml.side.form.ChoiceSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.messages.MsgId;

/**
 * The widget for ChoiceSearchField's.
 * 
 * @author Amenel
 * 
 */
public class RenderableChoiceSearchField extends RenderableSearchField<ChoiceSearchField> {

	public RenderableChoiceSearchField(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		super(generationManager, parent, formElement);

		RenderableSearchInput renderable = null;
		// add the elements of the widget
		add(new RenderableSearchOperators(this));
		renderable = new RenderableSearchInput(this, MsgId.INT_INSTANCE_SEARCH_VALUE.getText());
		renderable.setSelect(true); // necessary for rendering as a select list
		add(renderable);
	}

}
