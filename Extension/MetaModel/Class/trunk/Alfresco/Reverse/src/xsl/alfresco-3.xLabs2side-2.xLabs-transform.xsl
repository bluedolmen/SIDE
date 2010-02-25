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
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:clazz="http://www.kerblue.org/class/1.0"	xmlns:xmi="http://www.omg.org/XMI"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:d="http://www.alfresco.org/model/dictionary/1.0">

	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>	

	<xsl:template match="/">
		<xsl:apply-templates select="d:model"/>
	</xsl:template>
	
	<xsl:template match="d:model">
		<xsl:element name="clazz:Model">
			<xsl:attribute name="xmi:version">2.0</xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>				
			<xsl:apply-templates select="d:types/d:type"/> 
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="d:type">
		<xsl:element name="classSet">
			<xsl:attribute name="documentation"><xsl:value-of select="d:documentation/text()"/></xsl:attribute>
			<xsl:attribute name="description"><xsl:value-of select="d:description/text()"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:apply-templates select="d:parent"/> 
			<xsl:apply-templates select="d:properties/d:property"/> 
		</xsl:element>
		<xsl:apply-templates select="d:mandatory-aspects/d:aspect"/> 
		<xsl:apply-templates select="d:associations/d:child-association|d:association">
			<xsl:with-param name="source_position" select="position()-1"></xsl:with-param>
		</xsl:apply-templates> 		
	</xsl:template>
	
	<xsl:template match="d:parent">
	<xsl:variable name="parent_name" select="text()" />
	<xsl:choose>
		<xsl:when test="//d:types/d:type/@name=$parent_name">
			<xsl:element name="generalizations">
				<xsl:attribute name="href">#//@classSet.<xsl:number value="position()-1" format="1" /></xsl:attribute>
				<xsl:attribute name="abstract">true</xsl:attribute>
			</xsl:element>
		</xsl:when>
		<xsl:otherwise>
			<xsl:element name="hasComments">
				<xsl:attribute name="value">This class inherits from <xsl:value-of select="$parent_name" /> wich is in another Alfresco model.</xsl:attribute>
			</xsl:element>
		</xsl:otherwise>
	</xsl:choose>
	</xsl:template>
	
	<xsl:template match="d:aspect">
		<xsl:element name="aspectSet">
			<xsl:attribute name="name"><xsl:value-of select="substring-after(text(),':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:attribute name="description"><xsl:value-of select="d:description/text()"/></xsl:attribute>
			<xsl:apply-templates select="d:properties/d:property"/>
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="d:property">
		<xsl:element name="attributes">
			<xsl:attribute name="documentation"><xsl:value-of select="d:documentation/text()"/></xsl:attribute>
			<xsl:attribute name="description"><xsl:value-of select="d:description/text()"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:attribute name="visibility">Public</xsl:attribute>
			<xsl:attribute name="typ">
				<xsl:call-template name="type2bluexml">
					<xsl:with-param name="value">
						<xsl:value-of select="d:type/text()"/>
					</xsl:with-param>
				</xsl:call-template>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="d:child-association|d:association">
		<xsl:param name="source_position"></xsl:param>
		<xsl:variable name="target_name" select="d:target/d:class/text()"></xsl:variable>
		<xsl:element name="associationSet">
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:attribute name="description"><xsl:value-of select="description/text()"/></xsl:attribute>
			<xsl:attribute name="associationType">
				<xsl:choose>
					<xsl:when test="name()='child-association'">Composition</xsl:when>
					<xsl:otherwise>Direct</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:element name="firstEnd">
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:choose>
				<xsl:when test="d:source/d:many/text() = 'true'">
					<xsl:attribute name="cardMax">-1</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="cardMax">1</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:attribute name="linkedClass">//@classSet.<xsl:number value="$source_position" format="1"></xsl:number></xsl:attribute>
		</xsl:element>
		<xsl:element name="secondEnd">
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:choose>
				<xsl:when test="d:target/d:many/text() = 'true'">
					<xsl:attribute name="cardMax">-1</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="cardMax">1</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:choose>
				<xsl:when test="//d:types/d:type/@name=$target_name">
					<xsl:attribute name="linkedClass">//@classSet.<xsl:number value="position()-1" format="1"></xsl:number></xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="linkedClass"/>
					<xsl:element name="hasComments">
						<xsl:attribute name="value">This association has <xsl:value-of select="$target_name"/> as target wich depends of another Alfresco model.</xsl:attribute>
					</xsl:element>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
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
