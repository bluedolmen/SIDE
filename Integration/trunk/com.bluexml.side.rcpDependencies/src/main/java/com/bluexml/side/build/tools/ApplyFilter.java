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
