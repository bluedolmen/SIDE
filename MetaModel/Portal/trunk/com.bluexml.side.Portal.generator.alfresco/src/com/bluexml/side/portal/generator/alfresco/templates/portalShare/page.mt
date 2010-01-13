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
