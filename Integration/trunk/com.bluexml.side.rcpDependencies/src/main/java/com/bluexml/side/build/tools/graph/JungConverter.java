package com.bluexml.side.build.tools.graph;

import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections15.Transformer;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.bluexml.side.build.tools.componants.Componant;
import com.bluexml.side.build.tools.graph.test.MyLink;
import com.bluexml.side.build.tools.graph.test.MyNode;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.io.GraphMLReader;
import edu.uci.ics.jung.io.GraphMLWriter;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class JungConverter {
	static Logger logger = Logger.getLogger(JungConverter.class);

	public static void main(String[] args) {

		// Graph<V, E> where V is the type of the vertices 
		// and E is the type of the edges 
		Graph<Integer, String> g = new SparseMultigraph<Integer, String>();
		// Add some vertices. From above we defined these to be type Integer. 
		g.addVertex((Integer) 1);
		g.addVertex((Integer) 2);
		g.addVertex((Integer) 3);
		// Add some edges. From above we defined these to be of type String 
		// Note that the default is for undirected edges. 
		g.addEdge("Edge-A", 1, 2); // Note that Java 1.5 auto-boxes primitives 
		g.addEdge("Edge-B", 2, 3);
		// Let's see what we have. Note the nice output from the 
		// SparseMultigraph<V,E> toString() method 
		System.out.println("The graph g = " + g.toString());
		// Note that we can use the same nodes and edges in two different graphs. 
		Graph<Integer, String> g2 = new SparseMultigraph<Integer, String>();
		g2.addVertex((Integer) 1);
		g2.addVertex((Integer) 2);
		g2.addVertex((Integer) 3);
		g2.addEdge("Edge-A", 1, 3);
		g2.addEdge("Edge-B", 2, 3, EdgeType.DIRECTED);
		g2.addEdge("Edge-C", 3, 2, EdgeType.DIRECTED);
		g2.addEdge("Edge-P", 2, 3); // A parallel edge 
		System.out.println("The graph g2 = " + g2.toString());

		DirectedSparseMultigraph<MyNode, MyLink> g3 = new DirectedSparseMultigraph<MyNode, MyLink>();
		// Create some MyNode objects to use as vertices
		MyNode n1, n2, n3, n4, n5;
		n1 = new MyNode(1);
		n2 = new MyNode(2);
		n3 = new MyNode(3);
		n4 = new MyNode(4);
		n5 = new MyNode(5); // note n1-n5 declared elsewhere. 
		// Add some directed edges along with the vertices to the graph 
		g3.addEdge(new MyLink(2.0, 48), n1, n2, EdgeType.DIRECTED); // This method 
		g3.addEdge(new MyLink(2.0, 48), n2, n3, EdgeType.DIRECTED);
		g3.addEdge(new MyLink(3.0, 192), n3, n5, EdgeType.DIRECTED);
		g3.addEdge(new MyLink(2.0, 48), n5, n4, EdgeType.DIRECTED); // or we can use 
		g3.addEdge(new MyLink(2.0, 48), n4, n2); // In a directed graph the 
		g3.addEdge(new MyLink(2.0, 48), n3, n1); // first node is the source 
		g3.addEdge(new MyLink(10.0, 48), n2, n5);// and the second the destination
		System.out.println("The graph g3 = " + g3.toString());

		//SimpleGraphView sgv = new SimpleGraphView(); //We create our graph in here 
		// The Layout<V, E> is parameterized by the vertex and edge types 
		CircleLayout<MyNode, MyLink> layout = new CircleLayout<MyNode, MyLink>(g3);
		layout.setSize(new Dimension(300, 300)); // sets the initial size of the space 
		// The BasicVisualizationServer<V,E> is parameterized by the edge types 
		BasicVisualizationServer<MyNode, MyLink> vv = new BasicVisualizationServer<MyNode, MyLink>(layout);
		vv.setPreferredSize(new Dimension(350, 350)); //Sets the viewing area size 

		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);

	}

	public static Graph<Componant, String> convert(Map<Componant, Set<Componant>> tree) {
		logger.info("JungConverter.convert() input :");
		logger.info("nb Componants :" + tree.keySet().size());

		int c = 0;
		Graph<Componant, String> g = new DirectedSparseMultigraph<Componant, String>();

		for (Map.Entry<Componant, Set<Componant>> iterable_element : tree.entrySet()) {
			Componant com = iterable_element.getKey();
			Set<Componant> links = iterable_element.getValue();
			for (Componant componant : links) {

				g.addEdge(c + "-", com, componant);
				c++;
			}
		}
		printGraphStats(g, "tree -> graph");

		return g;
	}

	/**
	 * @param g
	 */
	private static void printGraphStats(Graph<Componant, String> g, String message) {
		logger.info(message);
		logger.info("graph :" + g);
		logger.info("Edges :" + g.getEdgeCount());
		logger.info("Vertex :" + g.getVertexCount());
	}

	public static Graph<Componant, String> convert(File graphml) throws ParserConfigurationException, SAXException, IOException {
		DirectedSparseMultigraph<Componant, String> g = new DirectedSparseMultigraph<Componant, String>();
		edu.uci.ics.jung.io.GraphMLReader<DirectedSparseMultigraph<Componant, String>, Componant, String> gmlr = new GraphMLReader<DirectedSparseMultigraph<Componant, String>, Componant, String>();

		gmlr.load(graphml.getAbsolutePath(), g);
		printGraphStats(g, "file -> graph");

		return g;
	}

	public static void saveGraph(Graph<Componant, String> g, File file) throws IOException {
		GraphMLWriter<Componant, String> gw = new GraphMLWriter<Componant, String>();
		gw.setEdgeIDs(new Transformer<String, String>() {

			public String transform(String input) {
				// TODO Auto-generated method stub
				return input;
			}
		});
		FileWriter w = new FileWriter(file);
		gw.save(g, w);
		w.close();

		printGraphStats(g, "graph -> file");
	}
}
