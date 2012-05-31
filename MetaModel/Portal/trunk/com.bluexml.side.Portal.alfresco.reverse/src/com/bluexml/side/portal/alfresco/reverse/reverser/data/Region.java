package com.bluexml.side.portal.alfresco.reverse.reverser.data;

public class Region {

	String regionId = null;
	String scope = null;
	
	public Region(String regionId,String scope) {
		this.regionId=regionId;
		this.scope=scope;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}
