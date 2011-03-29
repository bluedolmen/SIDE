<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:clazz="http://www.kerblue.org/class/1.0">

	<xsl:key name="getPackage" match="packageSet" use="@path" />

	<xsl:template match="clazz:Model[./packageSet]">
		<xsl:element name="clazz:Model">
			<xsl:copy-of select="@*" />
			<xsl:call-template name="copyPackages">
				<xsl:with-param name="context" select="packageSet[1]" />
			</xsl:call-template>
		</xsl:element>
	</xsl:template>

	<xsl:template name="copyPackages">
		<xsl:param name="context" />

		<xsl:element name="packageSet">
			<xsl:copy-of select="$context/@*" />
			<xsl:copy-of
				select="key('getPackage', $context/@path)/*[name() != 'packageSet']" />
			<xsl:for-each-group select="//packageSet[../@path = $context/@path]"
				group-by="@path">
				<xsl:call-template name="copyPackages">
					<xsl:with-param name="context" select="current()" />
				</xsl:call-template>
			</xsl:for-each-group>
		</xsl:element>
	</xsl:template>

	<xsl:template match="/">
		<xsl:apply-templates select="child::node()" />
	</xsl:template>
	
	<!-- standard copy template -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*" />
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>

</xsl:stylesheet>