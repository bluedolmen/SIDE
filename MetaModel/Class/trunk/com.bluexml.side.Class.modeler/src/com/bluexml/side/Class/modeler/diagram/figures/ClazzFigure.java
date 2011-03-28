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

import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerColorConstants;

import com.bluexml.side.Class.modeler.diagram.figure.ColorScaleUtil;
import com.bluexml.side.Class.modeler.diagram.figure.colorScaler.YellowScaler;

/**
 * @generated
 */
public class ClazzFigure extends org.topcased.draw2d.figures.ClassFigure {
	private Ellipse view;

	private Ellipse layout;

	private Figure fig;

	/**
	 * Constructor
	 * 
	 * @_generated
	 */
	public ClazzFigure() {
		createOptions();
		drawFigure();
	}

	@Override
	protected void drawFigure() {

		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);

		setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(1)));
		setOpaque(true);

		setLabel(new ComposedLabel(new Label(), new EditableLabel(), new Label(), false));
		add(getLabel());

		if (fig == null)
			createOptions();
		add(fig);

		setContentPane(new Figure());
		getContentPane().setLayoutManager(new ToolbarLayout());
		add(getContentPane());
	}

	private void createOptions() {
		fig = new Figure();
		ToolbarLayout l = new ToolbarLayout(true);
		l.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
		fig.setLayoutManager(l);

		view = new Ellipse();
		view.setSize(10, 10);
		view.setBackgroundColor(ModelerColorConstants.lightBrown);
		view.setVisible(false);
		fig.add(view);

		layout = new Ellipse();
		layout.setSize(10, 10);
		layout.setBackgroundColor(ModelerColorConstants.lightPurple);
		layout.setVisible(false);
		fig.add(layout);
	}

	/**
	 * @return the layout
	 */
	public Ellipse getLayout() {
		return layout;
	}

	/**
	 * @return the view
	 */
	public Ellipse getView() {
		return view;
	}

	@Override
	public void repaint() {
		super.repaint();

		if (view != null) {
			view.repaint();
		}
		if (layout != null) {
			layout.repaint();
		}
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		ColorScaleUtil.paintFigure(graphics, this, new YellowScaler());
	}

}