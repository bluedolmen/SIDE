<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmisra="http://docs.oasis-open.org/ns/cmis/restatom/200908/"
	xmlns:cmis="http://docs.oasis-open.org/ns/cmis/core/200908/" xmlns:alf="http://www.alfresco.org"
	xmlns:app="http://www.w3.org/2007/app" version="2.0">
	 <!-- classes node -->
	<xsl:template match="classes">
	    <facetmap>
	        <resources>
	            <xsl:apply-templates/>
	        </resources>
					<taxonomy title="Personne.nom" root-heading-title="Personne.nom" facetid="bluexml.demo.rh.Personne.nom">
						    <heading id="Nom is null" title="Nom is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_nom']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Personne.prenom" root-heading-title="Personne.prenom" facetid="bluexml.demo.rh.Personne.prenom">
						    <heading id="Prénom is null" title="Prénom is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_prenom']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Personne.age" root-heading-title="Personne.age" facetid="bluexml.demo.rh.Personne.age">
						    <heading id="Age is null" title="Age is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_age']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Personne.mobilite" root-heading-title="Personne.mobilite" facetid="bluexml.demo.rh.Personne.mobilite">
						    <heading id="Mobilité is null" title="Mobilité is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_mobilite']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Personne.permisB" root-heading-title="Personne.permisB" facetid="bluexml.demo.rh.Personne.permisB">
						    <heading id="Permis B is null" title="Permis B is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_permisB']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>

	    </facetmap>
	</xsl:template>

	<!-- entry node -->
	<xsl:template match="entry">
		<xsl:variable name="idDoc" select="substring(child::id[1],10)"/>  
		<resource
			title="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_nom']/cmis:value}#{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_prenom']/cmis:value}#{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_age']/cmis:value}#{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_mobilite']/cmis:value}#{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_permisB']/cmis:value}#"
			href="{$idDoc}">
				<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_nom']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_nom']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Nom is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_prenom']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_prenom']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Prénom is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_age']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_age']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Age is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_mobilite']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_mobilite']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Mobilité is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_permisB']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Personne_permisB']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Permis B is null"/>
			</xsl:otherwise>
		</xsl:choose>

		</resource>
	</xsl:template>
	  
    <!-- cmis:object node -->
    <xsl:template match="cmisra:object">
        <xsl:apply-templates/>
    </xsl:template>
    
    <!-- cmis:properties node -->
    <xsl:template match="cmis:properties">
        <xsl:apply-templates/>
    </xsl:template>
    
</xsl:stylesheet>