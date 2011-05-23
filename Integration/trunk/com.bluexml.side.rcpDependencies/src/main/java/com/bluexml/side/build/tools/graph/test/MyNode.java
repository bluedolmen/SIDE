package com.bluexml.side.build.tools.graph.test;

public class MyNode {

	int id; // good coding practice would have this as private

	public MyNode(int id) {
		this.id = id;
	}

	public String toString() { // Always a good idea for debuging
		return "V" + id; // JUNG2 makes good use of these.
	}
}
