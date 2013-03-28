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
/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.StaticText;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.rendered.RenderedStaticText;

/**
 * @author Amenel
 * 
 */
public class RenderableStaticText extends RenderableFormElement<StaticText> {

	private String textToDisplay;

	/**
	 * 
	 * @param generationManager
	 * @param parent
	 * @param formElement
	 *            the element from the form model. May be <em>null</em> but if so, the text
	 *            parameter should be given
	 * @param text
	 *            the text to render in the XHTML template. Ignored if the form element is not null.
	 */
	public RenderableStaticText(XFormsGenerator generationManager, FormElement parent,
			StaticText formElement, String text) {
		super(generationManager, parent, formElement);
		this.textToDisplay = text;
	}

	@Override
	public void compute() {
		// nothing to do
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		String text = (formElement != null) ? formElement.getLabel() : textToDisplay;
		RenderedStaticText rendered = new RenderedStaticText(text);
		if (formElement != null) {
			applyStyle(rendered, formElement.getStyle());
		}
		return rendered;
	}

}
