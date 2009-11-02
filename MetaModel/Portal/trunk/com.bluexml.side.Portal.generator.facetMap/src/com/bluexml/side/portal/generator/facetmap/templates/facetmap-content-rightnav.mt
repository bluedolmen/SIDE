<%
metamodel http://www.kerblue.org/portal/1.0
%>

<%script type="Page" name="facetmapcontentrightnav"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("rnavcontent_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<%getProperty("facetmap.webapps")%>facetmap-content-<%nGet("rnavcontent_name").substring(nGet("rnavcontent_name").lastIndexOf("-") +1)%>/xsl/display/Rightnav.xsl
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%script type="Page" name="facetmapGenerator" file="<%facetmapcontentrightnav%>" post="trim()"%>
<%if (eContainer() != null) {%>
<%ID.toLowerCase().nPut("rnavcontent_name")%>
<%for (portlets){%>
<%for (associationPortlet){%>
<%if (isPortletInternal != null && isPortletInternal.view != null){%>
<%for (isPortletInternal.view){%>
<%if (current().startsWith("view.FacetMap")){%>
<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">
  <xsl:output method="html" />
  
  <xsl:include href="includes/basic-Global.xsl"/>
  <xsl:include href="includes/basic-Results.xsl"/>
  
  <xsl:template match="selection">
    <div class="results" id="results-<%nGet("rnavcontent_name").substring(nGet("rnavcontent_name").lastIndexOf("-") +1)%>">
      <xsl:apply-templates select="results" />
    </div>
  </xsl:template>
</xsl:stylesheet>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>