<%
metamodel http://www.kerblue.org/portal/1.0
%>

<%-- Templates instance creation --%>
<%script type="Page" name="createTemplateInstances"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("ti_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("alf.share.paths.web-ext.template-instances")%><%nGet("ti_name")%>.xml
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createTemplateInstances%>" post="trim()"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("ti_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>com/bluexml/<%nGet("ti_name")%></template-type>
</template-instance>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>