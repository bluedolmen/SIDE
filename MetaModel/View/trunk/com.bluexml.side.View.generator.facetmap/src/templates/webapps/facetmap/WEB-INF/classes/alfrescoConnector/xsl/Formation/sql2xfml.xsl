<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com" version="2.0">
	 <!-- classes node -->
	<xsl:template match="nodes">
	    <facetmap>
	        <resources>
	            <xsl:apply-templates/>
	        </resources>
					<taxonomy title="Formation.besoinEntreprise" root-heading-title="Formation.besoinEntreprise" facetid="bluexml.demo.rh.Formation.besoinEntreprise">
						    <heading id="Besoin entreprise is null" title="Besoin entreprise is null"/> 
						    <xsl:for-each select="child::node/property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_besoinEntreprise']">          
						        <heading id="{normalize-space(current())}" title="{normalize-space(current())}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Formation.envieSalarie" root-heading-title="Formation.envieSalarie" facetid="bluexml.demo.rh.Formation.envieSalarie">
						    <heading id="Envie Salarié is null" title="Envie Salarié is null"/> 
						    <xsl:for-each select="child::node/property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_envieSalarie']">          
						        <heading id="{normalize-space(current())}" title="{normalize-space(current())}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Formation.theme" root-heading-title="Formation.theme" facetid="bluexml.demo.rh.Formation.theme">
						    <heading id="Thème is null" title="Thème is null"/> 
						    <xsl:for-each select="child::node/property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_theme']">          
						        <heading id="{normalize-space(current())}" title="{normalize-space(current())}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Formation.dateSouhaitee" root-heading-title="Formation.dateSouhaitee" facetid="bluexml.demo.rh.Formation.dateSouhaitee">
						    <heading id="Date souhaitée is null" title="Date souhaitée is null"/> 
						    <xsl:for-each select="child::node/property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_dateSouhaitee']">          
						        <heading id="{normalize-space(current())}" title="{normalize-space(current())}"/>             
						    </xsl:for-each> 
						</taxonomy>
					<taxonomy title="Formation.duree" root-heading-title="Formation.duree" facetid="bluexml.demo.rh.Formation.duree">
						    <heading id="Durée is null" title="Durée is null"/> 
						    <xsl:for-each select="child::node/property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_duree']">          
						        <heading id="{normalize-space(current())}" title="{normalize-space(current())}"/>             
						    </xsl:for-each> 
						</taxonomy>

	    </facetmap>
	</xsl:template>

	<!-- entry node -->
	<xsl:template match="node">
		<xsl:variable name="idDoc" select="substring(child::id[1],10)"/>  
		<resource
			title="{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_dateSouhaitee'])}{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_besoinEntreprise'])}{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_duree'])}{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_envieSalarie'])}{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_theme'])}"
			href="{$idDoc}">
				<xsl:choose>	
			<xsl:when test="child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_besoinEntreprise']!= ''">
				<map heading="{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_besoinEntreprise'])}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Besoin entreprise is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_envieSalarie']!= ''">
				<map heading="{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_envieSalarie'])}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Envie Salarié is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_theme']!= ''">
				<map heading="{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_theme'])}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Thème is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_dateSouhaitee']!= ''">
				<map heading="{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_dateSouhaitee'])}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Date souhaitée is null"/>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>	
			<xsl:when test="child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_duree']!= ''">
				<map heading="{normalize-space(child::property[@name='{http://www.bluexml.com/model/content/com/1.0}bluexml_demo_rh_Formation_duree'])}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="Durée is null"/>
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