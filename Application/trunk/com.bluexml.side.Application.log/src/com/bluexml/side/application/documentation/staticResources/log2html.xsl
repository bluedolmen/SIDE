<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
      xmlns:xs="http://www.w3.org/2001/XMLSchema"
      exclude-result-prefixes="xs"
      version="2.0">
    
    <xsl:param name="filter"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>S-IDE Generation</title>
                <link rel="stylesheet" href="css/style.css" media="screen" />
            </head>
            <body>
                <xsl:value-of select="$filter"/>
                <div id="header">
                    <div id="title">
                        <h1>S-IDE Logs</h1>
                    </div>
                </div>
                <div id="menu">
                    <a href="#">Stats</a> | <a href="#">Generation</a> | <a href="#">Deployement</a> | <a href="#">Error</a>
                </div>
                
                <div id="content">
                    <xsl:call-template name="deployementDisplay"/>
                    <xsl:call-template name="errorDisplay"/>
                    <xsl:call-template name="generationDisplay"/>
                    <xsl:call-template name="statsDisplay"/>
                </div>
                
                <div id="footer">
                    <a href="http://www.bluexml.com">BlueXML</a>
                </div>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template name="statsDisplay">
        <div id="stats"  class="box">
            <div class="box-header">
                Statistiques
            </div>
            <div class="box-body">
                <xsl:value-of select="count(//logEntry[@type='ERROR'])"/> error(s). <br/>
                <xsl:value-of select="count(//logEntry[@type='WARNING'])"/> warning(s). <br/>
                <xsl:value-of select="count(//logEntry[@type='GENERATION_INFORMATION'])"/> generation informations. <br/>
                <xsl:value-of select="count(//logEntry[@type='DEPLOYEMENT_INFORMATION'])"/> deployement informations. <br/>
            </div>
            <div class="box-footer">
            </div>
        </div>
    </xsl:template>
    
    <xsl:template name="deployementDisplay">
        <div id="deployement" class="box">
           
        </div>
    </xsl:template>
    
    <xsl:template name="generationDisplay">
        <div id="generation">
            
        </div>
    </xsl:template>
    
    <xsl:template name="errorDisplay">
        <div id="error">
            
        </div>
    </xsl:template>
    
    <xsl:template match="node()|@*">
        <xsl:apply-templates />
    </xsl:template>
</xsl:stylesheet>