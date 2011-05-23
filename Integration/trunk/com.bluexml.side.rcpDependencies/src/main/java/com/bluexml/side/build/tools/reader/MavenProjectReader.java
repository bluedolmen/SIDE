package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.Ostermiller.util.ExecHelper;
import com.bluexml.side.build.tools.componants.Componant;
import com.bluexml.side.build.tools.componants.Module;
import com.bluexml.side.build.tools.graph.DisplayGraph;
import com.bluexml.side.build.tools.graph.JungConverter;
import com.bluexml.side.build.tools.reader.graphml.Data;
import com.bluexml.side.build.tools.reader.graphml.DataT;
import com.bluexml.side.build.tools.reader.graphml.Edge;
import com.bluexml.side.build.tools.reader.graphml.Graph;
import com.bluexml.side.build.tools.reader.graphml.Graphml;
import com.bluexml.side.build.tools.reader.graphml.Node;

@SuppressWarnings("restriction")
public class MavenProjectReader {
	Logger logger = Logger.getLogger(this.getClass());
	ComponantsRegisters registries;

	public MavenProjectReader(ComponantsRegisters registries) {
		this.registries = registries;
	}

	public Module read(File project) throws Exception {
		//TODO : use maven-dependency-plugin to build tree and parse output filtered on side modules

		Module module = loadFromGraphML(project);

		return module;
	}

	/**
	 * @param project
	 * @throws IOException
	 */
	private void loadFromDot(File project) throws IOException {
		List<String> argss = new ArrayList<String>();

		argss.add("mvn"); //$NON-NLS-1$
		argss.add("dependency:tree");
		argss.add("-f");
		argss.add(new File(project, "pom.xml").getAbsolutePath());
		argss.add("-DoutputType=dot");

		argss.add("-DoutputFile=graph.dot");

		String[] args = new String[argss.size()];
		args = argss.toArray(args);
		ExecHelper status = ExecHelper.exec(args);

		String out = status.getOutput();
		System.out.println(out);
	}

	/**
	 * @param project
	 * @throws IOException
	 * @throws JAXBException
	 * @throws PropertyException
	 */
	private Module loadFromGraphML(File project) throws IOException, JAXBException, PropertyException {
		Module root = null;
		File graphmlfile = new File("graphml.xml");
		List<String> argss = new ArrayList<String>();

		argss.add("mvn"); //$NON-NLS-1$
		argss.add("dependency:tree");
		argss.add("-f");
		argss.add(new File(project, "pom.xml").getAbsolutePath());
		argss.add("-DoutputType=graphml");

		argss.add("-DoutputFile=" + graphmlfile.getAbsolutePath());

		String[] args = new String[argss.size()];
		args = argss.toArray(args);
		logger.info("Run system command :" + argss);
		ExecHelper status = ExecHelper.exec(args);

		String out = status.getOutput();
		logger.debug(out);

		// load graphml file

		Graphml graphml = (Graphml) loadGraphML(graphmlfile);

		Map<BigInteger, Module> map = new HashMap<BigInteger, Module>();

		Graph graph = graphml.getGraph();
		List<DataT> edgeOrNode = graph.getEdgeOrNode();
		for (int i = 0; i < edgeOrNode.size(); i++) {
			// create the module object and add to index
			Object node = edgeOrNode.get(i);

			if (node instanceof Node) {

				Module module = new Module();

				Node nodeo = (Node) node;
				BigInteger b = nodeo.getId();
				Data datao = nodeo.getData();
				String label = datao.getShapeNode().getNodeLabel();
				String[] parts = label.split(":");
				String artifactId = parts[1];
				module.setArtifactId(artifactId);
				String groupId = parts[0];
				module.setGroupId(groupId);
				String type = parts[2];
				module.setType(type);
				String version = parts[3];
				module.setVersion(version);
				String moduleID = groupId + "." + artifactId;
				module.setModuleID(moduleID);

				map.put(b, module);
				if (i == 0) {
					// first one = root
					root = module;
				}
			}
		}

		for (Object node : edgeOrNode) {
			if (node instanceof Edge) {
				// resolve references and add edges in tree
				Edge edgeo = (Edge) node;
				Module src = map.get(edgeo.getSource());
				Module target = map.get(edgeo.getTarget());
				String label = edgeo.getData().getPolyLineEdge().getEdgeLabel();

				src.getDependencies().add(target);

				logger.debug("add edges :" + src + " -(" + label + ")-> " + target);

				// beware src and target object instance must be unique
				// search if src and target
				Module fromres = registries.modulesRegister.get(src.getModuleID());
				if (fromres != null) {
					src = fromres;
				} else {
					logger.debug("add new module in registry :" + src.getModuleID());
					registries.modulesRegister.put(src.getModuleID(), src);
				}
				Module fromresT = registries.modulesRegister.get(target.getModuleID());
				if (fromresT != null) {
					target = fromresT;
				} else {
					logger.debug("add new module in registry :" + target.getModuleID());
					registries.modulesRegister.put(target.getModuleID(), target);
				}

				Utils.add(registries.tree, src, target);

			}
		}
		return root;
	}

	public static void main(String[] args) {
		try {
			//			MavenProjectReader mpr = new MavenProjectReader(null);
			//			mpr.read(new File("/Users/davidabad/Workspace2.0/side-rcp-dependencies"));

			edu.uci.ics.jung.graph.Graph<Componant, String> g = JungConverter.convert(new File("graphml.xml"));
			DisplayGraph.display(g);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 * @throws JAXBException
	 * @throws PropertyException
	 */

	private static Unmarshaller getUnmarshaller(String packageName) throws JAXBException, PropertyException {
		JAXBContext jaxbContext = JAXBContext.newInstance(packageName);

		Marshaller alfrescoMarshaller = jaxbContext.createMarshaller();
		alfrescoMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		alfrescoMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		Unmarshaller alfrescoUnmarshaller = jaxbContext.createUnmarshaller();

		return alfrescoUnmarshaller;
	}

	/**
	 * @return
	 * @throws JAXBException
	 * @throws PropertyException
	 * @throws IOException
	 */
	private static Object loadGraphML(File file) throws JAXBException, PropertyException, IOException {
		// replace xmlns="http://graphml.graphdrawing.org/xmlns" -> xmlns="http://graphml.graphdrawing.org/gxmlns"

		Map<String, String> map = new HashMap<String, String>();
		map.put("http://graphml.graphdrawing.org/xmlns", "http://graphml.graphdrawing.org/gxmlns");
		replaceInFile(map, file);
		Unmarshaller alfrescoUnmarshaller = getUnmarshaller("com.bluexml.side.build.tools.reader.graphml");

		Object root = alfrescoUnmarshaller.unmarshal(file);
		if (root instanceof JAXBElement<?>) {
			root = ((JAXBElement<?>) root).getValue();
		}
		return root;
	}

	private static void replaceInFile(Map<String, String> map, File file) throws IOException {
		// open file
		String data = FileUtils.readFileToString(file, "UTF-8");
		for (Map.Entry<String, String> token : map.entrySet()) {
			data = data.replace(token.getKey(), token.getValue());
		}

		// write back to file
		FileUtils.writeStringToFile(file, data, "UTF-8");
	}
}
