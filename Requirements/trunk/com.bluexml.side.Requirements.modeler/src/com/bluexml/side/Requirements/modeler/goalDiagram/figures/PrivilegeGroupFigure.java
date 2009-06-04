/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.figures;

import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.topcased.draw2d.figures.Label;


/**
 * @generated
 */
public class PrivilegeGroupFigure extends
		org.topcased.draw2d.figures.ClassFigure {

	/**
	 * The constructor
	 *
	 * @generated
	 */
	public PrivilegeGroupFigure() {
		super();
	}
	
	@Override
	protected void drawFigure() {

		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);

		setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(1)));
		setOpaque(true);
		
		setLabel(new Label());
		add(getLabel());

		setContentPane(new Figure());
		getContentPane().setLayoutManager(new ToolbarLayout());
		add(getContentPane());
	}

}