<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/com/bluexml/components/facetMap/facetMap.get.desc.xml
<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<webscript>
  <shortname>facetMap Component</shortname>
  <description>Component that renders a form based on a repository form model</description>
  <url>/components/facetMap</url>
</webscript>
