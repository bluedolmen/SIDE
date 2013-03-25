package com.bluexml.tools.graph.model;

public class Edge extends GraphElement {

	Node node1;
	Node node2;

	boolean directed;

	public Edge(Node node1, Node node2, String label, boolean directed) {
		super(label);
		this.node1 = node1;
		this.node2 = node2;
		this.directed = directed;

	}

	public Node getNode1() {
		return node1;
	}

	public Node getNode2() {
		return node2;
	}

	public boolean isDirected() {
		return directed;
	}

}
