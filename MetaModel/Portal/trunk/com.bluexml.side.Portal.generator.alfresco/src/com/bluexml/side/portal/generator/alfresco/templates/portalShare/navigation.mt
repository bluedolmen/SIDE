<%
metamodel http://www.kerblue.org/portal/1.0
%>


<%-- Navigation component templates creation --%>
<%script type="Page" name="createNavigationComponentTemplate"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("navigation_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("alf.share.paths.web-ext.components")%>template.navigation.<%nGet("navigation_name")%>.xml
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createNavigationComponentTemplate%>" post="trim()"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("navigation_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id>navigation</region-id>
   <source-id><%nGet("navigation_name")%></source-id>
   <url>/components/navigation/collaboration-navigation</url>
</component>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>