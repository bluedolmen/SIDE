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
package com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSDisplay;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSEdit;
import com.bluexml.xforms.generator.forms.rendered.RenderedRepeater;
import com.bluexml.xforms.messages.MsgId;

/**
 * The Class RenderableSMultipleDisplay.
 */
public class RenderableSMultipleDisplay extends AbstractRenderable {

	/** The edit button. */
	private RenderableSEdit editButton;

	/**
	 * Instantiates a new renderable s multiple display.
	 * 
	 * @param associationBean
	 *            the association bean
	 */
	public RenderableSMultipleDisplay(AssociationBean associationBean) {
		super(associationBean);
		add(new RenderableSDisplay(associationBean));
		if (associationBean.isShowingActions()) {
			editButton = new RenderableSEdit(associationBean, true);
			add(editButton);
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
		return ROOT_ABSOLUTE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		String repeaterId = renderedParents.peek().getOptionalData();
		ModelElementBindHolder bindRepeater = ((RenderableSMultiple) parents.peek())
				.getBindRepeater();
		RenderedRepeater renderedRepeater = new RenderedRepeater(bindRepeater, repeaterId);
		return renderedRepeater;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Renderable#getDivStyle()
	 */
	@Override
	public String getDivStyle() {
		return MsgId.INT_CSS_SELECT_SELECTED_ITEMS.getText();
	}

	
}
