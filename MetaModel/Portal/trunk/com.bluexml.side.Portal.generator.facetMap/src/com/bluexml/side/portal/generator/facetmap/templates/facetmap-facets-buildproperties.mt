<%
metamodel http://www.kerblue.org/portal/1.0
%>

<%script type="Page" name="facetmapfacetsbuildproperties"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("properties_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("facetmap.webapps")%>facetmap-facets-<%nGet("properties_name").substring(nGet("properties_name").lastIndexOf("-") +1)%>/WEB-INF/build.properties
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%script type="Page" name="facetmapGenerator" file="<%facetmapfacetsbuildproperties%>" post="trim()"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("properties_name")%>
<%ID.toU1Case().nPut("properties_name2")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
#Tue Oct 13 12:29:06 CEST 2009
ant.xslFolder=xsl/
ant.SQLurl=alfresco/service/sql/selectnodeproperties
ant.contentTypeSQL=<%nGet("properties_name2").substring(nGet("properties_name2").lastIndexOf("-") +1)%>
ant.infFolder=./
ant.SQLresult=SQL_result.xml
ant.rootPackage=com
ant.passwordAlfresco=admin
ant.Mapname=map.xml
ant.host=localhost\:8080
ant.CMISurl=alfresco/service/com/bluexml/side/doclist_user.xml
ant.usernameAlfresco=admin
ant.contentType=com_bluexml_demo_rh_<%nGet("properties_name2").substring(nGet("properties_name2").lastIndexOf("-") +1)%>
ant.encoding=ISO-8859-1
ant.CMISresult=CMIS_result.xml
ant.contentFolder=facetmap-content-<%nGet("properties_name").substring(nGet("properties_name").lastIndexOf("-") +1)%>
ant.Object=<%nGet("properties_name2").substring(nGet("properties_name2").lastIndexOf("-") +1)%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>