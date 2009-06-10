<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/class/1.0
import com.bluexml.side.clazz.generator.facetmap.ClassFacetmapGenerator
%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
	./facets/facetmap/WEB-INF/buildProperties.xml
	
<%script type="clazz.ClassPackage" name="facetmapGenerator"  file="<%validatedFilename%>" %>
<ant>
	<CMISurl>http://localhost:8080/alfresco/service/bluexml/doclist_user.xml</CMISurl>
	<CMISresult>CMIS_result.xml</CMISresult>
	<usernameAlfresco>admin</usernameAlfresco>
	<passwordAlfresco>admin</passwordAlfresco>
	<infFolder>./</infFolder>
	<Mapname>map.xml</Mapname>
	<xslFolder>xsl/</xslFolder>
	<encoding>ISO-8859-1</encoding>
</ant>