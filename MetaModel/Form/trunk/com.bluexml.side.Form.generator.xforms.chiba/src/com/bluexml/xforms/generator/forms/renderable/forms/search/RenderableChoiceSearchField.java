/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import java.util.Stack;

import com.bluexml.side.form.ChoiceSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchField;
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

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		Rendered rendered = new RenderedSearchField();
		return rendered;
	}

}
