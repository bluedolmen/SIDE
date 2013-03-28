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

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.bluexml.tools.graph.model.Edge;
import com.bluexml.tools.graph.model.GraphObject;
import com.bluexml.tools.graph.model.Node;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class JungGraphBuilder {
	static Logger logger = Logger.getLogger(JungGraphBuilder.class);

	public static edu.uci.ics.jung.graph.Graph<Node, Edge> build(GraphObject g) {
		edu.uci.ics.jung.graph.Graph<Node, Edge> g3 = null;

		g3 = new DirectedSparseMultigraph<Node, Edge>();

		for (Node node : g.getNodes()) {
			g3.addVertex(node);
		}

		for (Edge edge : g.getEdges()) {
			g3.addEdge(edge, edge.getNode1(), edge.getNode2(), edge.isDirected() ? EdgeType.DIRECTED : EdgeType.UNDIRECTED);
		}

		return g3;
	}

	public static BasicVisualizationServer<Node, Edge> buildBasicVisualizationServer(Graph<Node, Edge> g3, String layoutType, Dimension minimal) {

		Layout<Node, Edge> layout = null;
		if (layoutType == null) {
			layout = new CircleLayout<Node, Edge>(g3);
		} else if (layoutType.equals("forest")) {
			layout = new TreeLayout<Node, Edge>((Forest<Node, Edge>) g3);
		} else if (layoutType.equals("static")) {
			layout = new StaticLayout<Node, Edge>(g3);

			buildGrid((DirectedSparseMultigraph) g3, layout);

		}

		// compute dimension
		int h = new Double(minimal.getHeight() > layout.getSize().getHeight() ? minimal.getHeight() : layout.getSize().getHeight()).intValue();
		int w = new Double(minimal.getWidth() > layout.getSize().getWidth() ? minimal.getWidth() : layout.getSize().getWidth()).intValue();
		Dimension finalDimension = new Dimension(w, h);

		layout.setSize(finalDimension); // sets the initial size of the space

		// The BasicVisualizationServer<V,E> is parameterized by the edge types 
		BasicVisualizationServer<Node, Edge> vv = new BasicVisualizationServer<Node, Edge>(layout);
		vv.setPreferredSize(finalDimension); //Sets the viewing area size 
		vv.setSize(w, h);

		//		Test.display(g3);
		return vv;
	}

	public static Map<Node, Point> buildGrid(DirectedSparseMultigraph<Node, Edge> graph, Layout<Node, Edge> layout) {
		Map<Node, Point> grid = new HashMap<Node, Point>();

		Map<Integer, List<Node>> gridA = new TreeMap<Integer, List<Node>>();

		// scan graph to group Vertex by predecessorCount
		Collection<Node> vertices = graph.getVertices();
		for (Node node : vertices) {
			int predecessorCount = graph.getPredecessorCount(node);
			logger.debug("Node " + node + " predecessor :" + predecessorCount);
			if (gridA.containsKey(predecessorCount)) {
				gridA.get(predecessorCount).add(node);
			} else {
				ArrayList<Node> l = new ArrayList<Node>();
				l.add(node);
				gridA.put(predecessorCount, l);
			}
		}

		// get Max width (number of columns)
		int marginL = 10;
		int marginR = 10;
		int boxSize = 30;
		int interWidth = 150;
		int columns = gridA.keySet().size();
		int maxWeight = computeMaxSize(marginL, marginR, boxSize, interWidth, columns);
		logger.debug("Max weigth :" + maxWeight);

		// get Max height
		
		int marginU = 10;
		int marginD = 10;
		int boxSizeH = 30;
		int interH = 50;
		
		int rows = 0;
		// get the max row
		Collection<List<Node>> values = gridA.values();

		for (List<Node> list : values) {
			if (list.size() > rows) {
				rows = list.size();
			}
		}
		int maxHeight = computeMaxSize(marginU, marginD, boxSizeH, interH, rows);
		logger.debug("Max height :" + maxHeight);

		int currentColumn = 0;
		for (List<Node> list : values) {
			int xbase = computePosition(marginL, boxSize, interWidth, currentColumn);

			for (int i = 0; i < list.size(); i++) {
				int x = xbase;
				Node node = list.get(i);
				int y = computePosition(marginU, boxSizeH, interH, i);
//				if (graph.getSuccessorCount(node) == 0) {
					//					x += boxSize + interWiedgth;
					//										y = maxHeight / 2;
//				}
				xbase += boxSize + interWidth/2;
				
				Point value = new Point(x, y);
				grid.put(node, value);
				layout.setLocation(node, value);
			}
			currentColumn++;
		}

		layout.setSize(new Dimension(maxWeight, maxHeight)); // sets the initial size of the space

		if (logger.isDebugEnabled()) {
			logger.debug("GridA");
			Set<Entry<Integer, List<Node>>> entrySeta = gridA.entrySet();
			for (Entry<Integer, List<Node>> entry : entrySeta) {
				logger.debug("Key " + entry.getKey());
				List<Node> value = entry.getValue();
				for (Node node : value) {
					logger.debug("\t " + node);
				}
			}

			logger.debug("Final Grid");
			Set<Entry<Node, Point>> entrySet = grid.entrySet();
			for (Entry<Node, Point> entry : entrySet) {
				Point value = entry.getValue();
				logger.debug("Node :" + entry.getKey() + ", point :" + value);
			}
		}
		return grid;
	}

	private static int computeMaxSize(int margin1, int margin2, int boxSize, int inter, int itemCount) {
		return margin1 + itemCount * boxSize + (itemCount - 1) * inter + margin2;
	}

	private static int computePosition(int margin, int boxSize, int inter, int itemNumber) {
		return margin + itemNumber * (boxSize + inter);
	}
}
