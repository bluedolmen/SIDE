<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>


<%-- Navigation component templates creation --%>
<%script type="HavePortlet" name="createNavigationComponentTemplate"%>
<%if (associationPortlet.isPortletInternal == null){%>
<%getProperty("alf.share.paths.web-ext.components")%>template.<%associationPortlet.name%>.<%associationPage.ID%>.xml
<%}%>
<%script type="HavePortlet" name="alfrescoGenerator" file="<%createNavigationComponentTemplate%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope><%computeScope()%></scope>
   <region-id><%associationPortlet.name%></region-id>
   <source-id><%associationPage.ID%></source-id>
   <url><%computeUrl()%></url>
   <properties>
   <%for (associationPortlet.isInstanceOfPortletType.instances){%>
   <%if (instanceOf.name != "scope" && instanceOf.name != "url" && instanceOf.name != "region-id" && instanceOf.name != "source-id"){%>   
      <<%instanceOf.name%>><%value%></<%instanceOf.name%>>
   <%}%>   	
   <%}%>
   </properties>
</component>

<%script type="HavePortlet" name="computeScope" post="trim()"%>
<%for (associationPortlet.isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "scope"){%><%value%><%}%>	
<%}%>
<%script type="HavePortlet" name="computeUrl" post="trim()"%>
<%for (associationPortlet.isInstanceOfPortletType.instances){%>
<%if (instanceOf.name == "url"){%><%value%><%}%><%}%>
