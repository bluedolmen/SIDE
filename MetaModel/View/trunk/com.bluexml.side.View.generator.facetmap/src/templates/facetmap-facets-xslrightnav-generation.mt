<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./webapps/facetmap/xsl/display/Rightnav_<%name%>_facets.xsl
	
<%script type="view.FacetMap" name="morePagingFacet"%>
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
	
<%script type="view.FacetMap" name="morePagingFacetDisplay"%>
<xsl:if test="position() &gt; $nb_paging_facets">
    <xsl:attribute name="style">display:none;</xsl:attribute>
    <xsl:attribute name="name">facet<xsl:value-of select="$title"/></xsl:attribute>
</xsl:if>
	
<%script type="view.FacetMap" name="rightnavGenerator"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
    <!-- This will preface any links in the output that are based upon a refernce
        Likely this should be the path to your processor i.e. 'myinputprocessor.jsp?ref='
    -->
    <xsl:include href="includes/basic-Global.xsl"/>
    <xsl:include href="includes/basic_<%name%>-Facets.xsl"/>
    <!-- URL -->

    <xsl:template match="selection">
        <xsl:apply-templates select="superset"/>
        <div class="facets" id="facets">
	        <div class="facets-title">
	            <div style="display: inline; align: left;">Criteres</div>
		        <div style="display: inline; align: right;">
		        	<xsl:if test="{$isAdmin == 'true'}">
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
            <%for (operations){%>
            	<%if (name == "hideFacets"){%>
            		<span class="collapseExpandContainer">
            		<a>
					    <xsl:attribute name="name">hideLink<xsl:value-of select="@taxtitle"/></xsl:attribute>
					    <xsl:attribute name="onclick"> hide_facets('facets<xsl:value-of
					        select="@taxtitle"/>','hideLink<xsl:value-of select="@taxtitle"/>');</xsl:attribute>
					    <img src='{$icons_url}/collapse.png' class="imgIcon"/>
					</a>
					</span>
            	<%}%>
            <%}%>
        </div>
        <hr Class="hr2"/>
        <ul style="list-style-image: url('{$icons_url}/bullet.gif');">
            <xsl:attribute name="name">facets<xsl:value-of select="@taxtitle"/></xsl:attribute>
            <xsl:apply-templates select="s" mode="subselection">
                <xsl:with-param name="title">
                    <xsl:value-of select="@taxtitle"/>
                </xsl:with-param>
            </xsl:apply-templates>
            <%if (paging.paginationStyle != "none"){%>
            	<%if (facetDisplayType == "list") {%>
					<%morePagingFacet()%>
				<%}%>
			<%}%>     
        </ul>
    </xsl:template>

    <xsl:template match="s" mode="subselection">
    	<%if (facetDisplayType == "list") {%>
	        <xsl:param name="title"/>
	        <li class="facet">
	        	<%if (paging.paginationStyle != "none"){%>
					<%morePagingFacetDisplay()%>
				<%}else{%>
					<xsl:attribute name="style"/>
				<%}%>
				<a href="{$pre_reference_url}{@ref}{$facetName}{$community}"
	                onclick="show_selection('{$pre_reference_url}{@ref}{$facetName}{$community}')">
	                <xsl:value-of select="@title"/>(<xsl:value-of select="@resultcount"/>)
	            </a>
	        </li>
        <%}%>
        <%if (facetDisplayType == "cloud"){%>
              <a href="{$pre_reference_url}{@ref}{$facetName}{$community}"
	                onclick="show_selection('{$pre_reference_url}{@ref}{$facetName}{$community}')">
	                <xsl:value-of select="@title"/>(<xsl:value-of select="@resultcount"/>)
	          </a>
            <xsl:text>  </xsl:text>
        <%}%>
        <%if (facetDisplayType == "improvedCloud"){%>
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
              <a href="{$pre_reference_url}{@ref}{$facetName}{$community}"
	                onclick="show_selection('{$pre_reference_url}{@ref}{$facetName}{$community}')">
	                <xsl:value-of select="@title"/>(<xsl:value-of select="@resultcount"/>)
              </a>
            </span>
            <xsl:text>  </xsl:text>
        <%}%>
    </xsl:template>
</xsl:transform>
