<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./webapps/facetmap/xsl/display/Rightnav_<%name%>_content.xsl
	
<%script type="view.FacetMap" name="rightnavGeneratorContent"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">
  <xsl:output method="html" />
  
  <%if (getInnerView().filter("DataList")){%><xsl:include href="includes/basic-Global.xsl"/><%}%>
  <xsl:include href="includes/basic_<%name%>-Results.xsl"/>
  
  <xsl:template match="selection">
    <div class="results" id="results">
      <xsl:apply-templates select="results" />
    </div>
  </xsl:template>
</xsl:stylesheet>
