package com.bluexml.side.build.tools.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import com.bluexml.side.build.tools.componants.Configuration;
import com.bluexml.side.build.tools.componants.Constraint;
import com.bluexml.side.build.tools.componants.Extension;
import com.bluexml.side.build.tools.componants.Generator;
import com.bluexml.side.build.tools.componants.MetaModel;
import com.bluexml.side.build.tools.componants.Module;
import com.bluexml.side.build.tools.componants.Option;
import com.bluexml.side.build.tools.componants.Plugin;
import com.bluexml.side.build.tools.componants.Technology;
import com.bluexml.side.build.tools.componants.TechnologyVersion;

public class BlxExtensionPointReader {
	Logger logger = Logger.getLogger(this.getClass());
	ComponantsRegisters registries;
	boolean deepDependencies = false;

	public BlxExtensionPointReader(ComponantsRegisters regitries) {
		this.registries = regitries;
	}

	public List<Extension> read(File filePluginXML, String bundle) throws Exception {
		List<Extension> lext = new ArrayList<Extension>();
		// get XML doc
		Document pluginXML = Utils.buildJdomDocument(filePluginXML);

		// search ext
		XPath xpa = XPath.newInstance("/plugin/extension");
		List<?> exts = xpa.selectNodes(pluginXML);
		for (Object object : exts) {
			Element extXML = (Element) object;

			if (extXML.getAttributeValue("point").equals("com.bluexml.side.Application.com_bluexml_application_configuration")) {
				// SIDE Ext
				logger.debug("Extension found, extracting data");
				List<?> lmm = extXML.getChildren("metamodel");
				for (Object object2 : lmm) {
					Element metaE = (Element) object2;

					// MM
					MetaModel metaModel = new MetaModel();
					metaModel.setDescription(metaE.getAttributeValue("description"));
					metaModel.setId(metaE.getAttributeValue("id"));
					metaModel.setName(metaE.getAttributeValue("name"));
					metaModel.setUrl(metaE.getAttributeValue("url"));

					logger.debug("MM :" + metaModel.getId());

					// technology

					List<?> ltech = metaE.getChildren("technology");
					for (Object object3 : ltech) {
						Element techE = (Element) object3;
						Technology tech = new Technology();
						tech.setDescription(techE.getAttributeValue("description"));
						tech.setId(techE.getAttributeValue("id"));
						tech.setName(techE.getAttributeValue("name"));
						tech.setUrl(techE.getAttributeValue("url"));

						logger.debug("Tech :" + tech.getId());

						// technologyVersion

						List<?> ltechV = techE.getChildren("technologyVersion");
						for (Object object4 : ltechV) {
							Element techVE = (Element) object4;
							TechnologyVersion techV = new TechnologyVersion();
							techV.setDescription(techVE.getAttributeValue("description"));
							techV.setId(techVE.getAttributeValue("id"));
							techV.setVersion(techVE.getAttributeValue("version"));

							logger.debug("techV :" + techV.getId());

							// generatorVersion

							List<?> ltgene = techVE.getChildren("generatorVersion");
							for (Object object5 : ltgene) {
								Element geneE = (Element) object5;
								Generator gene = new Generator();
								gene.setClassId(geneE.getAttributeValue("class"));
								gene.setDescription(geneE.getAttributeValue("description"));
								gene.setHidden(geneE.getAttributeValue("hidden"));
								gene.setMetaModel(metaModel);
								gene.setTechno(tech);
								gene.setTechno_version(techV);
								gene.setVersion(geneE.getAttributeValue("version"));
								gene.setId(geneE.getAttributeValue("id"));

								logger.debug("Generator : " + gene.getId());

								// configuration
								getConf(geneE, gene);

								List<?> lop = geneE.getChildren("option");
								for (Object object6 : lop) {
									Element optionE = (Element) object6;
									Option op = new Option();
									op.setKey(optionE.getAttributeValue("key"));
									op.setLabel(optionE.getAttributeValue("label"));

									op.setFullKey(bundle + "_" + op.getKey());
									Option optionfromRegistry = registries.optionRegister.get(op.getFullKey());
									if (optionfromRegistry == null) {
										registries.optionRegister.put(op.getFullKey(), op);
									} else {
										logger.warn("@@@ For File :" + filePluginXML);
										logger.warn("@@@ Error ?? duplicate extension same bundle same key ??");
										logger.warn("@@@ For generator :" + gene);
										logger.warn("@@@ For option :" + op);
										op = optionfromRegistry;
									}

									// configuration
									getConf(optionE, op);

									logger.debug("Option :" + op.getFullKey());

									Utils.add(registries.tree, gene, op);
									gene.getOptions().add(op);
								}
								lext.add(gene);

							}

						}

					}

				}
			}

		}
		return lext;
	}

