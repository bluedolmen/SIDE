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
<%if (eContainer() == getRootContainer()){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/data/<%viewOf.getPrefixedQName("_")%>/<%name%>/rss/<%name%>.ftl
<%}else if (eContainer().filter("ComposedView") != null){%>webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/data/<%viewOf.getPrefixedQName("_")%>/<%eContainer().filter("ComposedView").name%>/rss/<%eContainer().filter("ComposedView").name%>.ftl
<%}%>
<%script type="view.AbstractViewOf" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<item>
	<title><%name%></title>
	<link>${absurl(url.context)}/navigate/showDocDetails/workspace/SpacesStore/${child.id}</link>
	<guid>${child.id}</guid>
	<pubDate>${child.properties["cm:modified"]?date}</pubDate>
	<description>
		<%for (getFields().mapTo.filter("clazz.Attribute")){%>
		<#if (child.properties["<%getPrefixedQName()%>"]?exists)>
			<#if child.properties["<%getPrefixedQName()%>"]?is_sequence>
		<%getLabel()%>:<#list child.properties["<%getPrefixedQName()%>"] as key>${key},</#list>;
			<#else>
		<%getLabel()%>:${child.properties["<%getPrefixedQName()%>"]<%if (typ.toString().equalsIgnoreCase("date")){%>?date<%}%>!""};
			</#if>
		</#if>
		<%}%>
	</description>
	<author>${child.properties["cm:creator"]}</author>
</item>