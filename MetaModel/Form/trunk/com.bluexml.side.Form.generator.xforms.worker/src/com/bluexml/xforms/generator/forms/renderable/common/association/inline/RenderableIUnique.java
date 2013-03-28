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
package com.bluexml.xforms.generator.forms.renderable.common.association.inline;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedGroup;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderableIUnique.
 */
public class RenderableIUnique extends AbstractRenderable {

	/** The own group. */
	private boolean ownGroup;

	/** The return to line. */
	private boolean returnToLine;

	/**
	 * Instantiates a new renderable i unique.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param ownGroup
	 *            the own group
	 * @param returnToLine
	 *            the return to line
	 */
	public RenderableIUnique(AssociationBean associationBean, boolean ownGroup, boolean returnToLine) {
		super(associationBean);
		add(associationBean.getDestinationRenderable());
		this.ownGroup = ownGroup;
		this.returnToLine = returnToLine;
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
		String path = "";
		return new Path(PathType.relativePath, path);
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
		if (ownGroup) {
			String groupTitle = ModelTools.getTitle(bean.getDestinationClass());
			if (bean.getTitle() != null) { // #1279
				groupTitle = bean.getTitle();
			}
			rendered = new RenderedGroup(groupTitle, ModelTools.getCompleteNameJAXB(bean
					.getDestinationClass()), null);
			if (returnToLine) {
				rendered.setReturnToLine(true);
			}
		} else {
			rendered = new RenderedParentGroup(renderedParents);
		}
		return rendered;
	}

}
