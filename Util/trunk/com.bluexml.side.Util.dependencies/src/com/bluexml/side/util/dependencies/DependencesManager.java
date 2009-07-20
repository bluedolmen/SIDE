package com.bluexml.side.util.dependencies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DependencesManager {

	private List<ModuleConstraint> contraints = new ArrayList<ModuleConstraint>();
	//private List<ModuleConstraint> tech_v_dep_ = null;

	
	public DependencesManager(List<ModuleConstraint> lmc) {
		contraints = lmc;
	}

//	 private void addEntry(Map<String, List<ModuleConstraint>> tech_v_dep,
//	 String tech_v, ModuleConstraint mc) {
//	 if (tech_v_dep.containsKey(tech_v)) {
//	 tech_v_dep.get(tech_v).add(mc);
//	 } else {
//	 List<ModuleConstraint> lmc = new ArrayList<ModuleConstraint>();
//	 lmc.add(mc);
//	 tech_v_dep.put(tech_v, lmc);
//	 }
//	 }

	
	
	

//	public List<ModuleConstraint> getContraints() throws Exception {
	// // made to avoid conflict between generators module dependencies (2 generators than depends of same Integration module but with 2 different versions)
//		if (tech_v_dep_ == null) {
//			tech_v_dep_ = new ArrayList<ModuleConstraint>();
//			Map<String, List<ModuleConstraint>> byId = new HashMap<String, List<ModuleConstraint>>();
//			for (ModuleConstraint moduleConstraint : tech_v_dep) {
//				addEntry(byId, moduleConstraint.getModuleId(), moduleConstraint);
//			}
//			for (Map.Entry<String, List<ModuleConstraint>> entry : byId.entrySet()) {
//				List<ModuleConstraint> l = entry.getValue();
//				if (l.size() > 1) {
//					// must merge bounds
//					ModuleVersion max = ModuleVersion.maxOf(ModuleConstraint.getAllMin(l));
//					ModuleVersion min = ModuleVersion.minOf(ModuleConstraint.getAllMax(l));
//					if (min.biggerThan(max)) {
//						String msg = "Incompatible contraints found please report this bug with following :";
//						msg += "\n";
//						for (ModuleConstraint moduleConstraint2 : l) {
//							msg += moduleConstraint2 + "\n";
//						}
//						throw new Exception(msg);
//					}
//					// build new constraints
//					ModuleConstraint moduleConstraint = l.get(0);
//					ModuleConstraint mc = new ModuleConstraint(moduleConstraint.getModuleId(), moduleConstraint.getModuleType(), min, max);
//					tech_v_dep_.add(mc);
//				} else {
//					// OK
//					tech_v_dep_.addAll(l);
//				}
//			}
//		}
//		return tech_v_dep_;
//
//	}

	public void copyDependencies(File workFolder,File whereToCopyDependencies) throws Exception {
		MavenTmpProject mvp = new MavenTmpProject(workFolder, this);
		mvp.copyAllDependencies(whereToCopyDependencies);
	}



	public List<ModuleConstraint> getContraints() {
		return contraints;
	}

}
