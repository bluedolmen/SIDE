<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.portal.generator.alfresco40d.templates.portal.template-instance
%>


<%-- Navigation component templates creation --%>
<%script type="HavePortlet" name="createNavigationComponentTemplate"%>
<%if (associationPortlet.isInstanceOfPortletType != null && associationPortlet.isInstanceOfPortletType.instances[instanceOf.name == "scope"].value == "global"){%>
<%getProperty("alf.share.paths.web-ext.components")%><%computeScope()%>.<%associationPortlet.name%>.<%associationPage.ID.toLowerCase()%>.xml
<%}%>
<%script type="HavePortlet" name="alfrescoGenerator" file="<%createNavigationComponentTemplate%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope><%computeScope()%></scope>
   <region-id><%associationPortlet.name%></region-id>
   <source-id><%associationPage.ID.toLowerCase()%></source-id>
   <url><%computeUrl()%></url>
   <%associationPortlet.computeProperties()%>
   <%compute_subComponent()%>
</component>

<%script type="HavePortlet" name="computeScope" post="trim()"%>
<%for (associationPortlet.isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "scope"){%><%value%><%}%>	
<%}%>
<%script type="HavePortlet" name="computeUrl" post="trim()"%>
<%for (associationPortlet.isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "url"){%><%value%><%}%><%}%>
