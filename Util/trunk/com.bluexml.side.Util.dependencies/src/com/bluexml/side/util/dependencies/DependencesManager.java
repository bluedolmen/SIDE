package com.bluexml.side.util.dependencies;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependencesManager {
	public static final String CUSTOM_FOLDER = "custom";
	private boolean offline;
	private Map<String, List<ModuleConstraint>> contraints = new HashMap<String, List<ModuleConstraint>>();
	private String generatorID;

	public DependencesManager(List<ModuleConstraint> lmc, boolean offline) {
		this.offline = offline;
		for (ModuleConstraint mc : lmc) {
			addEntry(contraints, mc.getTech_version(), mc);
		}
		System.err.println("display constraints :" + contraints); //$NON-NLS-1$
	}

	/**
	 * @return the generatorID
	 */
	public String getGeneratorID() {
		return generatorID;
	}

	/**
	 * @param generatorID
	 *            the generatorID to set
	 */
	public void setGeneratorID(String generatorID) {
		this.generatorID = generatorID;
	}

	private void addEntry(Map<String, List<ModuleConstraint>> tech_v_dep, String tech_v, ModuleConstraint mc) {
		if (tech_v_dep.containsKey(tech_v)) {
			List<ModuleConstraint> list = tech_v_dep.get(tech_v);
			if (!list.contains(mc)) {
				System.err.println("add :" + mc); //$NON-NLS-1$
				list.add(mc);
			} else {
				System.err.println("Avoid duplicate"); //$NON-NLS-1$
			}

		} else {
			ArrayList<ModuleConstraint> lmc = new ArrayList<ModuleConstraint>();
			System.err.println("add first:" + mc); //$NON-NLS-1$
			lmc.add(mc);
			tech_v_dep.put(tech_v, lmc);
		}
	}

	//	
	// public Map<String,List<ModuleConstraint>> getContraints() throws
	// Exception {
	// // made to avoid conflict between generators module dependencies (2
	// generators than depends of same Integration module but with 2 different
	// versions)
	// if (contraints == null) {
	// contraints = new HashMap<String,List<ModuleConstraint>>();
	// Map<String, List<ModuleConstraint>> byId = new HashMap<String,
	// List<ModuleConstraint>>();
	// for (ModuleConstraint moduleConstraint : contraints) {
	// addEntry(byId, moduleConstraint.getModuleId(), moduleConstraint);
	// }
	// for (Map.Entry<String, List<ModuleConstraint>> entry : byId.entrySet()) {
	// List<ModuleConstraint> l = entry.getValue();
	// if (l.size() > 1) {
	// // must merge bounds
	// ModuleVersion max = ModuleVersion.maxOf(ModuleConstraint.getAllMin(l));
	// ModuleVersion min = ModuleVersion.minOf(ModuleConstraint.getAllMax(l));
	// if (min.biggerThan(max)) {
	// String msg =
	// "Incompatible contraints found please report this bug with following :";
	// msg += "\n";
	// for (ModuleConstraint moduleConstraint2 : l) {
	// msg += moduleConstraint2 + "\n";
	// }
	// throw new Exception(msg);
	// }
	// // build new constraints
	// ModuleConstraint moduleConstraint = l.get(0);
	// ModuleConstraint mc = new
	// ModuleConstraint(moduleConstraint.getModuleId(),
	// moduleConstraint.getModuleType(), min, max);
	// contraints.add(mc);
	// } else {
	// // OK
	// contraints.addAll(l);
	// }
	// }
	// }
	// return contraints;
	//
	// }

	public void copyDependencies(File workFolder, File generateFolder, boolean custom) throws Exception {
		for (Map.Entry<String, List<ModuleConstraint>> mc : this.getContraints().entrySet()) {
			// copy dependencies
			MavenTmpProject mvp = new MavenTmpProject(workFolder, mc.getKey(), getConstraintsFor(mc.getKey()), offline);
			File whereTocopy = new File(generateFolder, mc.getKey());
			if (custom) {
				whereTocopy = new File(whereTocopy, CUSTOM_FOLDER);
			}
			mvp.copyAllDependencies(whereTocopy, generatorID);

		}
	}

	public void goOffline(File workFolder) throws Exception {
		for (Map.Entry<String, List<ModuleConstraint>> mc : this.getContraints().entrySet()) {
			// take care of offline mode
			MavenTmpProject mvp_offline = new MavenTmpProject(workFolder, mc.getKey() + "_offline", getConstraintsFor(mc.getKey()), false); //$NON-NLS-1$
			mvp_offline.goOffline(generatorID);
		}
	}

	public Map<String, List<ModuleConstraint>> getContraints() {
		return contraints;
	}

	public List<ModuleConstraint> getConstraintsFor(String tech_version) {
		return getContraints().get(tech_version);
	}
}
