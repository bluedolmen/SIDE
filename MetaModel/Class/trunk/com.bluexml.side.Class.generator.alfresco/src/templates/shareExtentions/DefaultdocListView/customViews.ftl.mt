<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>
<%-- Must be genereated using Portal generator to choose the view --%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/documentlibrary/customViews.ftl<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (getAllClasses().nSort("name")) {%>
<#if item.asset.type == "{<%getNameSpace()%>}<%getQualifiedName()%>">
	<#include "doclist_views/doclist_<%getQualifiedName()%>.ftl">
</#if>
<%}%>