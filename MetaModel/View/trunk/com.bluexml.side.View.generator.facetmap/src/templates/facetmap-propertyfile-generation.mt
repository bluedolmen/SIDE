<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./webapps/facetmap/WEB-INF/classes/multimap/configuration_<%name%>.properties

<%script type="view.FacetMap" name="propertyfileGenerator"  file="<%validatedFilename%>" %>
view_content=Rightnav_<%name%>_content.xsl
view_facets=Rightnav_<%name%>_facets.xsl
showEmptySelections=<%displayEmptyFacet%>
rebuildMap=true
map=map.xml
resultLimit=<%if (getInnerView().filter("AbstractDataTable").paging.maxItems){%><% getInnerView().filter("AbstractDataTable").paging.maxItems %>
<%}else{%>5<%}%>
workDirectory=.
log4jConfigFile=logProperties.txt
