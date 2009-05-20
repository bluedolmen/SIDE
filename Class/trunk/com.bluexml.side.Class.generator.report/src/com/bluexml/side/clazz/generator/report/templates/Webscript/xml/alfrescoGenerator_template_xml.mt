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
metamodel http://www.kerblue.org/class/1.0

import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.services.ClassServices
%>

<%script type="clazz.Clazz" name="validatedFilename"%>
webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/<%getQualifiedName().replaceAll("_","/")%>/xml/<%getQualifiedName()%>.ftl
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<item>
	<id>${child.id}</id>
	<url>${child.url}</url>
	<downloadUrl>${child.downloadUrl}</downloadUrl>
	<displayPath>${child.displayPath}</displayPath>
	<icon16>${child.icon16}</icon16>
	<icon32>${child.icon32}</icon32>
	<nodeRef>${child.nodeRef}</nodeRef>
	<parent>${child.parent.nodeRef}</parent>
	<%for (getAllAttributes()){%>
	<#if (child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"]?exists)>
		<#if child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"]?is_sequence>
	<<%getQualifiedName()%>><#list child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"] as key>${key} </#list></<%getQualifiedName()%>>
		<#else/>
	<<%getQualifiedName()%>>${child.properties["<%current(1).getFolder()%>:<%getQualifiedName()%>"]<%if (typ.toString().equalsIgnoreCase("date")){%>?date<%}%>!""}</<%getQualifiedName()%>>
		</#if>
	<#else/>
	<<%getQualifiedName()%>/>
	</#if>
	<%}%>
	<%for (getAllAssociationsByClass()){%>
		<%if (isSource(current(1))){%>
			<%if (source == current(2)){%>
	<<%getQualifiedName(destination)%>><#if child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"] as key><%destination.getNameForNode().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if></<%getQualifiedName(destination)%>>
			<%}else{%>
	<<%getQualifiedName(source)%>><#if child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"] as key><%source.getNameForNode().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if></<%getQualifiedName(source)%>>
			<%}%>
		<%}else{%>
			<%if (source == current(2)){%>
	<<%getQualifiedName(destination)%>><#list <%destination.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"] as other><#if other.id = child.id><%destination.getNameForNode().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list></<%getQualifiedName(destination)%>>
			<%}else{%>
	<<%getQualifiedName(source)%>><#list <%source.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"] as other><#if other.id = child.id><%source.getNameForNode().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list></<%getQualifiedName(source)%>>
			<%}%>
		<%}%>
	<%}%>
</item>