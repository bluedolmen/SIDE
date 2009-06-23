<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./facets/WEB-INF/buildProperties.xml
	
<%script type="view.FacetMap" name="hostname"%>
	localhost:8080

<%script type="view.FacetMap" name="buildPropertiesGenerator"  file="<%validatedFilename%>" %>
<ant>
	<CMISurl>http://<%hostname()%>/alfresco/service/bluexml/doclist_user.xml</CMISurl>
	<CMISresult>CMIS_result.xml</CMISresult>
	<usernameAlfresco>admin</usernameAlfresco>
	<passwordAlfresco>admin</passwordAlfresco>
	<infFolder>./</infFolder>
	<contentFolder>facetmap-content</contentFolder>
	<Mapname>map.xml</Mapname>
	<xslFolder>xsl/</xslFolder>
	<encoding>ISO-8859-1</encoding>
</ant>