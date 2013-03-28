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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

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
	File propertiesFileProjectsLocations;
	Properties graphCollectorConfiguration;
	List<File> repo;
	private String profile = "";
	ComponantsRegisters compReg;

	public DependencyTree(File productFile, List<File> repo, File propertiesFile, String profile) throws Exception {
		this.product = productFile;
		this.repo = repo;
		this.propertiesFileProjectsLocations = propertiesFile;
		this.profile = profile;
		this.graphCollectorConfiguration = getProperties(profile);
	}

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("-loadXStream")) {
			// -loadXStream xstreamedReg.xml profile out
			try {
				File xml = new File(args[1]);
				Properties graphCollectorConfiguration = null;
				String out = null;
				if (args.length == 4) {
					graphCollectorConfiguration = getProperties(args[2]);
					out = args[3];
				} else if (args.length == 3) {
					graphCollectorConfiguration = getProperties(null);
					out = args[2];
				}

				applyFilterFromXMLStream(xml, graphCollectorConfiguration, out);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (args.length < 3) {
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
				try {
					DependencyTree dt = new DependencyTree(productFile, repos, lastPAramFile, profile);

					dt.doIt();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	protected static Properties getProperties(String type) throws Exception {
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
			res = DependencyTree.class.getResourceAsStream("/" + configurationFileName);
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
		logger.debug("Properties File :" + propertiesFileProjectsLocations);
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
		JungConverter.saveGraph(g, new File("graph.xml"));

		filterGraphAndSave(graphCollectorConfiguration, g, "graph-filtered");

		validateGraph(g);
		compReg.saveToXML(new File("xstreamedReg.xml"));

		logger.info("try to Fix some anomalies");
		boolean featureAutoFix = Boolean.parseBoolean(graphCollectorConfiguration.getProperty("autoFix.features", "false"));
		boolean featureForceLicenseFix = Boolean.parseBoolean(graphCollectorConfiguration.getProperty("autoFix.features.lincense.force", "false"));
		boolean featureForceCopyRightFix = Boolean.parseBoolean(graphCollectorConfiguration.getProperty("autoFix.features.copyright.force", "false"));
		if (featureAutoFix) {
			

			Element root = getSampleFeature();

			ArrayList<String> allFeatures = new ArrayList<String>();
			for (String string : compReg.getFeaturesRegister().keySet()) {
				if (string.contains("com.bluexml.side")) {
					allFeatures.add(string);		
				}
			}
			
			
			List<String> list8 = featureForceCopyRightFix ? allFeatures : compReg.getAnomaly().getFeatureNoCopyright();
			if (list8.size() > 0) {
				logger.info("FIX Feature Missing copyright");
				fixFeatureElement(root, list8, "copyright");
			}

			List<String> list9 = featureForceLicenseFix ? allFeatures : compReg.getAnomaly().getFeatureNoLicence();
			if (list9.size() > 0) {
				logger.info("FIX Feature Missing Licence");
				fixFeatureElement(root, list9, "license");
			}

			List<String> list10 = compReg.getAnomaly().getFeatureNoDescription();
			if (list10.size() > 0) {
				logger.info("FIX Feature Missing Description");
				fixFeatureElement(root, list10, "description");
			}

			List<String> list11 = compReg.getAnomaly().getFeatureNoDescription();

			if (list11.size() > 0) {
				logger.info("FIX Feature Bad provider");
				for (String id : list11) {
					File projectFolder = compReg.getProjectFolder(id, null);
					String systemId = projectFolder + File.separator + "feature.xml";
					Document build = new SAXBuilder().build(systemId);
					Element currentFeature = build.getRootElement();
					String name = "provider-name";
					currentFeature.setAttribute(name, root.getAttributeValue(name));
					saveXML(systemId, build);
				}
			}

		}
	}

	public static Element getSampleFeature() throws JDOMException, IOException {
		InputStream resourceAsStream = DependencyTree.class.getResourceAsStream("/feature.xml");
		Document doc = new SAXBuilder().build(resourceAsStream);
		resourceAsStream.close();
		Element root = doc.getRootElement();
		return root;
	}

	protected void fixFeatureElement(Element root, List<String> list8, String elName) throws Exception, JDOMException, IOException {
		Element child = root.getChild(elName);
		String attributeURL = child.getAttributeValue("url");
		String text = child.getText();
		for (String id : list8) {
			File projectFolder = compReg.getProjectFolder(id, null);
			fixFeatureElement(projectFolder, elName, text, attributeURL);
		}
	}

	protected void fixFeatureElement(File projectFolder, String elName, String content, String url) throws JDOMException, IOException {
		String systemId = projectFolder + File.separator + "feature.xml";
		Document document = new SAXBuilder().build(systemId);
		Element root = document.getRootElement();
		Element child = root.getChild(elName);
		if (child == null) {
			child = new Element(elName);
			root.addContent(0, child);
		}
		child.setText(content);
		child.setAttribute("url", url);

		saveXML(systemId, document);
	}

	public static void saveXML(String systemId, Document root) throws FileNotFoundException, IOException {
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream out = new FileOutputStream(systemId);
		sortie.output(root, out);
		out.close();
		logger.info("File :" + systemId + " saved !");
	}

	public static void applyFilterFromXMLStream(File xml, Properties props, String out) throws Exception {
		ComponantsRegisters load = ComponantsRegisters.load(xml);
		Graph<Componant, String> g = JungConverter.convert(load.getTree());
		filterGraphAndSave(props, g, out);
	}

	public void validateGraph(Graph<Componant, String> g) {
		validate(g);

		//		DisplayGraph.display(filtered);

		// sumary

		List<String> list = compReg.getAnomaly().getNotTree();
		logger.warn("*** Anomaly summary ***");

		if (list.size() > 0) {
			logger.warn("componant with more than one parent :");
			for (String string : list) {
				logger.warn(string);
			}
		}

		List<String> list2 = compReg.getAnomaly().getInvalideCheckRef();
		if (list2.size() > 0) {
			logger.warn("invalide check constraints :");
			for (String string : list2) {
				logger.warn(string);
			}
		}

		List<String[]> list3 = compReg.getAnomaly().getBundleNotFoundInConf();
		if (list3.size() > 0) {
			logger.error("Bundle not found in conf file :");
			for (String[] string : list3) {
				logger.error(string[0] + " from " + string[1]);
			}
		}

		List<String> list4 = compReg.getAnomaly().getInvalideEntryInConf();
		if (list4.size() > 0) {
			logger.warn("Invalide Bundle in conf file :");
			for (String string : list4) {
				logger.warn(string);
			}
		}

		List<String> list5 = compReg.getAnomaly().getMissingPluginsInFeatures();
		if (list5.size() > 0) {
			logger.error("Missing required plugin in features");
			for (String string : list5) {
				logAnomaly(string);
			}
		}

		List<String> list6 = compReg.getAnomaly().getModuleNotFound();
		if (list6.size() > 0) {
			logger.error("Missing Module :");
			for (String string : list6) {
				logAnomaly(string);
			}
		}

		List<String> list7 = compReg.getAnomaly().getFeatureNoDescription();
		if (list7.size() > 0) {
			logger.error("Feature Missing description");
			for (String string : list7) {
				logAnomaly(string);
			}
		}

		List<String> list8 = compReg.getAnomaly().getFeatureNoCopyright();
		if (list8.size() > 0) {
			logger.error("Feature Missing copyright");
			for (String string : list8) {
				logAnomaly(string);
			}
		}

		List<String> list9 = compReg.getAnomaly().getFeatureNoLicence();
		if (list9.size() > 0) {
			logger.error("Feature Missing Licence");
			for (String string : list9) {
				logAnomaly(string);
			}
		}

	}

	protected void logAnomaly(String string) {
		logger.error("\t" + string);
	}

	public static void filterGraphAndSave(Properties properties, Graph<Componant, String> g, String outputFileName) throws IOException, Exception {
		Graph<Componant, String> filtered = filterGraph(properties, g);

		JungConverter.saveGraph(filtered, new File(outputFileName + ".xml"));

		// save filtered graph as dot file
		FileWriter filteredDot = new FileWriter(new File(outputFileName + ".dot"));
		Map<Componant, Set<Componant>> convertJungGraphToDot = DotRenderer.convertJungGraphToDot(filtered);
		new DotRenderer(filteredDot, convertJungGraphToDot).render();
		filteredDot.flush();
		filteredDot.close();
	}

	public static Graph<Componant, String> filterGraph(Properties properties, Graph<Componant, String> g) {
		String edgesfilter = getEdgesFilters(properties);
		String vertexNameFilter = getVertexFilters(properties);
		String vertexTypeFilter = getVertexTypeFilters(properties);
		boolean includeAscendantFilters = getIncludeAscendantFilters(properties);
		boolean includeDescendantFilters = getIncludeDescendantFilters(properties);
		boolean caseSensitiveFilters = getCaseSensitiveFilters(properties);
		GraphFilter gf = new GraphFilter(g, edgesfilter, vertexNameFilter, vertexTypeFilter, includeAscendantFilters, includeDescendantFilters, caseSensitiveFilters);

		Graph<Componant, String> filtered = gf.filter();
		return filtered;
	}

	public static String getVertexFilters(Properties properties) {
		return properties.getProperty("render.jung.filter", "");
	}

	public static String getVertexTypeFilters(Properties properties) {
		return properties.getProperty("render.jung.filter.vertexType", "");
	}

	public static String getEdgesFilters(Properties properties) {
		return properties.getProperty("render.jung.filter.edgesType", "");
	}

	public static boolean getIncludeAscendantFilters(Properties properties) {
		return Boolean.parseBoolean(properties.getProperty("render.jung.filter.includesAscendant", "false"));
	}

	public static boolean getIncludeDescendantFilters(Properties properties) {
		return Boolean.parseBoolean(properties.getProperty("render.jung.filter.includesDescendant", "true"));
	}

	public static boolean getCaseSensitiveFilters(Properties properties) {
		return Boolean.parseBoolean(properties.getProperty("render.jung.filter.caseSensitive", "false"));
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
		logger.debug("Search for Tree validation fault :");
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
					compReg.getAnomaly().addNotTree(componant.toString());
				}
			}
		}

		logger.debug("Search for missing required bundles :");

		// all Plugin -> Plugin dest must be in a feature
		for (Componant componant : vertices) {
			// destination of the edge must be Plugin
			if (componant instanceof Plugin) {
				Collection<String> outEdges = g.getOutEdges(componant);
				for (String string : outEdges) {
					Componant dest = g.getDest(string);
					if (dest instanceof Plugin) {
						// search in Features -> Plugin
						boolean ok = false;
						logger.trace("search ref in Feature for :" + dest);
						for (Componant componant1 : vertices) {
							if (componant1 instanceof Feature) {

								// get Feature -> plugin
								logger.trace("search ref in  :" + componant1);
								Collection<String> outEdges2 = g.getOutEdges(componant1);
								for (String string2 : outEdges2) {
									Componant dest2 = g.getDest(string2);
									Componant source = g.getSource(string2);
									logger.trace("Test :" + source + " -> " + dest2);
									if (dest2.equals(dest)) {
										// the dest of Plugin -> Plugin exists in Feature -> Plugin ok !
										ok = true;
										logger.debug("required bundle " + dest2 + " founded in :" + componant1);
										break;
									}
								}
							}
						}
						if (!ok) {
							logger.debug("Missing bundle in features :" + dest);
							compReg.getAnomaly().addMissingPluginsInFeatures(dest.toString());
						}
					}
				}
			}
		}
	}

	public ComponantsRegisters getComponantsRegisters() throws Exception {
		if (this.compReg != null) {
			return this.compReg;
		}

		// create ProductReader
		ProductReader pr = new ProductReader(product, new ComponantsRegisters(repo, propertiesFileProjectsLocations), this.graphCollectorConfiguration);
		// read product and all associated resources
		pr.read();
		ComponantsRegisters compReg = pr.getRegistries();
		return compReg;
	}

	public void validateConfigurationFile() throws Exception {
		HashMap<String, String> initMap = Utils.initMap(propertiesFileProjectsLocations);
		Set<String> keySet = initMap.keySet();
		for (String string : keySet) {
			File searchProjectForlerFromConf = Utils.searchProjectForlderFromConf(string, repo, propertiesFileProjectsLocations);
			if (searchProjectForlerFromConf == null || !searchProjectForlerFromConf.exists()) {
				String message = initMap.get(string) + "&" + string;
				logger.warn("invalide entry in properties file :" + message);
				logger.warn("The file do not exists :" + searchProjectForlerFromConf);
				compReg.getAnomaly().addInvalideEntryInConf(message);
			}
		}

	}

}
