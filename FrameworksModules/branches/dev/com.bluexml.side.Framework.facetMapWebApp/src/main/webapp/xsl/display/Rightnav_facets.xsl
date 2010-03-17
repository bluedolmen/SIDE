<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
    <!-- This will preface any links in the output that are based upon a refernce
        Likely this should be the path to your processor i.e. 'myinputprocessor.jsp?ref='
    -->
    <xsl:include href="includes/basic-Global.xsl"/>
    <xsl:include href="includes/basic-Facets.xsl"/>
    <!-- URL -->

    <xsl:template match="selection">
        <xsl:apply-templates select="superset"/>
        <div class="facets" id="facets">
	        <div class="facets-title">
	            <div style="display: inline; align: left;">Critères</div>
		        <div style="display: inline; align: right;">
				    <input type="button" name="config_facet" id="config_facet" value="Configurer"
				        onclick="setup()"/>
				    <input type="button" name="update_facets" id="update_facets" value="Mise à jour" 
		                    onclick="update_facets()"/>
				</div>
	        </div>
	        <hr Class="hr1"/>
	        <xsl:apply-templates select="subset[s]"/>
    	</div>
    </xsl:template>

    <xsl:template match="subset">
        <xsl:if test="position()!=1">
            <hr Class="hr2"/>
        </xsl:if>
        <div class="facet-title">
            <xsl:value-of select="@taxtitle"/>
            		<span class="collapseExpandContainer">
            		<a>
					    <xsl:attribute name="name">hideLink<xsl:value-of select="@taxtitle"/></xsl:attribute>
					    <xsl:attribute name="onclick"> hide_facets('facets<xsl:value-of
					        select="@taxtitle"/>','hideLink<xsl:value-of select="@taxtitle"/>');</xsl:attribute>
					    <img src='{$icons_url}/collapse.png' class="imgIcon"/>
					</a>
					</span>
        </div>
        <hr Class="hr2"/>
        <ul style="list-style-image: url('{$icons_url}/bullet.gif');">
            <xsl:attribute name="name">facets<xsl:value-of select="@taxtitle"/></xsl:attribute>
            <xsl:apply-templates select="s" mode="subselection">
                <xsl:with-param name="title">
                    <xsl:value-of select="@taxtitle"/>
                </xsl:with-param>
            </xsl:apply-templates>
        </ul>
    </xsl:template>

    <xsl:template match="s" mode="subselection">
        	
            <span>
              <xsl:variable name="fontsize" select="@resultcount div //selection/results/@count * count(../s)" />
              	<xsl:attribute name="style">
					<xsl:choose>
						<xsl:when test="$fontsize &gt; 2.5">
							font-size:2.5em;
						</xsl:when>
						<xsl:when test="$fontsize &lt; 0.7">
							font-size:0.7em;
						</xsl:when>
						<xsl:otherwise>
							font-size: 1em;
						</xsl:otherwise>
					</xsl:choose>
				</xsl:attribute>
              <a href="{$server}/{$app2}/{$pre_reference_url}{@ref}"
	                onclick="show_selection('{$server}/{$app}/{$pre_reference_url}{@ref}')">
	                <xsl:value-of select="@title"/>(<xsl:value-of select="@resultcount"/>)
              </a>
            </span>
            <xsl:text>  </xsl:text>
    </xsl:template>
</xsl:transform>
