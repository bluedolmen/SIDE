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
package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.Reference;
import com.bluexml.xforms.generator.FormGeneratorsManager;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableFormContainer;
import com.bluexml.xforms.generator.forms.rendered.RenderedDiv;

@Deprecated
public class RenderableReferenceWithAC extends RenderableFormElement<Reference> {

	private int index;

	public RenderableReferenceWithAC(XFormsGenerator generationManager, FormElement parent,
			Reference formElement, RenderableFormContainer renderableTarget,
			RenderableFormContainer renderableAC, int index) {
		super(generationManager, parent, formElement);
		add(renderableTarget);
		add(renderableAC);
		this.index = index;
	}

	@Override
	public void compute() {
		// nothing
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return new Path(PathType.relativePath, "item" + index + "/");
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		return new RenderedDiv(XFormsGenerator.getId(FormGeneratorsManager
				.getUniqueName(formElement)
				+ "Item" + index));
	}

}
