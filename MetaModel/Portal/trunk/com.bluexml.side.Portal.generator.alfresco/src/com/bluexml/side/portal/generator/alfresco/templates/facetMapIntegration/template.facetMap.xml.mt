<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="HavePortlet" name="createTemplates"%>
<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.view != null){%>
<%for (associationPortlet.isPortletInternal.view){%>
	<%if (current("view.FacetMap")){%>
<%getProperty("alf.share.paths.web-ext.components")%>/template.<%current("HavePortlet").associationPortlet.name%>.<%current("HavePortlet").associationPage.ID%>.xml
	<%}%>
<%}%>
<%}%>

<%script type="HavePortlet" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id><%associationPortlet.name%></region-id>
   <source-id><%associationPage.ID%></source-id>
   <url>/components/facetMap</url>
   <properties>
      <facetName><%associationPortlet.isPortletInternal.view.name%></facetName>
   </properties>
</component>