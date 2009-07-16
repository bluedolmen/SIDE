<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/documentlibrary/doclist.get.html.ftl<%}%>
<%script type="clazz.ClassPackage" name="doclist_get_html" file="<%validatedFilename%>"%>
<h3>${doclist.luceneQuery}</h3>
<#include "doclist.get.json.ftl">