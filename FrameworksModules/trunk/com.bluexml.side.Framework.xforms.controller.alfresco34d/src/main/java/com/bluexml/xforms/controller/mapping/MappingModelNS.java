package com.bluexml.xforms.controller.mapping;

public enum MappingModelNS {
	INT_NAMESPACE_ALFRESCO_CONTENT("cm", "http://www.alfresco.org/model/content/1.0"), NAMESPACE_ALFRESCO_CONTENT("sys", "http://www.alfresco.org/model/system/1.0");
	String prefix = "";
	String ns = "";

	MappingModelNS(String prefix, String ns) {
		this.prefix = prefix;
		this.ns = ns;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getNs() {
		return ns;
	}

}
