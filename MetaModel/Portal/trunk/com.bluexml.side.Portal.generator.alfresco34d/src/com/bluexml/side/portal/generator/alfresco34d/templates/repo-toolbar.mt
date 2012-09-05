<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.portal.generator.alfresco34d.templates.toolbar
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%if (pageSet[ID.toLowerCase().trim() == "documentlibrary"].nSize() > 0 &&
 pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets[associationPortlet.name.toLowerCase().trim() == "repo-toolbar-create-content" && associationPortlet.isPortletInternal != null].nSize() > 0){%>
 <%getProperty("alf.share.paths.web-ext.alf.components")%>/documentlibrary/repo-toolbar.get.config.xml
 <%}%>

<%script type="Portal" name="repo-toolbar-create-content" file="<%validatedFilename%>"%>
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[(name.toLowerCase().trim() == "repo-toolbar-create-content") && isPortletInternal != null]){%>
<%generate_toolbar_portlet()%>
<%}%>