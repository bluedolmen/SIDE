<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./common/WEB-INF/configuration.properties

<%script type="view.FacetMap" name="propertyfileGenerator"  file="<%validatedFilename%>" %>
view=Rightnav.xsl
showEmptySelections=<%displayEmptyFacet%>
rebuildMap=true
map=map.xml
resultLimit=<% getInnerView().filter("AbstractDataTable").paging.maxItems %>
workDirectory=.
log4jConfigFile=logProperties.txt
