<%
metamodel http://www.kerblue.org/portal/1.0
%>

<%-- Custom pages creation, templates creation and referencing --%>
<%script type="Page" name="createSitePages"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("page_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("alf.share.paths.web-ext.pages")%><%nGet("page_name")%>.xml
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createSitePages%>" post="trim()"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("page_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<?xml version='1.0' encoding='UTF-8'?>
<page>
   	<title><%nGet("page_name")%></title>
   	<title-id>page.<%nGet("page_name")%>.title</title-id>
   	<description>SIDE Generated Portal Page</description>
   	<description-id>page.<%nGet("page_name")%>.description</description-id>
   	<template-instance><%nGet("page_name")%></template-instance>
   	<authentication>user</authentication>
</page>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>