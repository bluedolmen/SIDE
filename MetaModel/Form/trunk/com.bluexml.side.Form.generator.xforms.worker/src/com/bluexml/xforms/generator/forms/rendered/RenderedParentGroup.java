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
package com.bluexml.xforms.generator.forms.rendered;

import java.util.List;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;

/**
 * The Class RenderedParentGroup.
 */
public class RenderedParentGroup extends Rendered {

	/** The parent. */
	private Rendered parent = null;

	/**
	 * Instantiates a new rendered parent group.
	 * 
	 * @param renderedParents
	 *            the rendered parents
	 */
	public RenderedParentGroup(List<Rendered> renderedParents) {
		super();
		for (Rendered rendered : renderedParents) {
			if (rendered.isHolder()) {
				parent = rendered;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.forms.Rendered#addRendered(com.bluexml.xforms.generator.forms
	 * .Rendered, com.bluexml.xforms.generator.forms.Renderable)
	 */
	@Override
	public void addRendered(Rendered rendered, Renderable renderable) {
		parent.addRendered(rendered, renderable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#getChildren()
	 */
	@Override
	public List<Rendered> getChildren() {
		return parent.getChildren();
	}

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public Rendered getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return false;
	}

}
