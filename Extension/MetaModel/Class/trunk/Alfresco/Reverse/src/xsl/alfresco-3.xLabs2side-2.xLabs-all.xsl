<?xml version="1.0" encoding="UTF-8"?>
    <!--
        This file is part of SIDE-Labs/Reverse. Copyright (C) 2009 BlueXML (http://www.bluexml.com) SIDE-Labs/Reverse is free software: you can redistribute it and/or modify it under the terms of the
        GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. SIDE-Labs/Reverse is distributed in the hope
        that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
        You should have received a copy of the GNU General Public License along with Foobar. If not, see <http://www.gnu.org/licenses/>.
    -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:clazz="http://www.kerblue.org/class/1.0"
    xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:d="http://www.alfresco.org/model/dictionary/1.0" xmlns:math="java:java.lang.Math" xmlns:my="http://bluexml.com">
    <xsl:output method="xml" encoding="UTF-8" indent="yes" />
    <xsl:param name="enum_prefix" select="'enum_'"></xsl:param>
    <xsl:template match="/">
        <clazz:Model xmi:version="2.0" name="models">
            <xsl:apply-templates select="models/d:model" />
        </clazz:Model>
    </xsl:template>
    <xsl:template match="d:model">
        <xsl:variable name="current_model" select="@name" />
        <xsl:variable name="prefix" select="substring-before(@name,':')" />
        <clazz:Model xmi:version="2.0" xmi:id="{math:random()}" name="{$prefix}">
            <xsl:attribute name="namespace">
			<xsl:if test="not(starts-with(//d:namespaces/d:namespace[@prefix=$prefix]/@uri, 'http://www.bluexml.com/model/content/'))">
				<xsl:value-of select="//d:namespaces/d:namespace[@prefix=$prefix]/@uri" />
			</xsl:if>
			</xsl:attribute>
            <xsl:apply-templates select="d:constraints/d:constraint" />
            <xsl:call-template name="extract_enum">
                <xsl:with-param name="model_name" select="$current_model" />
                <xsl:with-param name="element_type" select="d:types/d:type" />
            </xsl:call-template>
            <xsl:call-template name="extract_enum">
                <xsl:with-param name="model_name" select="$current_model" />
                <xsl:with-param name="element_type" select="d:aspects/d:aspect" />
            </xsl:call-template>
            <xsl:apply-templates select="d:types/d:type">
                <xsl:with-param name="current_model" select="$current_model" />
            </xsl:apply-templates>
            <xsl:apply-templates select="d:aspects/d:aspect">
                <xsl:with-param name="current_model" select="$current_model" />
            </xsl:apply-templates>
        </clazz:Model>
    </xsl:template>
    <xsl:template name="get_uri">
        <xsl:param name="prefix" />
        <xsl:value-of select="//d:namespaces/d:namespace[@prefix=$prefix]/@uri" />
    </xsl:template>
    <xsl:template match="d:constraint">
        <xsl:param name="current_model" />
        <xsl:variable name="name" select="substring-after(@name,':')" />
        <xsl:variable name="prefix" select="substring-after(@name,':')" />
        <xsl:if test="@type = 'LIST' and d:parameter/d:list">
            <enumerationSet xmi:id="{math:random()*1000000}" name="{$prefix}">
                <xsl:for-each select="d:parameter/d:list/d:value">
                    <literals xmi:id="{math:random()*1000000}" name="{normalize-space(text())}" />
                </xsl:for-each>
            </enumerationSet>
        </xsl:if>
        <xsl:if test="@type[. != 'LIST' and . != 'LENGTH' and . != 'REGEX' and not(ends-with(@ref, 'constraint:mail'))]">
            <!-- other global constraints -->
            <xsl:element name="constraintSet">
                <xsl:attribute name="xmi:id" select="math:random()*1000000" />
                <xsl:attribute name="description" select="''" />
                <xsl:attribute name="name" select="$name" />
                <xsl:attribute name="constraintType" select="@type" />
                <xsl:for-each select="d:parameter">
                    <xsl:element name="params">
                        <xsl:attribute name="xmi:id" select="math:random()*1000000" />
                        <xsl:attribute name="name" select="@name" />
                        <xsl:for-each select=".//value">
                            <xsl:element name="values">
                                <xsl:value-of select="text()" />
                            </xsl:element>
                        </xsl:for-each>
                    </xsl:element>
                </xsl:for-each>
            </xsl:element>
        </xsl:if>
    </xsl:template>
    <xsl:template name="extract_enum">
        <xsl:param name="model_name" />
        <xsl:param name="element_type" />
        <xsl:variable name="property_list" select="//d:model[@name=$model_name]/$element_type/d:properties/d:property" />
        <xsl:for-each select="$property_list/d:constraints/d:constraint">
            <xsl:variable name="property_name" select="./../../@name" />
            <xsl:variable name="enum_name">
                <xsl:choose>
                    <xsl:when test="@name and @name!=''">
                        <xsl:value-of select="@name" />
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="$enum_prefix" />
                        <xsl:value-of select="concat(substring-after($property_name,':'),generate-id(.))" />
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:variable>
            <xsl:if test="@type = 'LIST' and d:parameter/d:list">
                <enumerationSet xmi:id="{math:random()*1000000}" name="{$enum_name}">
                    <xsl:for-each select="d:parameter/d:list/d:value">
                        <literals xmi:id="{math:random()*1000000}" name="{normalize-space(text())}" />
                    </xsl:for-each>
                </enumerationSet>
            </xsl:if>
            <xsl:if test="@type[. != 'LIST' and . != 'LENGTH' and . != 'REGEX' and not(ends-with(@ref, 'constraint:mail'))]">
                <!-- other global constraints -->
                <xsl:element name="constraintSet">
                    <xsl:attribute name="xmi:id" select="math:random()*1000000" />
                    <xsl:attribute name="description" select="''" />
                    <xsl:attribute name="name" select="$enum_name" />
                    <xsl:attribute name="constraintType" select="@type" />
                    <xsl:for-each select="d:parameter">
                        <xsl:element name="params">
                            <xsl:attribute name="xmi:id" select="math:random()*1000000" />
                            <xsl:attribute name="name" select="@name" />
                            <xsl:for-each select=".//value">
                                <xsl:element name="values">
                                    <xsl:value-of select="text()" />
                                </xsl:element>
                            </xsl:for-each>
                        </xsl:element>
                    </xsl:for-each>
                </xsl:element>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="d:type">
        <xsl:param name="current_model" />
        <classSet xmi:id="{math:random()}" documentation="{d:documentation/text()}" description="{d:description/text()}" name="{substring-after(@name,':')}" title="{normalize-space(d:title/text())}">
            <xsl:call-template name="has_aspects">
                <xsl:with-param name="current_model" select="$current_model" />
            </xsl:call-template>
            <xsl:call-template name="parent">
                <xsl:with-param name="current_model" select="$current_model" />
                <xsl:with-param name="type" select="'classSet'" />
            </xsl:call-template>
            <xsl:variable name="prefix" select="substring-before(@name,':')" />
            <xsl:variable name="uri">
                <xsl:call-template name="get_uri">
                    <xsl:with-param name="prefix" select="$prefix" />
                </xsl:call-template>
            </xsl:variable>
            <tags xmi:id="{math:random()}" key="reversedURI" value="{$uri}" />
            <tags xmi:id="{math:random()}" key="prefix" value="{$prefix}" />

            <!-- manage external ref as elements instead of attribute -->
            <xsl:call-template name="parent_extra">
                <xsl:with-param name="current_model" select="$current_model" />
                <xsl:with-param name="type" select="'classSet'" />
            </xsl:call-template>
            <xsl:call-template name="has_aspects_extra">
                <xsl:with-param name="current_model" select="$current_model" />
            </xsl:call-template>
            <xsl:apply-templates select="d:properties/d:property">
                <xsl:with-param name="current_model" select="$current_model" />
            </xsl:apply-templates>
        </classSet>
        <xsl:apply-templates select="d:associations/d:child-association|d:associations/d:association">
            <xsl:with-param name="current_model" select="$current_model" />
            <xsl:with-param name="source_type" select="'classSet'" />
            <xsl:with-param name="source_position" select="position()-1"></xsl:with-param>
        </xsl:apply-templates>
    </xsl:template>
    <xsl:template name="has_aspects">
        <xsl:param name="current_model" />
        <xsl:variable name="toto">
            <xsl:for-each select="d:mandatory-aspects/d:aspect">
                <xsl:call-template name="my:isExternalAspect">
                    <xsl:with-param name="currentModel" select="$current_model" />
                    <xsl:with-param name="aspectName" select="normalize-space(text())" />
                </xsl:call-template>
            </xsl:for-each>
        </xsl:variable>
        <xsl:choose>
            <xsl:when test="count(d:mandatory-aspects/d:aspect) = count($toto//*[name() ='d:aspect'])">
                <!-- use attribute -->
                <xsl:attribute name="aspects">
					<xsl:for-each select="d:mandatory-aspects/d:aspect">
						<xsl:call-template name="add_aspects_attribute" />
					</xsl:for-each>
				</xsl:attribute>
            </xsl:when>
            <xsl:otherwise>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template name="has_aspects_extra">
        <xsl:param name="current_model" />
        <xsl:variable name="toto">
            <xsl:for-each select="d:mandatory-aspects/d:aspect">
                <xsl:call-template name="my:isExternalAspect">
                    <xsl:with-param name="currentModel" select="$current_model" />
                    <xsl:with-param name="aspectName" select="normalize-space(text())" />
                </xsl:call-template>
            </xsl:for-each>
        </xsl:variable>
        <xsl:choose>
            <xsl:when test="count(d:mandatory-aspects/d:aspect) = count($toto//*[name() ='d:aspect'])">
            </xsl:when>
            <xsl:otherwise>
                <!-- external -->
                <xsl:for-each select="d:mandatory-aspects/d:aspect">
                    <xsl:variable name="parent_name" select="normalize-space(text())" />
                    <aspects xsi:type="clazz:Aspect" xmi:id="{math:random()}" href="{concat(substring-before($parent_name,':'),'.dt')}#{$parent_name}" />
                </xsl:for-each>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template name="my:isExternalAspect">
        <xsl:param name="currentModel" />
        <xsl:param name="aspectName" />
        <xsl:copy-of select="//d:model[@name=$currentModel]/d:aspects/d:aspect[@name=$aspectName]" />
    </xsl:template>
    <xsl:template name="parent">
        <xsl:param name="current_model" />
        <xsl:param name="type" />
        <xsl:variable name="parent_name" select="normalize-space(d:parent/text())" />
        <xsl:for-each select="//d:model[@name=$current_model]/d:types/d:type[@name=$parent_name]">
            <xsl:attribute name="generalizations" select="$parent_name"></xsl:attribute>
        </xsl:for-each>
        <xsl:for-each select="//d:model[@name=$current_model]/d:aspects/d:aspect[@name=$parent_name]">
            <xsl:attribute name="generalizations" select="$parent_name"></xsl:attribute>
        </xsl:for-each>
    </xsl:template>
    <xsl:template name="parent_extra">
        <xsl:param name="current_model" />
        <xsl:param name="type" />
        <xsl:variable name="parent_name" select="normalize-space(d:parent/text())" />
        <xsl:if test="not(//d:model[@name=$current_model]/d:types/d:type[@name=$parent_name]) and not(//d:model[@name=$current_model]/d:aspects/d:aspect[@name=$parent_name])">
            <xsl:call-template name="find_ext_ref_generalizations">
                <xsl:with-param name="parent_name" select="$parent_name" />
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
    <xsl:template name="find_ext_ref_generalizations">
        <xsl:param name="parent_name" />
        <xsl:for-each select="//d:model[starts-with(@name,concat(substring-before($parent_name,':'),':'))]/d:types/d:type[@name=$parent_name]">
            <xsl:variable name="position" select="count(preceding-sibling::*)" />
            <generalizations xsi:type="clazz:Clazz" xmi:id="{math:random()}" href="{concat(substring-before($parent_name,':'),'.dt')}#{$parent_name}" />
        </xsl:for-each>
        <xsl:for-each select="//d:model[starts-with(@name,concat(substring-before($parent_name,':'),':'))]/d:aspects/d:aspect[@name=$parent_name]">
            <xsl:variable name="position" select="count(preceding-sibling::*)" />
            <generalizations xsi:type="clazz:Aspect" xmi:id="{math:random()}" href="{concat(substring-before($parent_name,':'),'.dt')}#{$parent_name}" />
        </xsl:for-each>
    </xsl:template>
    <xsl:template name="add_aspects_attribute">
        <xsl:if test="//d:aspects/d:aspect/@name=normalize-space(text())">
            <xsl:variable name="aspect_position">
                <xsl:number value="position()-1" />
            </xsl:variable>
            <xsl:variable name="aspect_link" select="normalize-space(text())" />
            <xsl:copy-of select="concat($aspect_link,' ')"></xsl:copy-of>
        </xsl:if>
    </xsl:template>
    <xsl:template match="d:aspect">
        <xsl:param name="current_model" />
        <aspectSet xmi:id="{math:random()}" name="{substring-after(@name,':')}" title="{normalize-space(d:title/text())}" description="{d:description/text()}" documentation="{d:documentation/text()}">
            <xsl:call-template name="parent">
                <xsl:with-param name="current_model" select="$current_model" />
                <xsl:with-param name="type" select="'classSet'" />
            </xsl:call-template>
            <xsl:variable name="prefix" select="substring-before(@name,':')" />
            <xsl:variable name="uri">
                <xsl:call-template name="get_uri">
                    <xsl:with-param name="prefix" select="$prefix" />
                </xsl:call-template>
            </xsl:variable>
            <tags xmi:id="{math:random()}" key="reversedURI" value="{$uri}" />
            <tags xmi:id="{math:random()}" key="prefix" value="{$prefix}" />
            <xsl:call-template name="parent_extra">
                <xsl:with-param name="current_model" select="$current_model" />
                <xsl:with-param name="type" select="'classSet'" />
            </xsl:call-template>
            <xsl:apply-templates select="d:properties/d:property">
                <xsl:with-param name="current_model" select="$current_model" />
            </xsl:apply-templates>
        </aspectSet>
        <xsl:apply-templates select="d:associations/d:child-association|d:associations/d:association">
            <xsl:with-param name="source_type">
                aspectSet
            </xsl:with-param>
            <xsl:with-param name="source_position" select="position()-1"></xsl:with-param>
        </xsl:apply-templates>
    </xsl:template>
    <xsl:template match="d:property">
        <xsl:param name="current_model" />
        <xsl:variable name="type">
            <xsl:call-template name="type2bluexml">
                <xsl:with-param name="value">
                    <xsl:value-of select="normalize-space(d:type/text())" />
                </xsl:with-param>
            </xsl:call-template>
        </xsl:variable>
<!--        <xsl:call-template name="get_constraints_refs">-->
<!--            <xsl:with-param name="current_model" select="$current_model" />-->
<!--        </xsl:call-template>-->
        <xsl:variable name="value_list">
            <xsl:call-template name="get_constraints_refs">
                <xsl:with-param name="current_model" select="$current_model" />
            </xsl:call-template>
        </xsl:variable>
        <xsl:variable name="prefix" select="substring-before(@name,':')" />
        <xsl:variable name="uri">
            <xsl:call-template name="get_uri">
                <xsl:with-param name="prefix" select="$prefix" />
            </xsl:call-template>
        </xsl:variable>
        <xsl:element name="attributes">
            <xsl:attribute name="xmi:id" select="math:random()" />
            <xsl:attribute name="name" select="substring-after(@name,':')" />
            <xsl:attribute name="visibility" select="'Public'" />
            <xsl:attribute name="title" select="normalize-space(d:title/text())" />
            <xsl:attribute name="documentation" select="normalize-space(d:documentation/text())" />
            <xsl:attribute name="typ" select="normalize-space($type)" />
            <xsl:attribute name="description" select="normalize-space(d:description/text())" />
            <xsl:if test="normalize-space($value_list/valueList/incurrent) != ''">
                <xsl:attribute name="valueList" select="normalize-space($value_list/valueList/incurrent)" />
            </xsl:if>
            <xsl:if test="normalize-space($value_list/constraints/incurrent) != ''">
                <xsl:attribute name="constraints" select="normalize-space($value_list/constraints/incurrent)" />
            </xsl:if>
            <xsl:if test="./d:default and normalize-space(./d:default/text()) != ''">
                <xsl:attribute name="initialValue" select="normalize-space(./d:default/text())" />
            </xsl:if>
            <tags xmi:id="{math:random()}" key="reversedURI" value="{$uri}" />
            <tags xmi:id="{math:random()}" key="prefix" value="{$prefix}" />
            <xsl:if test="./d:mandatory and normalize-space(./d:mandatory/text()) = 'true'">
                <metainfo xmi:id="{math:random()}" key="required" value="True" />
            </xsl:if>
            <xsl:if test="./d:index and ./d:index/@enabled='true'">
                <metainfo xmi:id="{math:random()}" key="propertySearched" value="True" />
            </xsl:if>
            <xsl:if test="./d:multiple and normalize-space(./d:multiple/text()) = 'true'">
                <metainfo xmi:id="{math:random()}" key="multiple" value="True" />
            </xsl:if>

            <!-- contraints -->
            <xsl:for-each select="d:constraints/d:constraint">
                <xsl:choose>
                    <xsl:when test="@type = 'LENGTH'">
                        <metainfo xmi:id="{math:random()}" key="min-length" value="{normalize-space(parameter[@name='minLength']/value/text())}" />
                        <metainfo xmi:id="{math:random()}" key="max-length" value="{normalize-space(parameter[@name='maxLength']/value/text())}" />
                    </xsl:when>
                    <xsl:when test="@type = 'REGEX'">
                        <metainfo xmi:id="{math:random()}" key="regular-expression" value="{normalize-space(parameter[@name='expression']/value/text())}" />
                    </xsl:when>
                    <xsl:when test="ends-with(@ref, 'constraint:mail')">
                        <metainfo xmi:id="{math:random()}" key="email" value="true" />
                    </xsl:when>
                </xsl:choose>
            </xsl:for-each>
            <!-- only external -->
            <!-- refer or declare a constraint so if refer create link to constraint object if declared Must create new global declaration !! not easy -->
            <xsl:for-each select="tokenize(normalize-space($value_list/valueList/external),' ')">
                <xsl:element name="valueList">
                    <xsl:attribute name="xmi:id" select="math:random()" />
                    <xsl:attribute name="href" select="." />
                </xsl:element>
            </xsl:for-each>
            <xsl:for-each select="tokenize(normalize-space($value_list/constraints/external),' ')">
                <xsl:element name="constraints">
                    <xsl:attribute name="xmi:id" select="math:random()" />
                    <xsl:attribute name="href" select="." />
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
    <xsl:template name="get_constraints_refs">
        <xsl:param name="current_model" />
        <xsl:variable name="refs">
            <refs>
                <xsl:variable name="property_name" select="@name" />
                <xsl:for-each select="d:constraints/d:constraint">
                    <xsl:choose>
                        <xsl:when test="@ref">
                            <xsl:variable name="ref" select="@ref" />
                            <xsl:variable name="constraint" select="//d:model/d:constraints/d:constraint[@name = $ref]" />
                            <xsl:variable name="prefix" select="substring-before($ref,':')" />
                            <xsl:variable name="prefix_model" select="substring-before($current_model,':')" />
                            <xsl:variable name="refType">
                                <xsl:choose>
                                    <xsl:when test="$prefix = $prefix_model">
                                        <xsl:text>in</xsl:text>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:text>ext</xsl:text>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:choose>
                                <xsl:when test="$constraint/@type = 'LIST'">
                                    <!-- enum -->
                                    <valueList name="{$ref}" refType="{$refType}" />
                                </xsl:when>
                                <xsl:when test="$constraint/@type != 'LENGTH' and $constraint/@type != 'REGEX' and not(ends-with($ref, 'constraint:mail'))">
                                    <!-- constraints -->
                                    <constraint name="{$ref}" refType="{$refType}" />
                                </xsl:when>
<!--                                                                <xsl:otherwise>-->
<!--                                                                <other>ref :<xsl:value-of select="$ref" /> <xsl:copy-of select="$constraint"/></other>-->
<!--                                                                </xsl:otherwise>-->
                            </xsl:choose>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:variable name="enum_name">
                                <xsl:choose>
                                    <xsl:when test="@name and @name!=''">
                                        <xsl:value-of select="@name" />
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="$enum_prefix" />
                                        <xsl:value-of select="concat(substring-after($property_name,':'),generate-id(.))" />
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:variable name="prefix" select="substring-before($enum_name,':')" />
                            <xsl:variable name="prefix_model" select="substring-before($current_model,':')" />
                            <xsl:variable name="refType">
                                <xsl:choose>
                                    <xsl:when test="$prefix = $prefix_model">
                                        <xsl:text>in</xsl:text>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:text>ext</xsl:text>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:choose>
                                <xsl:when test="@type = 'LIST'">
                                    <!-- enum -->
                                    <valueList name="{$enum_name}" refType="{$refType}" />
                                </xsl:when>
                                <xsl:when test="@type != 'LENGTH' and @type != 'REGEX' and not(ends-with($enum_name, 'constraint:mail'))">
                                    <!-- constraints -->
                                    <constraint name="{$enum_name}" refType="{$refType}" />
                                </xsl:when>
                                <!--                                <xsl:otherwise>-->
                                <!--                                <other>ref :<xsl:value-of select="$ref" /> <xsl:copy-of select="$constraint"/></other>-->
                                <!--                                </xsl:otherwise>-->
                            </xsl:choose>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:for-each>
            </refs>
        </xsl:variable>
<!--                <xsl:copy-of select="$refs"></xsl:copy-of>-->
        <constraints>
            <xsl:choose>
                <xsl:when test="count($refs/refs/constraint[@refType ='ext']) > 0">
                    <external>
                        <xsl:for-each select="$refs/refs/constraint">
                            <xsl:variable name="prefix" select="substring-before(@name,':')" />
                            <xsl:variable name="fileRef" select="concat($prefix, '.dt#')"></xsl:variable>
                            <xsl:value-of select="concat($fileRef, @name, ' ')" />
                        </xsl:for-each>
                    </external>
                </xsl:when>
                <xsl:otherwise>
                    <incurrent>
                        <xsl:for-each select="$refs/refs/constraint[@refType ='in']">
                            <xsl:value-of select="concat(@name,' ')" />
                        </xsl:for-each>
                    </incurrent>
                </xsl:otherwise>
            </xsl:choose>
        </constraints>
        <valueList>
            <xsl:choose>
                <xsl:when test="count($refs/refs/valueList[@refType ='ext']) > 0">
                    <external>
                        <xsl:for-each select="$refs/refs/valueList">
                            <xsl:variable name="prefix" select="substring-before(@name,':')" />
                            <xsl:variable name="fileRef" select="concat($prefix, '.dt#')"></xsl:variable>
                            <xsl:value-of select="concat($fileRef, @name, ' ')" />
                        </xsl:for-each>
                    </external>
                </xsl:when>
                <xsl:otherwise>
                    <incurrent>
                        <xsl:for-each select="$refs/refs/valueList[@refType ='in']">
                            <xsl:value-of select="concat(@name,' ')" />
                        </xsl:for-each>
                    </incurrent>
                </xsl:otherwise>
            </xsl:choose>
        </valueList>
    </xsl:template>
    <xsl:template match="d:child-association|d:association">
        <xsl:param name="current_model" />
        <xsl:param name="source_type"></xsl:param>
        <xsl:param name="source_position"></xsl:param>
        <xsl:variable name="target_name" select="normalize-space(d:target/d:class/text())"></xsl:variable>
        <xsl:element name="associationSet">
            <xsl:attribute name="xmi:id" select="math:random()" />
            <xsl:attribute name="name"><xsl:value-of select="substring-after(@name,':')" /></xsl:attribute>
            <xsl:attribute name="title"><xsl:value-of select="normalize-space(d:title/text())" /></xsl:attribute>
            <xsl:attribute name="description"><xsl:value-of select="d:description/text()" /></xsl:attribute>
            <xsl:attribute name="associationType">
				<xsl:choose>
					<xsl:when test="name()='child-association'">Composition</xsl:when>
					<xsl:otherwise>Direct</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
            <xsl:variable name="srcRole" select="substring-after(d:source/d:role/text(), ':')" />
            <xsl:variable name="tgRole" select="substring-after(d:target/d:role/text(), ':')" />
            <xsl:element name="firstEnd">
                <xsl:attribute name="xmi:id" select="math:random()" />
                <xsl:attribute name="name" select="$srcRole" />
                <xsl:attribute name="title" select="$srcRole" />
                <xsl:choose>
                    <xsl:when test="normalize-space(d:source/d:many/text()) = 'true'">
                        <xsl:attribute name="cardMax">-1</xsl:attribute>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:attribute name="cardMax">1</xsl:attribute>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:attribute name="linkedClass" select="../../@name" />
            </xsl:element>
            <xsl:element name="secondEnd">
                <xsl:attribute name="xmi:id" select="math:random()" />
                <xsl:attribute name="name" select="$tgRole" />
                <xsl:attribute name="title" select="$tgRole" />
                <xsl:attribute name="navigable">true</xsl:attribute>
                <xsl:choose>
                    <xsl:when test="normalize-space(d:target/d:many/text()) = 'true'">
                        <xsl:attribute name="cardMax">-1</xsl:attribute>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:attribute name="cardMax">1</xsl:attribute>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:for-each select="//d:model[@name=$current_model]/d:types/d:type[@name=$target_name]">
                    <xsl:variable name="position" select="count(preceding-sibling::*)" />
                    <xsl:attribute name="linkedClass" select="$target_name" />
                </xsl:for-each>
                <xsl:for-each select="//d:model[@name=$current_model]/d:aspects/d:aspect[@name=$target_name]">
                    <xsl:variable name="position" select="count(preceding-sibling::*)" />
                    <xsl:attribute name="linkedClass" select="$target_name"></xsl:attribute>
                </xsl:for-each>
                <xsl:if test="not(//d:model[@name=$current_model]/d:types/d:type[@name=$target_name]) and not(//d:model[@name=$current_model]/d:aspects/d:aspect[@name=$target_name])">
                    <xsl:call-template name="find_ext_ref_assocs">
                        <xsl:with-param name="target_name" select="$target_name" />
                    </xsl:call-template>
                </xsl:if>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    <xsl:template name="find_ext_ref_assocs">
        <xsl:param name="target_name" />
        <xsl:for-each select="//d:model[starts-with(@name,concat(substring-before($target_name,':'),':'))]/d:types/d:type[@name=$target_name]">
            <xsl:variable name="position" select="count(preceding-sibling::*)" />
            <linkedClass xmi:id="{math:random()}" xsi:type="clazz:Clazz" href="{concat(substring-before($target_name,':'),'.dt')}#{$target_name}" />
        </xsl:for-each>
        <xsl:for-each select="//d:model[starts-with(@name,concat(substring-before($target_name,':'),':'))]/d:aspects/d:aspect[@name=$target_name]">
            <xsl:variable name="position" select="count(preceding-sibling::*)" />
            <linkedClass xmi:id="{math:random()}" xsi:type="clazz:Aspect" href="{concat(substring-before($target_name,':'),'.dt')}#{$target_name}" />
        </xsl:for-each>
    </xsl:template>
    <xsl:template name="type2bluexml">
        <xsl:param name="value" />
        <xsl:choose>
            <xsl:when test="$value='d:text'">
                String
            </xsl:when>
            <xsl:when test="$value='d:mltext'">
                String
            </xsl:when>
            <xsl:when test="$value='d:content'">
                Object
            </xsl:when>
            <xsl:when test="$value='d:int'">
                int
            </xsl:when>
            <xsl:when test="$value='d:long'">
                long
            </xsl:when>
            <xsl:when test="$value='d:float'">
                float
            </xsl:when>
            <xsl:when test="$value='d:double'">
                double
            </xsl:when>
            <xsl:when test="$value='d:date'">
                Date
            </xsl:when>
            <xsl:when test="$value='d:datetime'">
                DateTime
            </xsl:when>
            <xsl:when test="$value='d:boolean'">
                boolean
            </xsl:when>
            <xsl:when test="$value='d:qname'">
                Object
            </xsl:when>
            <xsl:when test="$value='d:category'">
                String
            </xsl:when>
            <xsl:when test="$value='d:noderef'">
                Object
            </xsl:when>
            <xsl:when test="$value='d:path'">
                Object
            </xsl:when>
            <xsl:when test="$value='d:any'">
                Object
            </xsl:when>
            <xsl:otherwise>
                Object
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>