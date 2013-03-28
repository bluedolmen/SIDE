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
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.bluexml.tools.graph.engines.jung.transformer.GraphFilter;
import com.bluexml.tools.graph.model.Edge;
import com.bluexml.tools.graph.model.GraphObject;
import com.bluexml.tools.graph.model.Node;
import com.bluexml.tools.graph.model.io.graphviz.DotRenderer;
import com.bluexml.tools.graph.model.io.xml.XStreamUtils;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.DAGLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.VertexLabelAsShapeRenderer;

public class Test {

	static Logger logger = Logger.getLogger(Test.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		add(map, "Start", new String[] { "Verifier" });
		add(map, "Verifier", new String[] { "Valide" });
		add(map, "Valide", new String[] { "OK", "Verifier", "KO" });
		add(map, "OK", new String[] {});
		add(map, "KO", new String[] {});

//		GraphObject convert2graph = Utils.convert2graph(map);

		GraphObject convert2graph = null;
		try {
			// save as xml
//			XStreamUtils.save(convert2graph, new FileWriter(new File("processus.xml")));

			convert2graph = XStreamUtils.load(new FileReader(new File("processus.xml")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// test highlighting (on random node)
		convert2graph.getNodes().iterator().next().setFilterMatch(true);

		// using Jung
		Graph<Node, Edge> g3 = JungGraphBuilder.build(convert2graph);
		g3 = GraphFilter.applyVertexRemoveOrphelanFilter(g3);
		BasicVisualizationServer<Node, Edge> buildBasicVisualizationServer = JungGraphBuilder.buildBasicVisualizationServer(g3, "static", new Dimension(600, 600));

		try {

			// export to png
			File outputfile = new File("graph.png");
			VisualizationImageServer<Node, Edge> export = GraphExporter.export(buildBasicVisualizationServer, "png", new FileOutputStream(outputfile));

			// using Graphiz
			FileWriter fw = new FileWriter(new File("graph.dot"));
			DotRenderer dotRenderer = new DotRenderer(fw, convert2graph);
			dotRenderer.render();
			fw.close();
			
			
			Layout<Node, Edge> layout = export.getGraphLayout();
			// diplay in JFrame
			display(layout, export);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	public static void add(Map<String, List<String>> map, String node, String[] to) {
		List<String> value = new ArrayList<String>();
		for (String string : to) {
			value.add(string);
		}
		map.put(node, value);
	}

	public static void display(Graph<Node, Edge> g) {
		try {

			CircleLayout<Node, Edge> layout = new CircleLayout<Node, Edge>(g);
			render(layout);

			FRLayout<Node, Edge> layout3 = new FRLayout<Node, Edge>(g);
			render(layout3);

			FRLayout2<Node, Edge> layout4 = new FRLayout2<Node, Edge>(g);
			render(layout4);

			ISOMLayout<Node, Edge> layout2 = new ISOMLayout<Node, Edge>(g);
			render(layout2);

			KKLayout<Node, Edge> layout5 = new KKLayout<Node, Edge>(g);
			render(layout5);

			SpringLayout<Node, Edge> layout6 = new SpringLayout<Node, Edge>(g);
			render(layout6);

			DAGLayout<Node, Edge> layout7 = new DAGLayout<Node, Edge>(g);
			render(layout7);

			SpringLayout2<Node, Edge> layout8 = new SpringLayout2<Node, Edge>(g);
			render(layout8);

			//		StaticLayout<Node, String> layout9 = new StaticLayout<Node, Edge>(g);
			//		render(layout9);

			//		TreeLayout<Node, String> treeL = new TreeLayout<Node, String>(convertGraphToTree(g), 200, 50);
			//		render(treeL);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void render(Layout<Node, Edge> layout) {
		logger.info("rendering ...");
		try {
			layout.setSize(new Dimension(900, 900)); // sets the initial size of the	
		} catch (UnsupportedOperationException e) {

		}

		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<Node, Edge> vv = new BasicVisualizationServer<Node, Edge>(layout);
		vv.setPreferredSize(new Dimension(1000, 1000)); // Sets the viewing area
		// size
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

		//		VertexDisplayPredicateComplex vertexDisplayPredicateComplex = new VertexDisplayPredicateComplex(new String[] { "Class" }, new String[] {});
		//
		//		vv.getRenderContext().setVertexIncludePredicate(vertexDisplayPredicateComplex);

		VertexLabelAsShapeRenderer<Node, Edge> vlasr = new VertexLabelAsShapeRenderer<Node, Edge>(vv.getRenderContext());
		vv.getRenderContext().setVertexShapeTransformer(vlasr);
		vv.getRenderer().setVertexLabelRenderer(vlasr);
		logger.info("Nb of Visual Nodes :" + vv.getComponentCount());

		display(layout, vv);

	}

	private static void display(Layout<Node, Edge> layout, BasicVisualizationServer<Node, Edge> vv) {
		JFrame frame = new JFrame(layout.toString());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);

		// use double buffering until now

		// turn it off to capture
		vv.setDoubleBuffered(false);

		Graphics graphics = frame.getGraphics();
		vv.paintComponents(graphics);
	}

}
