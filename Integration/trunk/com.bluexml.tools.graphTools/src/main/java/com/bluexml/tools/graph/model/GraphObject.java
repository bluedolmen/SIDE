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
