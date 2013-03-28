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
package com.bluexml.tools.graph.engines.jung.transformer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.bluexml.tools.graph.model.GraphElement;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

public class MyRenderer<V, E> implements Renderer.Vertex<V, E> {
	int height;
	int width;

	public MyRenderer(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public void paintVertex(RenderContext<V, E> rc, Layout<V, E> layout, V vertex) {
		GraphicsDecorator graphicsContext = rc.getGraphicsContext();
		Point2D center = layout.transform(vertex);
		Shape shape = null;
		Color color = null;

		String transform = rc.getVertexLabelTransformer().transform(vertex);
//		width = transform.length() * 7;
		
		shape = new Rectangle((int) center.getX() - width / 2, (int) center.getY() - height / 2, width, height);
		shape = new Ellipse2D.Double(center.getX() - width / 2, center.getY() - height / 2, width, height);
		if (vertex instanceof GraphElement && ((GraphElement) vertex).isFilterMatch()) {
			GraphElement graphElement = (GraphElement) vertex;			
			
			color = new Color(127, 127, 0);

		} else {
//			shape = new Ellipse2D.Double(center.getX() - width / 2, center.getY() - height / 2, width, height);
			color = new Color(0, 127, 127);
		}
		
		
		graphicsContext.setPaint(color);
		graphicsContext.fill(shape);
		
		
		
		
	}
}
