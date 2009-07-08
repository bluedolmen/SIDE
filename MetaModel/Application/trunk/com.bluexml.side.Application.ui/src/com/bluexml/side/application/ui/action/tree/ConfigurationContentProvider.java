package com.bluexml.side.application.ui.action.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.table.GeneratorParameter;

public class ConfigurationContentProvider implements ITreeContentProvider {

	private Map<?, ?> rootSet;
	private Map<String, Metamodel> metamodelSet = new HashMap<String, Metamodel>();
	private Map<String, Technology> technologySet = new HashMap<String, Technology>();
	private Map<String, TechnologyVersion> technologyVersionSet = new HashMap<String, TechnologyVersion>();
	private Map<String, Generator> generatorSet = new HashMap<String, Generator>();
	private Map<String, Deployer> deployerSet = new HashMap<String, Deployer>();
	private Map<String, OptionGenerator> optGeneratorSet = new HashMap<String, OptionGenerator>();
	private Map<String, OptionDeployer> optDeployerSet = new HashMap<String, OptionDeployer>();
	private Map<Class<?>, Map<?, ?>> classByLevel = new HashMap<Class<?>, Map<?, ?>>();
	private Class<?> neededRootClass;
	List<?> omitedObject;
	private TreeView root;
	private Map<String, GeneratorParameter> configurationParameters;
	private Map<String, GeneratorParameter> deployerParameters;
	private Map<String, List<String>> genParamConfByGenerator;
	private Map<String, List<String>> deployParamConfByGenerator;
	private static String EXTENSIONPOINT_ID = "com.bluexml.side.Application.com_bluexml_application_configuration";

	public ConfigurationContentProvider(Class<?> p_neededRootClass, List<?> p_ommitedObject,
			TreeView p_tv, Map<String, GeneratorParameter> p_configurationParameters,
			Map<String, GeneratorParameter> p_deployerParameters, Map<String, List<String>> p_genParamConfByGenerator,
			Map<String, List<String>> p_deployParamConfByGenerator) {
		configurationParameters = p_configurationParameters;
		deployerParameters = p_deployerParameters;
		genParamConfByGenerator = p_genParamConfByGenerator;
		deployParamConfByGenerator = p_deployParamConfByGenerator;
		root = p_tv;
		neededRootClass = p_neededRootClass;
		if (p_ommitedObject != null) {
			omitedObject = p_ommitedObject;
		} else {
			omitedObject = new ArrayList<Class<?>>();
		}
		initializeClassByLevel();
		if (classByLevel.containsKey(neededRootClass)) {
			rootSet = classByLevel.get(neededRootClass);
		} else {
			rootSet = metamodelSet;
		}
	}

	/**
	 * Initialize the map with Class --> Set corresponding
	 */
	private void initializeClassByLevel() {
		classByLevel.put(Metamodel.class, metamodelSet);
		classByLevel.put(Technology.class, technologySet);
		classByLevel.put(TechnologyVersion.class, technologyVersionSet);
		classByLevel.put(Generator.class, generatorSet);
		classByLevel.put(Deployer.class, deployerSet);
		classByLevel.put(OptionGenerator.class, optGeneratorSet);
		classByLevel.put(OptionDeployer.class, optDeployerSet);
	}

	public Object[] getChildren(Object object) {
		if (object instanceof TreeNode) {
			TreeNode elt = (TreeNode) object;
			return elt.getChildren().toArray();
		}
		return null;
	}

	public Object getParent(Object object) {
		if (object instanceof TreeNode) {
			return ((TreeNode) object).getParent();
		}
		return null;
	}

	public boolean hasChildren(Object arg0) {
		// Get the children
		Object[] obj = getChildren(arg0);

		// Return whether the parent has children
		return obj == null ? false : obj.length > 0;
	}

	/**
	 * Return the elements corresponding (root nodes or childrens)
	 */
	public Object[] getElements(Object object) {
		if (object instanceof ApplicationDialog) {
			initialize();
			for (Object o : rootSet.values()) {
				if (o instanceof TreeNode) {
					((TreeNode) o).setEnabled(true);
				}
			}
			return rootSet.values().toArray();
		} else
			return getChildren(object);
	}

