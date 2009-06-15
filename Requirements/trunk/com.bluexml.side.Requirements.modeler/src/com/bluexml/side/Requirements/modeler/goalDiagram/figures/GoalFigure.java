/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;

/**
 * @generated
 */
public class GoalFigure extends
		org.topcased.draw2d.figures.ContainerWithInnerLabel {

	/**
	 * Constructor
	 * 
	 * @generated
	 */
	public GoalFigure() {
		super();
	}

	@Override
	protected IFigure createBackgroundFigure() {
		Ellipse fig = new Ellipse();
		fig.setOpaque(true);
		fig.setFill(true);
		fig.setLineStyle(Graphics.LINE_DOT);
		fig.setForegroundColor(new org.eclipse.swt.graphics.Color(null, 0, 102,
				104));
		return fig;
	}

	@Override
	protected ILabel createLabel() {
		return new EditableLabel();
	}

}