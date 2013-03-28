/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
