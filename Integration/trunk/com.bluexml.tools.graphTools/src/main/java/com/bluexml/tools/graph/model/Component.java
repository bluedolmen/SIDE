package com.bluexml.tools.graph.model;

public abstract class Component implements Comparable<Component> {
	boolean filterMatch = false;

	public int compareTo(Component o) {
		return this.toString().compareTo(o.toString());
	}

	public boolean isFilterMatch() {
		return filterMatch;
	}

	public void setFilterMatch(boolean filterMatch) {
		this.filterMatch = filterMatch;
	}

	
}
