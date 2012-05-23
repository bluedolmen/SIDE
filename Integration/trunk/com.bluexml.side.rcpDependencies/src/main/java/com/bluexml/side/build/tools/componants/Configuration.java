package com.bluexml.side.build.tools.componants;

import java.util.ArrayList;
import java.util.List;

public class Configuration extends LinkedWithModule {
	List<Constraint> constraints = new ArrayList<Constraint>();

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<Constraint> constraints) {
		this.constraints = constraints;
	}

}
