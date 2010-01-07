<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>


<%-- Navigation component templates creation --%>
<%script type="Page" name="createNavigationComponentTemplate"%>
<%ID.toLowerCase().nPut("navigation_name")%>
<%if (isDefaultSharePage() =="false"){%>
<%getProperty("alf.share.paths.web-ext.components")%>template.navigation.<%nGet("navigation_name")%>.xml
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createNavigationComponentTemplate%>" post="trim()"%>
<%ID.toLowerCase().nPut("navigation_name")%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id>navigation</region-id>
   <source-id><%nGet("navigation_name")%></source-id>
   <url>/components/navigation/collaboration-navigation</url>
</component>