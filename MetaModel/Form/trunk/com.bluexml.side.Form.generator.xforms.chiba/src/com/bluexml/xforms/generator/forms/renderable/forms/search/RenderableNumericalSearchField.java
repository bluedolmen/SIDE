/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import java.util.Stack;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.NumericalSearchField;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableStaticText;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchField;
import com.bluexml.xforms.messages.MsgId;

/**
 * The widget for NumericalSearchField's.
 * 
 * @author Amenel
 * 
 */
public class RenderableNumericalSearchField extends RenderableSearchField<NumericalSearchField> {

	/**
	 * @param generationManager
	 * @param parent
	 * @param formElement
	 */
	public RenderableNumericalSearchField(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		super(generationManager, parent, formElement);

		// add the elements of the widget
		add(new RenderableSearchOperators(this));
		add(new RenderableSearchInput(this, MsgId.INT_INSTANCE_SEARCH_VALUE_LO.getText()));
		add(new RenderableStaticText(generationManager, null, null, " - "));
		add(new RenderableSearchInput(this, MsgId.INT_INSTANCE_SEARCH_VALUE_HI.getText()));
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		Rendered rendered = new RenderedSearchField();
		return rendered;
	}

}
