/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import java.util.Stack;

import javax.xml.namespace.QName;

import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchInput;
import com.bluexml.xforms.messages.MsgId;

/**
 * An input for widgets of search fields.
 * 
 * @author Amenel
 * 
 */
public class RenderableSearchInput extends Renderable {
	RenderableSearchField<? extends SearchField> refField;
	String nodeset;

	/**
	 * 
	 */
	public RenderableSearchInput(RenderableSearchField<? extends SearchField> field, String nodeset) {
		this.refField = field;
		this.nodeset = nodeset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack, boolean)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		ModelElementBindSimple inputBind = new ModelElementBindSimple(path + nodeset);
		inputBind.setType(new QName(MsgId.INT_TYPE_XSD_STRING.getText()));

		RenderedSearchInput rendered = new RenderedSearchInput(inputBind, refField.getFormElement()
				.getId()
				+ "_" + nodeset);
		rendered.addModelElement(inputBind);
		return rendered;
	}

}
