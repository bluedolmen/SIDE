/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.search;

import java.util.Stack;

import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableSearchField;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchOperators;
import com.bluexml.xforms.messages.MsgId;

/**
 * A generic element for the list of operators of a search field.
 * 
 * @author Amenel
 * 
 */
public class RenderableSearchOperators extends Renderable {

	RenderableSearchField<? extends SearchField> refField;

	/**
	 * 
	 */
	public RenderableSearchOperators(RenderableSearchField<? extends SearchField> field) {
		refField = field;
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
		String nodeset = MsgId.INT_INSTANCE_SEARCH_OPCODE.getText();
		ModelElementBindSimple opBind = new ModelElementBindSimple(nodeset);
		refField.getBind().addSubBind(opBind);

		String opSetId = getFormGenerator().getOperatorListId(refField);
		String opFileId = MsgId.INT_PREFIX_FILENAME_OPERATORS + opSetId;
		String opInstanceId = MsgId.INT_PREFIX_INSTANCE_OPERATORS + opSetId;
		return new RenderedSearchOperators(opBind, refField.getFormElement().getId() + "_"
				+ nodeset, opFileId, opInstanceId, refField.getFormElement().getLabel());
	}

}
