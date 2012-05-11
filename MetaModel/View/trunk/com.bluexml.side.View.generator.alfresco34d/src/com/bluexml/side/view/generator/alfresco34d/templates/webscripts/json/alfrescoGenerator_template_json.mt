<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
 <%
metamodel http://www.kerblue.org/view/1.0

import com.bluexml.side.view.generator.alfresco.templates.services.common
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>
<%script type="view.AbstractViewOf" name="validatedFilename"%>
<%if (eContainer() == getRootContainer()){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/data/<%viewOf.getPrefixedQName("_")%>/<%name%>/json/<%name%>.ftl
<%}else if (eContainer().filter("ComposedView") != null){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/data/<%viewOf.getPrefixedQName("_")%>/<%eContainer().filter("ComposedView").name%>/json/<%eContainer().filter("ComposedView").name%>.ftl
<%}%>
<%script type="view.AbstractViewOf" name="alfrescoGenerator" file="<%validatedFilename%>"%>
{
	"id":"${child.id}",
	"url":"${child.url}",
	"downloadUrl":"${child.downloadUrl}",
	"displayPath":"${child.displayPath}",
	"icon16":"${child.icon16}",
	"icon32":"${child.icon32}",
	"nodeRef":"${child.nodeRef}",
	"parent":"${child.parent.nodeRef}",
<%for (getAllSortedAttibutes()){%>
	<%generateAttributeStatement("child")%>,
<%}%>
<%for (getFields()[path != null && path.nSize() == 1]){%>
	<%for (path.filter("clazz.Association")){%>
		<%getAssociationEnd(current("AbstractViewOf").viewOf.filter("clazz.Clazz")).put("assoEnd")%>
	"<%getAssociationQName(get("assoEnd"))%>":
	<#if child.<%getAssociationVariableName()%>["<%getPrefixedURIAssociationQName(get("assoEnd"))%>"]?exists>
	[	<#list child.<%getAssociationVariableName()%>["<%getPrefixedURIAssociationQName(get("assoEnd"))%>"] as item>
	{"nodeRef" : "${item.nodeRef}",
			<%current("Field").mapTo.filter("clazz.Attribute").generateAttributeStatement("item")%>
	}<#if item_has_next>,</#if>
		</#list>
	]
	<#else>
	""
	</#if>
	<%}%>
	,		
<%}%>
	
	"alfresco_actions":"<a href=\"javascript:void(0)\" onclick=\"AlfNodeInfoMgr.toggle('workspace://SpacesStore/${child.id}',this);\"><img src='${url.context}/images/icons/popup.gif' border=0/></a>"	
}<#if child_has_next>,</#if>

