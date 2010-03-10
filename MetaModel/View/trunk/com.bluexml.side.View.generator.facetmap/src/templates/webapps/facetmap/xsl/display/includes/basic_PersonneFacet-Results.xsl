<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="1.0"
xmlns:str="http://exslt.org/strings"
extension-element-prefixes="str">

<xsl:import href="str.split.template.xsl"/>
  
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
	<table>
		<tr>
			<th>Actions</th>
			<th>nom</th>
			<th>prenom</th>
			<th>age</th>
			<th>mobilite</th>
			<th>permisB</th>
		</tr>
    <xsl:apply-templates select="resource" >
    	<xsl:with-param name="nbParPages" select="count(resource)"/>
    	<xsl:with-param name="NbTotal" select="@count"/>
    </xsl:apply-templates>
    </table>
  </xsl:template>
  
  <xsl:template match="resource">
  	<xsl:param name="nbParPages"/>
  	<xsl:param name="NbTotal"/>
    <tr>
		<td>
        	<a>
        		<xsl:attribute name="target">_blank</xsl:attribute>
					<xsl:attribute name="href">../share/page/site/<xsl:value-of select="substring-after($community, '&amp;community=')"/>/edit-metadata?nodeRef=workspace://SpacesStore/<xsl:value-of select="@href"/></xsl:attribute>
					<img src="{$icons_url}/edit.png" class="imgIcon"/>
			</a>
    	</td>
		<xsl:for-each select="str:tokenize(@name,'#')">
		<td> <xsl:value-of select="text()" />  </td>
		</xsl:for-each>
	</tr>
	<br />
  </xsl:template>
</xsl:stylesheet>
