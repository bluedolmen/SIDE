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
