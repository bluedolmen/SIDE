<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>

<%-- Custom pages creation, templates creation and referencing --%>
<%script type="Page" name="createSitePages"%>
<%ID.toLowerCase().nPut("page_name")%>
<%if (generate){%>
<%getProperty("alf.share.paths.web-ext.pages")%><%nGet("page_name")%>.xml
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createSitePages%>" post="trim()"%>
<%ID.toLowerCase().nPut("page_name")%>
<?xml version='1.0' encoding='UTF-8'?>
<page>
   	<title><%nGet("page_name")%></title>
   	<title-id>page.<%nGet("page_name")%>.title</title-id>
   	<description>SIDE Generated Portal Page</description>
   	<description-id>page.<%nGet("page_name")%>.description</description-id>
   	<template-instance><%nGet("page_name")%></template-instance>
   	<authentication>user</authentication>
</page>
