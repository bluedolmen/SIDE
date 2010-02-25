<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://www.omg.org/XMI"
				xmlns:clazz="http://www.kerblue.org/class/1.0">

	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	

	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()"/>
		</xsl:copy>
	</xsl:template>


	<xsl:template match="@valueList">
		<xsl:if test=".!=''">		
			<xsl:variable name="value" select="."/>
			<xsl:choose>
				<xsl:when test="//enumerationSet[@name=$value]">	
					<xsl:variable name="ref" select="//enumerationSet[@name=$value]"/>
					<xsl:variable name="id" select="$ref/@xmi:id"/>
					<xsl:for-each select="parent::node()">
				  		<xsl:attribute name="valueList" select="$id"/>
				  	</xsl:for-each>
				</xsl:when>
				<xsl:otherwise/>
			</xsl:choose>
		</xsl:if>
	</xsl:template>
	
</xsl:stylesheet>