/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import java.util.Stack;

import com.bluexml.side.form.CharSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchField;
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

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		// the bind
//		ModelElementBindHolder mebRoot = getBind();
////		String prefix = "instance('minstance')/";
//		String prefix = "";
//		String nodeset = prefix + path + formElement.getId();
//		mebRoot.setNodeset(nodeset);
		//
		Rendered rendered = new RenderedSearchField();
//		rendered.addModelElement(mebRoot);
		return rendered;
	}

}
