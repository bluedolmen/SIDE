<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./webapps/facetmap/xsl/display/includes/basic_<%name%>-Results.xsl
	
<%script type="view.FacetMap" name="rightnavGenerator"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="1.0">
  
  <xsl:template match="results">
  	<div class="results-title">
  	<xsl:choose>
        <xsl:when test="@count &gt; 1">
			<xsl:value-of select="@count" />
			<xsl:text> Resultats </xsl:text>
			<xsl:if test="@count != count(resource)">
			(<xsl:value-of select="count(resource)" />
			<xsl:text> affiches)</xsl:text>
			</xsl:if>
		</xsl:when>
        <xsl:otherwise>
			<xsl:value-of select="@count" /><xsl:text> Resultats</xsl:text>
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
    <%-- Get the operations of the model and insert an image for each --%>
    	<%for (getInnerView().filter("Actionable").operations.getOperations()){%>
        	<a>
        		<xsl:attribute name="target">_blank</xsl:attribute>
        	<%-- Download operation --%>
        	<%if (name == "download"){%>
					<xsl:attribute name="href">../share/proxy/alfresco/api/node/content/workspace/SpacesStore/<xsl:value-of select="@href"/>/?a=true</xsl:attribute>
					<img src="{$icons_url}/disk.png" class="imgIcon"/>
			<%}%>
			<%-- View operation --%>
			<%if (name == "view"){%>
					<xsl:attribute name="href">../share/page/site/test/document-details?nodeRef=workspace://SpacesStore/<xsl:value-of select="@href"/></xsl:attribute>
					<img src="{$icons_url}/eye.png" class="imgIcon"/>
			<%}%>
			<%-- Edit operation --%>
			<%if (name == "edit"){%>
					<xsl:attribute name="href">../share/page/site/test/edit-metadata?nodeRef=workspace://SpacesStore/<xsl:value-of select="@href"/></xsl:attribute>
					<img src="{$icons_url}/edit.png" class="imgIcon"/>
			<%}%>
			</a>
        <%}%>
    	<xsl:value-of select="@name"/>
	</div>
    <xsl:if test="position()!=last()">
      <hr class="hr2" />
    </xsl:if>
  </xsl:template>
</xsl:stylesheet>
