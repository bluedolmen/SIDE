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
					<taxonomy title="Formation.besoinEntreprise" root-heading-title="Formation.besoinEntreprise" facetid="bluexml.demo.rh.Formation.besoinEntreprise">
						    <heading id="Besoin entreprise is null" title="Besoin entreprise is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_besoinEntreprise']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Formation.envieSalarie" root-heading-title="Formation.envieSalarie" facetid="bluexml.demo.rh.Formation.envieSalarie">
						    <heading id="Envie Salari� is null" title="Envie Salari� is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_envieSalarie']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Formation.theme" root-heading-title="Formation.theme" facetid="bluexml.demo.rh.Formation.theme">
						    <heading id="Th�me is null" title="Th�me is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_theme']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Formation.dateSouhaitee" root-heading-title="Formation.dateSouhaitee" facetid="bluexml.demo.rh.Formation.dateSouhaitee">
						    <heading id="Date souhait�e is null" title="Date souhait�e is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyInteger[@propertyDefinitionId='com:bluexml_demo_rh_Formation_dateSouhaitee']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Formation.duree" root-heading-title="Formation.duree" facetid="bluexml.demo.rh.Formation.duree">
						    <heading id="Dur�e is null" title="Dur�e is null"/> 
						    <xsl:for-each select="child::entry/cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_duree']/cmis:value">          
						        <heading id="{text()}" title="{text()}"/>             
						    </xsl:for-each> 
						</taxonomy>

	    </facetmap>
	</xsl:template>

	<!-- entry node -->
	<xsl:template match="entry">
		<xsl:variable name="idDoc" select="substring(child::id[1],10)"/>  
		<resource
			title="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_besoinEntreprise']/cmis:value}#{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_envieSalarie']/cmis:value}#{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_theme']/cmis:value}#{child::cmisra:object/cmis:properties/cmis:propertyInteger[@propertyDefinitionId='com:bluexml_demo_rh_Formation_dateSouhaitee']/cmis:value}#{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_duree']/cmis:value}#"
			href="{$idDoc}">
				<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_besoinEntreprise']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_besoinEntreprise']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Besoin entreprise is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_envieSalarie']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_envieSalarie']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Envie Salari� is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_theme']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_theme']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Th�me is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyInteger[@propertyDefinitionId='com:bluexml_demo_rh_Formation_dateSouhaitee']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyInteger[@propertyDefinitionId='com:bluexml_demo_rh_Formation_dateSouhaitee']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Date souhait�e is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_duree']/cmis:value!= ''">
				<map heading="{child::cmisra:object/cmis:properties/cmis:propertyString[@propertyDefinitionId='com:bluexml_demo_rh_Formation_duree']/cmis:value}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Dur�e is null"/>
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