<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">
  <xsl:output method="html" />
  
  <xsl:include href="includes/basic-Global.xsl"/>
  <xsl:include href="includes/basic-Results.xsl"/>
  
  <xsl:template match="selection">
    <div class="results" id="results">
      <xsl:apply-templates select="results" />
    </div>
  </xsl:template>
</xsl:stylesheet>
