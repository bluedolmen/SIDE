<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>

<%-- Templates instance creation --%>
<%script type="Page" name="createTemplateInstances"%>
<%ID.toLowerCase().nPut("ti_name")%>
<%if (generate){%>
<%getProperty("alf.share.paths.web-ext.template-instances")%><%nGet("ti_name")%>.xml
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createTemplateInstances%>" post="trim()"%>
<%ID.toLowerCase().nPut("ti_name")%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>com/bluexml/<%nGet("ti_name")%></template-type>
</template-instance>
