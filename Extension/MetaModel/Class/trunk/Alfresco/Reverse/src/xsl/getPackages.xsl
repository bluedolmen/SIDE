<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:xmi="http://www.omg.org/XMI" xmlns:clazz="http://www.kerblue.org/class/1.0"
	xmlns:my="http://bluexml.com" xmlns:math="java:java.lang.Math">
	<xsl:output indent="yes" />



	<xsl:template match="clazz:Model[clazz:Model]">
		<clazz:Model>
			<xsl:apply-templates select="@*|node()" />
		</clazz:Model>
	</xsl:template>

	<xsl:template match="clazz:Model">
		<clazz:Model>
			<xsl:copy-of select="@*" />
			<xsl:for-each select="classSet | aspectSet">
				<xsl:call-template name="packages">
					<xsl:with-param name="items" select="my:tokenizePackageName(@name)" />
				</xsl:call-template>
			</xsl:for-each>
			<xsl:for-each
				select="enumerationSet[@name != 'nomenclature:comparison' and @name != 'nomenclature:booleanoperator']">
				<xsl:call-template name="packages">
					<xsl:with-param name="items"
						select="my:tokenizePackageName(substring-after(@name, ':'))" />
				</xsl:call-template>
			</xsl:for-each>
			<xsl:for-each select="associationSet">
				<xsl:call-template name="packages">
					<xsl:with-param name="items"
						select="my:tokenizeAssoName(@name,//classSet/@name | //aspectSet/@name)" />
				</xsl:call-template>
			</xsl:for-each>
		</clazz:Model>
	</xsl:template>

	<xsl:template name="packages">
		<xsl:param name="items" />
		<xsl:call-template name="add">
			<xsl:with-param name="items" select="$items" />
			<xsl:with-param name="index" select="1" />
			<xsl:with-param name="path" select="''" />
		</xsl:call-template>
		<xsl:apply-templates />
	</xsl:template>



	<xsl:template name="add">
		<xsl:param name="items" />
		<xsl:param name="index" />
		<xsl:param name="path" />
		<xsl:choose>
			<xsl:when test="$index &gt; count($items)">
				<xsl:copy-of select="." />
			</xsl:when>
			<xsl:otherwise>
				<xsl:variable name="current" select="$items[$index]" />
				<xsl:variable name="newPath">
					<xsl:choose>
						<xsl:when test="$index = 1">
							<xsl:value-of select="$items[$index]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="concat($path,'_',$items[$index])" />
						</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
				<xsl:element name="packageSet">
					<xsl:attribute name="xsi:type" select="'clazz:ClassPackage'" />
					<xsl:attribute name="xmi:id" select="math:random()" />
					<xsl:attribute name="name" select="$current" />
					<xsl:attribute name="path" select="$newPath" />
					<!--					<xsl:attribute name="index" select="$index" />-->
					<!--					<xsl:attribute name="items" select="$items" />-->
					<!--					<xsl:attribute name="total" select="count($items)" />-->

					<xsl:call-template name="add">
						<xsl:with-param name="items" select="$items" />
						<xsl:with-param name="path" select="$newPath" />
						<xsl:with-param name="index" select="$index + 1" />
					</xsl:call-template>
				</xsl:element>
			</xsl:otherwise>
		</xsl:choose>

	</xsl:template>



	<xsl:template match="@*|node()">
		<xsl:apply-templates select="@*" />
		<xsl:apply-templates />
	</xsl:template>

	<xsl:function name="my:tokenizePackageName">
		<xsl:param name="string" />

		<xsl:variable name="packageName">
			<xsl:analyze-string select="$string" regex="(.*)_[^_]*">
				<xsl:matching-substring>
					<xsl:value-of select="regex-group(1)" />
				</xsl:matching-substring>
			</xsl:analyze-string>
		</xsl:variable>
		<xsl:copy-of select="tokenize(translate($packageName,':','_'),'_')" />
	</xsl:function>

	<xsl:function name="my:tokenizeAssoName">
		<xsl:param name="string" />
		<xsl:param name="context" />
		<xsl:copy-of
			select="my:tokenizePackageName(my:getAssoNames($string,$context)/source/@name)" />
	</xsl:function>



	<xsl:function name="my:getAssoNames">
		<xsl:param name="string" />
		<xsl:param name="context" />
		<xsl:variable name="source">
			<xsl:for-each select="$context">
				<xsl:if test="starts-with($string, .)">
					<xsl:value-of select="." />
				</xsl:if>
			</xsl:for-each>
		</xsl:variable>
		<xsl:variable name="target">
			<xsl:for-each select="$context">
				<xsl:if test="ends-with($string, .)">
					<xsl:value-of select="." />
				</xsl:if>
			</xsl:for-each>
		</xsl:variable>
		<xsl:variable name="assoName"
			select="substring-before(substring-after($string, concat($source,'_')), concat('_',$target))" />
		<names name="{$assoName}">
			<source name="{$source}" />
			<target name="{$target}" />
		</names>
	</xsl:function>
</xsl:stylesheet>