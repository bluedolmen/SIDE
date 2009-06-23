<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/class/1.0
import com.bluexml.side.clazz.generator.facetmap.ClassFacetmapGenerator
%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
	./common/facetmap/WEB-INF/configuration.properties
<%script type="clazz.ClassPackage" name="emptySelection"%>
false
<%script type="clazz.ClassPackage" name="numberResultsShown"%>
20
<%script type="clazz.ClassPackage" name="propertyfileGenerator"  file="<%validatedFilename%>" %>
view=Righnav.xsl
showEmptySelections=<%emptySelection()%>
rebuildMap=true
map=test.xml
resultLimit=<%numberResultsShown()%>
workDirectory=.
log4jConfigFile=logProperties.txt
