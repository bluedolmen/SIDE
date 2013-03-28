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
package com.bluexml.xforms.generator.forms.renderable.classes;

import java.util.Stack;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.rendered.RenderedTab;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderableAspect.
 */
public class RenderableAspect extends Renderable {

	/** The label. */
	private String label;

	/** The name. */
	private String name;

	/** The renderable layout. */
	private RenderableLayout renderableLayout;

	/**
	 * Instantiates a new renderable aspect.
	 * 
	 * @param aspect
	 *            the aspect
	 */
	public RenderableAspect(Aspect aspect) {
		this.label = ModelTools.getTitle(aspect);
		this.name = aspect.getName();

		this.renderableLayout = new RenderableLayout(aspect);
		add(renderableLayout);

		EList<Attribute> attributes = aspect.getAttributes();
		for (Attribute attribute : attributes) {
			if (!ModelTools.isProperty(attribute, "hidden")) {
				renderableLayout
						.addAttribute(attribute, new RenderableAttribute(aspect, attribute));
			}
		}
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
		return new Path(PathType.relativePath, this.name + "/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		return new RenderedTab(label, doShowTab(renderedParents));
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return renderableLayout.isEmpty();
	}

}
