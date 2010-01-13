<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>


<%-- Title component templates creation --%>
<%script type="Page" name="createTitleComponentTemplate"%>
<%ID.toLowerCase().nPut("title_name")%>
<%if (generate){%>
<%getProperty("alf.share.paths.web-ext.components")%>template.title.<%nGet("title_name")%>.xml
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createTitleComponentTemplate%>" post="trim()"%>
<%ID.toLowerCase().nPut("title_name")%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id>title</region-id>
   <source-id><%nGet("title_name")%></source-id>
   <url>/components/title/collaboration-title</url>
</component>