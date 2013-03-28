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
package com.bluexml.side.build.tools.graph;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.apache.commons.collections15.Predicate;
import org.apache.log4j.Logger;

import com.bluexml.side.build.tools.componants.Componant;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.VertexLabelAsShapeRenderer;

public class DisplayGraph {
	static Logger logger = Logger.getLogger(DisplayGraph.class);

	public static void display(Graph<Componant, String> g) throws Exception {

		CircleLayout<Componant, String> layout = new CircleLayout<Componant, String>(g);
		render(layout);

		FRLayout<Componant, String> layout3 = new FRLayout<Componant, String>(g);
		render(layout3);
		//
		//		FRLayout2<Componant, String> layout4 = new FRLayout2<Componant, String>(g);
		//		render(layout4);
		//
		//		ISOMLayout<Componant, String> layout2 = new ISOMLayout<Componant, String>(g);
		//		render(layout2);
		//
		//		KKLayout<Componant, String> layout5 = new KKLayout<Componant, String>(g);
		//		render(layout5);
		//
		//		SpringLayout<Componant, String> layout6 = new SpringLayout<Componant, String>(g);
		//		render(layout6);
		//
		//		DAGLayout<Componant, String> layout7 = new DAGLayout<Componant, String>(g);
		//		render(layout7);

		SpringLayout2<Componant, String> layout8 = new SpringLayout2<Componant, String>(g);
		render(layout8);

		//		StaticLayout<Componant, String> layout9 = new StaticLayout<Componant, String>(g);
		//		render(layout9);

		TreeLayout<Componant, String> treeL = new TreeLayout<Componant, String>(convertGraphToTree(g), 200, 50);
		render(treeL);
	}

	private static void render(Layout<Componant, String> layout) {
		logger.info("rendering ...");
		try {
			layout.setSize(new Dimension(900, 900)); // sets the initial size of the	
		} catch (UnsupportedOperationException e) {

		}

		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<Componant, String> vv = new BasicVisualizationServer<Componant, String>(layout);
		vv.setPreferredSize(new Dimension(1000, 1000)); // Sets the viewing area
		// size
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		

		//		VertexDisplayPredicateComplex vertexDisplayPredicateComplex = new VertexDisplayPredicateComplex(new String[] { "Class" }, new String[] {});
		//
		//		vv.getRenderContext().setVertexIncludePredicate(vertexDisplayPredicateComplex);

		VertexLabelAsShapeRenderer<Componant, String> vlasr = new VertexLabelAsShapeRenderer<Componant, String>(vv.getRenderContext());
		vv.getRenderContext().setVertexShapeTransformer(vlasr);
		vv.getRenderer().setVertexLabelRenderer(vlasr);
		logger.info("Nb of Visual Componants :" + vv.getComponentCount());

		JFrame frame = new JFrame(layout.toString());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);

		// use double buffering until now

		// turn it off to capture
		vv.setDoubleBuffered(false);

		// capture: create a BufferedImage
		// create the Graphics2D object that paints to it
		// Graphics2D replaced_graphics2D = frame.getG
		Graphics graphics = frame.getGraphics();
		vv.paintComponents(graphics);

	}

	private final static class VertexDisplayPredicate implements Predicate<Context<Graph<Componant, String>, Componant>>
	//	extends  AbstractGraphPredicate<V,E>
	{
		protected String filter_small;

		public VertexDisplayPredicate(String filter) {
			this.filter_small = filter;
		}

		public void filterSmall(String b) {
			filter_small = b;
		}

		public boolean evaluate(Context<Graph<Componant, String>, Componant> context) {
			Graph<Componant, String> graph = context.graph;
			Object v = context.element;
			return v.toString().contains(filter_small);
		}
	}

	private final static class VertexDisplayPredicateComplex implements Predicate<Context<Graph<Componant, String>, Componant>>
	//	extends  AbstractGraphPredicate<V,E>
	{
		String[] filterInclude;
		String[] filterexclude;

		public VertexDisplayPredicateComplex(String[] filterInclude, String[] exclude) {
			this.filterInclude = filterInclude;
			this.filterexclude = exclude;

		}

		public boolean evaluate(Context<Graph<Componant, String>, Componant> context) {
			Graph<Componant, String> graph = context.graph;
			Object v = context.element;

			String label = v.toString().toLowerCase();

			// test if included
			boolean include = false;
			for (String s : filterInclude) {
				include |= label.contains(s.toLowerCase());
			}

			boolean exclude = false;
			for (String s : filterexclude) {
				exclude |= label.contains(s.toLowerCase());
			}

			return include && !exclude;
		}
	}

	public static DelegateForest<Componant, String> convertGraphToTree(Graph<Componant, String> graph) {
		DelegateForest<Componant, String> mTree = new DelegateForest<Componant, String>();

		for (Componant n : graph.getVertices()) {
			mTree.addVertex(n);
		}
		for (String e : graph.getEdges()) {
			mTree.addEdge(e, graph.getSource(e), graph.getDest(e));
		}
		return mTree;
	}
}
