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
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:clazz="http://www.kerblue.org/class/1.0"
	xmlns:xmi="http://www.omg.org/XMI"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:d="http://www.alfresco.org/model/dictionary/1.0" >

	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>	

	<xsl:template match="/">
		<clazz:Model xmi:version="2.0" name="models">
			<xsl:apply-templates select="models/d:model"/>
		</clazz:Model>
	</xsl:template>
	
	<xsl:template match="d:model">
		<xsl:variable name="uri" select="d:namespaces/d:namespace/@uri"/>
		<xsl:variable name="current_model" select="@name"/>
		<clazz:Model xmi:version="2.0" xmi:id="{math:random()}" xmlns:math="java:java.lang.Math" name="{substring-before(@name,':')}">
			<xsl:apply-templates select="d:constraints/d:constraint"/>				
			<xsl:apply-templates select="d:types/d:type">
				<xsl:with-param name="current_model" select="$current_model"/>
				<xsl:with-param name="uri" select="$uri"/>
			</xsl:apply-templates>
			<xsl:apply-templates select="d:aspects/d:aspect"/> 
		</clazz:Model>
	</xsl:template>
	
	<xsl:template match="d:constraint">
		<xsl:variable name="name" select="@name"/>
		<xsl:if test="d:parameter/d:list">
			<enumerationSet xmlns:math="java:java.lang.Math" xmi:id="{math:random()*1000000}" name="{substring-after($name,':')}">
				<xsl:for-each select="d:parameter/d:list/d:value">
					<literals xmlns:math="java:java.lang.Math" xmi:id="{math:random()*1000000}" name="{text()}"/>
				</xsl:for-each>
			</enumerationSet>
		</xsl:if>
	</xsl:template>
	
	<xsl:template match="d:type">
		<xsl:param name="current_model"/>
		<xsl:param name="uri"/>
		<xsl:variable name="aspects">
			<xsl:for-each select="d:mandatory-aspects/d:aspect">
				<xsl:call-template name="add_aspects_attribute"/>
			</xsl:for-each>
		</xsl:variable>

		<classSet xmi:id="{math:random()}" xmlns:math="java:java.lang.Math" documentation="{d:documentation/text()}" description="{d:description/text()}" 
			name="{substring-after(@name,':')}" title="{d:title/text()}" aspects="{$aspects}">
			<xsl:apply-templates select="d:parent">
				<xsl:with-param name="current_model" select="$current_model"/>
				<xsl:with-param name="type">classSet</xsl:with-param>
			</xsl:apply-templates>
			<tags xmi:id="{math:random()}" key="reversedURI" value="{$uri}"/> 
			<xsl:apply-templates select="d:properties/d:property"/>
		</classSet> 
		<xsl:apply-templates select="d:associations/d:child-association|d:associations/d:association">
			<xsl:with-param name="current_model" select="$current_model"/>
			<xsl:with-param name="source_type">classSet</xsl:with-param>
			<xsl:with-param name="source_position" select="position()-1"></xsl:with-param>
		</xsl:apply-templates> 		
	</xsl:template>
	
	<xsl:template match="d:parent">
		<xsl:param name="current_model"/>
		<xsl:param name="type"/>
		<xsl:variable name="parent_name" select="text()"/>
	
		<xsl:for-each select="//d:model[@name=$current_model]/d:types/d:type[@name=$parent_name]">
			<xsl:variable name="position" select="count(preceding-sibling::*)"/>
			<xsl:attribute name="generalizations">//@classSet.<xsl:value-of select="$position"/></xsl:attribute>
		</xsl:for-each>
		<xsl:for-each select="//d:model[@name=$current_model]/d:aspects/d:aspect[@name=$parent_name]">
			<xsl:variable name="position" select="count(preceding-sibling::*)"/>
			<xsl:attribute name="generalizations">//@aspectSet.<xsl:value-of select="$position"/></xsl:attribute>
		</xsl:for-each>
		<xsl:if test="not(//d:model[@name=$current_model]/d:types/d:type[@name=$parent_name]) and not(//d:model[@name=$current_model]/d:aspects/d:aspect[@name=$parent_name])">
			<xsl:call-template name="find_ext_ref_generalizations">
				<xsl:with-param name="parent_name" select="$parent_name"/>
			</xsl:call-template>
		</xsl:if>		
	</xsl:template>
	
	<xsl:template name="find_ext_ref_generalizations">
		<xsl:param name="parent_name"/>
		<xsl:for-each select="//d:model[starts-with(@name,concat(substring-before($parent_name,':'),':'))]/d:types/d:type[@name=$parent_name]">
			<xsl:variable name="position" select="count(preceding-sibling::*)"/>
			<generalizations xmi:id="{math:random()}" xmlns:math="java:java.lang.Math" href="{concat(substring-before($parent_name,':'),'.dt')}#//@classSet.{$position}"/>
		</xsl:for-each>
		<xsl:for-each select="//d:model[starts-with(@name,concat(substring-before($parent_name,':'),':'))]/d:aspects/d:aspect[@name=$parent_name]">
			<xsl:variable name="position" select="count(preceding-sibling::*)"/>
			<generalizations xmi:id="{math:random()}" xmlns:math="java:java.lang.Math" href="{concat(substring-before($parent_name,':'),'.dt')}#//@aspectSet.{$position}"/>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="add_aspects_attribute">
		<xsl:if test="//d:aspects/d:aspect/@name=text()">
			<xsl:variable name="aspect_position"><xsl:number value="position()-1"/></xsl:variable>
			<xsl:variable name="aspect_link">//@aspectSet.<xsl:value-of select="$aspect_position"/></xsl:variable>
			<xsl:copy-of select="concat($aspect_link,' ')"></xsl:copy-of>
		</xsl:if>
	</xsl:template>
	
	<xsl:template match="d:aspect">
		<aspectSet xmi:id="{math:random()}" xmlns:math="java:java.lang.Math" name="{substring-after(@name,':')}" title="{d:title/text()}"
			description="{d:description/text()}" documentation="{d:documentation/text()}">
			<xsl:apply-templates select="d:parent">
				<xsl:with-param name="type">aspectSet</xsl:with-param>
			</xsl:apply-templates>
			<xsl:apply-templates select="d:properties/d:property"/>
		</aspectSet>
		<xsl:apply-templates select="d:associations/d:child-association|d:associations/d:association">
			<xsl:with-param name="source_type">aspectSet</xsl:with-param>
			<xsl:with-param name="source_position" select="position()-1"></xsl:with-param>
		</xsl:apply-templates> 
	</xsl:template>
	
	<xsl:template match="d:property">
		<xsl:variable name="type">
			<xsl:call-template name="type2bluexml">
				<xsl:with-param name="value">
					<xsl:value-of select="d:type/text()"/>
				</xsl:with-param>
			</xsl:call-template>
		</xsl:variable>	
		<xsl:variable name="value_list">
			<xsl:call-template name="get_ref_list"/>
		</xsl:variable>
		<attributes xmi:id="{math:random()}" xmlns:math="java:java.lang.Math" name="{substring-after(@name,':')}" title="{d:title/text()}" visibility="Public" typ="{$type}"
			 description="{d:description/text()}" documentation="{d:documentation/text()}" valueList="{$value_list}">
			 <tags xmi:id="{math:random()}" key="reversed" value="alfresco" />
		</attributes>
	</xsl:template>
	
	<xsl:template name="get_ref_list">
		<xsl:if test="d:constraints/d:constraint">
			<xsl:value-of select="substring-after(d:constraints/d:constraint/@ref,':')"/>	
		</xsl:if>
	</xsl:template>
	
	<xsl:template match="d:child-association|d:association">
		<xsl:param name="current_model"/>
		<xsl:param name="source_type"></xsl:param>
		<xsl:param name="source_position"></xsl:param>
		
		<xsl:variable name="target_name" select="d:target/d:class/text()"></xsl:variable>
		<xsl:element name="associationSet">
			<xsl:attribute name="xmi:id" select="math:random()" xmlns:math="java:java.lang.Math"/>
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:attribute name="description"><xsl:value-of select="d:description/text()"/></xsl:attribute>
			<xsl:attribute name="associationType">
				<xsl:choose>
					<xsl:when test="name()='child-association'">Composition</xsl:when>
					<xsl:otherwise>Direct</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
		<xsl:element name="firstEnd">
			<xsl:attribute name="xmi:id" select="math:random()" xmlns:math="java:java.lang.Math"/>
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
			<xsl:attribute name="linkedClass">//@<xsl:value-of select="$source_type"></xsl:value-of>.<xsl:number value="$source_position" format="1"></xsl:number></xsl:attribute>
		</xsl:element>
		<xsl:element name="secondEnd">
			<xsl:attribute name="xmi:id" select="math:random()" xmlns:math="java:java.lang.Math"/>
			<xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="d:title/text()"/></xsl:attribute>
			<xsl:attribute name="navigable">true</xsl:attribute>
			<xsl:choose>
				<xsl:when test="d:target/d:many/text() = 'true'">
					<xsl:attribute name="cardMax">-1</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="cardMax">1</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:for-each select="//d:model[@name=$current_model]/d:types/d:type[@name=$target_name]">
				<xsl:variable name="position" select="count(preceding-sibling::*)"/>
				<xsl:attribute name="linkedClass">//@classSet.<xsl:value-of select="$position"/></xsl:attribute>
			</xsl:for-each>
			<xsl:for-each select="//d:model[@name=$current_model]/d:aspects/d:aspect[@name=$target_name]">
				<xsl:variable name="position" select="count(preceding-sibling::*)"/>
				<xsl:attribute name="linkedClass">//@aspectSet.<xsl:value-of select="$position"/></xsl:attribute>
			</xsl:for-each>
			<xsl:if test="not(//d:model[@name=$current_model]/d:types/d:type[@name=$target_name]) and not(//d:model[@name=$current_model]/d:aspects/d:aspect[@name=$target_name])">
				<xsl:call-template name="find_ext_ref_assocs">
					<xsl:with-param name="target_name" select="$target_name"/>
				</xsl:call-template>
			</xsl:if>
		</xsl:element>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="find_ext_ref_assocs">
		<xsl:param name="target_name"/>
		<xsl:for-each select="//d:model[starts-with(@name,concat(substring-before($target_name,':'),':'))]/d:types/d:type[@name=$target_name]">
			<xsl:variable name="position" select="count(preceding-sibling::*)"/>
			<linkedClass xmi:id="{math:random()}" xmlns:math="java:java.lang.Math" xsi:type="clazz:Clazz"
				href="{concat(substring-before($target_name,':'),'.dt')}#//@classSet.{$position}"/>
		</xsl:for-each>
		<xsl:for-each select="//d:model[starts-with(@name,concat(substring-before($target_name,':'),':'))]/d:aspects/d:aspect[@name=$target_name]">
			<xsl:variable name="position" select="count(preceding-sibling::*)"/>
			<linkedClass xmi:id="{math:random()}" xmlns:math="java:java.lang.Math" xsi:type="clazz:Aspect"
				href="{concat(substring-before($target_name,':'),'.dt')}#//@aspectSet.{$position}"/>
		</xsl:for-each>
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
