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

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.side.webscripts")%>/<%name%>/all/all.get.xml.ftl<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>

<%for (getAllClasses().nSort("name")){%>
<#assign recordsCount=<%getQualifiedName%>?size>
<#if argsM["start"]?exists><#assign start=argsM["start"][0]></#if>
<#if (start?exists)>
	<#assign minBound=start?number>
<#else/>
	<#assign minBound=0>
</#if>
<#if argsM["limit"]?exists><#assign limit=argsM["limit"][0]></#if>
<#if (limit?exists)>
	<#assign maxBound=minBound+limit?number-1>
	<#if (maxBound>recordsCount-1)>
		<#assign maxBound=recordsCount-1>
	</#if>
<#else/>
	<#assign maxBound=recordsCount-1>
</#if>
	
	<#assign records<%getQualifiedName%>Count=recordsCount>
	<#assign min<%getQualifiedName%>Bound=minBound>
	<#assign max<%getQualifiedName%>Bound=maxBound>

<%}%>

	<items>
	<%for (getAllClasses().nSort("name")){%>
		<item>
			<type><%getLabel()%></type>
			<totalcount>${records<%getQualifiedName%>Count}</totalcount>
			<#list <%getQualifiedName%> as child>
				<#assign index=0>
				<#if max<%getQualifiedName%>Bound < index><#break/></#if>
					<instance>
					<#if min<%getQualifiedName%>Bound<=index>
					<#include "xml/all.ftl">
					</#if>
					<#assign index=index+1>
					</instance>
			</#list>
		</item>
	<%}%>	
	</items>