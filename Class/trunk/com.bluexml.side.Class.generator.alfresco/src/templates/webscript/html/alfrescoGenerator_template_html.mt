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
webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/<%getQualifiedName().replaceAll("_","/")%>/html/<%getQualifiedName()%>.ftl
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
	<%for (getAllAttributes()){%>
	<#if (child.properties["<%current(1).getFolder()%>:<%eContainer().getQualifiedName()%>_<%name%>"]?exists)>
		<#if child.properties["<%current(1).getFolder()%>:<%eContainer().getQualifiedName()%>_<%name%>"]?is_sequence>
	<li><%eContainer().getQualifiedName()%>_<%name%>:<#list child.properties["<%current(1).getFolder()%>:<%eContainer().getQualifiedName()%>_<%name%>"] as key>${key} </#list></li>
		<#else/>
	<li><%eContainer().getQualifiedName()%>_<%name%>:${child.properties["<%current(1).getFolder()%>:<%eContainer().getQualifiedName()%>_<%name%>"]<%if (typ.toString().equalsIgnoreCase("date")){%>?date<%}%>!""}</li>
		</#if>
	<#else/>
	<li><%eContainer().getQualifiedName()%>_<%name%>:</li>
	</#if>
	<%}%>
	<%for (getAllSourceAssociations()){%>
		<%if (isSource(current(1))){%>
			<%if (source == current(2)){%>
	<li><%getQualifiedName(destination)%>:<#if child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"] as key><%destination.getNameForNode().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if></li>
			<%}else{%>
	<li><%getQualifiedName(source)%>:<#if child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"]?exists><#list child.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"] as key><%source.getNameForNode().replaceAll("child","key").replaceAll("\n","" )%> </#list></#if></li>
			<%}%>
		<%}else{%>
			<%if (source == current(2)){%>
	<li><%getQualifiedName(destination)%>:<#list <%destination.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(destination)%>"] as other><#if other.id = child.id><%destination.getNameForNode().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list></li>
			<%}else{%>
	<li><%getQualifiedName(source)%>:<#list <%source.getQualifiedName()%>_list as object><#if object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"]?exists><#list object.<%getAssociationVariableName()%>["<%getFolder()%>:<%getQualifiedName(source)%>"] as other><#if other.id = child.id><%source.getNameForNode().replaceAll("child","object").replaceAll("\n","" )%> <#break></#if></#list></#if></#list></li>
			<%}%>
		<%}%>
	<%}%>
</ul>