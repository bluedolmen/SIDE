package com.bluexml.side.util.generator.dependency;

import java.util.Collection;

public class ModuleVersion {
	static String separator = ".";
	static String splitSeparator = "\\.";
	int major = 0;
	int middle = 0;
	int minor = 0;
	String buildVersion = "";

	public ModuleVersion() {

	}

	public ModuleVersion(int major, int middle, int minor) {
		this.major = major;
		this.middle = middle;
		this.minor = minor;
	}

	public ModuleVersion(String version) {
		if (version != null) {
			String[] v = version.split(splitSeparator);
			this.major = Integer.parseInt(v[0]);
			if (v.length > 1) {
				this.middle = Integer.parseInt(v[1]);
			}
			if (v.length > 2) {
				this.minor = Integer.parseInt(v[2]);
			}
			for (int i = 3; v.length < i; i++) {
				this.buildVersion += v[i];
			}
		}
	}

	public boolean biggerThan(ModuleVersion mv) {
		if (this.major > mv.major) {
			return true;
		} else if (this.major == mv.major) {
			if (this.middle > mv.middle) {
				return true;
			} else if (this.middle == mv.middle) {
				if (this.minor > mv.minor) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean equals(Object o) {
		if (o instanceof ModuleVersion) {
			ModuleVersion mv = (ModuleVersion) o;
			return this.major == mv.major && this.middle == mv.middle && this.minor == mv.minor;
		}
		return false;
	}

	public static ModuleVersion minOf(Collection<ModuleVersion> list) {
		ModuleVersion smaller = null;
		for (ModuleVersion iterable_element : list) {
			if (smaller == null) {
				smaller = iterable_element;
			} else {
				if (smaller.biggerThan(iterable_element)) {
					smaller = iterable_element;
				}
			}
		}
		return smaller;
	}

	public static ModuleVersion maxOf(Collection<ModuleVersion> list) {
		ModuleVersion biggest = null;
		for (ModuleVersion iterable_element : list) {
			if (biggest == null) {
				biggest = iterable_element;
			} else {
				if (iterable_element.biggerThan(biggest)) {
					biggest = iterable_element;
				}
			}
		}
		return biggest;
	}

	public String toString() {
		if (buildVersion.equals("")) {
			return major + separator + middle + separator + minor;
		}
		return major + separator + middle + separator + minor + separator + buildVersion;
	}

}
