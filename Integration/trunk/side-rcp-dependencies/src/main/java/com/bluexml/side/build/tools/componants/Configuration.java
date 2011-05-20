package com.bluexml.side.build.tools.componants;

import java.util.ArrayList;
import java.util.List;

public abstract class Configuration extends Componant{
	List<Constraint> constraints = new ArrayList<Constraint>();
	List<Module> modules= new ArrayList<Module>();
	
	public List<Constraint> getConstraints() {
		return constraints;
	}
	public void setConstraints(List<Constraint> constraints) {
		this.constraints = constraints;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
}
