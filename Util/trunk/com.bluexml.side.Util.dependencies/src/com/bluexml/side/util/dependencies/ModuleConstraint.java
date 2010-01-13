package com.bluexml.side.util.dependencies;

import java.util.ArrayList;
import java.util.Collection;

public class ModuleConstraint {

	protected String artifactId = null;
	protected String groupId = null;
	protected String moduleType = null;
	protected String tech_version = "";
	protected ModuleVersion versionMin = null;
	protected ModuleVersion versionMax = null;
	protected ModuleVersion resolvedVersion = null;

	protected static String exclusiveMin = "(";
	protected static String exclusiveMax = ")";
	protected static String inclusiveMin = "[";
	protected static String inclusiveMax = "]";

	public String getTech_version() {
		return tech_version;
	}

	public ModuleConstraint() {
	};

	public ModuleConstraint(String id, String tech_version, String moduleType, String versionNumMin, String versionNumMax) {
		setGroupAndArtifactId(id);
		this.tech_version = tech_version;
		this.moduleType = moduleType;
		if (versionNumMin != null) {
			this.versionMin = new ModuleVersion(versionNumMin);
		}
		if (versionNumMax != null) {
			this.versionMax = new ModuleVersion(versionNumMax);
		}
	}

	public ModuleConstraint(String id, String moduleType, ModuleVersion versionNumMin, ModuleVersion versionNumMax) {
		setGroupAndArtifactId(id);
		this.moduleType = moduleType;
		this.versionMin = versionNumMin;
		this.versionMax = versionNumMax;
	}

	public String getModuleType() {
		return moduleType;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public String getGroupId() {
		return groupId;
	}

	public ModuleVersion getVersionMin() {
		return versionMin;
	}

	public void setVersionMin(String versionMin) {
		this.versionMin = new ModuleVersion(versionMin);
	}

	public ModuleVersion getVersionMax() {
		return versionMax;
	}

	public void setVersionMax(String versionMax) {
		this.versionMax = new ModuleVersion(versionMax);
	}

	/**
	 * extract from moduleId the maven groupId, and artifactId
	 * 
	 * @param id
	 */
	public void setGroupAndArtifactId(String id) {
		this.groupId = id.substring(0, id.lastIndexOf("."));
		// this.artifactId = id;
		this.artifactId = id.substring(id.lastIndexOf(".") + 1);
	}

	public static Collection<ModuleVersion> getAllMin(Collection<ModuleConstraint> col) {
		Collection<ModuleVersion> ext = new ArrayList<ModuleVersion>();
		for (ModuleConstraint mc : col) {
			ext.add(mc.versionMin);
		}
		return ext;
	}

	public static Collection<ModuleVersion> getAllMax(Collection<ModuleConstraint> col) {
		Collection<ModuleVersion> ext = new ArrayList<ModuleVersion>();
		for (ModuleConstraint mc : col) {
			ext.add(mc.versionMax);
		}
		return ext;
	}

	public String toString() {
		return this.artifactId + " :" + getVersionRange();
	}

	public String getVersionRange() {
		String open = "";
		String close = "";
		String min = "";
		String max = "";
		if (versionMin != null) {
			open = inclusiveMin;
			min = versionMin.toString();
		} else {
			open = exclusiveMin;
		}
		if (versionMax != null) {
			close = inclusiveMax;
			max = versionMax.toString();
		} else {
			close = exclusiveMax;
		}
		if (min.equals(max)) {
			return min;
		}
		return open + min + "," + max + close;
	}

	public String getModuleId() {
		return groupId + "." + artifactId;
	}

	public boolean isLastVersion() {
		return versionMax == null;
	}

	public void setResolvedVersion(ModuleVersion resolvedVersion) {
		this.resolvedVersion = resolvedVersion;
	}

	public ModuleVersion getResolvedVersion() {
		return resolvedVersion;
	}
}
