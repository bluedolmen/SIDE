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
	./webapps/facetmap/xsl/display/includes/basic_<%name%>-Facets.xsl

<%script type="view.FacetMap" name="basicGenerator"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="1.0">
 
  <!-- URL -->
  <xsl:param name="nb_paging_facets"><%paging.maxItems%></xsl:param>
  
  <xsl:template match="superset">
  <div class="chosen-facets">
    <xsl:variable name="taxtitle" select="@taxtitle" />
      <xsl:if test="position()=1">
        <div class="chosen-facets-title">Critères sélectionnés</div>
        <hr Class="hr1" />
      </xsl:if>
      <ul style="list-style-image: url('{$icons_url}/bullet.gif');">
        <li class="chosen-facet">
          <xsl:apply-templates select="s" mode="superselection" />
          <xsl:text> &gt; </xsl:text>
          <xsl:value-of select="../heading[@facet=$taxtitle]/@title" />
        </li>
      </ul>
	 </div>
  </xsl:template>
  <xsl:template match="s" mode="superselection">
    <xsl:if test="position()!=1">
    <xsl:text> &gt; </xsl:text>
	</xsl:if>
    <a href="{$pre_reference_url}{@ref}{$facetName}{$community}" onclick="show_selection('{$pre_reference_url}{@ref}{$facetName}{$community}')">
      <xsl:value-of select="@title" />
    </a>
  </xsl:template>
  
<xsl:template name="make_link_from_ref">
    <xsl:param name="ref" />
    <xsl:param name="text" />
    <xsl:element name="a">
      <xsl:attribute name="href">
        <xsl:value-of select="$pre_reference_url" />
        <xsl:value-of select="$ref" />
        <xsl:value-of select="$post_reference_url" />
        <xsl:value-of select="$facetName" />
        <xsl:value-of select="$community" />
      </xsl:attribute>
      <xsl:value-of select="$text" />
    </xsl:element>
  </xsl:template>
</xsl:stylesheet>
