<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
%>


<%-- Navigation component templates creation --%>
<%script type="HavePortlet" name="createNavigationComponentTemplate"%>
<%if (associationPortlet.isPortletInternal == null || associationPortlet.isPortletInternal.view == null){%>
<%getProperty("alf.share.paths.web-ext.components")%>template.<%associationPortlet.name%>.xml
<%}%>
<%script type="HavePortlet" name="alfrescoGenerator" file="<%createNavigationComponentTemplate%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>
   <%for (associationPortlet.isInstanceOfPortletType.instances){%>
   <%if (instanceOf == "scope"){%><%value%><%}%>	
   <%}%>
   </scope>
   <region-id><%associationPortlet.name%></region-id>
   <source-id><%associationPage.ID%></source-id>
   <url>
   <%for (associationPortlet.isInstanceOfPortletType.instances){%>
   <%if (instanceOf.name == "url"){%><%value%><%}%><%}%>
   </url>
   <properties>
   <%for (associationPortlet.isInstanceOfPortletType.instances){%>
   <%if (instanceOf.name != "scope" && instanceOf.name != "url" && instanceOf.name != "region-id" && instanceOf.name != "source-id"){%>   
      <<%instanceOf.name%>><%value%></<%instanceOf.name%>>
   <%}%>   	
   <%}%>
   </properties>
</component>