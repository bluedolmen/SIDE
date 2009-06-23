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