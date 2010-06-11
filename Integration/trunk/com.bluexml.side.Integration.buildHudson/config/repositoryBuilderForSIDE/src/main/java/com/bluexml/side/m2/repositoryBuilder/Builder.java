package com.bluexml.side.m2.repositoryBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.bluexml.side.util.dependencies.ModuleConstraint;
import com.bluexml.side.util.dependencies.PluginsUtils;

public class Builder {

	public static List<ModuleConstraint> getModulesConstraint(File rootPlugins) throws Exception {

		List<ModuleConstraint> moduleIds = new ArrayList<ModuleConstraint>();

		// search in folder to get plugin.xml files

		List<File> lPlug = PluginsUtils.getPluginXML(rootPlugins);
		for (File file : lPlug) {
			// search <moduleDependency>.*<moduleDependency>
			try {
				Document pluginXML = PluginsUtils.buildJdomDocument(file);
				System.out.println("Scanning File :" + file);
				XPath xpa = XPath.newInstance("//moduleDependence");
				List<?> lmd = xpa.selectNodes(pluginXML.getRootElement());
				for (Object object : lmd) {
					Element el = (Element) object;
					String id = null;
					String tech_version = null;
					String moduleType = null;
					String versionNumMin = null;
					String versionNumMax = null;

					Attribute moduleId = el.getAttribute("moduleId");
					if (moduleId != null) {
						id = moduleId.getValue();
					}
					moduleId = el.getAttribute("moduleType");
					if (moduleId != null) {
						moduleType = moduleId.getValue();
					}
					moduleId = el.getAttribute("technologyVersion");
					if (moduleId != null) {
						tech_version = moduleId.getValue();
					}
					moduleId = el.getAttribute("versionMax");
					if (moduleId != null) {
						versionNumMax = moduleId.getValue();
					}
					moduleId = el.getAttribute("versionMin");
					if (moduleId != null) {
						versionNumMin = moduleId.getValue();
					}

					ModuleConstraint mc = new ModuleConstraint(id, tech_version, moduleType, versionNumMin, versionNumMax);
					System.out.println("found :" + mc);
					moduleIds.add(mc);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return moduleIds;
	}

	public static void main(String[] args) {

		File rootPlugins = getPluginRepo(args);
		try {
			switch (args.length) {
			case 5:
				// two option activated
				String option2 = args[3];
				String output2 = args[4];
				applyOptionalJob(rootPlugins, option2, output2);
			case 3:
				// only one option activated				
				String option = args[1];
				String output = args[2];
				applyOptionalJob(rootPlugins, option, output);
				break;
			default:
				displayUsage();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static File getPluginRepo(String[] args) {
		File rootPlugins = new File(args[0]);
		if (!rootPlugins.exists()) {
			System.err.println("File do not exist :" + rootPlugins);
		}
		return rootPlugins;
	}

	private static void applyOptionalJob(File rootPlugins, String option, String filePath) throws Exception, IOException {
		if (option.equals("-makeDot")) {
			writeDotFile(rootPlugins, filePath);
			System.out.println("graph dot file generated :" + filePath);
		} else if (option.equals("-patchPom")) {
			List<ModuleConstraint> res = Builder.getModulesConstraint(rootPlugins);
			File file = new File(filePath);
			if (!file.exists()) {
				System.err.println("File do not exist :" + file);
			}
			writePomFile(file, res);
			System.out.println("pom Patched");
		}
	}

	private static void displayUsage() {
		System.out.println("Usage : repositoryBuilderForSIDE.jar <pluginsSRCHome> [-patchPom <pom.xml>] [-makeDot <output dot file>]");
		System.out.println(" <pluginsSRCHome> : directory containing Eclipse plugins");
		System.out.println(" <pom.xml>        : pom file to patch");
		System.out.println(" -makeDot         : option to generate a graph dot file");
		System.out.println(" <output dot file>: the output dot file (created or overriten)");
	}

	private static void writeDotFile(File rootPlugins, String output) throws Exception, IOException {
		SideOptionsTree sot = new SideOptionsTree();
		sot.extensionPointRenderer(rootPlugins);

		File outputFile = new File(output);
		FileWriter fw = new FileWriter(outputFile);
		DotRenderer dotRenderer = new DotRenderer(fw, sot.getParentChildren(), sot.getNode2NodeType());
		dotRenderer.render();
		fw.flush();
		fw.close();
	}

	private static void writePomFile(File pomFile, List<ModuleConstraint> res) throws Exception, JDOMException, FileNotFoundException, IOException {
		Document pom = PluginsUtils.buildJdomDocument(pomFile);
		// search existing dependencies node
		XPath xpa = XPath.newInstance("/project/dependencies");
		Element deps = (Element) xpa.selectSingleNode(pom.getRootElement());
		if (deps == null) {
			deps = new Element("dependencies");
			pom.getRootElement().addContent(deps);
		}
		// add dependencies
		for (ModuleConstraint moduleConstraint : res) {
			Element dep = new Element("dependency");

			Element groupId = new Element("groupId").setText(moduleConstraint.getGroupId());
			Element artifactId = new Element("artifactId").setText(moduleConstraint.getArtifactId());
			Element version = new Element("version").setText(moduleConstraint.getVersionRange());
			Element type = new Element("type").setText(moduleConstraint.getModuleType());
			Element scope = new Element("scope").setText("compile");

			dep.addContent(groupId);
			dep.addContent(artifactId);
			dep.addContent(version);
			dep.addContent(type);
			dep.addContent(scope);
			deps.addContent(dep);
			System.out.println("added :" + moduleConstraint);
		}
		// save changes
		org.jdom.output.XMLOutputter out = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		out.setFormat(format);
		FileOutputStream outStream = new FileOutputStream(pomFile);
		out.output(pom, outStream);
		outStream.close();
	}

}
