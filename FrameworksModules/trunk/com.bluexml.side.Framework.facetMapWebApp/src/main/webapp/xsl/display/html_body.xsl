<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="xml" indent="yes" omit-xml-declaration="yes" />

  <xsl:template match="/">
    <xsl:choose>
      <xsl:when test="html/body">
        <xsl:copy-of select="html/body/node()|html/body/*" />
      </xsl:when>
      <xsl:otherwise>
        <xsl:copy-of select="node()|*" />
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
    
</xsl:transform>

