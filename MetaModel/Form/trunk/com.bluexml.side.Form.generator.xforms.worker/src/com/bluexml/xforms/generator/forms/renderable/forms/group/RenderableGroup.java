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
package com.bluexml.xforms.generator.forms.renderable.forms.group;

import java.util.Stack;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormGroupPresentationType;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableFormElement;
import com.bluexml.xforms.generator.forms.rendered.RenderedDiv;
import com.bluexml.xforms.generator.forms.rendered.RenderedGroup;
import com.bluexml.xforms.generator.forms.rendered.RenderedLine;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;

/**
 * The Class RenderableGroup.
 */
public class RenderableGroup<G extends FormGroup> extends RenderableFormElement<G> {

	private String styleClass = null;
	/**
	 * Instantiates a new renderable group.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param group
	 *            the group
	 */
	public RenderableGroup(XFormsGenerator generationManager, FormElement parent, G group) {
		super(generationManager, parent, group);
		styleClass = group.getStyle();
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
		Rendered rendered = null;
		if (formElement.getId() == null) {
			throw new RuntimeException("Form element " + formElement.getLabel() + " has no id!");
		}
		if (formElement.getPresentation() == FormGroupPresentationType.TABBED) {
			rendered = new RenderedParentGroup(renderedParents);
		} else if (formElement.getPresentation() == FormGroupPresentationType.HORIZONTAL) {
			rendered = new RenderedLine();
		} else if (formElement.getPresentation() == FormGroupPresentationType.VERTICAL) {
			rendered = new RenderedDiv(XFormsGenerator.getId(formElement.getId()));
		} else if (formElement.getPresentation() == FormGroupPresentationType.BORDERLESS) {
			rendered = new RenderedDiv(XFormsGenerator.getId(formElement.getId()));
		} else {
			rendered = new RenderedGroup(formElement.getLabel(), XFormsGenerator.getId(formElement
					.getId()), styleClass);
//			applyStyle(rendered, MsgId.INT_CSS_FORM_TITLE.getText());
		}
		return rendered;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.forms.RenderableFormElement#compute()
	 */
	@Override
	public void compute() {
		addFormElements(formElement.getChildren());
	}

	/**
	 * @param styleClass the styleClass to set
	 */
	protected void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @return the styleClass
	 */
	protected String getStyleClass() {
		return styleClass;
	}

}
