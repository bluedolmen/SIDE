package com.bluexml.side.ant;

public abstract class SideComponentTask extends SideTask {

	private String componentId = "";

	public void setComponentid(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentid() {
		return componentId;
	}
	
}
