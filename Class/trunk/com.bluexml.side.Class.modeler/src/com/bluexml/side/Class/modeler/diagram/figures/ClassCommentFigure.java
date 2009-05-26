/*******************************************************************************
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

/**
 * @generated
 */
public class ClassCommentFigure extends org.topcased.draw2d.figures.ContainerWithInnerLabel {
	/**
	 * Constructor
	 *
	 * @generated
	 */
	public ClassCommentFigure() {
		super();
	}

	@Override
	protected ILabel createLabel() {
		return new ComposedLabel(new Label(), new EditableLabel(), new Label(), false);
	}

	@Override
	protected IFigure createBackgroundFigure() {
		return new org.topcased.draw2d.figures.CommentFigure();
	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		Dimension d = super.getPreferredSize(wHint, hHint);
		d.width = getLabel().getPreferredSize().width + 25;
		return d;
	}
}