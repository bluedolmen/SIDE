<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:form="http://www.kerblue.org/form/1.0" xmlns:clazz="http://www.kerblue.org/class/1.0" >

	<xsl:template match="real_class">
		<real_class>
			<xsl:attribute name="xsi:type">clazz:Clazz</xsl:attribute>
			<xsl:apply-templates select="@*" />
		</real_class>
	</xsl:template>
	<!-- standard copy template -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*" />
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>