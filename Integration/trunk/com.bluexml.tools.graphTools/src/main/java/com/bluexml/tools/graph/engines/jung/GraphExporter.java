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
package com.bluexml.tools.graph.engines.jung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.bluexml.tools.graph.engines.jung.transformer.GraphElementLabelTrasnformer;
import com.bluexml.tools.graph.engines.jung.transformer.MyRenderer;

import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.AbstractEdgeShapeTransformer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.Vertex;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel;

public class GraphExporter {
	static Logger logger = Logger.getLogger(GraphExporter.class);

	public static <Node, Edge> VisualizationImageServer<Node, Edge> export(BasicVisualizationServer<Node, Edge> vv, String imageType, OutputStream output) {
		// Create the VisualizationImageServer
		// vv is the VisualizationViewer containing my graph
		VisualizationImageServer<Node, Edge> vis = new VisualizationImageServer<Node, Edge>(vv.getGraphLayout(), vv.getGraphLayout().getSize());

		// Configure the VisualizationImageServer the same way
		// you did your VisualizationViewer. In my case e.g.

		GraphElementLabelTrasnformer<Edge> edgeStringer = new GraphElementLabelTrasnformer<Edge>();

		AbstractEdgeShapeTransformer<Node, Edge> edgeShapeTransformer = null;
		edgeShapeTransformer = new EdgeShape.BentLine<Node, Edge>();
		//		edgeShapeTransformer = new EdgeShape.Box<Node, Edge>();
		//		edgeShapeTransformer = new EdgeShape.CubicCurve<Node, Edge>();
		//		edgeShapeTransformer = new EdgeShape.Line<Node, Edge>();
		//		edgeShapeTransformer = new EdgeShape.Loop<Node, Edge>();
		//		edgeShapeTransformer = new EdgeShape.Orthogonal<Node, Edge>();
		//		edgeShapeTransformer = new EdgeShape.QuadCurve<Node, Edge>();
		//		edgeShapeTransformer = new EdgeShape.SimpleLoop<Node, Edge>();

		GraphElementLabelTrasnformer<Node> vertexStringer = new GraphElementLabelTrasnformer<Node>();
		
		VertexLabel<Node, Edge> vertexLabelRenderer = vis.getRenderer().getVertexLabelRenderer();
		
		vis.setBackground(Color.WHITE);
		vis.getRenderContext().setEdgeLabelTransformer(edgeStringer);
//		vis.getRenderContext().setEdgeShapeTransformer(edgeShapeTransformer);
		vis.getRenderContext().setVertexLabelTransformer(vertexStringer);
		vertexLabelRenderer.setPosition(Renderer.VertexLabel.Position.AUTO);

		Vertex<Node, Edge> r = (Vertex<Node, Edge>) new MyRenderer<Node, Edge>(20, 20);
		
		vis.getRenderer().setVertexRenderer(r);
		
		
		
		
		
		// Create the buffered image
		BufferedImage image = (BufferedImage) vis.getImage(new Point2D.Double(vv.getGraphLayout().getSize().getWidth() / 2, vv.getGraphLayout().getSize().getHeight() / 2), new Dimension(vv.getGraphLayout().getSize()));
		
		try {
			ImageIO.write(image, imageType, output);
		} catch (IOException e) {
			logger.error("error", e);
		}
		return vis;
	}
}
