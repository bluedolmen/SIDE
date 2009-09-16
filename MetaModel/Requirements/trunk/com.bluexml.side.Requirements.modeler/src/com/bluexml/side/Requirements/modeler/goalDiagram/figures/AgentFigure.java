/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.figures;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.swt.graphics.Image;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;

/**
 * @_generated
 */
public class AgentFigure extends ImageWithLabelFigure implements ILabelFigure {

	/**
	 * Constructor
	 *
	 * @_generated
	 */
	public AgentFigure() {
		super(new Image(null, AgentFigure.class
				.getResourceAsStream("img/agent.png")), PositionConstants.TOP);
	}

	@Override
	protected ILabel createLabel() {
		return new EditableLabel(true);
	}

}