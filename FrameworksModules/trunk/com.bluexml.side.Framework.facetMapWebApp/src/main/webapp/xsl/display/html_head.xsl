<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="xml" indent="yes" omit-xml-declaration="yes" />

  <xsl:template match="/">
    <xsl:if test="html/head">
      <xsl:copy-of select="html/head/node()|html/head/*" />
    </xsl:if>
  </xsl:template>
    
</xsl:transform>

