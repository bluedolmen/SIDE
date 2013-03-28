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

import com.bluexml.side.clazz.Association;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationProperties;
import com.bluexml.xforms.generator.forms.renderable.common.CommonRenderableAssociation;

/**
 * The Class RenderableAssociation.
 */
public class RenderableAssociation extends CommonRenderableAssociation {

	/** The association. */
	private Association association;

	/**
	 * Instantiates a new renderable association.
	 * 
	 * @param properties
	 *            the properties
	 * @param association
	 *            the association
	 */
	public RenderableAssociation(AssociationProperties properties, Association association) {
		super(properties);
		this.association = association;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#shouldRender(java.util.Stack)
	 */
	@Override
	protected boolean shouldRender(Stack<Renderable> parents) {
		boolean render = true;
		for (Renderable renderable : parents) {
			if (renderable instanceof RenderableAssociation) {
				RenderableAssociation renderableAssociation = (RenderableAssociation) renderable;
				if (renderableAssociation.association == this.association) {
					return false;
				}
			}
		}
		return render;
	}

}
