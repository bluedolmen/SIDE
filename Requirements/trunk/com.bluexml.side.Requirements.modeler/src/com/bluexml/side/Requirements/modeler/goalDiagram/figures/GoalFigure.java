/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;

import com.bluexml.side.Requirements.modeler.goalDiagram.figures.ColorScaler.GreenScale;

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
		RectangleFigure fig = new RectangleFigure();
		fig.setOpaque(false);
		fig.setFill(false);
		return fig;
	}

	@Override
	protected ILabel createLabel() {
		return new EditableLabel();
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		ColorScaleUtil.paintFigure(graphics, this, new GreenScale());
	}

}