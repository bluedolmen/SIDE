package com.bluexml.side.build.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bluexml.side.build.tools.componants.Componant;
import com.bluexml.side.build.tools.graph.DisplayGraph;
import com.bluexml.side.build.tools.graph.JungConverter;
import com.bluexml.side.build.tools.graph.jung.algorithms.GraphFilter;
import com.bluexml.side.build.tools.reader.ComponantsRegisters;
import com.bluexml.side.build.tools.reader.ProductReader;
import com.bluexml.side.build.tools.renderer.DotRenderer;

import edu.uci.ics.jung.graph.Graph;

public class DependencyTree {
	static Logger logger = Logger.getLogger(DependencyTree.class);
	File product;
	File propertiesFile;
	List<File> repo;
	private String configType = "";

	public DependencyTree(File productFile, List<File> repo, File propertiesFile) {
		this.product = productFile;
		this.repo = repo;
		this.propertiesFile = propertiesFile;
	}

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("Usage : <.product> <projects repository> [<propetiesFile>]");
			System.out.println("");
			System.exit(-1);
		} else {
			if (args[0].equals("-graphmlFile")) {
				File file = new File(args[1]);

				Graph<Componant, String> g;
				try {
					g = JungConverter.convert(file);
					JungConverter.saveGraph(g, file);
					DisplayGraph.display(g);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				// 
				String product = args[0];
				File productFile = new File(product);
				if (!productFile.exists()) {
					System.err.println("please check .product location");
				}

				List<File> repos = new ArrayList<File>();
				for (int c = 1; c < args.length; c++) {
					File repo = new File(args[c]);
					if (repo.isDirectory()) {
						repos.add(repo);
					} else {
						break;
					}

				}
				String lastParam = args[args.length - 1];
				File lastPAramFile = new File(lastParam);
				if (lastPAramFile.isDirectory()) {
					lastPAramFile = null;
				}
				DependencyTree dt = new DependencyTree(productFile, repos, lastPAramFile);
				try {
					dt.doIt();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	protected Properties getProperties(String type) throws Exception {
		String configurationFileName = "config.properties";
		if (type != null && !type.equals("")) {
			configurationFileName = "config-" + type + ".properties";
		}
		File confFile = new File(configurationFileName);
		InputStream res = null;
		if (confFile.exists()) {
			res = new FileInputStream(confFile);
		} else {
			// try to get resource from classPath

			res = this.getClass().getResourceAsStream("/" + configurationFileName);

		}
		Properties p = new Properties();
		if (res != null) {
			p.load(res);
		} else {
			logger.warn("configuration file not found");
		}

		return p;
	}

	public void doIt() throws Exception {
		// create component registers

		ComponantsRegisters compReg = getComponantsRegisters();
		// print out
		compReg.print();
		// get tree
		Map<Componant, Set<Componant>> tree = compReg.getTree();

		// render as .dot file
		FileWriter fw = new FileWriter(new File("graph.dot"));

		DotRenderer dotRenderer = new DotRenderer(fw, tree);
		dotRenderer.render();
		fw.flush();
		fw.close();

		// jung rendering and save as graphml
		Graph<Componant, String> g = JungConverter.convert(tree);

		GraphFilter gf = new GraphFilter(g);

		Graph<Componant, String> filtered = g;

		String property = getProperties(configType).getProperty("render.jung.filter", "");
		if (!property.equals("")) {
			filtered = gf.getFilteredVertex(property, true, false);
		}

		JungConverter.saveGraph(filtered, new File("graph.graphml"));
		JungConverter.saveGraph(filtered, new File("graph.xml"));

		// save filtered graph as dot file
		FileWriter filteredDot = new FileWriter(new File("graph-filtered.dot"));
		Map<Componant, Set<Componant>> convertJungGraphToDot = DotRenderer.convertJungGraphToDot(filtered);
		new DotRenderer(filteredDot, convertJungGraphToDot).render();
		filteredDot.flush();
		filteredDot.close();

		//		DisplayGraph.display(filtered);

	}

	public ComponantsRegisters getComponantsRegisters() throws Exception {
		// create ProductReader
		ProductReader pr = new ProductReader(product, new ComponantsRegisters(repo, propertiesFile), getProperties(configType));
		// read product and all associated resources
		pr.read();
		ComponantsRegisters compReg = pr.getRegistries();
		return compReg;
	}

}
