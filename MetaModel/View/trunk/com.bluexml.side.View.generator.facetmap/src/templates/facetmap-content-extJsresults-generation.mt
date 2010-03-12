<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="rightnavGenerator_extjs"%>
<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0" xmlns:str="http://exslt.org/strings"
	extension-element-prefixes="str">
<xsl:output method="xml"/>

	<xsl:template match="results">
		<link rel="stylesheet" type="text/css"
			href="/facetmap/ext-3.1.1/resources/css/ext-all.css" />

		<!-- GC -->
		<!-- LIBS -->
		<script type="text/javascript" src="/facetmap/ext-3.1.1/adapter/ext/ext-base.js"><xsl:comment></xsl:comment></script>
		<!-- ENDLIBS -->

		<script type="text/javascript" src="/facetmap/ext-3.1.1/ext-all.js"><xsl:comment></xsl:comment></script>

		<script type="text/javascript" src="/facetmap/xsl/display/includes/xml-grid_<%name%>.js"><xsl:comment></xsl:comment></script>
		<!--link rel="stylesheet" type="text/css" href="/facetmap/ext-3.1.1/examples/grid/grid-examples.css" /-->

		<!-- Common Styles for the examples -->
		<!--link rel="stylesheet" type="text/css" href="/facetmap/ext-3.1.1/examples/shared/examples.css" /-->

		<!--script type="text/javascript" src="/facetmap/ext-3.1.1/examples/shared/examples.js"><xsl:comment></xsl:comment></script-->
		<!-- EXAMPLES -->

		<div id="extjs-grid"></div>

		<div style="display: none;">
			<Items id="data">
				<TotalResults>
					<xsl:value-of select="@count"></xsl:value-of>
				</TotalResults>
				<TotalPages><xsl:value-of select="number(@count) div count(resource)"></xsl:value-of></TotalPages>
				<xsl:apply-templates select="resource">
					<xsl:with-param name="nbParPages" select="count(resource)" />
					<xsl:with-param name="NbTotal" select="@count" />
				</xsl:apply-templates>
			</Items>
		</div>

	</xsl:template>

	<xsl:template match="resource">
		<xsl:param name="nbParPages" />
		<xsl:param name="NbTotal" />
		<Item>
		<id><xsl:value-of select="@href"></xsl:value-of></id>
		<xsl:variable name="headers" select="//heading"/>
			<xsl:for-each select="str:split(@name,'#')">
				<xsl:variable name="index" select="position()" />
				<xsl:variable name="propId" select="translate($headers[$index]/@facetid,'.','_')"></xsl:variable>
				
				<xsl:element name="{$propId}">
					<xsl:value-of select="text()" />
					<xsl:comment></xsl:comment>
				</xsl:element>
			</xsl:for-each>
		</Item>
	</xsl:template>
</xsl:stylesheet>
