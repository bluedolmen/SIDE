<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.services.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaModelAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/model/<%name%>/<%getQualifiedName().replaceAll("_","/")%>/ModelQNames.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.model.<%name%>;

public class ModelQNames {

	// Model	
	public static final String NURI_CONTENT_MODEL ="<%getNamespaceURI()%>";
	
	public static final String PREFIX_CONTENT_MODEL ="<%getPrefixe()%>";
	// Types
	<%for (getAllClasses().nSort("name")) {%>
	
	public static final String CONTENT_MODEL_TYPE_<%name.toUpperCase()%> = createQNameString("<%getQualifiedName()%>");	
	// Attributes
		<%for (getAllSortedAttibutes()){%>
	public static final String CONTENT_MODEL_ATTRIBUTE_<%current("Clazz").name.toUpperCase()%>_<%name.toUpperCase()%> = createQNameString("<%getQualifiedName()%>");	
		<%}%>
	// Associations
		<%for (getAllSourceAssociationEnds()){%>
	public static final String CONTENT_MODEL_ASSOCIATION_<%eContainer().getQualifiedName(current()).toUpperCase()%> = createQNameString("<%eContainer().getAssociationQName(current("AssociationEnd"))%>");	
		<%}%>
	<%}%>
	
	
	
	// Aspects
	<%for (getAllAspects().nSort("name")){%>
		public static final String CONTENT_MODEL_ASPECT_<%name.toUpperCase()%> = createQNameString("<%getQualifiedName()%>");
	
	// Attributes
		<%for (getSortedAttibutes()){%>
	public static final String CONTENT_MODEL_ATTRIBUTE_<%current("Aspect").name.toUpperCase()%>_<%name.toUpperCase()%> = createQNameString("<%getQualifiedName()%>");	
		<%}%>	
	<%}%>
	
	public static String createQNameString(String name) {
		return "{" + NURI_CONTENT_MODEL + "}" + name;
	}
	
	public static String createPrefixedQNameString(String name) {
		return PREFIX_CONTENT_MODEL + name;
	}
}