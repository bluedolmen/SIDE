<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
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
