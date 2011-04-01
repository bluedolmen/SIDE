<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://www.omg.org/XMI" xmlns:clazz="http://www.kerblue.org/class/1.0" xmlns:my="http://bluexml.com"
    xmlns:math="java:java.lang.Math">
    <xsl:output method="xml" encoding="UTF-8" indent="yes" />
    <xsl:template match="@valueList">
        <xsl:if test=".!=''">
            <xsl:variable name="value" select="." />
            <xsl:choose>
                <xsl:when test="//enumerationSet[@name=$value]">
                    <xsl:variable name="ref" select="//enumerationSet[@name=$value]" />
                    <xsl:variable name="id" select="$ref/@xmi:id" />
                    <xsl:for-each select="parent::node()">
                        <xsl:attribute name="valueList" select="$id" />
                    </xsl:for-each>
                </xsl:when>
                <xsl:otherwise />
            </xsl:choose>
        </xsl:if>
    </xsl:template>
    <xsl:template match="//generalizations | //linkedClass | //aspects" priority="2">
        <xsl:variable name="fileRef" select="substring-before(@href, '#')" />
        <xsl:variable name="modelID" select="substring-before(substring-after(@href, '#'),':')" />
        <xsl:variable name="targetId" select="substring-after(substring-after(@href, '#'),':')" />
        <xsl:variable name="newRef" select="concat($fileRef,'#',//clazz:Model[@name = $modelID]//*[name() = 'classSet' or name() = 'aspectSet'][@name = $targetId]/@xmi:id)" />
        <xsl:element name="{name()}">
            <xsl:copy-of select="@*" />
            <xsl:attribute name="href" select="$newRef" />
        </xsl:element>
    </xsl:template>
    <xsl:template match="//valueList" priority="2">
        <xsl:variable name="fileRef" select="substring-before(@href, '#')" />
        <xsl:variable name="modelID" select="substring-before(substring-after(@href, '#'),':')" />
        <xsl:variable name="targetId" select="substring-after(substring-after(@href, '#'),':')" />
        <xsl:variable name="newRef" select="concat($fileRef,'#',//clazz:Model[@name = $modelID]//enumerationSet[@name = $targetId]/@xmi:id)" />
        <xsl:element name="{name()}">
            <xsl:copy-of select="@*" />
            <xsl:attribute name="href" select="$newRef" />
        </xsl:element>
    </xsl:template>
    <xsl:template match="//constraints" priority="2">
        <xsl:variable name="fileRef" select="substring-before(@href, '#')" />
        <xsl:variable name="modelID" select="substring-before(substring-after(@href, '#'),':')" />
        <xsl:variable name="targetId" select="substring-after(substring-after(@href, '#'),':')" />
        <xsl:variable name="newRef" select="concat($fileRef,'#',//clazz:Model[@name = $modelID]//constraintSet[@name = $targetId]/@xmi:id)" />
        <xsl:element name="{name()}">
            <xsl:copy-of select="@*" />
            <xsl:attribute name="href" select="$newRef" />
        </xsl:element>
    </xsl:template>
    <xsl:template match="//@generalizations | //@linkedClass">
        <xsl:variable name="attn" select="name()" />
        <xsl:variable name="modelID" select="substring-before(.,':')" />
        <xsl:variable name="targetId" select="substring-after(.,':')" />
        <xsl:variable name="newRef" select="//clazz:Model[@name = $modelID]//*[name() = 'classSet' or name() = 'aspectSet'][@name = $targetId]/@xmi:id" />
        <xsl:for-each select="parent::node()">
            <xsl:attribute name="{$attn}" select="$newRef" />
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="//@aspects">
        <xsl:variable name="tokens" select="tokenize(.,' ')" />
        <xsl:for-each select="parent::node()">
            <xsl:attribute name="aspects">
				<xsl:call-template name="construct_aspects">
                <xsl:with-param name="filter" select="'classSet aspectSet'" />
					<xsl:with-param name="index" select="1" />
					<xsl:with-param name="items" select="$tokens" />
				</xsl:call-template>
			</xsl:attribute>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="//@valueList">
        <xsl:variable name="attn" select="name()" />
        <xsl:variable name="modelID" select="substring-before(.,':')" />
        <xsl:variable name="targetId" select="substring-after(.,':')" />
        <xsl:variable name="newRef" select="//clazz:Model[@name = $modelID]//enumerationSet[@name = $targetId]/@xmi:id" />
        <xsl:for-each select="parent::node()">
            <xsl:attribute name="{$attn}" select="$newRef" />
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="//@constraints">
        <xsl:variable name="tokens" select="tokenize(.,' ')" />
        <xsl:for-each select="parent::node()">
            <xsl:attribute name="constraints">
                <xsl:call-template name="construct_aspects">
                    <xsl:with-param name="filter" select="'constraintSet'" />
                    <xsl:with-param name="index" select="1" />
                    <xsl:with-param name="items" select="$tokens" />
                </xsl:call-template>
            </xsl:attribute>
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template name="construct_aspects">
        <xsl:param name="filter" />
        <xsl:param name="index" />
        <xsl:param name="items" />
        <xsl:param name="concatened" select="''" />
        <xsl:choose>
            <xsl:when test="number($index) &gt; count($items)">
                <xsl:value-of select="normalize-space($concatened)" />
            </xsl:when>
            <xsl:otherwise>
                <xsl:variable name="current" select="$items[$index]" />
                <xsl:variable name="modelID" select="substring-before($current,':')" />
                <xsl:variable name="targetId" select="substring-after($current,':')" />
                <xsl:variable name="test">
                    <xsl:text>[name() = 'classSet' or name() = 'aspectSet']</xsl:text>
                </xsl:variable>
                <xsl:variable name="newRef" select="//clazz:Model[@name = $modelID]//*[my:isEqualsToOneOf(name(),tokenize($filter,' ')) = true()][@name = $targetId]/@xmi:id" />
                <xsl:call-template name="construct_aspects">
                    <xsl:with-param name="filter" select="$filter" />
                    <xsl:with-param name="index" select="number($index) +1" />
                    <xsl:with-param name="items" select="$items" />
                    <xsl:with-param name="concatened" select="concat($concatened,' ',$newRef)"></xsl:with-param>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:function name="my:isEqualsToOneOf">
        <xsl:param name="item" />
        <xsl:param name="list" />
        <xsl:variable name="response">
            <xsl:for-each select="$list">
                <xsl:value-of select=". = $item" />
            </xsl:for-each>
        </xsl:variable>
        <xsl:value-of select="not(contains($response, 'false'))" />
    </xsl:function>
    
    <xsl:template match="classSet[contains(@name, '_')]/@name | aspectSet[contains(@name, '_')]/@name | enumerationSet[contains(@name, '_')]/@name | constraintSet[contains(@name, '_')]/@name">
        <xsl:for-each select="parent::node()[contains(@name, '_')]">
            <xsl:attribute name="name" select="substring-after(@name, concat(../@path,'_'))" />
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template match="attributes[contains(@name, '_')]/@name">
        <xsl:for-each select="parent::node()">
            <xsl:variable name="newName" select="substring-after(@name, concat(../@name,'_'))" />
            <xsl:choose>
                <xsl:when test="$newName != ''">
                    <xsl:attribute name="name" select="$newName" />
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="name" select="@name" />
                </xsl:otherwise>
            </xsl:choose>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="@path">
        <!-- remove @path attribute used to move elements is right package -->
    </xsl:template>
    <xsl:template match="associationSet">
        <xsl:variable name="assonames" select="my:getAssoNames(., //classSet | //aspectSet)" />
        <xsl:element name="{name()}">
            <xsl:apply-templates select="@*" />
            <xsl:attribute name="name" select="$assonames/@name" />
            <xsl:apply-templates select="*" />
            <xsl:if test="$assonames/@source = '' or $assonames/@target = ''">
                <tags xmi:id="{math:random()}" key="simpleAssoName" value="true" />
            </xsl:if>
            <!--			<xsl:copy-of select="$assonames" />-->
        </xsl:element>
    </xsl:template>
    <xsl:function name="my:getAssoNames">
        <xsl:param name="asso" />
        <xsl:param name="context" />
        <xsl:variable name="source">
            <xsl:for-each select="$context">
                <xsl:choose>
                    <xsl:when test="starts-with($asso/@name, @name) and contains($asso/@name, '_')">
                        <xsl:value-of select="@name" />
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="''" />
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:for-each>
        </xsl:variable>
        <xsl:variable name="target">
            <xsl:for-each select="$context">
                <xsl:choose>
                    <xsl:when test="ends-with($asso/@name, @name) and contains($asso/@name, '_')">
                        <xsl:value-of select="@name" />
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="''" />
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:for-each>
        </xsl:variable>
        <xsl:variable name="assoName">
            <xsl:choose>
                <xsl:when test="$source != '' and $target != '' and contains($asso/@name, '_')">
                    <xsl:variable name="withrole" select="substring-before(substring-after($asso/@name, concat($source,'_')), concat('_',$target))" />
                    <xsl:choose>
                        <xsl:when test="contains($withrole, '_') and $asso/*[@navigable = 'true']/@name != ''">
                            <xsl:value-of select="substring-before($withrole, concat('_',$asso/*[@navigable = 'true']/@name))" />
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="$withrole" />
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$asso/@name" />
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <names name="{$assoName}" source="{$source}" target="{$target}" />
    </xsl:function>
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()" />
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>