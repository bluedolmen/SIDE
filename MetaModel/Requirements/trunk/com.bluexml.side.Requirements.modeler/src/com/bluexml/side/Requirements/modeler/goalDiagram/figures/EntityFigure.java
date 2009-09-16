/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.figures;

import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;

import com.bluexml.side.Requirements.modeler.goalDiagram.figures.ColorScaler.GreyScale;

/**
 * @generated
 */
public class EntityFigure extends org.topcased.draw2d.figures.ClassFigure {
	/**
	 * Constructor
	 *
	 * @generated
	 */
	public EntityFigure() {
		super();
	}

	@Override
	protected void drawFigure() {
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);

		setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(1)));
		setOpaque(true);

		setLabel(new EditableLabel(true));
		add(getLabel());

		setContentPane(new Figure());
		getContentPane().setLayoutManager(new ToolbarLayout());
		add(getContentPane());
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		ColorScaleUtil.paintFigure(graphics, this, new GreyScale());
	}
}