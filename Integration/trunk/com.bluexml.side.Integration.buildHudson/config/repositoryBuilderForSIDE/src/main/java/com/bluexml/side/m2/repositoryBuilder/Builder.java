package com.bluexml.side.m2.repositoryBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.bluexml.side.build.tools.DependencyTree;
import com.bluexml.side.build.tools.componants.Module;
import com.bluexml.side.build.tools.reader.ComponantsRegisters;
import com.bluexml.side.util.dependencies.ModuleConstraint;
import com.bluexml.side.util.dependencies.PluginsUtils;

public class Builder {

	private static void displayUsage() {
		System.out.println("Usage : repositoryBuilderForSIDE.jar <action> <pluginsSRCHome> [<action arg>]*");
		System.out.println(" <action> : [patchPom | makeDot]?");
		System.out.println("          * patchPom :");
		System.out.println("               - <pluginsSRCHome> : directory containing Eclipse plugins");
		System.out.println("               - pom file path");
		System.out.println("               - [product file path] to use to search modules following product/feature/plugin/ext");
		System.out.println("          * makeDot :");
		System.out.println("               - <pluginsSRCHome> : directory containing Eclipse plugins");
		System.out.println("               - dot file path to output");
		System.out.println("               - [product file path] to use to search modules following product/feature/plugin/ext");
	}

	public static void main(String[] args) {
		try {
			boolean argsError = false;
			if (args == null || args.length == 0) {
				displayUsage();
			} else {
				String action = args[0];
				if (action.equals("patchPom")) {
					if (args.length > 3) {
						File src = getFile(args[1]);
						File pomPath = getFile(args[2]);

						if (args.length > 3) {
							File product = getFile(args[3]);
							File properties = null;
							if (args.length == 5) {
								properties = getFile(args[4]);
							}
							pathPom(src, pomPath, product, properties);
						} else {
							pathPom(src, pomPath);
						}

					} else {
						argsError = true;
					}

				} else if (action.equals("makeDot")) {
					File src = getFile(args[1]);
					String filePath = args[2];
					writeDotFile(src, filePath);
					System.out.println("graph dot file generated :" + filePath);
				}

				if (argsError) {
					displayUsage();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			displayUsage();
		}
	}

	private static File getFile(String path) throws FileNotFoundException {
		File rootPlugins = new File(path);
		if (!rootPlugins.exists()) {
			throw new FileNotFoundException("File do not exist :" + rootPlugins);
		}
		return rootPlugins;
	}

	private static void pathPom(File rootPlugins, File pomFile, File product, File properties) throws Exception, IOException, JDOMException, FileNotFoundException {
		System.out.println("rootPlugins :" + rootPlugins);
		System.out.println("pomFile :" + pomFile);
		System.out.println("product :" + product);
		System.out.println("properties :" + properties);
		List<ModuleConstraint> res = Builder.getModulesConstraint(rootPlugins, product, properties);
		writeScript(pomFile, res);
	}

	private static void writeScript(File pomFile, List<ModuleConstraint> res) throws IOException, Exception, JDOMException, FileNotFoundException {
		// add dependency plugin used by SIDE
		ModuleConstraint McForMavenDependeciesPlugins = new ModuleConstraint("org.apache.maven.plugins.maven-dependency-plugin", null, "maven-plugin", "2.2", "2.2");
		res.add(McForMavenDependeciesPlugins);
		// amp archetype and ampMojo
		ModuleConstraint McForArchetype_amp = new ModuleConstraint("com.bluexml.side.Framework.maven.ampArchetypeForSide", null, "jar", "1.0.5", "1.0.5");
		res.add(McForArchetype_amp);
		ModuleConstraint McMojo_amp = new ModuleConstraint("com.bluexml.side.Integration.m2.ampMojo", null, "jar", "1.0.15", "1.0.15");
		res.add(McMojo_amp);

		// zip (share) archetype
		ModuleConstraint McForArchetype_zip = new ModuleConstraint("com.bluexml.side.Framework.maven.warPatchArchetypeForSide", null, "jar", "1.0.5", "1.0.5");
		res.add(McForArchetype_zip);
		ModuleConstraint McMojo_zip = new ModuleConstraint("com.bluexml.side.Integration.m2.zipPackage", null, "jar", "1.0.19", "1.0.19");
		res.add(McMojo_zip);

		if (!pomFile.exists()) {
			System.err.println("File do not exist :" + pomFile);
		}
		List<File> poms = new ArrayList<File>();
		for (ModuleConstraint moduleConstraint : res) {
			File destFile = new File(pomFile.getParent(), res.indexOf(moduleConstraint) + "_.pom");
			FileUtils.copyFile(pomFile, destFile);
			List<ModuleConstraint> res_ = new ArrayList<ModuleConstraint>();
			res_.add(moduleConstraint);
			writePomFile(destFile, res_);
			System.out.println("pom Patched :" + destFile);
			poms.add(destFile);
		}
		// write pom.sh script that call go-offline for each patched poms
		Builder.writeShellScript(new File(pomFile.getParent(), "pom.sh"), poms);
	}

	private static void pathPom(File rootPlugins, File pomFile) throws Exception, IOException, JDOMException, FileNotFoundException {
		List<ModuleConstraint> res = Builder.getModulesConstraint(rootPlugins);
		writeScript(pomFile, res);
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

		// change artifactId, so superpom can be resolved
		Element artifactId = pom.getRootElement().getChild("artifactId");
		artifactId.setText("sideOfflineRepository");

		// save changes
		org.jdom.output.XMLOutputter out = new XMLOutputter();
		Format format = Format.getPrettyFormat();
		out.setFormat(format);
		FileOutputStream outStream = new FileOutputStream(pomFile);
		out.output(pom, outStream);
		outStream.close();
	}

	private static void writeShellScript(File script, List<File> poms) throws Exception {
		// create file
		if (!script.exists()) {
			script.createNewFile();
		}

		FileWriter fw = new FileWriter(script);

		// write header
		String header = "#!/bin/bash\n";
		header += "if [ $# -eq 1 ]; then" + "\n";
		header += "  MAVENREPO=$1" + "\n";
		header += "else" + "\n";
		header += "  echo \"Usage: patcher.sh MAVENREPO\"" + "\n";
		header += "  echo \"       with MAVENREPO = maven.repo.local absolute path\"" + "\n";
		header += "  exit -2" + "\n";
		header += "fi" + "\n";
		fw.write(header);

		for (File file : poms) {
			fw.write("mvn dependency:go-offline -P public -f " + file.getAbsolutePath() + " -Dmaven.repo.local=$MAVENREPO\n");
		}
		fw.write("mvn archetype:crawl -P public -Dmaven.repo.local=$MAVENREPO\n");
		// save close
		fw.flush();
		fw.close();
	}

	public static List<ModuleConstraint> getModulesConstraint(File sources, File product, File properties) throws Exception {
		List<ModuleConstraint> moduleIds = new ArrayList<ModuleConstraint>();

		List<File> repos = new ArrayList<File>();
		repos.add(sources);
		DependencyTree dtree = new DependencyTree(product, repos, properties);
		ComponantsRegisters componantsRegisters = dtree.getComponantsRegisters();
		Map<String, Module> modulesRegister = componantsRegisters.getModulesRegister();
		Collection<Module> values = modulesRegister.values();
		for (Module module : values) {
			ModuleConstraint mc = new ModuleConstraint(module.getModuleID(), null, module.getType(), module.getVersion(), module.getVersion());
			moduleIds.add(mc);
		}
		return moduleIds;
	}

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
}
