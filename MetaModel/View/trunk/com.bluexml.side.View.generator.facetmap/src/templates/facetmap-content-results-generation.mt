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
