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
<%if (eContainer() == getRootContainer()){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/webscript/data/<%name%>/xml/<%name%>.ftl<%}%>
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
	<%for (getAllSortedAttibutes()){%>
	<% getRootContainer().name.put("modelId") %>
	<%if (get("modelId")=="cm") {%><% mapTo.name.put("qName") %><%}else{%><% getQualifiedName().put("qName") %><%}%>
	<#if (child.properties["<%get("modelId")%>:<%get("qName")%>"]?exists)>
		<#if child.properties["<%get("modelId")%>:<%get("qName")%>"]?is_sequence>
		<<%eContainer().getQualifiedName()%>_<%name%>><#list child.properties["<%get("modelId")%>:<%get("qName")%>"] as key>${key} </#list></<%eContainer().getQualifiedName()%>_<%name%>>
		<#else/>
		<%if (typ.toString().equalsIgnoreCase("date")){%>
		<<%eContainer().getQualifiedName()%>_<%name%>>${child.properties["<%get("modelId")%>:<%get("qName")%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}</<%eContainer().getQualifiedName()%>_<%name%>>
		<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
		<<%eContainer().getQualifiedName()%>_<%name%>>${child.properties["<%get("modelId")%>:<%get("qName")%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}</<%eContainer().getQualifiedName()%>_<%name%>>
		<%}else{%>
		<<%eContainer().getQualifiedName()%>_<%name%>>${child.properties["<%get("modelId")%>:<%get("qName")%>"]?string!""}</<%eContainer().getQualifiedName()%>_<%name%>>
		<%}%>
		</#if>
	<#else/>
	<<%eContainer().getQualifiedName()%>_<%name%>/>
	</#if>
	<%}%>
	<%for (getFields()[path != null && path.nSize() == 1]){%>
	<%for (path.filter("clazz.Association")){%>
		<%getAssociationEnd(current("AbstractViewOf").viewOf.filter("clazz.Clazz")).put("assoEnd")%>
	<<%getAssociationQName(get("assoEnd"))%>>
	<#if child.<%getAssociationVariableName()%>["<%getPrefixedURIAssociationQName(get("assoEnd"))%>"]?exists>
		<#list child.<%getAssociationVariableName()%>["<%getPrefixedURIAssociationQName(get("assoEnd"))%>"] as item>
	<target>
		<%for (current("Field").mapTo.filter("clazz.Attribute")){%>
		<nodeRef>${item.nodeRef}</nodeRef>
		<#if (item.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?exists)>
			<#if item.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?is_sequence>
			<<%eContainer().getQualifiedName()%>_<%name%>><#list item.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"] as key>${key} </#list></<%eContainer().getQualifiedName()%>_<%name%>>
			<#else/>
			<%if (typ.toString().equalsIgnoreCase("date")){%>
			<<%eContainer().getQualifiedName()%>_<%name%>>${item.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}</<%eContainer().getQualifiedName()%>_<%name%>>
			<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
			<<%eContainer().getQualifiedName()%>_<%name%>>${item.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}</<%eContainer().getQualifiedName()%>_<%name%>>
			<%}else{%>
			<<%eContainer().getQualifiedName()%>_<%name%>>${item.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?string!""}</<%eContainer().getQualifiedName()%>_<%name%>>
			<%}%>
			</#if>
		<#else/>
			<<%eContainer().getQualifiedName()%>_<%name%>></<%eContainer().getQualifiedName()%>_<%name%>>
		</#if>
		<%}%>
	</target>
		</#list>
	</#if>
	</<%getAssociationQName(get("assoEnd"))%>>
	<%}%>
	<%}%>
</item>