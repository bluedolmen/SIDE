<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
    <!-- This will preface any links in the output that are based upon a refernce
        Likely this should be the path to your processor i.e. 'myinputprocessor.jsp?ref='
    -->
    <xsl:include href="includes/basic-Global.xsl"/>
    <xsl:include href="includes/basic_Formation-Facets.xsl"/>
    <!-- URL -->

    <xsl:template match="selection">
        <xsl:apply-templates select="superset"/>
        <div class="facets" id="facets">
	        <div class="facets-title">
	            <div style="display: inline; align: left;">Criteres</div>
		        <div style="display: inline; align: right;">
		        	<xsl:if test="$isAdmin = 'true'">
						<input type="button" name="config_facet" id="config_facet"
							value="Configurer" onclick="setup('{$facetName}','{$community}')" />
					</xsl:if>
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
        </div>
        <hr Class="hr2"/>
        <ul style="list-style-image: url('{$icons_url}/bullet.gif');">
            <xsl:attribute name="name">facets<xsl:value-of select="@taxtitle"/></xsl:attribute>
            <xsl:apply-templates select="s" mode="subselection">
                <xsl:with-param name="title">
                    <xsl:value-of select="@taxtitle"/>
                </xsl:with-param>
            </xsl:apply-templates>
					<xsl:if test="count(s) &gt; $nb_paging_facets">
<li class="facet">
	<xsl:attribute name="name">more<xsl:value-of select="@taxtitle"/></xsl:attribute>
	<a>
	    <xsl:attribute name="onclick"> show_facet('facet<xsl:value-of
	            select="@taxtitle"/>','more<xsl:value-of select="@taxtitle"
	        />');</xsl:attribute>
	    <xsl:value-of select="count(s)-$nb_paging_facets"/> more... 
	</a>
</li>
</xsl:if>
        </ul>
    </xsl:template>

    <xsl:template match="s" mode="subselection">
	        <xsl:param name="title"/>
	        <li class="facet">
					<xsl:if test="position() &gt; $nb_paging_facets">
    <xsl:attribute name="style">display:none;</xsl:attribute>
    <xsl:attribute name="name">facet<xsl:value-of select="$title"/></xsl:attribute>
</xsl:if>
				<a href="{$pre_reference_url}{@ref}{$facetName}{$community}"
	                onclick="show_selection('{$pre_reference_url}{@ref}{$facetName}{$community}')">
	                <xsl:value-of select="@title"/>(<xsl:value-of select="@resultcount"/>)
	            </a>
	        </li>
    </xsl:template>
</xsl:transform>