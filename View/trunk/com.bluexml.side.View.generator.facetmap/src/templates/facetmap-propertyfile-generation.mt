<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./common/facetmap/WEB-INF/configuration.properties
<%script type="view.FacetMap" name="numberResultsShown"%>
20

<%script type="view.FacetMap" name="propertyfileGenerator"  file="<%validatedFilename%>" %>
view=Righnav.xsl
showEmptySelections=<%displayEmptyFacet%>
rebuildMap=true
map=test.xml
resultLimit=<%numberResultsShown()%>
workDirectory=.
log4jConfigFile=logProperties.txt
