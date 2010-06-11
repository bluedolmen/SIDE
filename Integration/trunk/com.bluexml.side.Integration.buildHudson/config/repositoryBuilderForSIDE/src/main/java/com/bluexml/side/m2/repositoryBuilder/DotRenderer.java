package com.bluexml.side.m2.repositoryBuilder;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DotRenderer {
	Writer w;

	Map<String, String> node2NodeType = new TreeMap<String, String>();
	Map<String, Integer> node2id = new TreeMap<String, Integer>();
	Map<String, String> type2Color = new HashMap<String, String>();
	Map<String, String> type2Shape = new HashMap<String, String>();

	private Map<String, Set<String>> parentChildren = new TreeMap<String, Set<String>>();

	boolean displayNull = false;

	public DotRenderer(Writer w, Map<String, Set<String>> parentChildren, Map<String, String> node2NodeType) throws Exception {
		this.w = w;
		type2Color.put(SideOptionsTree.metamodel, "blue");
		type2Color.put(SideOptionsTree.techName, "blue");
		type2Color.put(SideOptionsTree.techVersion, "blue");
		type2Color.put(SideOptionsTree.geneName, "green");
		type2Color.put(SideOptionsTree.modName, "blue");
		type2Color.put(SideOptionsTree.optName, "orange");
		type2Color.put(SideOptionsTree.geneName + "_" + SideOptionsTree.mustBeChecked, "purple");
		type2Color.put(SideOptionsTree.geneName + "_" + SideOptionsTree.optName, "orange");
		type2Color.put(SideOptionsTree.geneName + "_" + SideOptionsTree.modName, "blue");
		type2Color.put(SideOptionsTree.optName + "_" + SideOptionsTree.modName, "blue");

		type2Shape.put(SideOptionsTree.metamodel, "box");
		type2Shape.put(SideOptionsTree.techName, "box");
		type2Shape.put(SideOptionsTree.techVersion, "box");
		type2Shape.put(SideOptionsTree.geneName, "box");
		type2Shape.put(SideOptionsTree.modName, "component");
		type2Shape.put(SideOptionsTree.optName, "box");

		this.parentChildren = parentChildren;
		this.node2NodeType = node2NodeType;
	}

	private void writeHeader(Writer w) throws Exception {
		write("digraph dependencies {\n");
		int c = 1;
		// Map.Entry<String, Set<String>> entries = parentChildren.entrySet();
		for (Map.Entry<String, Set<String>> entries : parentChildren.entrySet()) {
			Set<String> children = entries.getValue();
			// children.add(parent+"");
			for (String id : children) {
				String key = id;
				if (!node2NodeType.get(id).equals(SideOptionsTree.mustBeChecked) && !node2id.containsKey(key)) {
					String str = "";
					str += c + " ";
					str += getNodeConfiguration(id);
					write(str);
					node2id.put(key, c);
					c++;
				}
			}
		}

	}

	private String getNodeConfiguration(String id) {
		String color = getNodeColor(id);
		String shape = getNodeShape(id);
		return "[label=\"" + getLabel(id) + "\",color=" + color + ",shape=" + shape + "]";
	}

	private String getNodeColor(String node) {
		String nodeType = node2NodeType.get(node);
		String color = type2Color.get(nodeType);
		if (color == null) {
			color = "red";
		}
		return color;
	}

	private String getNodeShape(String node) {
		String nodeType = node2NodeType.get(node);
		String shape = type2Shape.get(nodeType);
		if (shape == null) {
			shape = "none";
		}
		return shape;
	}

	private void writeTail(Writer w) throws Exception {
		write("}");
	}

	public void render() throws Exception {
		writeHeader(w);
		// write node declaration

		for (Map.Entry<String, Set<String>> entries : parentChildren.entrySet()) {
			String parent = entries.getKey();
			Set<String> children = entries.getValue();
			for (String child : children) {
				Integer sourceId = node2id.get(parent);
				Integer targetId = node2id.get(child);
				if (displayNull || (sourceId != null)) {
					String link = getLink(parent, child, sourceId, targetId);
					write(link);
				}
			}
		}
		writeTail(w);
	}

	private String getLink(String parent, String child, Integer sourceId, Integer targetId) throws Exception {
		String nodeType = node2NodeType.get(child);
		if (nodeType.equals(SideOptionsTree.mustBeChecked)) {
			String trueTargetNode = getNodeFromRef(child);
			targetId = node2id.get(trueTargetNode);
		}
		if (targetId == null) {
			System.err.println("null target parent:" + parent + " child:" + child);
		}
		return sourceId + " -> " + targetId + getLinkConfiguration(parent, child);
	}

	private String getLinkConfiguration(String parentNode, String targetNode) {
		String conf = "[color=" + getLinkColor(parentNode, targetNode) + "]";
		return conf;
	}

	private String getLinkColor(String source, String target) {
		String sourceType = node2NodeType.get(source);
		String targetType = node2NodeType.get(target);
		String key = sourceType + "_" + targetType;
		String color = type2Color.get(key);
		if (color == null) {
			color = "red";
			System.out.println("DotRenderer.getLinkColor() use default, " + key + " not found");
		}
		return color;
	}

	private void write(String s) throws Exception {
		w.write(s + ";\n");
	}

	private String getLabel(String node) {
		String[] split = node.split(SideOptionsTree.separator);
		String label = split[split.length - 1];
		return label;
	}

	private String getNodeFromRef(String node) throws Exception {
		String parentR = null;
		int c = 0;
		for (Map.Entry<String, Set<String>> entries : parentChildren.entrySet()) {
			Set<String> children = entries.getValue();
			for (String id : children) {
				if (id.endsWith(node) && !id.equals(node)) {
					if (parentR != null && !parentR.equals(id) && node2NodeType.get(id).equals(SideOptionsTree.optName)) {
						throw new Exception("DotRenderer.getNodeFromRef() Error more than one parent found");
					}
					parentR = id;

					c++;
				}
			}
		}

		return parentR;
	}
}
