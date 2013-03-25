package com.bluexml.tools.graph.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Utils {

	public static GraphObject convert2graph(Map<String, List<String>> map) {
		Map<String, Node> nodes = new HashMap<String, Node>();
		Map<String, Edge> edges = new HashMap<String, Edge>();
		for (Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			Node e = new Node(key);
			nodes.put(e.getLabel(), e);
		}

		for (Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<String> value = entry.getValue();
			Node node1 = nodes.get(key);
			for (String string : value) {
				Node node2 = nodes.get(string);
				Edge e = new Edge(node1, node2, node1.getLabel() + "->" + node2.getLabel(), true);
				edges.put(e.getLabel(), e);
			}

		}

		return new GraphObject(edges.values(), nodes.values());
	}
}
