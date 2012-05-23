package com.bluexml.side.build.tools.componants;

import java.util.ArrayList;
import java.util.List;

public class LinkedWithModule extends Componant {
	List<Module> modules = new ArrayList<Module>();

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
}
