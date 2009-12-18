<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./webapps/facetmap/WEB-INF/classes/alfrescoConnector/build.<%name%>.properties

<%script type="view.FacetMap" name="propertyfileGenerator"  file="<%validatedFilename%>" %>
<%-- the contentType is used to get the right files in the CMIS webscript --%>
ant.contentType=<% viewOf.filter("NamedModelElement").getPrefixedQName().replaceAll("\.","_") %>
ant.rootPackage=<% viewOf.getRootContainer().name %>
ant.xslFolder=xsl/<%name%>/
ant.infFolder=./
ant.passwordAlfresco=admin
ant.Mapname=map.xml
ant.host=localhost\:8080
ant.CMISurl=alfresco/service/com/bluexml/side/facetMap/doclist_user.xml
ant.SQLURL=<Not Available>
ant.usernameAlfresco=admin
ant.encoding=ISO-8859-1
ant.contentFolder=facetmap
ant.CMISresult=CMIS_result.xml