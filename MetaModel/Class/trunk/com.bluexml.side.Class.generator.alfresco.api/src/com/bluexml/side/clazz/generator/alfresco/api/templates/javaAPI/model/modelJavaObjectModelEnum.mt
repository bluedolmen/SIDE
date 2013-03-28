<%--
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
--%>
<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.Enumeration" name="validatedFilename"%>
<%getProperty("javaModelAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%name.toU1Case()%>.java
<%script type="clazz.Enumeration" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.model.<%service::getRootContainer().name.toLowerCase()%>.<%eContainer().getQualifiedName().replaceAll("_","\\.").toLowerCase()%>;

public enum <%name.toU1Case()%> {

<%for (literals.nSort("name")){%>
<%name.toUpperCase()%>("<%if (value != null && value !="") {%><%value%><%}else{%><%name%><%}%>")<%if (i() < current("Enumeration").literals.nSize() -1){%>,<%}else{%>;<%}%>
<%}%>
	
	String key;	
	
	<%name.toU1Case()%>(String key) {
		this.key = key;
	}
	
	public String getKey() {		
		return key;
	}
}
