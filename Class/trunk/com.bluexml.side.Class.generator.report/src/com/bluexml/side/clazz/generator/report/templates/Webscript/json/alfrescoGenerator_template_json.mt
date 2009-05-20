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
webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/<%getQualifiedName().replaceAll("_","/")%>/json/<%getQualifiedName()%>.ftl
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
	<%for (getAllAssociationsByClass()){%>
		<%if (isSource(current(1))){%>
			<%if (source == current(2)){%>
			"<%getQualifiedName(destination)%>":"<#if child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"] as key><%destination.getNameForNode().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if>",
			<%}else{%>
			"<%getQualifiedName(source)%>":"<#if child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"] as key><%source.getNameForNode().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if>",
			<%}%>
		<%}else{%>
			<%if (source == current(2)){%>
			"<%getQualifiedName(destination)%>":"<#list <%destination.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"] as other><#if other.id = child.id><%destination.getNameForNode().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list>",
			<%}else{%>
			"<%getQualifiedName(source)%>":"<#list <%source.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"] as other><#if other.id = child.id><%source.getNameForNode().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list>",
			<%}%>
		<%}%>
	<%}%>
	"alfresco_actions":"<a href=\"javascript:void(0)\" onclick=\"AlfNodeInfoMgr.toggle('workspace://SpacesStore/${child.id}',this);\"><img src='${url.context}/images/icons/popup.gif' border=0/></a>"	
}<#if child_has_next>,</#if>