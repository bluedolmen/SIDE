<?xml version="1.0" encoding="UTF-8"?>
<!--
	This file is part of SIDE-Labs/Reverse.

	Copyright (C) 2009  BlueXML (http://www.bluexml.com)

    SIDE-Labs/Reverse is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SIDE-Labs/Reverse is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
-->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:MMUseCase="http://MMUseCase"	xmlns:xmi="http://www.omg.org/XMI"
	xmlns:d="http://www.alfresco.org/model/dictionary/1.0">

	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>	

	<xsl:template match="/">
		<xsl:apply-templates select="d:model"/>
		<xsl:apply-templates select="models"/>
	</xsl:template>
	
	<xsl:template match="models">
		<xsl:element name="MMUseCase:Package">
			<xsl:attribute name="xmi:version">2.0</xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="generate-id(.)"/></xsl:attribute>
			<xsl:attribute name="name">all</xsl:attribute>
			<xsl:for-each select="d:model">
				<xsl:element name="packageSet">
					<xsl:attribute name="xmi:version">2.0</xsl:attribute>
					<xsl:attribute name="xmi:id"><xsl:value-of select="generate-id(.)"/></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="substring-before(@name,':')"/></xsl:attribute>
					<xsl:attribute name="documentation"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
					<xsl:apply-templates select="d:types/d:type"/>
					<xsl:apply-templates select="d:aspects/d:aspect"/>
					<xsl:for-each select=".//d:association|.//d:child-association">
						<xsl:apply-templates select="."/>
					</xsl:for-each>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="d:model">
		<xsl:element name="MMUseCase:Package">
			<xsl:attribute name="xmi:version">2.0</xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="generate-id(.)"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="substring-before(@name,':')"/></xsl:attribute>
			<xsl:attribute name="documentation"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:for-each select="//d:association|//d:child-association">
				<xsl:apply-templates select="."/>
			</xsl:for-each>
			<xsl:apply-templates select="d:types/d:type"/>
			<xsl:apply-templates select="d:aspects/d:aspect"/>
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="d:type">
		<xsl:element name="classSet">
			<xsl:attribute name="xmi:id"><xsl:value-of select="generate-id(.)"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:attribute name="description"><xsl:value-of select="d:description/text()"/></xsl:attribute>
			<xsl:attribute name="aspects">
				<xsl:for-each select="d:mandatory-aspects/d:aspect">
					<xsl:variable name="name"><xsl:value-of select="text()"/></xsl:variable>
					<xsl:copy-of select="concat(generate-id(//d:aspect[@name=$name]),' ')"/>
				</xsl:for-each>
			</xsl:attribute>
			<xsl:attribute name="associations">
				<xsl:for-each select="d:associations/d:child-association">
					<xsl:variable name="name"><xsl:value-of select="@name"/></xsl:variable>
					<xsl:value-of select="concat(generate-id(//d:child-association[@name=$name]),' ')"/>					
				</xsl:for-each>
				<xsl:for-each select="d:associations/d:association">
					<xsl:variable name="name"><xsl:value-of select="@name"/></xsl:variable>
					<xsl:value-of select="concat(generate-id(//d:association[@name=$name]),' ')"/>					
				</xsl:for-each>
			</xsl:attribute>
			<xsl:apply-templates select="d:parent"/>
			<xsl:apply-templates select="d:properties/d:property"/>
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="d:parent">
		<xsl:variable name="prefix"><xsl:value-of select="substring-before(ancestor::d:model/@name,':')"/></xsl:variable>
		<xsl:variable name="name"><xsl:value-of select="text()"/></xsl:variable>
		<generalizations xmi:id="{generate-id(.)}">
			<xsl:attribute name="Target"><xsl:value-of select="generate-id(//d:type[@name=$name])"/></xsl:attribute>
		</generalizations>
	</xsl:template>
	
	<xsl:template match="d:aspect">
		<xsl:element name="aspectSet">
			<xsl:attribute name="xmi:id"><xsl:value-of select="generate-id(.)"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:attribute name="description"><xsl:value-of select="d:description/text()"/></xsl:attribute>
			<xsl:apply-templates select="d:properties/d:property"/>
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="d:property">
		<xsl:element name="attributes">
			<xsl:attribute name="xmi:id"><xsl:value-of select="generate-id(.)"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:attribute name="description"><xsl:value-of select="d:description/text()"/></xsl:attribute>
			<xsl:attribute name="typ">
				<xsl:call-template name="type2bluexml">
					<xsl:with-param name="value">
						<xsl:value-of select="d:type/text()"/>
					</xsl:with-param>
				</xsl:call-template>
			</xsl:attribute>
			<xsl:apply-templates select="d:mandatory"/>
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="d:mandatory">
		<metainfo xmi:id="{generate-id(.)}" value="True" key="required"/>
	</xsl:template>
	
	<xsl:template match="d:child-association|d:association">
		<xsl:element name="associationSet">
			<xsl:attribute name="xmi:id"><xsl:value-of select="generate-id(.)"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:attribute name="description"><xsl:value-of select="d:description/text()"/></xsl:attribute>
			<xsl:attribute name="source"><xsl:value-of select="generate-id(parent::d:associations/parent::d:aspect)"/><xsl:value-of select="generate-id(parent::d:associations/parent::d:type)"/></xsl:attribute>
			<xsl:variable name="target"><xsl:value-of select="d:target/d:class/text()"/></xsl:variable>
			<xsl:attribute name="destination"><xsl:value-of select="generate-id(//d:type[@name=$target])"/></xsl:attribute>
			<xsl:attribute name="isNavigableTARGET">true</xsl:attribute>
			<xsl:attribute name="associationType">
				<xsl:choose>
					<xsl:when test="name()='child-association'">Composition</xsl:when>
					<xsl:otherwise>Direct</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:choose>
				<xsl:when test="d:source/d:mandatory/text() = 'true'">
					<xsl:attribute name="minSRC">1</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="minSRC">0</xsl:attribute>					
				</xsl:otherwise>
			</xsl:choose>
			<xsl:choose>
				<xsl:when test="d:source/d:many/text() = 'true'">
					<xsl:attribute name="maxSRC">-1</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="maxSRC">1</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:choose>
				<xsl:when test="d:target/d:mandatory/text() = 'true'">
					<xsl:attribute name="minTARGET">1</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="minTARGET">0</xsl:attribute>					
				</xsl:otherwise>
			</xsl:choose>
			<xsl:choose>
				<xsl:when test="d:target/d:many/text() = 'true'">
					<xsl:attribute name="maxTARGET">-1</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="maxTARGET">1</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="type2bluexml">
		<xsl:param name="value"/>
		<xsl:choose>
			<xsl:when test="$value='d:text'">String</xsl:when>
			<xsl:when test="$value='d:mltext'">String</xsl:when>
			<xsl:when test="$value='d:content'">Object</xsl:when>
			<xsl:when test="$value='d:int'">int</xsl:when>
			<xsl:when test="$value='d:long'">long</xsl:when>
			<xsl:when test="$value='d:float'">float</xsl:when>
			<xsl:when test="$value='d:double'">double</xsl:when>
			<xsl:when test="$value='d:date'">Date</xsl:when>
			<xsl:when test="$value='d:datetime'">Date</xsl:when>
			<xsl:when test="$value='d:boolean'">boolean</xsl:when>
			<xsl:when test="$value='d:qname'">Object</xsl:when>
			<xsl:when test="$value='d:category'">String</xsl:when>
			<xsl:when test="$value='d:noderef'">Object</xsl:when>
			<xsl:when test="$value='d:path'">Object</xsl:when>
			<xsl:when test="$value='d:any'">Object</xsl:when>
			<xsl:otherwise>Object</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
</xsl:stylesheet>
