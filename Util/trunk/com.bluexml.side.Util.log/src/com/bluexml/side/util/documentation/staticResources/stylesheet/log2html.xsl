<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">
    <!--
    <xsl:output omit-xml-declaration="yes"
        doctype-public="-//W3C//DTD XHTML 1.0 Transitiona    l//EN"
        doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
        encoding="UTF-8"
        indent="no"
        method="xml"/>
    -->

    <xsl:param name="filter"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>S-IDE Generation</title>
                <link rel="stylesheet" href="css/style.css" media="screen"/>
                <script type="text/javascript" src="js/jquery.js"> //</script>
                <script type="text/javascript" src="js/log.js"> //</script>
            </head>
            <body>
                <xsl:value-of select="$filter"/>
                <div id="container">
                    <div id="header">
                        <div id="title">
                            <h1>S-IDE Logs</h1>
                        </div>
                    </div>
                    <div id="menu">
                        <a href="#" onclick="javascript:showPannel('stats');">Stats</a> | 
                        <a href="#" onclick="javascript:showPannel('generation');">Generation</a> | 
                        <a href="#" onclick="javascript:showPannel('deployment');">Deployment</a> |
                        <a href="#" onclick="javascript:showPannel('service');">Services</a> |
                        <a href="#" onclick="javascript:showPannel('documentation');">Documentation</a> |
                    </div>
    
                    <div id="content">
                        <xsl:call-template name="deploymentDisplay"/>
                        <xsl:call-template name="generationDisplay"/>
                        <xsl:call-template name="statsDisplay"/>
                        
                    </div>
    
                    <div id="footer">
                        <a href="http://www.bluexml.com">BlueXML</a>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="statsDisplay">
        <div id="stats">
            <div id="leftColumn">
                <div id="statsLogTypeBox" class="box">
                    <div class="box-header"> Statistiques : log type</div>
                    <div class="box-body">
                        <xsl:value-of select="count(//logEntry[@type='ERROR'])"/> error(s). <br/>
                        <xsl:value-of select="count(//logEntry[@type='WARNING'])"/> warning(s). <br/>
                        <xsl:value-of select="count(//logEntry[@type='GENERATION_INFORMATION'])"/>
                        generation informations. <br/>
                        <xsl:value-of select="count(//logEntry[@type='DEPLOYEMENT_INFORMATION'])"/>
                        deployment informations. <br/>
                        <xsl:value-of select="count(//logEntry[@type='GENERATED_FILE'])"/>
                        logs on generated files. <br/>
                    </div>
                </div>
            </div>
            <div id="rightColumn">
                <div id="statsLogBox" class="box">
                    <div class="box-header"> Statistiques : generation and deployment</div>
                    <div class="box-body">
                        <xsl:value-of select="count(//SIDELog[@type='GENERATION'])"/> generator. <br/>
                        <xsl:value-of select="count(//SIDELog[@type='DEPLOYEMENT'])"/> deployer. <br/>
                    </div>
                </div>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="deploymentDisplay">
        <div id="deployment" style="display:none;"> 
            <xsl:call-template name="logerDisplay">
                <xsl:with-param name="type">DEPLOYEMENT</xsl:with-param>
            </xsl:call-template>
        </div>
    </xsl:template>

    <xsl:template name="generationDisplay">
        <div id="generation" style="display:none;">
            <xsl:call-template name="logerDisplay">
                <xsl:with-param name="type">GENERATION</xsl:with-param>
            </xsl:call-template>
        </div>
    </xsl:template>
    
    <xsl:template name="logerDisplay">
        <xsl:param name="type"></xsl:param>
        <xsl:for-each select="//SIDELog[@type=$type]">
            <xsl:sort select="@name"/>
            <div class="box">
                <xsl:variable name="name">
                    <xsl:call-template name="removeSpecialChars"><xsl:with-param name="string"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template>
                </xsl:variable>
                <xsl:attribute name="id"><xsl:value-of select="$name"/></xsl:attribute>
                <div class="box-header">
                    <xsl:attribute name="id"><xsl:value-of select="$name"/>-header</xsl:attribute>
                    <img src="img/expand.png" align="right" class="clickable" style="display:none;">
                        <xsl:attribute name="id"><xsl:value-of select="$name"/>-ico-expand</xsl:attribute>
                        <xsl:attribute name="onclick">javascript:expandBox('<xsl:value-of select="$name"/>-body',this,'<xsl:value-of select="$name"/>-ico-collapse')</xsl:attribute>
                    </img>
                    <img src="img/collapse.png" align="right" class="clickable">
                        <xsl:attribute name="id"><xsl:value-of select="$name"/>-ico-collapse</xsl:attribute>
                        <xsl:attribute name="onclick">javascript:collapseBox('<xsl:value-of select="$name"/>-body',this,'<xsl:value-of select="$name"/>-ico-expand')</xsl:attribute>
                    </img>
                    <xsl:value-of select="@name"/> (<xsl:call-template name="displayDate"><xsl:with-param name="date"><xsl:value-of select="@date"/></xsl:with-param></xsl:call-template>)
                </div>
                <div class="box-body">
                    <xsl:attribute name="id"><xsl:value-of select="$name"/>-body</xsl:attribute>
                    <xsl:if test="count(logEntry) = 0">No Log registered</xsl:if>
                    <ul>
                        <xsl:for-each select="logEntry">
                            <xsl:sort select="@date"/>
                            <li>
                                <xsl:if test="@type='ERROR'">
                                    <xsl:attribute name="class">error</xsl:attribute>
                                </xsl:if>
                                <xsl:if test="@type='WARNING'">
                                    <xsl:attribute name="class">warning</xsl:attribute>
                                </xsl:if>
                                <strong><xsl:value-of select="name"/></strong>
                                <br/>
                                <xsl:choose>
                                    <xsl:when test="string-length(description) > 250">
                                        <div>
                                            <div class="clickable">
                                                <xsl:attribute name="onclick">javascript:switchLog(this);</xsl:attribute>
                                                <xsl:value-of select="substring(description,1,250)"/>...
                                            </div>
                                            <div class="clickable" style="display:none;" onclick="javascript:switchLog(this);">
                                                <xsl:value-of select="description"/>
                                            </div>
                                        </div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="description"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                                <xsl:if test="uri != ''">
                                    <a>
                                        <xsl:attribute name="href"><xsl:value-of select="uri"/></xsl:attribute>
                                        <img src="img/link.png"/>
                                    </a>
                                </xsl:if>
                            </li>
                        </xsl:for-each>
                    </ul>
                </div>
            </div>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="node()|@*">
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template name="displayDate">
        <xsl:param name="date"/>
        <xsl:value-of select="concat(substring($date, 9, 2), '/', substring($date, 6, 2), '/', substring($date,1,4))"/>
        - <xsl:value-of select="substring($date, 12, 8)"/>
    </xsl:template>
    
    <xsl:template name="removeSpecialChars">
        <xsl:param name="string"/>
        <xsl:value-of select="translate($string,' ','')"/>
    </xsl:template>
</xsl:stylesheet>
