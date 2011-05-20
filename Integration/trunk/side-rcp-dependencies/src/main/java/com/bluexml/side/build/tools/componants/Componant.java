package com.bluexml.side.build.tools.componants;

public class Componant implements Comparable<Componant>{

	public int compareTo(Componant o) {
		return this.toString().compareTo(o.toString());
	}

}
