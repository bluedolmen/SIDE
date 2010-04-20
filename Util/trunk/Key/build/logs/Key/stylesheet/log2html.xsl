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
                <title>Report MDA procedure</title>
                <link rel="stylesheet" href="css/style.css" media="screen"/>
                <script type="text/javascript" src="js/jquery.js"> //</script>
                <script type="text/javascript" src="js/log.js"> //</script>
            </head>
            <body>
                <xsl:value-of select="$filter"/>
                <div id="container">
                    <div id="header">
                        <div id="title">
                            <h1>S-IDE : MDA report procedure.</h1>
                        </div>
                    </div>
                    <div id="menu">
                        <a href="#" onclick="javascript:showPannel('stats');">Stats</a> |
                        <a href="#" onclick="javascript:showPannel('generation');">Generation</a> |
                        <a href="#" onclick="javascript:showPannel('deployment');">Deployment</a> |
                        <a href="#" onclick="javascript:showPannel('console');">Console</a> |
                        <a href="#" onclick="javascript:showPannel('service');">Services</a> |
                        <a href="#" onclick="javascript:showPannel('documentation');">Documentation</a> |
                    </div>

                    <div id="content">
                        <xsl:call-template name="deploymentDisplay"/>
                        <xsl:call-template name="generationDisplay"/>
                        <xsl:call-template name="statsDisplay"/>
                        <xsl:call-template name="console"/>
                        <xsl:call-template name="service"/>
                        <xsl:call-template name="documentation"/>
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
                        <xsl:value-of select="count(//logEntry[@type='DEPLOYMENT_INFORMATION'])"/>
                        deployment informations. <br/>
                        <xsl:value-of select="count(//logEntry[@type='SERVICE' and //deployed/toDeploy[@id=@id]])"/>
                        service(s). <br/>
                        <xsl:value-of select="count(//logEntry[@type='GENERATED_FILE'])"/>
                        generated files logged. <br/>
                    </div>
                </div>
            </div>
            <div id="rightColumn">
                <div id="statsLogBox" class="box">
                    <div class="box-header"> Statistics : generation and deployment</div>
                    <div class="box-body">
                        <xsl:value-of select="count(//SIDELog[@type='GENERATION'])"/> generator(s). <br/>
                        <xsl:value-of select="count(//SIDELog[@type='DEPLOYMENT'])"/> deployer(s). <br/>
                    </div>
                </div>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="deploymentDisplay">
        <div id="deployment" style="display:none;">
            <xsl:call-template name="logerDisplay">
            	<xsl:with-param name="type" select="'DEPLOYMENT'"></xsl:with-param>
            </xsl:call-template>
        </div>
    </xsl:template>

    <xsl:template name="generationDisplay">
        <div id="generation" style="display:none;">
            <xsl:call-template name="logerDisplay">
            	<xsl:with-param name="type" select="'GENERATION'"></xsl:with-param>
            </xsl:call-template>
        </div>
    </xsl:template>
    
    <xsl:template name="console">
        <div id="console" style="display:none;">
            <xsl:call-template name="logerDisplay">
            	<xsl:with-param name="type" select="'CONSOLE'"></xsl:with-param>
            </xsl:call-template>
        </div>
    </xsl:template>

    <xsl:template name="logerDisplay">
        <xsl:param name="type" required="yes"></xsl:param>
    	<xsl:param name="showServices" required="no" select="'false'"></xsl:param>
        <xsl:param name="idSuffix" required="no"></xsl:param>
        <xsl:for-each select="//SIDELog[@type=$type and not(contains(@id,'documentation'))]">
            <xsl:sort select="@name"/>
        	<xsl:variable name="entryId" select="@id"></xsl:variable>
            <xsl:if test="($showServices = 'false') or ($showServices = 'true' and //deployed/toDeploy[@id=$entryId])">
                <div class="box">
                    <xsl:variable name="name">
                    	<xsl:call-template name="removeSpecialChars"><xsl:with-param name="string" select="concat(@name,$idSuffix)"></xsl:with-param></xsl:call-template>
                    </xsl:variable>
                    <div class="box-header">
                    	<xsl:attribute name="id" select="concat($name,'-header')"></xsl:attribute>
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
                        <xsl:if test="$showServices = 'false'">
                            <fieldset>
                                <legend>Summary</legend>
                                <xsl:value-of select="count(//SIDELog[@id=$entryId]/logEntry[@type='ERROR'])"/> error(s). <br/>
                                <xsl:value-of select="count(//SIDELog[@id=$entryId]/logEntry[@type='WARNING'])"/> warning(s). <br/>
                                <xsl:if test="$type='GENERATION'">
                                    <xsl:value-of select="count(//SIDELog[@id=$entryId]/logEntry[@type='GENERATION_INFORMATION'])"/>
                                    generation informations. <br/>
                                    <xsl:value-of select="count(//SIDELog[@id=$entryId]/logEntry[@type='SERVICE'])"/>
                                    service(s). <br/>
                                    <xsl:value-of select="count(//SIDELog[@id=$entryId]/logEntry[@type='GENERATED_FILE'])"/>
                                    generated files logged. <br/>
                                </xsl:if>
                                <xsl:if test="$type='DEPLOYMENT'">
                                    <xsl:value-of select="count(//SIDELog[@id=$entryId]/logEntry[@type='DEPLOYMENT_INFORMATION'])"/>
                                    deployment informations. <br/>
                                </xsl:if>
                                <xsl:if test="//deployed/toDeploy[@id=$entryId]">
                                    Deployed <xsl:value-of select="//deployed/toDeploy[@id=$entryId]/@date"/>
                                </xsl:if>
                                <xsl:if test="$type='GENERATION'">
                                    <br></br>Models :
                                    <xsl:for-each select="models/model">
                                       <xsl:value-of select="text()"/> -
                                    </xsl:for-each>
                                </xsl:if>
                            </fieldset>
                        <ul>
                            <xsl:for-each select="logEntry[@type!='SERVICE']">
                                <xsl:sort select="@date"/>
                                <xsl:call-template name="showEntry"/>
                            </xsl:for-each>
                        </ul>
                        </xsl:if>
                        <xsl:if test="$showServices = 'true'">
                            <ul>
                                <xsl:if test="count(logEntry[@type='SERVICE']) = 0">No Service Registered</xsl:if>
                                <xsl:for-each select="logEntry[@type='SERVICE']">
                                    <xsl:call-template name="showEntry"/>
                                </xsl:for-each>
                            </ul>
                        </xsl:if>
                    </div>
                </div>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>

    <xsl:template name="showEntry">
        <li>
            <xsl:if test="@type='ERROR'">
                <xsl:attribute name="class">error</xsl:attribute>
            </xsl:if>
            <xsl:if test="@type='WARNING'">
                <xsl:attribute name="class">warning</xsl:attribute>
            </xsl:if>
            <strong><xsl:value-of select="name"/></strong>
            <br/>
            <xsl:apply-templates select="description"></xsl:apply-templates>
            <xsl:if test="uri != ''">
                <a>
                    <xsl:attribute name="href"><xsl:value-of select="uri"/></xsl:attribute>
                    <img src="img/link.png"/>
                </a>
            </xsl:if>
        </li>
    </xsl:template>

    <xsl:template name="service">
        <div id="service" style="display:none;">
            <xsl:call-template name="logerDisplay">
            	<xsl:with-param name="type" select="'GENERATION'"></xsl:with-param>
            	<xsl:with-param name="showServices" select="'true'"></xsl:with-param>
            	<xsl:with-param name="idSuffix" select="'srv'"></xsl:with-param>
            </xsl:call-template>
        </div>
    </xsl:template>

    <xsl:template name="documentation">
        <div id="documentation" style="display:none;">
            <div class="box">
                <div class="box-header">
                    Documentation
                </div>
                <div class="box-body">
                    <ul>
                        <xsl:for-each select="//documentation/entry">
                            <li>
                                <a>
                                    <xsl:attribute name="href"><xsl:value-of select="@path"/></xsl:attribute>
                                    <xsl:value-of select="@path"/>
                                </a>
                            </li>
                        </xsl:for-each>
                    </ul>
                </div>
            </div>
        </div>
    </xsl:template>

<xsl:template match="description">
	<xsl:choose>
		<xsl:when test="count(model) &lt; 10">
			<xsl:call-template name="displayAlldescriptionModel"></xsl:call-template>
		</xsl:when>
		<xsl:otherwise>
			<div>
				<div class="clickable">
					<xsl:attribute name="onclick">javascript:switchLog(this);</xsl:attribute>
					<xsl:value-of select="model[1]" />
					<xsl:for-each select="model[position() != 1 and position() &lt; 11]">
						<br />
						<xsl:value-of select="." />
					</xsl:for-each>
					...
				</div>
				<div class="clickable" style="display:none;" onclick="javascript:switchLog(this);">
					<xsl:call-template name="displayAlldescriptionModel"></xsl:call-template>
				</div>
			</div>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>


<xsl:template name="displayAlldescriptionModel">
	<xsl:value-of select="model[1]" />
	<xsl:for-each select="model[position() != 1]">
		<br />
		<xsl:value-of select="." />
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
