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
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>
<%--import com.bluexml.side.portal.generator.alfresco.services.ProxieServices--%>
<%-- Must be genereated using Portal generator to choose the view --%>

<%script type="Portal" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/search/customViews.ftl<%}%>

<%script type="Portal" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (pageSet[ID.toLowerCase().trim() == "search"].portlets.associationPortlet[name.toLowerCase().trim() == "searchresults" && isPortletInternal != null && isPortletInternal.view != null]){%>
<%for (isPortletInternal.view.getInnerView()){%>
<%if (filter("view.AbstractViewOf").viewOf.filter("clazz.Clazz")){%>
<#if node.type == "<%filter("view.AbstractViewOf").viewOf.filter("clazz.AbstractClass").getPrefixedNamespaceQName()%>">
<%}else{%>
<#if node.hasAspect("<%filter("view.AbstractViewOf").viewOf.filter("clazz.AbstractClass").getPrefixedQName()%>")>
<%}%>
	<#include "../documentlibrary/doclist_views/doclist_<%filter("view.AbstractViewOf").getFullName().replaceAll("\\.", "_")%>.ftl">
</#if>
<%}%>
<%}%>
