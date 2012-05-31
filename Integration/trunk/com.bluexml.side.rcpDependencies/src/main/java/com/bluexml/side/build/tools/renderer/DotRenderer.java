package com.bluexml.side.build.tools.renderer;

import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.bluexml.side.build.tools.componants.Componant;
import com.bluexml.side.build.tools.componants.Constraint;
import com.bluexml.side.build.tools.componants.Extension;
import com.bluexml.side.build.tools.componants.Feature;
import com.bluexml.side.build.tools.componants.MetaModel;
import com.bluexml.side.build.tools.componants.Module;
import com.bluexml.side.build.tools.componants.Option;
import com.bluexml.side.build.tools.componants.Plugin;
import com.bluexml.side.build.tools.componants.Technology;
import com.bluexml.side.build.tools.componants.TechnologyVersion;

import edu.uci.ics.jung.graph.Graph;

public class DotRenderer {
	static Logger logger = Logger.getLogger(DotRenderer.class);
	public static final String separator = "##";
	Writer w;

	Map<Componant, Integer> node2id = new TreeMap<Componant, Integer>();
	Map<String, String> type2Color = new HashMap<String, String>();
	Map<String, String> type2Shape = new HashMap<String, String>();
	String matchesColor = "purple";
	String matchesShape = "ellipse";
	private Map<Componant, Set<Componant>> parentChildren = new TreeMap<Componant, Set<Componant>>();

	boolean displayNull = false;

	public DotRenderer(Writer w, Map<Componant, Set<Componant>> parentChildren) throws Exception {
		this.w = w;

		// node colors
		type2Color.put(Componant.class.getName(), "yellow");
		type2Color.put(Feature.class.getName(), "black");
		type2Color.put(MetaModel.class.getName(), "blue");
		type2Color.put(Technology.class.getName(), "blue");
		type2Color.put(TechnologyVersion.class.getName(), "blue");
		type2Color.put(Extension.class.getName(), "green");
		type2Color.put(Module.class.getName(), "blue");
		type2Color.put(Option.class.getName(), "orange");

		// links
		type2Color.put(Extension.class.getName() + "_" + Constraint.class.getName(), "purple");
		type2Color.put(Extension.class.getName() + "_" + Option.class.getName(), "orange");
		type2Color.put(Extension.class.getName() + "_" + Module.class.getName(), "blue");
		type2Color.put(Module.class.getName() + "_" + Module.class.getName(), "blue");
		type2Color.put(Option.class.getName() + "_" + Module.class.getName(), "blue");
		type2Color.put(Plugin.class.getName() + "_" + Plugin.class.getName(), "green");
		type2Color.put(Feature.class.getName() + "_" + Feature.class.getName(), "black");

		// nodes shape
		type2Shape.put(Feature.class.getName(), "box");
		type2Shape.put(MetaModel.class.getName(), "box");
		type2Shape.put(Technology.class.getName(), "box");
		type2Shape.put(TechnologyVersion.class.getName(), "box");
		type2Shape.put(Extension.class.getName(), "box");
		type2Shape.put(Module.class.getName(), "component");
		type2Shape.put(Option.class.getName(), "box");

		this.parentChildren = parentChildren;
	}

	private void writeHeader() throws Exception {
		write("digraph dependencies {\n");
		int c = 1;

		for (Map.Entry<Componant, Set<Componant>> entries : parentChildren.entrySet()) {
			c = addVertexHeader(c, entries.getKey());
			Set<Componant> children = entries.getValue();
			// children.add(parent+"");
			for (Componant id : children) {
				c = addVertexHeader(c, id);
			}

		}

	}

	private int addVertexHeader(int c, Componant id) throws Exception {
		if (!id.getClass().equals(Constraint.class.getName()) && !node2id.containsKey(id)) {
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

	private String getNodeConfiguration(Componant id) {
		String color = getNodeColor(id);
		String shape = getNodeShape(id);
		return "[label=\"" + getLabel(id) + "\",color=" + color + ",shape=" + shape + "]";
	}

	private String getNodeColor(Componant node) {
		String nodeType = node.getClass().getName();
		String color = type2Color.get(nodeType);
		if (color == null) {
			color = "red";
		}
		if (node.isFilterMatch()) {
			color = matchesColor;
		}
		return color;
	}

	private String getNodeShape(Componant node) {
		String nodeType = node.getClass().getName();
		String shape = type2Shape.get(nodeType);
		if (shape == null) {
			shape = "none";
		}
		if (node.isFilterMatch()) {
			shape = matchesShape;
		}

		return shape;
	}

	private void writeTail() throws Exception {
		write("}");
	}

	public void render() throws Exception {
		writeHeader();
		// write node declaration

		for (Map.Entry<Componant, Set<Componant>> entries : parentChildren.entrySet()) {
			Componant parent = entries.getKey();
			Set<Componant> children = entries.getValue();
			for (Componant child : children) {
				Integer sourceId = node2id.get(parent);
				Integer targetId = node2id.get(child);
				if (displayNull || (sourceId != null)) {
					String link = getLink(parent, child, sourceId, targetId);
					writeLine(link);
				}
			}
		}
		writeTail();
	}

	private String getLink(Componant parent, Componant child, Integer sourceId, Integer targetId) throws Exception {
		String nodeType = child.getClass().getName();
		if (nodeType.equals(Constraint.class.getName())) {
			targetId = node2id.get(child);
		}
		if (targetId == null) {
			logger.error("null target parent:" + parent + " child:" + child);
		}
		return sourceId + " -> " + targetId + getLinkConfiguration(parent, child);
	}

	private String getLinkConfiguration(Componant parentNode, Componant targetNode) {
		String conf = "[color=" + getLinkColor(parentNode, targetNode) + "]";
		return conf;
	}

	private String getLinkColor(Componant source, Componant target) {
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
	}
	
	private void write(String s) throws Exception {
		w.write(s);
	}

	private String getLabel(Componant node) {
		String label = node.toString();
		return label;
	}

	//	private String getNodeFromRef(Componant node) throws Exception {
	//		String parentR = null;
	//		int c = 0;
	//		for (Map.Entry<Componant, Set<Componant>> entries : parentChildren.entrySet()) {
	//			Set<Componant> children = entries.getValue();
	//			for (Componant id : children) {
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

	public static Map<Componant, Set<Componant>> convertJungGraphToDot(Graph<Componant, String> g) throws Exception {

		Map<Componant, Set<Componant>> parentChildren = new TreeMap<Componant, Set<Componant>>();

		Collection<Componant> vertices = g.getVertices();

		for (Componant componant : vertices) {
			HashSet<Componant> hashSet = new HashSet<Componant>();
			parentChildren.put(componant, hashSet);
			// get children
			logger.debug("get children for " + componant);
			int degree = g.degree(componant);
			logger.debug("degree :" + degree);
			Collection<String> outEdges = g.getOutEdges(componant);
			for (String string : outEdges) {
				Componant dest = g.getDest(string);
				hashSet.add(dest);
			}

		}
		logger.debug("parents :" + parentChildren.keySet());

		return parentChildren;
	}
}
