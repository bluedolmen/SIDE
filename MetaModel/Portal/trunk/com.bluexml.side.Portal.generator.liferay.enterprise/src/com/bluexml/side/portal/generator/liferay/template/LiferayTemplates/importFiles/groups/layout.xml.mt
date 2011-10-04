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
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.liferay.services.PageService
%>
<%script type="portal.Page" name="validatedFilename"%>
<%getProperty("liferayGenerationLar")%>/groups/10131/layouts/<%getPageIndex()+1%>/layout.xml
<%script type="portal.Page" name="liferayGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="UTF-8"?>
<layout old-plid="10696" layout-id="<%getPageIndex()+1%>">
	<parent-layout-id><%getParentPageIndex()%></parent-layout-id>
	<name><![CDATA[<?xml version='1.0' encoding='UTF-8'?><root available-locales="en_US" default-locale="en_US"><name language-id="en_US"><%if (title != ""){%><%title%><%}else{%><%ID%><%}%></name></root>]]></name>
	<title><![CDATA[<root />]]></title>
	<description><%if (description != null){%><%description%><%}%></description>
	<type>portlet</type>
	<type-settings><%getLayoutColumn()%></type-settings>
	<hidden>false</hidden>
	<friendly-url>/<%ID%></friendly-url>
	<icon-image>false</icon-image>
	<theme-id></theme-id>
	<color-scheme-id></color-scheme-id>
	<wap-theme-id></wap-theme-id>
	<wap-color-scheme-id></wap-color-scheme-id>
	<css><![CDATA[]]></css>
	<priority><%position%></priority>
	<permissions>
		<community-actions/>
		<guest-actions/>
	</permissions>
</layout>