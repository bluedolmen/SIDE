<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com" version="2.0">
	<xsl:include href="../cmisTransformProperties.xml"/>
	 <!-- classes node -->
	<xsl:template match="classes">
	    <facetmap>
	        <resources>
	            <xsl:apply-templates/>
	        </resources>
			<taxonomy title="Contrat de travail->Type" root-heading-title="Contrat de travail->Type" facetid="com.bluexml.demo.rh.ContratDeTravail.type">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.type']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Contrat de travail->Date d'embauche" root-heading-title="Contrat de travail->Date d'embauche" facetid="com.bluexml.demo.rh.ContratDeTravail.dateDembauche">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.dateDembauche']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Contrat de travail->Statut" root-heading-title="Contrat de travail->Statut" facetid="com.bluexml.demo.rh.ContratDeTravail.statut">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.statut']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Contrat de travail->Niveau Contrat" root-heading-title="Contrat de travail->Niveau Contrat" facetid="com.bluexml.demo.rh.ContratDeTravail.niveauContrat">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.niveauContrat']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Contrat de travail->Fonction" root-heading-title="Contrat de travail->Fonction" facetid="com.bluexml.demo.rh.ContratDeTravail.fonction">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.fonction']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Absence->Date de début" root-heading-title="Absence->Date de début" facetid="com.bluexml.demo.rh.Absence.dateDeDebut">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Absence.dateDeDebut']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Absence->Durée" root-heading-title="Absence->Durée" facetid="com.bluexml.demo.rh.Absence.duree">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Absence.duree']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Absence->Motif" root-heading-title="Absence->Motif" facetid="com.bluexml.demo.rh.Absence.motif">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Absence.motif']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Absence->Nombre" root-heading-title="Absence->Nombre" facetid="com.bluexml.demo.rh.Absence.nombre">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Absence.nombre']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Formation->Besoin entreprise" root-heading-title="Formation->Besoin entreprise" facetid="com.bluexml.demo.rh.Formation.besoinEntreprise">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.besoinEntreprise']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Formation->Envie Salarié" root-heading-title="Formation->Envie Salarié" facetid="com.bluexml.demo.rh.Formation.envieSalarie">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.envieSalarie']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Formation->Thème" root-heading-title="Formation->Thème" facetid="com.bluexml.demo.rh.Formation.theme">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.theme']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Formation->Date souhaitée" root-heading-title="Formation->Date souhaitée" facetid="com.bluexml.demo.rh.Formation.dateSouhaitee">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.dateSouhaitee']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
			<taxonomy title="Formation->Durée" root-heading-title="Formation->Durée" facetid="com.bluexml.demo.rh.Formation.duree">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.duree']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
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
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.age']/cmis:value">          
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
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.type']/cmis:value}"></map>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.dateDembauche']/cmis:value}"></map>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.statut']/cmis:value}"></map>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.niveauContrat']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.ContratDeTravail.fonction']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Absence.dateDeDebut']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Absence.duree']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Absence.motif']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Absence.nombre']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.besoinEntreprise']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.envieSalarie']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.theme']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.dateSouhaitee']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Formation.duree']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.nom']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.prenom']/cmis:value}"/>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='com.bluexml.demo.rh.Personne.age']/cmis:value}"/>
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