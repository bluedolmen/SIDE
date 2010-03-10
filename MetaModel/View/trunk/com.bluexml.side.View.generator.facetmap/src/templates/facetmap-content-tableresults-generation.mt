<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="rightnavGenerator_table"%>
<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="1.0"
xmlns:str="http://exslt.org/strings"
extension-element-prefixes="str">

  
  <xsl:template match="results">
  <style type="text/css">
	.resultTab{
		border-style:solid;
		border-width: 1px;
		border-collapse: collapse;
		
	}	
	.resultTab td {
		border-style:solid;
		border-width: 1px;
		min-width: 100px;
		text-align: center;
		
	}	
	.resultTab th {
		font-weight:bold;
		color: #FFFFFF;
		background-color: #6CA5CE;
		border-style:solid;
		border-width: 1px;
	}
  </style>
  
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
    <br />
	<table class="resultTab">
		<tr>
			<%if (getInnerView().filter("Actionable").operations.getOperations().nSize() >0){%>
			<th>Actions</th>
			<%}%>				
		<%for (getInnerView().getFields()){%>
			<th><%name%></th>
		<%}%>
		</tr>
    <xsl:apply-templates select="resource" >
    	<xsl:with-param name="nbParPages" select="count(resource)"/>
    	<xsl:with-param name="NbTotal" select="@count"/>
    </xsl:apply-templates>
    </table>
    <br />
  </xsl:template>
  
  <xsl:template match="resource">
  	<xsl:param name="nbParPages"/>
  	<xsl:param name="NbTotal"/>
    <tr>
    <%if (getInnerView().filter("Actionable").operations.getOperations().nSize() >0){%>
		<td>
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
					<xsl:attribute name="href">../share/page/site/<xsl:value-of select="substring-after($community, '&amp;community=')"/>/document-details?nodeRef=workspace://SpacesStore/<xsl:value-of select="@href"/></xsl:attribute>
					<img src="{$icons_url}/eye.png" class="imgIcon"/>
			<%}%>
			<%-- Edit operation --%>
			<%if (name == "edit"){%>
					<xsl:attribute name="href">../share/page/site/<xsl:value-of select="substring-after($community, '&amp;community=')"/>/edit-metadata?nodeRef=workspace://SpacesStore/<xsl:value-of select="@href"/></xsl:attribute>
					<img src="{$icons_url}/edit.png" class="imgIcon"/>
			<%}%>
			</a>
        <%}%>
    	</td>
    <%}%>
		<xsl:for-each select="str:split(@name,'#')">
		<td> <xsl:value-of select="text()" />  </td>
		</xsl:for-each>
	</tr>
	
  </xsl:template>
</xsl:stylesheet>
