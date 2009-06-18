<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./common/facetmap/WEB-INF/cmisTransformProperties.xml
	
<%script type="view.FacetMap" name="hostname"%>
	localhost:8080

<%script type="view.FacetMap" name="cmisPropertiesGenerator"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com" version="2.0">
	<xsl:param name="host">http://<%hostname()%></xsl:param>
	<xsl:param name="DLUrl">/share/page/site/base/document-details?nodeRef=workspace://SpacesStore/</xsl:param>
	<xsl:param name="ViewURL">/alfresco/d/a/workspace/SpacesStore/</xsl:param>
</xsl:stylesheet>