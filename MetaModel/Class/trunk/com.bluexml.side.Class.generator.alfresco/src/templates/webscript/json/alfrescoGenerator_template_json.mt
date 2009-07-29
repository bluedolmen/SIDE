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
<%getProperty("alf.paths.extension.side.webscripts")%>/<%getQualifiedName().replaceAll("_","/")%>/json/<%getQualifiedName()%>.ftl
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
	<%for (getAllAttributes()){%>
	<#if (child.properties["<%current(1).getFolder()%>:<%eContainer().getQualifiedName()%>_<%name%>"]?exists)>
		<#if child.properties["<%current(1).getFolder()%>:<%eContainer().getQualifiedName()%>_<%name%>"]?is_sequence>
		"<%eContainer().getQualifiedName()%>_<%name%>":"<#list child.properties["<%current(1).getFolder()%>:<%eContainer().getQualifiedName()%>_<%name%>"] as key>${key} </#list>",
		<#else/>
		"<%eContainer().getQualifiedName()%>_<%name%>":"${child.properties["<%current(1).getFolder()%>:<%eContainer().getQualifiedName()%>_<%name%>"]<%if (typ.toString().equalsIgnoreCase("date")){%>?date<%}%>!""}",
		</#if>
	<#else/>
		"<%eContainer().getQualifiedName()%>_<%name%>":"",
	</#if>
	<%}%>
		<%for (getAllSourceAssociations()){%>
		<%if (isSource(current(1))){%>
			<%if (firstEnd == current(2)){%>
			"<%secondEnd.getFullName()%>":"<#if child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(firstEnd)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(firstEnd)%>"] as key><%secondEnd.getFullName().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if>",
			<%}else{%>
			"<%firstEnd.getFullName()%>":"<#if child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(secondEnd)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(secondEnd)%>"] as key><%firstEnd.getFullName().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if>",
			<%}%>
		<%}else{%>
			<%if (firstEnd == current(2)){%>
			"<%secondEnd.getFullName()%>":"<#list <%secondEnd.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(secondEnd)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(secondEnd)%>"] as other><#if other.id = child.id><%secondEnd.getFullName().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list>",
			<%}else{%>
			"<%firstEnd.getFullName()%>":"<#list <%firstEnd.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(firstEnd)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(firstEnd)%>"] as other><#if other.id = child.id><%firstEnd.getFullName().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list>",
			<%}%>
		<%}%>
	<%}%>
	"alfresco_actions":"<a href=\"javascript:void(0)\" onclick=\"AlfNodeInfoMgr.toggle('workspace://SpacesStore/${child.id}',this);\"><img src='${url.context}/images/icons/popup.gif' border=0/></a>"	
}<#if child_has_next>,</#if>