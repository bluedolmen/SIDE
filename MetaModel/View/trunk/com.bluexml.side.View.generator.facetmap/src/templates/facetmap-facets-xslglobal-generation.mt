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
	./webapps/facetmap/xsl/display/includes/basic-Global.xsl
<%script type="view.FacetMap" name="rightnavGenerator"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="1.0">
  <xsl:param name="resource_base_url">xsl/display</xsl:param>
  <xsl:param name="icons_url">xsl/display/icons</xsl:param>
  <xsl:param name="pre_reference_url" />
  <xsl:param name="post_reference_url" />
  <xsl:param name="facetName" />
  <xsl:param name="community" />
  <xsl:param name="isAdmin" />
  
  
  <xsl:template match="/">
    <html>
      <head>
        <link rel="stylesheet"
        href="{$resource_base_url}/facetmap.css" />
      </head>
      <body>
          <xsl:apply-templates select="selection" />
      </body>
    </html>
  </xsl:template>
  
</xsl:stylesheet>
