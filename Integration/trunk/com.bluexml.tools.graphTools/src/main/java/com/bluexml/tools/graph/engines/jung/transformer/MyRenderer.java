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