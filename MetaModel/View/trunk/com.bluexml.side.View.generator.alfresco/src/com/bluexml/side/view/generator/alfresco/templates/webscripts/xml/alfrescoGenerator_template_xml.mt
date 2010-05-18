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
<%if (generateWebscript){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/webscript/data/<%name%>/xml/<%name%>.ftl<%}%>
<%script type="view.AbstractViewOf" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<item>
	<id>${child.id}</id>
	<url>${child.url}</url>
	<downloadUrl>${child.downloadUrl}</downloadUrl>
	<displayPath>${child.displayPath}</displayPath>
	<icon16>${child.icon16}</icon16>
	<icon32>${child.icon32}</icon32>
	<nodeRef>${child.nodeRef}</nodeRef>
	<parent>${child.parent.nodeRef}</parent>
	<size>${child.size}</size>
	<created>${child.properties["cm:created"]?datetime}</created>
	<modified>${child.properties["cm:modified"]?datetime}</modified>
	<%for (getFields()[mapTo.eClass().name.equalsIgnoreCase("Attribute")]){%>
	<% mapTo.getRootContainer().name.put("modelId") %>
	<%if (get("modelId")=="cm") {%><% mapTo.name.put("qName") %><%}else{%><% mapTo.getQualifiedName().put("qName") %><%}%>
	<#if (child.properties["<%get("modelId")%>:<%get("qName")%>"]?exists)>
		<#if child.properties["<%get("modelId")%>:<%get("qName")%>"]?is_sequence>
		<_<%name%>><#list child.properties["<%get("modelId")%>:<%get("qName")%>"] as key>${key} </#list></_<%name%>>
		<#else/>
		<%if (mapTo.typ.toString().equalsIgnoreCase("date")){%>
		<_<%name%>>${child.properties["<%get("modelId")%>:<%get("qName")%>"]?date!""}</_<%name%>>
		<%}else if (mapTo.typ.toString().equalsIgnoreCase("datetime")){%>
		<_<%name%>>${child.properties["<%get("modelId")%>:<%get("qName")%>"]?datetime!""}</_<%name%>>
		<%}else{%>
		<_<%name%>>${child.properties["<%get("modelId")%>:<%get("qName")%>"]?string!""}</_<%name%>>
		<%}%>
		</#if>
	<#else/>
	<_<%name%>/>
	</#if>
	<%}%>
</item>