<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="Portlet" name="createTemplates"%>
<%if (name.toLowerCase() =="edit-metadata"){%>
<%getProperty("alf.share.paths.core.site-data")%>/template-instances/edit-metadata.xml
<%}%>

<%script type="Portlet" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>com/bluexml/edit-metadataXForm</template-type>
</template-instance>