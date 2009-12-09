<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com" version="2.0">
	 <!-- classes node -->
	<xsl:template match="classes">
	    <facetmap>
	        <resources>
	            <xsl:apply-templates/>
	        </resources>
					<taxonomy title="Personne.mobilite" root-heading-title="Personne.mobilite" facetid="com.bluexml.demo.rh.Personne.mobilite">
						    <heading id="Mobilité is null" title="Mobilité is null"/> 
						    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.com.bluexml.demo.rh.Personne.mobilite']/cmis:value">          
						        <heading id="{current()}" title="{current()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Personne.permisB" root-heading-title="Personne.permisB" facetid="com.bluexml.demo.rh.Personne.permisB">
						    <heading id="Permis B is null" title="Permis B is null"/> 
						    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.com.bluexml.demo.rh.Personne.permisB']/cmis:value">          
						        <heading id="{current()}" title="{current()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Personne.age" root-heading-title="Personne.age" facetid="com.bluexml.demo.rh.Personne.age">
						    <heading id="Age is null" title="Age is null"/> 
						    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyInteger[@cmis:name='com.com.bluexml.demo.rh.Personne.age']/cmis:value">          
						        <heading id="{current()}" title="{current()}"/>             
						    </xsl:for-each> 
						</taxonomy>

	    </facetmap>
	</xsl:template>

	<!-- entry node -->
	<xsl:template match="entry">
		<xsl:variable name="idDoc" select="substring(child::id[1],10)"/>  
		<resource
			title="Employe : {child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.com.bluexml.demo.rh.Personne.nom']/cmis:value} {child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.com.bluexml.demo.rh.Personne.prenom']/cmis:value}"
			href="{$idDoc}">
				<xsl:choose>	
			<xsl:when test="child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.com.bluexml.demo.rh.Personne.mobilite']/cmis:value!= ''">
				<map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.com.bluexml.demo.rh.Personne.mobilite']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Mobilité is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.com.bluexml.demo.rh.Personne.permisB']/cmis:value!= ''">
				<map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.com.bluexml.demo.rh.Personne.permisB']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Permis B is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmis:object/cmis:properties/cmis:propertyInteger[@cmis:name='com.com.bluexml.demo.rh.Personne.age']/cmis:value!= ''">
				<map heading="{child::cmis:object/cmis:properties/cmis:propertyInteger[@cmis:name='com.com.bluexml.demo.rh.Personne.age']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Age is null"/>
			</xsl:otherwise>
		</xsl:choose>

		</resource>
	</xsl:template>
	  
    <!-- cmis:object node -->
    <xsl:template match="cmis:object">
        <xsl:apply-templates/>
    </xsl:template>
    
    <!-- cmis:properties node -->
    <xsl:template match="cmis:properties">
        <xsl:apply-templates/>
    </xsl:template>
    
</xsl:stylesheet>
