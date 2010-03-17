<?xml version="1.0" encoding="ISO-8859-1"?>

<!--
  An XSL Transform to produce facetmap-compliant XML from XFML <http://xfml.org/spec> .
  This is designed for XFML v1.0; however, <connect> info is lost because
  FacetMap interfaces don't use it (yet).

  Note that you should always run the output of this transform through inserttaxid.xsl
  to provide a taxid attribute to the f-bonds (occurrences).

  Copyright 2002 by Travis Wilson and FacetMap <http://facetmap.com> . This document may
  be freely distributed or included in other applications as long as this copyright notice
  remains intact. This document may be modified only if such modifications are also freely
  distributable.
-->

<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" omit-xml-declaration="no" indent="yes" />

  <!-- xfml:xfml ~= facetmap:facetmap -->
  <xsl:template match="/xfml">
    <facetmap>
     <xsl:attribute name="title"><xsl:value-of select="@url" /></xsl:attribute>
     
     <!-- Define taxonomies -->
     <xsl:apply-templates select="facet" />
     
     <!-- Define resources -->
     <resources>
      <xsl:apply-templates select="page" />
     </resources>
    </facetmap>
  </xsl:template>

  <!-- xfml:facet ~= facetmap:taxonomy -->
  <xsl:template match="facet">
    <taxonomy>
     <xsl:attribute name="taxid"><xsl:value-of select="@id" /></xsl:attribute>
     <xsl:attribute name="rootfacet-title"><xsl:value-of select="." /></xsl:attribute>
     <xsl:attribute name="title"><xsl:value-of select="." /></xsl:attribute>
     <!-- nonstandard XFML in following line: -->
     <xsl:attribute name="href"><xsl:value-of select="@href" /></xsl:attribute>
     <xsl:variable name="taxonomy_id"><xsl:value-of select="@id" /></xsl:variable>
     <xsl:apply-templates select="//topic[@parentTopicid=$taxonomy_id or (@facetid=$taxonomy_id and not(@parentTopicid))]" />
    </taxonomy>
  </xsl:template>

  <!-- xfml:topic ~= facetmap:facet -->
  <xsl:template match="topic">
    <facet>
     <xsl:attribute name="id"><xsl:value-of select="@id" /></xsl:attribute>
     <xsl:attribute name="title"><xsl:value-of select="name" /></xsl:attribute>

     <xsl:variable name="facet_id"><xsl:value-of select="@id" /></xsl:variable>
     <xsl:apply-templates select="//topic[@parentTopicid=$facet_id]" />
    </facet>
  </xsl:template>
  
  <!-- xfml:page ~= facetmap:r (resource) -->
  <xsl:template match="page">
    <r>
     <xsl:attribute name="title"><xsl:value-of select="title" /></xsl:attribute>
     <xsl:attribute name="href"><xsl:value-of select="@url" /></xsl:attribute>
     <xsl:apply-templates select="occurrence" />
    </r>
  </xsl:template>
  
  <!-- xfml:occurrence ~= facetmap:f (f-bond) -->
  <!--
    It's critical to note that taxid is not applied here! XFML topics don't
    know what facet structure they're part of (since their names must be unique
    across structures anyway). If the XFML page element specifies occurrences
    for every facet structure, in the order those structures appear, then the
    facetmap markup will work. But that usually doesn't happen. Easiest fix
    is to run the output of this transform through inserttaxid.xsl .
  -->
  <xsl:template match="occurrence">
    <f>
     <xsl:attribute name="id"><xsl:value-of select="@topicid" /></xsl:attribute>
     <!-- nonstandard XFML in following line: -->
     <xsl:attribute name="taxid"><xsl:value-of select="@facetid" /></xsl:attribute>
    </f>
  </xsl:template>

</xsl:transform>
