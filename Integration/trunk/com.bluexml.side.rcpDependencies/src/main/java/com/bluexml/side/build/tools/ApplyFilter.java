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
package com.bluexml.side.build.tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.bluexml.side.build.tools.componants.Componant;
import com.bluexml.side.build.tools.graph.JungConverter;

import edu.uci.ics.jung.graph.Graph;

public class ApplyFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		for (String string : args) {
			String[] arg = string.split("=");
			params.put(arg[0].substring(1), arg[1]);
		}

		if (!params.containsKey("graphmlFile")) {
			System.out.println("Usage : -graphmlFile=<graphmlfilePath> [-profile=<profile>]");
			System.out.println("");
			System.exit(-1);
		} else {
			String graphmlFilePath = params.get("graphmlFile");

			File graphmlFile = new File(graphmlFilePath);

			String type = params.get("profile");
			try {
				Properties properties = DependencyTree.getProperties(type);

				// open graph and load
				Graph<Componant, String> g = JungConverter.convert(graphmlFile);

				DependencyTree.filterGraphAndSave(properties, g, "graph-filtered");

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}
}
