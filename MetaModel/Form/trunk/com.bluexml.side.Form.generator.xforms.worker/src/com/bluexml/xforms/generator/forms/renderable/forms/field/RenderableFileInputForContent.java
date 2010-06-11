/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import javax.xml.namespace.QName;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.rendered.RenderedFileInputForContent;
import com.bluexml.xforms.messages.MsgId;

/**
 * Defines the implicit form control for uploading node content.
 * 
 * @author Amenel
 * 
 */
public class RenderableFileInputForContent extends Renderable {
	ModelElementBindSimple meb;

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
		meb = new ModelElementBindSimple(path + MsgId.INT_INSTANCE_SIDE_NODE_CONTENT.getText());
		meb.setType(new QName("anyURI"));
		return new RenderedFileInputForContent(meb);
	}

}
