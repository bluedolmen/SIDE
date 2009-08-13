<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./facets/WEB-INF/build.properties

<%script type="view.FacetMap" name="propertyfileGenerator"  file="<%validatedFilename%>" %>
<%-- the contentType is used to get the right files in the CMIS webscript --%>
ant.contentType=<% viewOf.getRootContainer().name %>:<% viewOf.filter("NamedModelElement").getFullName().replaceAll("\.","_") %>
ant.xslFolder=xsl/
ant.infFolder=./
ant.passwordAlfresco=admin
ant.Mapname=map.xml
ant.host=localhost\:8080
ant.CMISurl=alfresco/service/com/bluexml/side/facetMap/doclist_user.xml
ant.usernameAlfresco=admin
ant.encoding=ISO-8859-1
ant.contentFolder=facetmap-content
ant.CMISresult=CMIS_result.xml