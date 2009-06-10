<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/class/1.0
import com.bluexml.side.clazz.generator.facetmap.ClassFacetmapGenerator
%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
	.common/facetmap/WEB-INF/configuration.properties

<%script type="clazz.ClassPackage" name="facetmapGenerator"  file="<%validatedFilename%>" %>
view=Righnav-paging.xsl
showEmptySelections=false
rebuildMap=true
map=test.xml
resultLimit=20
workDirectory=.
log4jConfigFile=logProperties.txt
