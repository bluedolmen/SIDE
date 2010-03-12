<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/com/bluexml/components/Xform/Xform.get.head.ftl
<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<@link rel="stylesheet" type="text/css" href="${page.url.context}/components/Xform/Xform.css" />