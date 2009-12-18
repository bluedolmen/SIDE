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
