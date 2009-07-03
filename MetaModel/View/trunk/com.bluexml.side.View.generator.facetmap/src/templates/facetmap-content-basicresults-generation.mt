<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./content/xsl/display/includes/basic-Results.xsl
	
<%script type="view.FacetMap" name="rightnavGenerator"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="1.0">
  
  <xsl:template match="results">
  	<div class="results-title">
  	<xsl:choose>
        <xsl:when test="@count &gt; 1">
			<xsl:value-of select="@count" />
			<xsl:text> Résultats </xsl:text>
			<xsl:if test="@count != count(resource)">
			(<xsl:value-of select="count(resource)" />
			<xsl:text> affichés)</xsl:text>
			</xsl:if>
		</xsl:when>
        <xsl:otherwise>
			<xsl:value-of select="@count" /><xsl:text> Résultats</xsl:text>
		</xsl:otherwise>
      </xsl:choose>
    </div>
    <hr Class="hr1" />
    <xsl:apply-templates select="resource" >
    	<xsl:with-param name="nbParPages" select="count(resource)"/>
    	<xsl:with-param name="NbTotal" select="@count"/>
    </xsl:apply-templates>
  </xsl:template>
  
  <xsl:template match="resource">
  	<xsl:param name="nbParPages"/>
  	<xsl:param name="NbTotal"/>
    <div class="result">
    	<%for (getInnerView().filter("Actionable").operations.getOperations()){%>
        	<%if (name == "download"){%>
				<a>
					<xsl:attribute name="href"><xsl:value-of select="@idDoc"/></xsl:attribute>
					<xsl:attribute name="target">_blank</xsl:attribute>
					<img src="{$icons_url}/disk.png" class="imgIcon"/>
				</a>
			<%}%>
			<%if (name == "view"){%>
				<a>
					<xsl:attribute name="href"><xsl:value-of select="@idDoc"/></xsl:attribute>
					<xsl:attribute name="target">_blank</xsl:attribute>
					<img src="{$icons_url}/eye.png" class="imgIcon"/>
				</a>
			<%}%>
			<%if (name == "edit"){%>
				<a>
					<xsl:attribute name="href"><xsl:value-of select="@idDoc"/></xsl:attribute>
					<xsl:attribute name="target">_blank</xsl:attribute>
					<img src="{$icons_url}/edit.png" class="imgIcon"/>
				</a>
			<%}%>
        <%}%>
    	<xsl:value-of select="@name"/>
	</div>
    <xsl:if test="position()!=last()">
      <hr class="hr2" />
    </xsl:if>
  </xsl:template>
</xsl:stylesheet>
