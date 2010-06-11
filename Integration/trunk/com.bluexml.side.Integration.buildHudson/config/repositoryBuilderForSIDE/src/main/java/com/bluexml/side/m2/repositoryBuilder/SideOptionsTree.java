package com.bluexml.side.m2.repositoryBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import com.bluexml.side.util.dependencies.PluginsUtils;

public class SideOptionsTree {
	public static final String separator = "##";

	private Map<String, Set<String>> parentChildren = new TreeMap<String, Set<String>>();

	private Map<String, String> node2NodeType = new TreeMap<String, String>();

	public static final String techName = "technology";
	public static final String techVersion = "technologyVersion";
	public static final String geneName = "generatorVersion";
	public static final String optName = "option";
	public static final String modName = "moduleDependence";
	public static final String mustBeChecked = "mustBeChecked";
	public static final String mustBeUnchecked = "mustBeUnchecked";
	public static final String metamodel = "metamodel";

	public Map<String, Set<String>> getParentChildren() {
		return parentChildren;
	}

	public void setParentChildren(Map<String, Set<String>> parentChildren) {
		this.parentChildren = parentChildren;
	}

	public Map<String, String> getNode2NodeType() {
		return node2NodeType;
	}

	public void setNode2NodeType(Map<String, String> node2NodeType) {
		this.node2NodeType = node2NodeType;
	}

	/**
	 * 
	 * 
	 * TODO : optional display MM tech techV PluginsUtils.add maven dependencies
	 * (type = amp or zip)
	 * 
	 * @param whereToSearch
	 * @throws Exception
	 */
	public void extensionPointRenderer(File whereToSearch) throws Exception {

		// get pluginlist
		System.out.println("SideOptionsTree.extensionPointRenderer() Start");
		System.out.println("SideOptionsTree.extensionPointRenderer() search for plugin.xml files");
		List<File> lPlug = PluginsUtils.getPluginXML(whereToSearch);
		System.out.println("SideOptionsTree.extensionPointRenderer() search done plugin.xml founded :" + lPlug.size());

		for (File file : lPlug) {
			System.out.println("SideOptionsTree.extensionPointRenderer() scan :" + file);
			Document pluginDoc = PluginsUtils.buildJdomDocument(file);
			// if exists search extpoint
			Element racine = pluginDoc.getRootElement();
			XPath xpa_mm = XPath.newInstance(".//metamodel");

			List<?> metamodel = xpa_mm.selectNodes(racine);
			for (Object object : metamodel) {

				Element emm = (Element) object;
				String id_mm = emm.getAttributeValue("id");

				List<?> techs = emm.getChildren(techName);
				for (Object object2 : techs) {
					Element tech = (Element) object2;
					String id_tech = id_mm + separator + tech.getAttributeValue("id");

					// PluginsUtils.add(parentChildren, id_mm, id_tech);

					List<?> techVs = tech.getChildren(techVersion);
					for (Object object3 : techVs) {
						Element techV = (Element) object3;
						String id_techV = id_tech + separator + techV.getAttributeValue("id");

						// PluginsUtils.add(parentChildren, id_tech, id_techV);

						List<?> genes = techV.getChildren(geneName);
						for (Object object4 : genes) {
							Element gene = (Element) object4;
							String id_gene = id_techV + separator + gene.getAttributeValue("id");
							node2NodeType.put(id_gene, geneName);
							PluginsUtils.add(parentChildren, id_techV, id_gene);
							// modules
							addModule_checked_unchecked(gene, id_gene);

							// options
							List<?> options = gene.getChildren(optName);
							for (Object object5 : options) {
								Element option = (Element) object5;
								String key = id_gene + separator + option.getAttributeValue("key");

								PluginsUtils.add(parentChildren, id_gene, key);
								node2NodeType.put(key, optName);
								addModule_checked_unchecked(option, key);
							}
						}
					}
				}
			}
		}
	}

	private void addModule_checked_unchecked(Element gene, String id_parent) {
		List<?> modules1 = gene.getChildren(modName);
		for (Object object5 : modules1) {
			Element module = (Element) object5;
			String id_module = module.getAttributeValue("moduleId");
			PluginsUtils.add(parentChildren, id_parent, id_module);
			node2NodeType.put(id_module, modName);
		}

		List<?> mustBeCheckedList = gene.getChildren(mustBeChecked);
		for (Object object5 : mustBeCheckedList) {
			Element mbce = (Element) object5;
			String pluginId = mbce.getAttributeValue("pluginId");
			String optionRef = mbce.getChild("optionRef").getAttributeValue("optionId");
			String id_optionRef = pluginId + separator + optionRef;
			PluginsUtils.add(parentChildren, id_parent, id_optionRef);
			node2NodeType.put(id_optionRef, mustBeChecked);
		}

		List<?> mustBeUncheckedList = gene.getChildren(mustBeUnchecked);
		for (Object object5 : mustBeUncheckedList) {
			Element mbce = (Element) object5;
			String pluginId = mbce.getAttributeValue("pluginId");
			String optionRef = mbce.getChild("optionRef").getAttributeValue("optionId");
			String id_optionRef = pluginId + separator + optionRef;
			PluginsUtils.add(parentChildren, id_parent, id_optionRef);
			node2NodeType.put(id_optionRef, mustBeUnchecked);
		}
	}

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("Usage : repositoryBuilderForSIDE.jar ");
			System.out.println("repositoryBuilderForSIDE.jar <pluginsSRCHome> <pom.xml>");
		} else {

			File pluginRepo = new File("/Users/davidabad/.hudson/jobs/Build_RCP_Enterprise/workspace");
			SideOptionsTree sot = new SideOptionsTree();
			try {
				sot.extensionPointRenderer(pluginRepo);
				StringWriter sw = new StringWriter();
				// sot.print(sw);

				FileWriter fw = new FileWriter(new File("graph.dot"));
				DotRenderer dotRenderer = new DotRenderer(fw, sot.parentChildren, sot.node2NodeType);
				dotRenderer.render();
				fw.flush();
				fw.close();

				System.out.println(sw);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
