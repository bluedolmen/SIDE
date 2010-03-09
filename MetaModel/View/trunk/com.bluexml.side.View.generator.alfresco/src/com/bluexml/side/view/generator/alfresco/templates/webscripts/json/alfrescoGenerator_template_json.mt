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

import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>
<%script type="view.AbstractViewOf" name="validatedFilename"%>
<%if (generateWebscript){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/webscript/data/<%name%>/json/<%name%>.ftl<%}%>
<%script type="view.AbstractViewOf" name="alfrescoGenerator" file="<%validatedFilename%>"%>
{
	<%for (getFields()[mapTo.eClass().name.equalsIgnoreCase("Attribute")]){%>
	<#if (child.properties["<%current("AbstractViewOf").viewOf.getFolder()%>:<%mapTo.getQualifiedName()%>"]?exists)>
		<#if child.properties["<%current("AbstractViewOf").viewOf.getFolder()%>:<%mapTo.getQualifiedName()%>"]?is_sequence>
		"_<%name%>":"<#list child.properties["<%current("AbstractViewOf").viewOf.getFolder()%>:<%mapTo.getQualifiedName()%>"] as key>${key} </#list>",
		<#else/>
		<%if (mapTo.typ.toString().equalsIgnoreCase("date")){%>
		"_<%name%>":"${child.properties["<%current("AbstractViewOf").viewOf.getFolder()%>:<%mapTo.getQualifiedName()%>"]?date!""}",
		<%}else if (mapTo.typ.toString().equalsIgnoreCase("datetime")){%>
		"_<%name%>":"${child.properties["<%current("AbstractViewOf").viewOf.getFolder()%>:<%mapTo.getQualifiedName()%>"]?datetime!""}",
		<%}else{%>
		"_<%name%>":"${child.properties["<%current("AbstractViewOf").viewOf.getFolder()%>:<%mapTo.getQualifiedName()%>"]?string!""}",
		<%}%>
		</#if>
	<#else/>
		"_<%name%>":"",
	</#if>
	<%}%>
	"id":"${child.id}",
	"url":"${child.url}",
	"downloadUrl":"${child.downloadUrl}",
	"displayPath":"${child.displayPath}",
	"icon16":"${child.icon16}",
	"icon32":"${child.icon32}",
	"nodeRef":"${child.nodeRef}",
	"parent":"${child.parent.nodeRef}"
}<#if child_has_next>,</#if>