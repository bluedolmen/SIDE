package com.bluexml.side.util.dependencies;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

public class ModuleConstraint {

	protected String artifactId = null;
	protected String groupId = null;
	protected String classifier = null;
	protected String moduleType = null;
	protected String tech_version = ""; //$NON-NLS-1$
	protected ModuleVersion versionMin = null;
	protected ModuleVersion versionMax = null;
	protected ModuleVersion resolvedVersion = null;

	protected static String exclusiveMin = "("; //$NON-NLS-1$
	protected static String exclusiveMax = ")"; //$NON-NLS-1$
	protected static String inclusiveMin = "["; //$NON-NLS-1$
	protected static String inclusiveMax = "]"; //$NON-NLS-1$

	public String getTech_version() {
		return tech_version;
	}

	public ModuleConstraint() {
	};

	public ModuleConstraint(String id, String tech_version, String moduleType, String versionNumMin, String versionNumMax) {
		this(id, null, tech_version, moduleType, versionNumMin, versionNumMax);
	}

	public ModuleConstraint(String id, String classifier, String tech_version, String moduleType, String versionNumMin, String versionNumMax) {
		setGroupAndArtifactId(id);
		this.tech_version = tech_version;
		this.moduleType = moduleType;
		this.classifier = classifier;
		if (StringUtils.trimToNull(versionNumMin) != null) {
			this.versionMin = new ModuleVersion(versionNumMin);
		}
		if (StringUtils.trimToNull(versionNumMax) != null) {
			this.versionMax = new ModuleVersion(versionNumMax);
		}
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
	 * @return the classifier
	 */
	public String getClassifier() {
		return classifier;
	}

	/**
	 * extract from moduleId the maven groupId, and artifactId
	 * 
	 * @param id
	 */
	public void setGroupAndArtifactId(String id) {
		this.groupId = id.substring(0, id.lastIndexOf(".")); //$NON-NLS-1$
		// this.artifactId = id;
		this.artifactId = id.substring(id.lastIndexOf(".") + 1); //$NON-NLS-1$
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
		return this.groupId + ":" + this.artifactId + ":" + this.moduleType + ":" + getVersionRange(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public String getVersionRange() {
		String open = ""; //$NON-NLS-1$
		String close = ""; //$NON-NLS-1$
		String min = ""; //$NON-NLS-1$
		String max = ""; //$NON-NLS-1$
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
		return open + min + "," + max + close; //$NON-NLS-1$
	}

	public String getModuleId() {
		return groupId + "." + artifactId; //$NON-NLS-1$
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

	@Override
	public boolean equals(Object obj) {
		boolean equal = true;
		equal &= (obj instanceof ModuleConstraint);
		equal &= obj.toString().equals(this.toString());
		return equal;
	}

}
