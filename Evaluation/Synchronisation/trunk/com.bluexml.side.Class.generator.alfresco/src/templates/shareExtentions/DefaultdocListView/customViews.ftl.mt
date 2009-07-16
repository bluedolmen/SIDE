<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
%>
<%-- Must be genereated using Portal generator to choose the view --%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/documentlibrary/customViews.ftl<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (getAllClasses()) {%>
<#if item.asset.type == "{http://www.bluexml.com/model/content/<%current("ClassPackage").name%>/1.0}<%getQualifiedName()%>">
	<#include "doclist_views/doclist_<%getQualifiedName()%>.ftl">
</#if>
<%}%>