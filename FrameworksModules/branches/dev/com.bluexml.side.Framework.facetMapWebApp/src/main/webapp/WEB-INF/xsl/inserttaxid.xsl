<?xml version="1.0" encoding="ISO-8859-1"?>

<!--
  Utility transform to insert taxid attributes inside <f> elements.
  This comes in handy when all your facets have unique IDs across
  an entire facetmap document. Just specify the id attribute of each
  <f> element (f-bond), skip the taxid attribute, then run this transform.
  
  Of course, if you have two facets in different taxonomies with the
  same ID, which is legitimate, the XSL won't be able to distinguish
  which taxonomy to use, and this transform won't work.
-->
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" omit-xml-declaration="no" indent="yes" doctype-system="/usr/local/tomcat/webapps/facetmap/spec/facetmap1_0.dtd" />

  <xsl:template match="*">
    <xsl:copy>
     <xsl:copy-of select="@*" />
     <xsl:apply-templates />
    </xsl:copy>
  </xsl:template>
  
  <xsl:template match="f">
    <f>
     <xsl:attribute name="id"><xsl:value-of select="@id" /></xsl:attribute>
     <xsl:variable name="facet_id"><xsl:value-of select="@id" /></xsl:variable>
     <xsl:for-each select="//taxonomy[descendant::facet[@id=$facet_id]]">
      <xsl:attribute name="taxid">
       <xsl:value-of select="@taxid" />
      </xsl:attribute>
     </xsl:for-each>
    </f>
  </xsl:template>

</xsl:transform>
