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
webapps/alfresco/WEB-INF/classes/alfresco/webscripts/extension/com/bluexml/side/webscript/data/<%name%>/<%name%>.get.rss.ftl
<%script type="view.AbstractViewOf" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<#assign recordsCount=records?size>
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
<#if argsM["years"]?exists><#assign years=argsM["years"][0]></#if>
<#if years?exists>
	<#assign years=years?number>
<#else/>
	<#assign years=0>
</#if>
<#if argsM["months"]?exists><#assign months=argsM["months"][0]></#if>
<#if months?exists>
	<#assign months=months?number>
<#else/>
	<#assign months=0>
</#if>
<#if argsM["days"]?exists><#assign days=argsM["days"][0]></#if>
<#if days?exists>
	<#assign days=days?number>
<#else/>
	<#assign days=0>
</#if>
<#if argsM["hours"]?exists><#assign hours=argsM["hours"][0]></#if>
<#if hours?exists>
	<#assign hours=hours?number>
<#else/>
	<#assign hours=0>
</#if>
<#if argsM["minutes"]?exists><#assign minutes=argsM["minutes"][0]></#if>
<#if minutes?exists>
	<#assign minutes=minutes?number>
<#else/>
	<#assign minutes=0>
</#if>
<#assign time=1000*60*(minutes+hours*60+days*24*60+months*30*24*60+years*12*30*24*60)>
<?xml version="1.0"?>
<rss version="2.0">
	<channel>
		<title><%viewOf.name%></title>
		<description><%getDescriptionOrName()%></description>
		<link>${absurl(url.serviceContext)}/<%getQualifiedName()%>.rss</link>
		<generator>Alfresco</generator>
		<#assign index=0>
		<#list records as child>
			<#if maxBound < index><#break/></#if>
			<#if ((time==0) || (dateCompare(child.properties["cm:modified"], date, time) == 1) || (dateCompare(child.properties["cm:created"], date, time) == 1))>
				<#if minBound<=index>
				<#include "rss/<%name%>.ftl">
				</#if>
				<#assign index=index+1>
			</#if>
		</#list>
	</channel>
</rss>