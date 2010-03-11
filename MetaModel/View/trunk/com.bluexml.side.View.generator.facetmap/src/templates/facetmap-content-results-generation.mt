<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
import templates.facetmap-content-basicresults-generation
import templates.facetmap-content-tableresults-generation
import templates.facetmap-content-extJsresults-generation
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./webapps/facetmap/xsl/display/includes/basic_<%name%>-Results.xsl
<%script type="view.FacetMap" name="rightnavGenerator"  file="<%validatedFilename%>" %>
<%if (getInnerView().filter("DataTable")){%>
<%--rightnavGenerator_table()--%>
<%rightnavGenerator_extjs()%>
<%}%>
<%if (getInnerView().filter("DataList")){%>
<%rightnavGenerator_basic()%>
<%}%>
