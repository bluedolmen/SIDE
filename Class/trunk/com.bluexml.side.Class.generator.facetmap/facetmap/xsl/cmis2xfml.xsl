<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com" version="2.0">
	<xsl:include href="../cmisTransformProperties.xml"/>
	 <!-- classes node -->
	<xsl:template match="classes">
	    <facetmap>
	        <resources>
	            <xsl:apply-templates/>
	        </resources>
				<taxonomy title="Personne->Nom" root-heading-title="Personne->Nom" facetid="com.bluexml.demo.rh.Personne.nom">
					<!-- criteria -->
				    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
				    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.nom']/cmis:value">          
				        <heading id="{current()}" title="{current()}"/>             
				    </xsl:for-each> 
				</taxonomy>
				<taxonomy title="Personne->Prénom" root-heading-title="Personne->Prénom" facetid="com.bluexml.demo.rh.Personne.prenom">
					<!-- criteria -->
				    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
				    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.prenom']/cmis:value">          
				        <heading id="{current()}" title="{current()}"/>             
				    </xsl:for-each> 
				</taxonomy>
				<taxonomy title="Personne->Age" root-heading-title="Personne->Age" facetid="com.bluexml.demo.rh.Personne.age">
					<!-- criteria -->
				    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
				    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyInteger[@cmis:name='com.bluexml.demo.rh.Personne.age']/cmis:value">          
				        <heading id="{current()}" title="{current()}"/>             
				    </xsl:for-each> 
				</taxonomy>
				<taxonomy title="Personne->Mobilité" root-heading-title="Personne->Mobilité" facetid="com.bluexml.demo.rh.Personne.mobilite">
					<!-- criteria -->
				    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
				    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.mobilite']/cmis:value">          
				        <heading id="{current()}" title="{current()}"/>             
				    </xsl:for-each> 
				</taxonomy>
				<taxonomy title="Personne->Permis B" root-heading-title="Personne->Permis B" facetid="com.bluexml.demo.rh.Personne.permisB">
					<!-- criteria -->
				    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
				    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.permisB']/cmis:value">          
				        <heading id="{current()}" title="{current()}"/>             
				    </xsl:for-each> 
				</taxonomy>
	    </facetmap>
	</xsl:template>

	<!-- entry node -->
	<xsl:template match="entry">
		<xsl:variable name="ref_share" select="concat($host,$DLUrl,substring(child::id[1],10))"/>
		<xsl:variable name="ref_alfresco" select="concat($host,$ViewURL,substring(child::id[1],10),'/',child::title[1])"/>       
		<resource title="{child::title[1]}" href="{concat($ref_alfresco,'+',$ref_share)}">
	           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.nom']/cmis:value}"/>
	           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.prenom']/cmis:value}"/>
	           <map heading="{child::cmis:object/cmis:properties/cmis:propertyInteger[@cmis:name='com.bluexml.demo.rh.Personne.age']/cmis:value}"/>
	           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.mobilite']/cmis:value}"/>
	           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.permisB']/cmis:value}"/>
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