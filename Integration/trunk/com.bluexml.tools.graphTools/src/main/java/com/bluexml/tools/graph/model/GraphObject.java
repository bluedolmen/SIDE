package com.bluexml.tools.graph.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GraphObject {

	Set<Edge> edges;
	Set<Node> nodes;

	public GraphObject(Collection<Edge> edges, Collection<Node> nodes) {
		this.nodes = new HashSet<Node>();
		for (Node node : nodes) {
			this.nodes.add(node);
		}

		this.edges = new HashSet<Edge>();
		for (Edge edge : edges) {
			this.edges.add(edge);
		}
	}

	public GraphObject(Set<Edge> edges, Set<Node> nodes) {
		this.edges = edges;
		this.nodes = nodes;
	}

	public Set<Edge> getEdges() {
		return edges;
	}

	public Set<Node> getNodes() {
		return nodes;
	}

}
