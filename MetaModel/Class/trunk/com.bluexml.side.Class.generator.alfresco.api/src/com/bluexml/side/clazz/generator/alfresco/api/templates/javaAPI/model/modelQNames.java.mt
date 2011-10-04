<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaModelAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/model/<%name%>/<%getQualifiedName().replaceAll("_","/")%>/ModelQNames.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.model.<%name.toLowerCase()%>;

import java.util.Iterator;
import java.util.List;

public class ModelQNames {

	String prefix = "";
	String nsURI = "";
	String name = "";

	public ModelQNames(String prefix, String nsURI, String name) {
		this.name = name;
		this.nsURI = nsURI;
		this.prefix = prefix;
	}

	public String getPrefixedQName() {
		return prefix + ":" + name;
	}

	public String getQnameString() {
		return "{" + nsURI + "}" + name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getNsURI() {
		return nsURI;
	}

	public void setNsURI(String nsURI) {
		this.nsURI = nsURI;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}





	// Model	
	public static final String NURI_CONTENT_MODEL ="<%getNamespaceURI()%>";
	
	public static final String PREFIX_CONTENT_MODEL ="<%getPrefix()%>";
	// Types
	<%for (getAllClassesFromEveryWhere().nSort("name")) {%>
	
	public static final ModelQNames CONTENT_MODEL_TYPE_<%name.toUpperCase()%> = <%getQName(getQualifiedName())%>;
	// Attributes
		<%for (attributes){%>
	public static final ModelQNames CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%> = <%getQName(getQualifiedName())%>;
		<%}%>
	// Associations
		<%for (getSourceAssociationEnds()){%>
	public static final String CONTENT_MODEL_ASSOCIATION_<%eContainer().getQualifiedName(current()).toUpperCase()%> = createQNameString("<%eContainer().getAssociationQName(current("AssociationEnd"))%>");	
		<%}%>
	<%}%>
	
	
	
	// Aspects
	<%for (getAllAspectsFromEveryWhere().nSort("name")){%>
		public static final ModelQNames CONTENT_MODEL_ASPECT_<%name.toUpperCase()%> = <%getQName(getQualifiedName())%>;
	
	// Attributes
		<%for (getSortedAttibutes()){%>
	public static final ModelQNames CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%> = <%getQName(getQualifiedName())%>;
		<%}%>	
	<%}%>
	
	public static String createQNameString(String name) {
		return "{" + NURI_CONTENT_MODEL + "}" + name;
	}
	
	public static String createPrefixedQNameString(String name) {
		return PREFIX_CONTENT_MODEL + name;
	}
	
	public static String addquote(Object string) {
		return "\"" + string + "\"";
	}
	
	public static String serializeList(List<String> l) {
		String rt = "[";
		Iterator<String> it = l.iterator();
		while(it.hasNext()) {
			rt += "\"" + it.next() + "\"";
			if (it.hasNext()) {
				rt += ", ";
			}
		}
		return rt + "]";
	}
}


