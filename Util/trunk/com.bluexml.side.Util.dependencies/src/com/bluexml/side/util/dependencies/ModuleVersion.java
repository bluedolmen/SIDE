package com.bluexml.side.util.dependencies;

import java.util.Collection;

public class ModuleVersion {
	static String separator = ".";
	static String splitSeparator = "\\.";
	Integer major = null;
	Integer middle = null;
	Integer minor = null;
	String qualifier = "";

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

			switch (v.length) {
			case 1:
				System.out.println("CASE1");
				// version=1 ; version=1-SNAPSHOT
				String p1 = v[0];
				boolean isqualifier_0 = !p1.matches("^[\\d]*$");
				if (isqualifier_0) {
					if (!p1.matches("^[^\\d]*$")) {
						this.major = Integer.parseInt(p1.replaceAll("[^\\d]*$", ""));
					}
					this.qualifier = p1.replaceAll("^[\\d]*", "");
				} else {
					this.major = Integer.parseInt(v[0]);
				}
				break;
			case 2:
				System.out.println("CASE2");
				// version=1.1 version 1.1-SNAPSHOT
				this.major = Integer.parseInt(v[0]);
				// search for qualifier
				String p2 = v[1];
				boolean isqualifier_1 = !p2.matches("^[\\d]*$");
				if (isqualifier_1) {
					if (!p2.matches("^[^\\d]*$")) {
						this.middle = Integer.parseInt(p2.replaceAll("[^\\d]*$", ""));
					}
					this.qualifier = p2.replaceAll("^[\\d]*", "");
				} else {
					this.middle = Integer.parseInt(v[1]);
				}
				break;
			case 3:
				System.out.println("CASE3");
				// version 1.1.1 version=1.1.1-SNAPSHOT
				this.major = Integer.parseInt(v[0]);
				this.middle = Integer.parseInt(v[1]);
				// search for qualifier
				String p3 = v[2];
				boolean isqualifier_2 = !p3.matches("^[\\d]*$");
				if (isqualifier_2) {
					if (!p3.matches("^[^\\d]*$")) {
						this.minor = Integer.parseInt(p3.replaceAll("[^\\d]*$", ""));
					}
					this.qualifier = p3.replaceAll("^[\\d]*", "");
				} else {
					this.minor = Integer.parseInt(v[2]);
				}
				break;
			case 4:
				System.out.println("CASE4");
				// version 1.1.1.qualifier
				this.major = Integer.parseInt(v[0]);
				this.middle = Integer.parseInt(v[1]);
				this.minor = Integer.parseInt(v[2]);
				this.qualifier = v[3];
				break;
			default:
				System.out.println("CASE default");
				// version 1.1.1.qualifier.qualifier
				if (v.length > 4) {
					this.major = Integer.parseInt(v[0]);
					this.middle = Integer.parseInt(v[1]);
					this.minor = Integer.parseInt(v[2]);					
					for (int i = 3; i < v.length; i++) {
						this.qualifier += v[i];
						this.qualifier +=".";
					}
					// remove last '.'
					this.qualifier = this.qualifier.substring(0,this.qualifier.length()-1);
				}
				break;
			}
		}
		// System.out.println("build VersionNumber ::" + version + "-->" + this.toString());
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
		String version="";
		if (this.major !=null) {
			version=this.major.toString();
		}
		if (this.middle !=null) {
			version+=separator;
			version+=this.middle.toString();
		}
		if (this.minor !=null) {
			version+=separator;
			version+=this.minor.toString();
		}
		
		if (!qualifier.equals("")) {
			version+=qualifier;
		}
		return version;
		
	}

}
