package com.bluexml.side.m2.repositoryBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.bluexml.side.util.dependencies.ModuleConstraint;

public class Builder {

	public static List<ModuleConstraint> getModulesConstraint(File rootPlugins) {

		List<ModuleConstraint> moduleIds = new ArrayList<ModuleConstraint>();

		// search in folder to get plugin.xml files

		List<File> lPlug = getPluginXML(rootPlugins);
		for (File file : lPlug) {
			// search <moduleDependency>.*<moduleDependency>
			try {
				Document pluginXML = buildJdomDocument(file);
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

	/**
	 * search for plugin.xml files
	 * 
	 * @param folder
	 *            where to start search
	 * @return List of plugin.xml files
	 */
	private static List<File> getPluginXML(File f) {
		List<File> rs = new ArrayList<File>();

		File[] lf = f.listFiles();
		for (File file : lf) {
			if (file.isFile()) {
				if (file.getName().equals("plugin.xml")) {
					rs.add(file);
					break;
				}
			} else if (!file.getName().startsWith(".")) {
				List<File> ll = getPluginXML(file);
				rs.addAll(ll);
			}
		}
		return rs;
	}

	public static Document buildJdomDocument(File xmlFile) throws Exception {
		Document doc;
		org.jdom.input.SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
		return doc;
	}

	public static void main(String[] args) {
		// check parameters
		boolean ok = true;
		File rootPlugins = new File(args[0]);
		if (!rootPlugins.exists()) {
			ok = false;
			System.err.println("File do not exist :" + rootPlugins);
		}
		File pomFile = new File(args[1]);
		if (!pomFile.exists()) {
			ok = false;
			System.err.println("File do not exist :" + pomFile);
		}
		if (!ok) {
			System.out.println("Usage :");
			System.out.println("repositoryBuilderForSIDE.jar <pluginsSRCHome> <pom.xml>");
		}

		List<ModuleConstraint> res = Builder.getModulesConstraint(rootPlugins);

		// add default dependencies to allow dependency maven plugin to be used
		// later
		ModuleConstraint McForMavenDependeciesPlugins = new ModuleConstraint("org.apache.maven.plugins.maven-dependency-plugin", null, "maven-plugin", "2.0", "2.0");

		res.add(McForMavenDependeciesPlugins);
		ModuleConstraint McForMavenDependeciesPlugins2 = new ModuleConstraint("xml-apis.xml-apis", null, "jar", "1.0.b2", "1.0.b2");
		res.add(McForMavenDependeciesPlugins2);

		try {
			Document pom = buildJdomDocument(pomFile);
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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("pom Patched");
	}

}
