package com.bluexml.tools.graph.model.io.graphviz;

import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.bluexml.tools.graph.model.Edge;
import com.bluexml.tools.graph.model.GraphObject;
import com.bluexml.tools.graph.model.Node;

import edu.uci.ics.jung.graph.Graph;

public class DotRenderer {
	static Logger logger = Logger.getLogger(DotRenderer.class);
	public static final String separator = "##";

	Map<String, String> type2Color = new HashMap<String, String>();
	Map<String, String> type2Shape = new HashMap<String, String>();
	String highlightColor = "purple";
	String highlightShape = "ellipse";

	private Map<Node, Integer> node2id = new TreeMap<Node, Integer>();

	GraphObject graph;

	Writer w;

	boolean displayNull = false;

	public DotRenderer(Writer w, GraphObject graph) throws Exception {
		this.w = w;
		this.graph = graph;

		// node colors
		type2Color.put(Node.class.getName(), "yellow");
		//		type2Color.put(Feature.class.getName(), "black");
		//		type2Color.put(MetaModel.class.getName(), "blue");
		//		type2Color.put(Technology.class.getName(), "blue");
		//		type2Color.put(TechnologyVersion.class.getName(), "blue");
		//		type2Color.put(Extension.class.getName(), "green");
		//		type2Color.put(Module.class.getName(), "blue");
		//		type2Color.put(Option.class.getName(), "orange");

		// links
		type2Color.put(Node.class.getName() + "_" + Node.class.getName(), "purple");
		//		type2Color.put(Extension.class.getName() + "_" + Option.class.getName(), "orange");
		//		type2Color.put(Extension.class.getName() + "_" + Module.class.getName(), "blue");
		//		type2Color.put(Module.class.getName() + "_" + Module.class.getName(), "blue");
		//		type2Color.put(Option.class.getName() + "_" + Module.class.getName(), "blue");
		//		type2Color.put(Plugin.class.getName() + "_" + Plugin.class.getName(), "green");
		//		type2Color.put(Feature.class.getName() + "_" + Feature.class.getName(), "black");

		// nodes shape
		type2Shape.put(Node.class.getName(), "box");
		//		type2Shape.put(MetaModel.class.getName(), "box");
		//		type2Shape.put(Technology.class.getName(), "box");
		//		type2Shape.put(TechnologyVersion.class.getName(), "box");
		//		type2Shape.put(Extension.class.getName(), "box");
		//		type2Shape.put(Module.class.getName(), "component");
		//		type2Shape.put(Option.class.getName(), "box");

	}

	private void writeHeader() throws Exception {
		write("digraph dependencies {\n");
		int c = 1;

		for (Node node : this.graph.getNodes()) {
			c = addVertexHeader(c, node);
		}

	}

	private int addVertexHeader(int c, Node id) throws Exception {
		if (!node2id.containsKey(id)) {
			logger.debug("add vertex entry :" + id);
			String str = "";
			str += c + " ";
			str += getNodeConfiguration(id);
			writeLine(str);
			node2id.put(id, c);
			c++;
		}
		return c;
	}

	private String getNodeConfiguration(Node id) {
		String color = getNodeColor(id);
		String shape = getNodeShape(id);
		return "[label=\"" + getLabel(id) + "\",color=" + color + ",shape=" + shape + "]";
	}

	private String getNodeColor(Node node) {
		String nodeType = node.getClass().getName();
		String color = type2Color.get(nodeType);
		if (color == null) {
			color = "red";
		}
		if (node.isFilterMatch()) {
			color = highlightColor;
		}
		return color;
	}

	private String getNodeShape(Node node) {
		String nodeType = node.getClass().getName();
		String shape = type2Shape.get(nodeType);
		if (shape == null) {
			shape = "none";
		}
		if (node.isFilterMatch()) {
			shape = highlightShape;
		}

		return shape;
	}

	private void writeTail() throws Exception {
		write("}");
	}

	public void render() throws Exception {
		writeHeader();
		// write node declaration

		for (Edge edge : graph.getEdges()) {
			Node parent = edge.getNode1();
			Integer sourceId = node2id.get(parent);
			Node child = edge.getNode2();
			Integer targetId = node2id.get(child);
			if (displayNull || (sourceId != null)) {
				String link = getLink(edge, sourceId, targetId);
				writeLine(link);
			}

		}
		writeTail();
	}

	private String getLink(Edge edge, Integer sourceId, Integer targetId) throws Exception {
		if (targetId == null) {
			logger.error("null for " + edge);
		}
		return sourceId + " -> " + targetId + getLinkConfiguration(edge);
	}

	private String getLinkConfiguration(Edge edge) {
		String conf = "[label=\"" + edge.getLabel() + "\" color=" + getLinkColor(edge.getNode1(), edge.getNode2()) + "]";
		return conf;
	}

	private String getLinkColor(Node source, Node target) {
		String sourceType = source.getClass().getName();
		String targetType = target.getClass().getName();
		String key = sourceType + "_" + targetType;
		String color = type2Color.get(key);
		if (color == null) {
			color = "red";
			logger.debug("DotRenderer.getLinkColor() use default, " + key + " not found");
		}
		return color;
	}

	private void writeLine(String s) throws Exception {
		w.write(s + ";\n");
		logger.debug("writeLine" + s);
	}

	private void write(String s) throws Exception {
		w.write(s);
		logger.debug("write" + s);
	}

	private String getLabel(Node node) {
		String label = node.toString();
		return label;
	}

	//	private String getNodeFromRef(Node node) throws Exception {
	//		String parentR = null;
	//		int c = 0;
	//		for (Map.Entry<Node, Set<Node>> entries : parentChildren.entrySet()) {
	//			Set<Node> children = entries.getValue();
	//			for (Node id : children) {
	//				if (id.endsWith(node) && !id.equals(node)) {
	//					if (parentR != null && !parentR.equals(id) && node2NodeType.get(id).equals(Option.class.getName())) {
	//						throw new Exception("DotRenderer.getNodeFromRef() Error more than one parent found");
	//					}
	//					parentR = id;
	//
	//					c++;
	//				}
	//			}
	//		}
	//
	//		return parentR;
	//	}

	public static Map<Node, Set<Node>> convertJungGraphToDot(Graph<Node, Edge> g) throws Exception {

		Map<Node, Set<Node>> parentChildren = new TreeMap<Node, Set<Node>>();

		Collection<Node> vertices = g.getVertices();

		for (Node componant : vertices) {
			HashSet<Node> hashSet = new HashSet<Node>();
			parentChildren.put(componant, hashSet);
			// get children
			logger.debug("get children for " + componant);
			int degree = g.degree(componant);
			logger.debug("degree :" + degree);
			Collection<Edge> outEdges = g.getOutEdges(componant);
			for (Edge string : outEdges) {
				Node dest = g.getDest(string);
				hashSet.add(dest);
			}

		}
		logger.debug("parents :" + parentChildren.keySet());

		return parentChildren;
	}
}