	private Configuration getConf(Element context, Configuration conf) throws Exception {
		logger.debug("START");
		// get constraints
		try {
			List<?> mustBeUncheckedL = context.getChildren("mustBeUnchecked");
			for (Object object : mustBeUncheckedL) {
				registerCheckConstraints(conf, object, false);
			}
			List<?> mustBeCheckedL = context.getChildren("mustBeChecked");
			for (Object object : mustBeCheckedL) {
				registerCheckConstraints(conf, object, true);
			}
		} catch (Exception e) {
			logger.error("Error while register check/uncheck constraints", e);
		}
		// get Modules
		List<?> modules = context.getChildren("moduleDependence");
		for (Object object : modules) {
			Element moduleE = (Element) object;

			String moduleId = moduleE.getAttributeValue("moduleId");

			// get module
			Module module = registries.modulesRegister.get(moduleId);
			if (module == null) {
				File pom = registries.getProjectFolder(moduleId);
				
				if (pom.exists() && deepDependencies) {
					// use maven to get all dependencies
					MavenProjectReader mpr = new MavenProjectReader(registries);
					module = mpr.read(pom);
				} else {
					logger.error("pom resource not found, add module without read pom (no dependencies added):" + moduleId);
					module = new Module();
					module.setModuleID(moduleId);
					module.setType(moduleE.getAttributeValue("moduleType"));
					module.setVersion(moduleE.getAttributeValue("versionMax"));

				}
				logger.debug("Maven Module :" + module.getModuleID());
			}
			conf.getModules().add(module);
			registries.modulesRegister.put(module.getModuleID(), module);
			Utils.add(registries.tree, conf, module);

		}
		logger.debug("END");
		return conf;
	}

	/**
	 * @param conf
	 * @param object
	 * @throws Exception
	 */
	private void registerCheckConstraints(Configuration conf, Object object, boolean check) throws Exception {
		Element munE = (Element) object;
		String bundle = munE.getAttributeValue("pluginId");
		String optionRef = munE.getChild("optionRef").getAttributeValue("optionId");
		Constraint cons = new Constraint();
		Option op = getOption(bundle, optionRef, cons);
		cons.setOption(op);
		cons.setCheck(check);
		logger.debug("Constraint :" + cons.getBundle() + "/" + cons.getOption());
		if (cons.getBundle() != null && cons.getOption() != null) {
			conf.getConstraints().add(cons);
			Utils.add(registries.tree, conf, cons);
		} else {
			throw new Exception("Check Constraints Ref not found for bundle" + bundle + " ref:" + optionRef);
		}

	}

	private Option getOption(String bundle, String optionRef, Constraint cons) throws Exception {
		Plugin p = registries.pluginsRegister.get(bundle);
		Option op = null;
		
		if (p == null) {
			logger.debug("Plugin not yet registered so search in repository for bundle :" + bundle);
			// we must read the plugin definition
			File pluginFolder = registries.getProjectFolder(bundle);
			if (pluginFolder != null) {
//				PluginReader pr = new PluginReader(registries);
//				p = pr.read(pluginFolder);
			} else {
				logger.error("Plugin not found for bundle :" + bundle);
			}
		}

		// this plugin is recorded its options should be recorded too
//		cons.setBundle(p);
		
		
		op = registries.optionRegister.get(bundle + "_" + optionRef);

		return op;
	}
}