	/**
	 * Read all extension point and construct the tree
	 */
	public void initialize() {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIONPOINT_ID);
		System.err.println("-----------------------------------------------------------------");
		for (IConfigurationElement config : contributions) {
			System.err.println("DEBUG : " + config.getName() + " " + config.getNamespaceIdentifier() + " (" + config.getAttribute("id") + " " + config.getAttribute("name") + ")");
			manageConfiguration(config, null);
		}
		initializeFromKey();
	}

	/**
	 * For each element of the extension will manage it and create the
	 * corresponding object
	 *
	 * @param config
	 * @param parent
	 */
	private void manageConfiguration(IConfigurationElement config, TreeNode parent) {
		TreeNode futurParent = null;
		// Scan for metamodels
		if (config.getName().equalsIgnoreCase("metamodel")) {
			// We create the metal for this config element
			Metamodel m = new Metamodel(config, root);
			// We check if we already have this metamodel in your set
			if (!metamodelSet.containsKey(m.getId())) {
				metamodelSet.put(m.getId(), m);
				System.err.println("\t + Add metamodel " + m.getId());
			} else {
				m = metamodelSet.get(m.getId());
				System.err.println("\t * Get metamodel " + m.getId());
			}
			futurParent = m;
		}

		// Scan for technology
		if (!omitedObject.contains(Technology.class) && config.getName().equalsIgnoreCase("technology")) {
			Technology t = new Technology(config, (Metamodel) parent, root);
			String fullId = t.getFullId();
			if (!technologySet.containsKey(fullId) || (rootSet != technologySet && parent != technologySet.get(fullId).getParent())) {
				technologySet.put(fullId, t);
				System.err.println("\t\t + Add techno " + fullId);
			} else {
				t = technologySet.get(fullId);
				System.err.println("\t\t * Get techno " + fullId);
			}
			futurParent = t;
		}

		// Scan for technology version
		if (!omitedObject.contains(TechnologyVersion.class) && config.getName().equalsIgnoreCase("technologyVersion")) {
			TechnologyVersion tv = new TechnologyVersion(config, (Technology) parent, root);
			String fullId = tv.getFullId();
			if (!technologyVersionSet.containsKey(fullId) || (rootSet != technologyVersionSet && parent != technologyVersionSet.get(fullId).getParent())) {
				technologyVersionSet.put(fullId, tv);
				System.err.println("\t\t\t + Add technoVersion " + fullId);
			} else {
				tv = technologyVersionSet.get(fullId);
				System.err.println("\t\t\t * Get technoVersion " + fullId);
			}
			futurParent = tv;
		}

		// Scan for Generator Version
		if (!omitedObject.contains(Generator.class) && config.getName().equalsIgnoreCase("generatorVersion")) {
			if (config.getAttribute("hidden") == null || config.getAttribute("hidden").equals("visible")) {
				Generator gv = new Generator(config, (TechnologyVersion) parent, root);
				String fullId = gv.getFullId();
				if (!generatorSet.containsKey(fullId) ||
						(rootSet != technologyVersionSet && parent != generatorSet.get(fullId).getParent())) {
					generatorSet.put(fullId, gv);
					System.err.println("\t\t\t\t + Add Generator " + fullId);
				} else {
					gv = generatorSet.get(fullId);
					System.err.println("\t\t\t\t * Get Generator " + fullId);
				}
				futurParent = gv;
			}
		}

		// Scan for deployer version
		if (!omitedObject.contains(Deployer.class) && config.getName().equalsIgnoreCase("deployerVersion")) {
			Deployer dv = new Deployer(config, (TechnologyVersion) parent, root);
			String fullId = dv.getFullId();
			if (!deployerSet.containsKey(fullId) || (rootSet != deployerSet && parent != deployerSet.get(fullId).getParent())) {
				deployerSet.put(fullId, dv);
			} else {
				dv = deployerSet.get(fullId);
			}
			futurParent = dv;
		}

		// Scan for generator or deployer option
		if (!omitedObject.contains(OptionComponant.class) && config.getName().equalsIgnoreCase("option")) {
			OptionComponant opt = null;
			if (parent instanceof Generator) {
				opt = new OptionGenerator(config, (Generator) parent, root);
				String fullid = opt.getFullId();
				if (!optGeneratorSet.containsKey(fullid)) {
					optGeneratorSet.put(fullid, (OptionGenerator) opt);
				} else {
					opt = optGeneratorSet.get(fullid);
				}
			} else if (parent instanceof Deployer) {
				opt = new OptionDeployer(config, (Deployer) parent, root);
				String fullid = opt.getFullId();
				if (!optDeployerSet.containsKey(fullid)) {
					optDeployerSet.put(fullid, (OptionDeployer) opt);
				} else {
					opt = optDeployerSet.get(fullid);
				}
			}
			futurParent = opt;
		}

		// Scan for generator or deployer parameter
		if (config.getName().equalsIgnoreCase("configurationParameter")) {
			GeneratorParameter param = null;
			if (parent instanceof Generator) {
				Generator g = (Generator) parent;
				param = new GeneratorParameter(config);
				if (!genParamConfByGenerator.containsKey(g.getId())) {
					genParamConfByGenerator.put(g.getId(), new ArrayList<String>());
				}
				genParamConfByGenerator.get(g.getId()).add(param.getKey());
				configurationParameters.put(param.getKey(), param);
			} else if (parent instanceof Deployer) {
				Deployer d = (Deployer) parent;
				param = new GeneratorParameter(config);
				if (!deployParamConfByGenerator.containsKey(d.getId())) {
					deployParamConfByGenerator.put(d.getId(), new ArrayList<String>());
				}
				deployParamConfByGenerator.get(d.getId()).add(param.getKey());
				deployerParameters.put(param.getKey(), param);
			}
			futurParent = null;
		}

		// Will add children if not already set
		if (parent != null && futurParent != null) {
			if (parent.getChild(futurParent.getId()) == null) {
				parent.addChildren(futurParent);
			}
		}

		for (IConfigurationElement child : config.getChildren()) {
			// System.err.println("Manage conf for child " +
			// (child.getAttribute("id") != null ? child.getAttribute("id")
			// : child.getAttribute("key")) + " and parent " + (parent !=
			// null ? parent.getId() : ""));
			manageConfiguration(child, futurParent);

		}
	}

	/**
	 * Initialise les éléments de l'arbre en les checkant sur la clé. Si
	 * l'élément est invalide il sera desactivé
	 */
	public void initializeFromKey() {

	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
