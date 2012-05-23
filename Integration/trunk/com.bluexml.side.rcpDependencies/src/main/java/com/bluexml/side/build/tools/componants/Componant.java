package com.bluexml.side.build.tools.componants;

public abstract class Componant implements Comparable<Componant> {
	boolean filterMatch = false;

	public int compareTo(Componant o) {
		return this.toString().compareTo(o.toString());
	}

	public boolean isFilterMatch() {
		return filterMatch;
	}

	public void setFilterMatch(boolean filterMatch) {
		this.filterMatch = filterMatch;
	}

	
}
