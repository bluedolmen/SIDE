<%
metamodel http://www.kerblue.org/portal/1.0
%>

<%script type="Page" name="facetmapfacetsbuildsql"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("buildsql_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("facetmap.webapps")%>facetmap-facets-<%nGet("buildsql_name").substring(nGet("buildsql_name").lastIndexOf("-") +1)%>/WEB-INF/buildsql.xml
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%script type="Page" name="alfrescoGenerator" file="<%facetmapfacetsbuildsql%>" post="trim()"%>
<%if (eContainer() != null) {%>
<%parent().name.nPut("site_name")%>
<%ID.toLowerCase().nPut("buildsql_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<?xml version="1.0" encoding="ISO-8859-1"?>

<project name="updtating-Documents" default="main" basedir=".">
	<property file="build.properties" />
	<target name="main">
		<!-- creating the folder for result-->
		<mkdir dir="${ant.infFolder}" />
		<!-- getting the file-->
		<get src="http://${ant.host}/${ant.SQLurl}?type=${ant.contentTypeSQL}" dest="${ant.infFolder}${ant.SQLresult}" username="${ant.usernameAlfresco}" password="${ant.passwordAlfresco}" />
		<echo message="http://${ant.host}/${ant.SQLurl}?type=${ant.contentTypeSQL}" />
		<echo message="${ant.infFolder}${ant.SQLresult}" />
		<echo message="${ant.usernameAlfresco}" />
		<echo message="${ant.passwordAlfresco}" />
		<!-- replacing &amp;-->
		<replaceregexp byline="true">
			<regexp pattern="&amp;(.*)" />
			<substitution expression="&amp;amp;\1" />
			<fileset dir="." includes="${ant.infFolder}${ant.SQLresult}" />
		</replaceregexp>
		<!-- making the xslt tranformation -->
		<xslt basedir="." in="${ant.infFolder}${ant.SQLresult}" out="${ant.infFolder}${ant.Mapname}" processor="trax" style="${ant.infFolder}${ant.xslFolder}sql2xfml.xsl">
			<outputproperty name="encoding" value="${ant.encoding}" />
		</xslt>
		<!-- Copying the file in the folder of the results -->
		<copy file="${ant.infFolder}${ant.Mapname}" toFile="../../facetmap-content-<%nGet("buildsql_name").substring(nGet("buildsql_name").lastIndexOf("-") +1)%>/WEB-INF/${ant.Mapname}" overwrite="true" />
	</target>
</project>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>