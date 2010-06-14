package com.bluexml.side.application.ui.action.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.table.GeneratorParameter;
import com.bluexml.side.application.ui.action.utils.ApplicationUtil;

public class ConfigurationContentProvider implements ITreeContentProvider {

	private Map<?, ?> rootSet;
	private Map<Metamodel, Metamodel> metamodelSet = new TreeMap<Metamodel, Metamodel>();
	private Map<Technology, Technology> technologySet = new TreeMap<Technology, Technology>();
	private Map<TechnologyVersion, TechnologyVersion> technologyVersionSet = new TreeMap<TechnologyVersion, TechnologyVersion>();
	private Map<Generator, Generator> generatorSet = new TreeMap<Generator, Generator>();
	private Map<Deployer, Deployer> deployerSet = new TreeMap<Deployer, Deployer>();
	private Map<String, OptionGenerator> optGeneratorSet = new TreeMap<String, OptionGenerator>();
	private Map<String, OptionDeployer> optDeployerSet = new TreeMap<String, OptionDeployer>();
	private Map<Class<?>, Map<?, ?>> classByLevel = new HashMap<Class<?>, Map<?, ?>>();
	private Class<?> neededRootClass;
	List<?> omitedObject;
	private TreeView root;
	private Map<String, GeneratorParameter> configurationParameters;
	private Map<String, GeneratorParameter> deployerParameters;
	private Map<String, List<String>> genParamConfByGenerator;
	private Map<String, List<String>> deployParamConfByGenerator;

	private List<TreeNode> toCheck = new ArrayList<TreeNode>();

