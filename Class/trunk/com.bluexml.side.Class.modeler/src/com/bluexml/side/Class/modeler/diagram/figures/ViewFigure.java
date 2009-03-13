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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

import com.bluexml.side.Class.modeler.diagram.figures.common.RectangleFigure;

/**
 * @_generated
 */
public class ViewFigure extends
		org.topcased.draw2d.figures.ContainerWithInnerLabel {

	/**
	 * Constructor
	 *
	 * @generated
	 */
	public ViewFigure() {
		super();
	}

	@Override
	protected IFigure createBackgroundFigure() {
		return new RectangleFigure(this);
	}

	@Override
	protected ILabel createLabel() {
		return new ComposedLabel(new Label(), new Label(), new Label(), false);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		((RectangleFigure) getBackgroundFigure()).paint(graphics);
	}
}