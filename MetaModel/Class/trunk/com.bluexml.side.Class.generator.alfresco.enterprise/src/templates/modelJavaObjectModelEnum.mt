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
package com.bluexml.side.alfresco.model.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>;

public enum <%name.toU1Case()%> {

<%for (literals.nSort("name")){%>
<%name.toUpperCase()%>("<%value%>")<%if (i() < current("Enumeration").literals.nSize() -1){%>,<%}else{%>;<%}%>
<%}%>
	
	String key;	
	
	<%name.toU1Case()%>(String key) {
		this.key = key;
	}
	
	public String getKey() {		
		return key;
	}
}
