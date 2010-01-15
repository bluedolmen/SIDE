<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="Portlet" name="createTemplates"%>
<%if (name.toLowerCase() =="edit-metadata-XFrom"){%>
<%getProperty("alf.share.paths.core.templates")%>/edit-metadataXForm.ftl
<%}%>

<%script type="Portlet" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<#include "../../org/alfresco/include/alfresco-template.ftl" />
<@templateHeader>
   <@link rel="stylesheet" type="text/css" href="${url.context}/templates/edit-metadata/edit-metadata.css" />
<#--   <@script type="text/javascript" src="${page.url.context}/templates/edit-metadata/edit-metadata.js"></@script> -->
</@>

<@templateBody>
   <div id="alf-hd">
      <@region id="header" scope="global" protected=true />
      <@region id="title" scope="template" protected=true />
      <@region id="navigation" scope="template" protected=true />
   </div>
   <div id="bd">
      <div class="metadata">
         <@region id="edit-metadata-mgr" scope="template" protected=true />
         <@region id="edit-metadata-XForm" scope="template" protected=true />         
      </div>
   </div>
   
</@>


<@templateFooter>
   <div id="alf-ft">
      <@region id="footer" scope="global" protected=true />
   </div>
</@>
