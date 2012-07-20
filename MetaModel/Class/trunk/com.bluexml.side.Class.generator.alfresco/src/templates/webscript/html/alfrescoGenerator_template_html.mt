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
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("alf.paths.extension.side.webscripts")%>/<%getFolder()%>/<%getQualifiedName().replaceAll("_","/")%>/html/<%getQualifiedName()%>.ftl
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<ul>
	<li>id:${child.id}</li>
	<li>url:${child.url}</li>
	<li>downloadUrl:${child.downloadUrl}</li>
	<li>displayPath:${child.displayPath}</li>
	<li>icon16:${child.icon16}</li>
	<li>icon32:${child.icon32}</li>
	<li>nodeRef:${child.nodeRef}</li>
	<li>parent:${child.parent.nodeRef}</li>
	<%for (getAllSortedAttibutes()){%>
	<#if (child.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?exists)>
		<#if child.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?is_sequence>
	<li><%getQualifiedName()%>:<#list child.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"] as key>${key} </#list></li>
		<#else>
	<li><%getQualifiedName()%>:${child.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]<%if (typ.toString().equalsIgnoreCase("date")){%>?date<%}%>!""}</li>
		</#if>
	<#else>
	<li><%getQualifiedName()%>:</li>
	</#if>
	<%}%>
	<%for (getAllSourceAssociations()){%>
		<%if (isSource(current(1))){%>
			<%if (firstEnd == current(2)){%>
	<li><%secondEnd.getFullName()%>:<#if child.<%getAssociationVariableName()%>["<%getRootContainer().name%>:<%getQualifiedName(firstEnd)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(firstEnd)%>"] as key><%secondEnd.getFullName().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if></li>
			<%}else{%>
	<li><%firstEnd.getFullName()%>:<#if child.<%getAssociationVariableName()%>["<%getRootContainer().name%>:<%getQualifiedName(secondEnd)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(secondEnd)%>"] as key><%firstEnd.getFullName().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if></li>
			<%}%>
		<%}else{%>
			<%if (firstEnd == current(2)){%>
	<li><%secondEnd.getFullName()%>:<#list <%secondEnd.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getRootContainer().name%>:<%getQualifiedName(secondEnd)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(secondEnd)%>"] as other><#if other.id = child.id><%secondEnd.getFullName().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list></li>
			<%}else{%>
	<li><%firstEnd.getFullName()%>:<#list <%firstEnd.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getRootContainer().name%>:<%getQualifiedName(firstEnd)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(firstEnd)%>"] as other><#if other.id = child.id><%firstEnd.getFullName().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list></li>
			<%}%>
		<%}%>
	<%}%>
</ul>