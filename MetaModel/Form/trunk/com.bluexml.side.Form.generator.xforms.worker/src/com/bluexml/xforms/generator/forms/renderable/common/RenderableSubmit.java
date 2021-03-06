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
package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;

/**
 * The Class RenderableSubmit.
 */
public class RenderableSubmit extends Renderable {

	/** The label. */
	private String label;

	/** The submission. */
	private ModelElementSubmission submission;

	private boolean hideIfReadOnlyMode; // #1238

	/**
	 * Instantiates a new renderable submit.
	 * 
	 * @param submission
	 *            the submission
	 * @param label
	 *            the label
	 */
	public RenderableSubmit(ModelElementSubmission submission, String label,
			boolean hideIfReadOnlyMode) {
		super();
		this.submission = submission;
		this.label = label;
		this.hideIfReadOnlyMode = hideIfReadOnlyMode;
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
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		RenderedXMLElement rendered = new RenderedXMLElement();

		if (hideIfReadOnlyMode && getFormGenerator().isInReadOnlyMode()) {
			return rendered;
		}
		Element submit = XFormsGenerator.createElementWithLabel("submit",
				XFormsGenerator.NAMESPACE_XFORMS, label);
		submission.addLinkedElement(submit);
		rendered.setXformsElement(submit);

		return rendered;
	}

}
