<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/org/alfresco/components/edit-metadata/edit-metadata-mgr.get.html.ftl

<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<script type="text/javascript">//<![CDATA[
   var editMetadataMgr=new Alfresco.EditMetadataMgr("${args.htmlid}");
   editMetadataMgr.setOptions(
   {
      nodeRef: "${page.url.args.nodeRef!}",
      nodeType: "${nodeType}",
      siteId: "${page.url.templateArgs.site!""}"
   }).setMessages(
      ${messages}
   );
//]]></script>

<div class="edit-metadata-mgr">
   <div class="heading">${msg("edit-metadata-mgr.heading")}</div>
</div>