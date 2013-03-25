package com.bluexml.tools.graph.model.io.xml;

import java.io.Reader;
import java.io.Writer;

import com.bluexml.tools.graph.model.GraphObject;
import com.thoughtworks.xstream.XStream;

public class XStreamUtils {

	static XStream xstream = new XStream();

	public static void save(GraphObject graph, Writer out) {
		xstream.toXML(graph, out);
	}

	public static GraphObject load(Reader r) {
		GraphObject graph = (GraphObject) xstream.fromXML(r);
		return graph;
	}
}
