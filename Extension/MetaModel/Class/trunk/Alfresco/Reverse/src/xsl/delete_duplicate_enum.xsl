<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://www.omg.org/XMI"
				xmlns:clazz="http://www.kerblue.org/class/1.0">

	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	

	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()"/>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="enumerationSet">
		<xsl:call-template name="del_duplicate_enum">
			<xsl:with-param name="enum_to_compare" select="."/>
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template name="del_duplicate_enum">
		<xsl:param name="enum_to_compare"/>
		<xsl:variable name="same" select="//enumerationSet[(../@name = $enum_to_compare/../@name) and (@name = $enum_to_compare/@name)]"/>
		<xsl:choose>
			<xsl:when test="count($same) = 1">
				<xsl:copy-of select="$enum_to_compare"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:if test="$enum_to_compare/@xmi:id = $same[1]/@xmi:id">
					<xsl:copy-of select="$enum_to_compare"/>
				</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
</xsl:stylesheet>