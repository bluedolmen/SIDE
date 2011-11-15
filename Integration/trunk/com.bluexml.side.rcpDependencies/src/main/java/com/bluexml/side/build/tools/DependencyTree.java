package com.bluexml.side.build.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bluexml.side.build.tools.componants.Componant;
import com.bluexml.side.build.tools.componants.Feature;
import com.bluexml.side.build.tools.componants.Plugin;
import com.bluexml.side.build.tools.componants.Product;
import com.bluexml.side.build.tools.graph.DisplayGraph;
import com.bluexml.side.build.tools.graph.JungConverter;
import com.bluexml.side.build.tools.graph.jung.algorithms.GraphFilter;
import com.bluexml.side.build.tools.reader.ComponantsRegisters;
import com.bluexml.side.build.tools.reader.ProductReader;
import com.bluexml.side.build.tools.reader.Utils;
import com.bluexml.side.build.tools.renderer.DotRenderer;

import edu.uci.ics.jung.graph.Graph;

public class DependencyTree {
	static Logger logger = Logger.getLogger(DependencyTree.class);
	File product;
	File propertiesFile;
	List<File> repo;
	private String profile = "";
	ComponantsRegisters compReg;

	public DependencyTree(File productFile, List<File> repo, File propertiesFile, String profile) {
		this.product = productFile;
		this.repo = repo;
		this.propertiesFile = propertiesFile;
		this.profile = profile;
	}

	public static void main(String[] args) {

		if (args.length < 3) {
			System.out.println("Usage : <.product> [<projects repository>]+ <propetiesFile> [-profile <profile>]");
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
				String profile = "";
				int indice = 0;
				if (args[args.length - 2].equals("-profile")) {
					indice = 2;
					profile = args[args.length - 1];
				}
				// 
				String product = args[0];
				File productFile = new File(product);
				if (!productFile.exists()) {
					System.err.println("please check .product location");
				}

				List<File> repos = new ArrayList<File>();
				for (int c = 1; c < args.length - indice; c++) {
					File repo = new File(args[c]);
					if (repo.isDirectory()) {
						repos.add(repo);
					} else {
						break;
					}

				}
				String lastParam = args[args.length - (1 + indice)];
				File lastPAramFile = new File(lastParam);
				if (lastPAramFile.isDirectory()) {
					lastPAramFile = null;
				}

				DependencyTree dt = new DependencyTree(productFile, repos, lastPAramFile, profile);
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
		logger.debug("product :" + product);
		logger.debug("source repository :" + repo);
		logger.debug("Properties File :" + propertiesFile);
		logger.debug("profile :" + profile);

		validateConfigurationFile();

		compReg = getComponantsRegisters();
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

		String property = getProperties(profile).getProperty("render.jung.filter", "");
		if (!property.equals("")) {
			filtered = gf.getFilteredVertex(property, true, false);
		}

		JungConverter.saveGraph(g, new File("graph.xml"));
		JungConverter.saveGraph(filtered, new File("graph-filtered.xml"));

		// save filtered graph as dot file
		FileWriter filteredDot = new FileWriter(new File("graph-filtered.dot"));
		Map<Componant, Set<Componant>> convertJungGraphToDot = DotRenderer.convertJungGraphToDot(filtered);
		new DotRenderer(filteredDot, convertJungGraphToDot).render();
		filteredDot.flush();
		filteredDot.close();

		validate(g);

		//		DisplayGraph.display(filtered);

		// sumary

		List<String> list = compReg.getAnomaly().notTree;
		logger.warn("*** Anomaly summary ***");
		logger.warn("componant with more than one parent :");
		for (String string : list) {
			logger.warn(string);
		}
		logger.warn("invalide check constraints :");
		List<String> list2 = compReg.getAnomaly().invalideCheckRef;
		for (String string : list2) {
			logger.warn(string);
		}
		logger.warn("Bundle not found in conf file :");
		List<String> list3 = compReg.getAnomaly().bundleNotFoundInConf;
		for (String string : list3) {
			logger.warn(string);
		}

		logger.warn("Invalide Bundle in conf file :");
		List<String> list4 = compReg.getAnomaly().invalideEntryInConf;
		for (String string : list4) {
			logger.warn(string);
		}
	}

	/**
	 * do some advanced validation on the graph :
	 * <ul>
	 * <li>sub graph with Product->Feature, Feature->Feature, Feature -> Plugin
	 * should be a tree</li>
	 * <ul>
	 * 
	 * @param g
	 */
	public void validate(Graph<Componant, String> g) {
		// search if the graph is a tree (only one parent per vertex)
		Collection<Componant> vertices = g.getVertices();

		for (Componant componant : vertices) {
			// destination of the edge can be Feature or Plugin
			if (componant instanceof Feature || componant instanceof Plugin) {
				int count = 0;
				Collection<String> incidentEdges = g.getIncidentEdges(componant);
				for (String string : incidentEdges) {
					Componant source = g.getSource(string);
					if (source instanceof Feature || source instanceof Product) {
						count++;
					}
				}
				if (count > 1) {
					compReg.getAnomaly().notTree.add(componant.toString());
				}
			}
		}
	}

	public ComponantsRegisters getComponantsRegisters() throws Exception {
		// create ProductReader
		ProductReader pr = new ProductReader(product, new ComponantsRegisters(repo, propertiesFile), getProperties(profile));
		// read product and all associated resources
		pr.read();
		ComponantsRegisters compReg = pr.getRegistries();
		return compReg;
	}

	public void validateConfigurationFile() throws Exception {
		HashMap<String, String> initMap = Utils.initMap(propertiesFile);
		Set<String> keySet = initMap.keySet();
		for (String string : keySet) {
			File searchProjectForlerFromConf = Utils.searchProjectForlerFromConf(string, repo, propertiesFile);
			if (searchProjectForlerFromConf == null || !searchProjectForlerFromConf.exists()) {
				String message = initMap.get(string) + "&" + string;
				logger.warn("invalide entry in properties file :" + message);
				logger.warn("The file do not exists :" + searchProjectForlerFromConf);
				compReg.getAnomaly().addInvalideEntryInConf(message);
			}
		}

	}

}
