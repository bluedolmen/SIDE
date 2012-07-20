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
<%getProperty("alf.paths.extension.side.webscripts")%>/<%getFolder()%>/<%getQualifiedName().replaceAll("_","/")%>/json/<%getQualifiedName()%>.ftl
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
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
	<#if (child.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?exists)>
		<#if child.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?is_sequence>
		"<%getQualifiedName()%>":"<#list child.properties["<%current(1).getPrefixedQName()%>"] as key>${key} </#list>",
		<#else>
		<%if (typ.toString().equalsIgnoreCase("date")){%>
		"<%getQualifiedName()%>":"${child.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}",
		<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
		"<%getQualifiedName()%>":"${child.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}",
		<%}else{%>
		"<%getQualifiedName()%>":"${child.properties["<%getRootContainer().name%>:<%getQualifiedName()%>"]?string!""}",
		<%}%>
		</#if>
	<#else>
		"<%getQualifiedName()%>":"",
	</#if>
	<%}%>
	<%for (getAllSourceAssociationEnds()){%>
		"<%eContainer().getAssociationQName(current("AssociationEnd"))%>":<#if child.<%eContainer().getAssociationVariableName()%>["<%eContainer().getPrefixedURIAssociationQName(current("AssociationEnd"))%>"]?exists>[<#list child.<%eContainer().getAssociationVariableName()%>["<%eContainer().getPrefixedAssociationQName(current("AssociationEnd"))%>"] as item>"${item.nodeRef}"<#if item_has_next>,</#if></#list>]</#if><%if (i() < current("Clazz").getAllSourceAssociationEnds().nSize() -1){%>, <%}%>
	<%--
		<%if (isSource(current(1))){%>
			<%if (firstEnd == current(2)){%>
			"<%secondEnd.getFullName()%>":"<#if child.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(firstEnd)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(firstEnd)%>"] as key><%secondEnd.getFullName().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if>",
			<%}else{%>
			"<%firstEnd.getFullName()%>":"<#if child.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(secondEnd)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(secondEnd)%>"] as key><%firstEnd.getFullName().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if>",
			<%}%>
		<%}else{%>
			<%if (firstEnd == current(2)){%>
			"<%secondEnd.getFullName()%>":"<#list <%secondEnd.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(secondEnd)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(secondEnd)%>"] as other><#if other.id = child.id><%secondEnd.getFullName().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list>",
			<%}else{%>
			"<%firstEnd.getFullName()%>":"<#list <%firstEnd.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(firstEnd)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getPrefixedAssociationQName(firstEnd)%>"] as other><#if other.id = child.id><%firstEnd.getFullName().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list>",
			<%}%>
		<%}%>
	--%>
	<%}%>
	"alfresco_actions":"<a href=\"javascript:void(0)\" onclick=\"AlfNodeInfoMgr.toggle('workspace://SpacesStore/${child.id}',this);\"><img src='${url.context}/images/icons/popup.gif' border=0/></a>"	
}<#if child_has_next>,</#if>