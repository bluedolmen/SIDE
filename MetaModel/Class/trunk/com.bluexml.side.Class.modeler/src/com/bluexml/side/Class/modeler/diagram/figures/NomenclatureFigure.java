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
/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerColorConstants;

/**
 * @generated
 */
public class NomenclatureFigure extends
		org.topcased.draw2d.figures.ContainerWithInnerLabel {
	
	/**
	 * Constructor
	 * 
	 * @generated
	 */
	public NomenclatureFigure() {
		super();
	}

	@Override
	public IFigure getBackgroundFigure() {
		IFigure fig = new RectangleFigure();
		fig.setMinimumSize(new Dimension(-1,-1));
		fig.setMaximumSize(new Dimension(-1,-1));
		fig.setPreferredSize(new Dimension(-1,-1));
		return fig;
	}
	
	@Override
	public Color getBackgroundColor() {
		return ModelerColorConstants.lightYellow;
	}
	
	@Override
	protected ILabel createLabel() {
		return new ComposedLabel(new Label(), new EditableLabel(),
				new Label(), false);
	}
}
