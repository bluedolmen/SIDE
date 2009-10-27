<%
metamodel http://www.kerblue.org/portal/1.0
%>


<%-- Title component templates creation --%>
<%script type="Page" name="createTitleComponentTemplate"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("title_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("alf.share.paths.web-ext.components")%>template.title.<%nGet("title_name")%>.xml
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%createTitleComponentTemplate%>" post="trim()"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("title_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id>title</region-id>
   <source-id><%nGet("title_name")%></source-id>
   <url>/components/title/collaboration-title</url>
</component>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>