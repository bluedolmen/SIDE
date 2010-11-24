<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:xmi="http://www.omg.org/XMI"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:d="http://www.alfresco.org/model/dictionary/1.0" >

	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	
	<!-- "Unitary" copy of model -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()"/>
		</xsl:copy>
	</xsl:template>
		
	<!-- Recursive copy of properties of parents's aspects -->
	<xsl:template name="copy">
		<xsl:param name="parent_name"/>
		<xsl:value-of select="$parent_name"/>
		<xsl:for-each select="//d:aspect[@name=$parent_name]/d:properties/d:property">
			<xsl:copy-of select="."/>
		</xsl:for-each>
		<xsl:choose>
			<xsl:when test="//d:aspect[@name=$parent_name]/d:parent">
				<xsl:variable name="parent" select="//d:aspect[@name=$parent_name]/d:parent/text()"/>
				<xsl:call-template name="copy">
					<xsl:with-param name="parent_name" select="$parent"/>
				</xsl:call-template>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	<!-- Loop on aspects -->
	<xsl:template match="d:aspect">
		<xsl:choose>
			<xsl:when test="d:parent">
				<aspect>
				<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
				<xsl:variable name="parent" select="d:parent/text()"/>
				<xsl:copy-of select="child::node()[name() != 'properties' and name() != 'parent']"/>
				<xsl:for-each select="d:properties">
				<properties>
					<xsl:copy-of select="child::node()"/>
					<xsl:call-template name="copy">
						<xsl:with-param name="parent_name" select="$parent"/>
					</xsl:call-template>
				</properties>
				</xsl:for-each>
				</aspect>
			</xsl:when>
			<xsl:otherwise>
				<xsl:copy-of select="."/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
</xsl:stylesheet>