	public ConfigurationContentProvider(Class<?> p_neededRootClass, List<?> p_ommitedObject, TreeView p_tv, Map<String, GeneratorParameter> p_configurationParameters,
			Map<String, GeneratorParameter> p_deployerParameters, Map<String, List<String>> p_genParamConfByGenerator, Map<String, List<String>> p_deployParamConfByGenerator) {
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
			Object[] arr = elt.getChildren().toArray();
			ArrayList<Object> result = new ArrayList<Object>();
			// System.out.println("getChildren from :" + elt);
			for (Object o : arr) {
				if (o instanceof TreeNode) {
					TreeNode tn = (TreeNode) o;
					if (!tn.isToHidde()) {
						result.add(tn);
						// System.err.println("getChildrens ADD :" + tn);
					} else {
						// System.err.println("getChildrens HIDDEN :" + tn);
					}
				} else {
					// System.out.println("NOT TREE NODE :" + o);
				}
			}
			return result.toArray();
		}
		// System.out.println("Very Bad !");
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
		// System.out.println("getEleemnts :" + object);
		if (object instanceof ApplicationDialog) {
			initialize();
			ArrayList<Object> result = new ArrayList<Object>();
			for (Object o : rootSet.values()) {
				if (o instanceof TreeNode) {
					TreeNode tn = (TreeNode) o;
					tn.setEnabled(true);
					if (!tn.isToHidde()) {
						result.add(tn);
						// System.out.println("CHILD :" + o);
					} else {
						// System.out.println("HIDDE :" + o);
					}
				} else {
					// System.out.println("NOT TREE NODE :" + o);
				}
			}

			return result.toArray();
		}
		return getChildren(object);
	}

	/**
	 * Read all extension point and construct the tree
	 */
	public void initialize() {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(ApplicationUtil.EXTENSIONPOINT_ID);
		// System.err.println("-----------------------------------------------------------------");
		// Tree initialization
		for (IConfigurationElement config : contributions) {
			// System.out.println("Extension : " + config.getName() + " " +
			// config.getNamespaceIdentifier() + " (" +
			// config.getAttribute("id") + " " + config.getAttribute("name") +
			// ")");
			manageConfiguration(config, null);
		}
		// System.err.println("Tree :");
		// printTree();
		// Now we hide branches of the tree without generator or deployer leaf
		cleanupTree(rootSet);
		initializeFromKey();
//		printTree();

	}

	/**
	 * Will seek over the rootSet and will hide all element of a branch with no
	 * generator or deployer (or with hidden leaf).
	 */
	protected void cleanupTree(Map<?, ?> rootSet) {
		Collection<?> rootEntry = rootSet.values();
		for (Object o : rootEntry) {
			if (o instanceof TreeNode) {
				TreeNode tn = (TreeNode) o;
				if (!cleanupBranch(tn)) {
					tn.setToHidde(true);
					// System.err.println("CleanUp :" + tn);
				}
			}
		}
	}

	private boolean cleanupBranch(TreeNode tn) {
		boolean result = false;
		for (TreeNode child : tn.getChildren()) {
			if (child instanceof ImplNode) {
				if (!child.isToHidde()) {
					result = true;
				}
			} else if (child.getChildren().size() > 0) {
				for (TreeNode c : tn.getChildren()) {
					result = result | cleanupBranch(c);
				}
			}
		}
		if (!result) {
			tn.setToHidde(true);
		}
		return result;
	}

	/**
	 * For each element of the extension will manage it and create the
	 * corresponding object
	 * 
	 * @param config
	 * @param parent
	 */
	protected void manageConfiguration(IConfigurationElement config, TreeNode parent) {
		TreeNode futurParent = null;
		// Scan for metamodels
		if (!omitedObject.contains(Metamodel.class) && config.getName().equalsIgnoreCase("metamodel")) {
			// We create the metal for this config element
			Metamodel m = new Metamodel(config, root);
			// We check if we already have this metamodel in your set
			if (!metamodelSet.containsKey(m)) {
				metamodelSet.put(m, m);
				// System.err.println("\t + Add metamodel " + m.getId());
			} else {
				m = metamodelSet.get(m);
				// System.err.println("\t * Get metamodel " + m.getId());
			}
			futurParent = m;
		}

		// Scan for technology
		if (!omitedObject.contains(Technology.class) && config.getName().equalsIgnoreCase("technology")) {
			Technology t = new Technology(config, (Metamodel) parent, root);
//			String fullId = t.getFullId();
			if (!technologySet.containsKey(t) || (rootSet != technologySet && parent != technologySet.get(t).getParent())) {
				technologySet.put(t, t);
				// System.err.println("\t\t + Add techno " + fullId);
			} else {
				t = technologySet.get(t);
				// System.err.println("\t\t * Get techno " + fullId);
			}
			futurParent = t;
		}

		// Scan for technology version
		if (!omitedObject.contains(TechnologyVersion.class) && config.getName().equalsIgnoreCase("technologyVersion")) {
			TechnologyVersion tv = new TechnologyVersion(config, (Technology) parent, root);
//			String fullId = tv.getFullId();
			if (!technologyVersionSet.containsKey(tv) || (rootSet != technologyVersionSet && parent != technologyVersionSet.get(tv).getParent())) {
				technologyVersionSet.put(tv, tv);
				// System.err.println("\t\t\t + Add technoVersion " + fullId);
			} else {
				tv = technologyVersionSet.get(tv);
				// System.err.println("\t\t\t * Get technoVersion " + fullId);
			}
			futurParent = tv;
		}

		// Scan for Generator Version
		if (!omitedObject.contains(Generator.class) && config.getName().equalsIgnoreCase("generatorVersion")) {
			if (config.getAttribute("hidden") == null || !config.getAttribute("hidden").equals("hidden")) {
				Generator gv = new Generator(config, (TechnologyVersion) parent, root);
				if (config.getAttribute("hidden") != null && config.getAttribute("hidden").equals("hidden and used by default")) {
					gv.setToHidde(true);
					toCheck.add(gv);
				}
//				String fullId = gv.getFullId() + " desc:" + gv.getDescription();
				if (!generatorSet.containsKey(gv) || (rootSet != technologyVersionSet && parent != generatorSet.get(gv).getParent())) {
					generatorSet.put(gv, gv);
					// System.err.println("\t\t\t\t + Add Generator " + fullId);
				} else {
					gv = generatorSet.get(gv);
					// System.err.println("\t\t\t\t * Get Generator " + fullId);
				}
				futurParent = gv;
			}
		}

		// Scan for deployer version
		if (!omitedObject.contains(Deployer.class) && config.getName().equalsIgnoreCase("deployerVersion")) {
			if (config.getAttribute("hidden") == null || !config.getAttribute("hidden").equals("hidden")) {
				Deployer dv = new Deployer(config, (TechnologyVersion) parent, root);
				if (config.getAttribute("hidden") != null && config.getAttribute("hidden").equals("hidden and used by default")) {
					dv.setToHidde(true);
					toCheck.add(dv);
				}
				// String fullId = dv.getFullId();
				if (!deployerSet.containsKey(dv) || (rootSet != deployerSet && parent != deployerSet.get(dv).getParent())) {
					deployerSet.put(dv, dv);
				} else {
					dv = deployerSet.get(dv);
				}
				futurParent = dv;
			}
		}

		// Scan for generator or deployer option
		if (!omitedObject.contains(OptionComponant.class) && config.getName().equalsIgnoreCase("option")) {
			OptionComponant opt = null;
			if (parent instanceof Generator) {
				opt = new OptionGenerator(config, (Generator) parent, root);
				// String fullid = opt.getFullId();
				if (!optGeneratorSet.containsKey(opt.getLabel() + opt.getId())) {
					optGeneratorSet.put(opt.getLabel() + opt.getId(), (OptionGenerator) opt);
				} else {
					opt = optGeneratorSet.get(opt.getLabel() + opt.getId());
				}
			} else if (parent instanceof Deployer) {
				opt = new OptionDeployer(config, (Deployer) parent, root);
				// String fullid = opt.getFullId();
				if (!optDeployerSet.containsKey(opt.getLabel() + opt.getId())) {
					optDeployerSet.put(opt.getLabel() + opt.getId(), (OptionDeployer) opt);
				} else {
					opt = optDeployerSet.get(opt.getLabel() + opt.getId());
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
			TreeNode child = parent.getChild(futurParent.getId());
			if (child == null) {
				// System.out.println("ADDChildren !! : parent " + parent +
				// ", child " + futurParent);
				parent.addChildren(futurParent);
			} else {
				// child exist but can be different from futureParent, recusive
				// call must be done on same instance, so replca futureParent by
				// founded child
				// System.err.println("SKIPPED Node found in child !! : parent "
				// + parent + ", child " + futurParent);
				futurParent = child;
				// merge(child, futurParent);
			}
		} else {
			// System.err.println("SKIPPED !! : parent " + parent + ", child " +
			// futurParent);
		}

		if (futurParent != null) {
			for (IConfigurationElement child : config.getChildren()) {
				// //System.err.println("Manage conf for child " +
				// (child.getAttribute("id") != null ? child.getAttribute("id")
				// : child.getAttribute("key")) + " and parent " + (parent !=
				// null ? parent.getId() : ""));
				manageConfiguration(child, futurParent);

			}
		}
	}

	public List<TreeNode> getToCheck() {
		return toCheck;
	}

	/**
	 * Initialise les �l�ments de l'arbre en les checkant sur la cl�. Si
	 * l'�l�ment est invalide il sera desactiv�
	 */
	public void initializeFromKey() {

	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void printTree() {
		System.err.println("MetaModel");
		for (TreeNode mm : metamodelSet.values()) {
			printTree_(mm, 0);
		}
//		System.err.println("Tech");
//		for (TreeNode mm : technologySet.values()) {
//			printTree_(mm, 0);
//		}
//		System.err.println("technologyVersion");
//		for (TreeNode mm : technologyVersionSet.values()) {
//			printTree_(mm, 0);
//		}
//
//		System.err.println("generator");
//		for (TreeNode mm : generatorSet.values()) {
//			printTree_(mm, 0);
//		}

	}

	/**
	 * @param mm
	 */
	private void printTree_(TreeNode mm, int level) {
		String identation = "";
		for (int i = 0; i < level; i++) {
			identation += "\t";
		}
		System.out.println(identation + mm);
		Collection<TreeNode> chidren = mm.getChildren();
		for (TreeNode treeNode : chidren) {
			printTree_(treeNode, level + 1);
		}
	}

}
