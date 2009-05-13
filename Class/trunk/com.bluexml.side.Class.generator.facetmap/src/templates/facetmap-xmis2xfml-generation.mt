<%
metamodel http://www.kerblue.org/class/1.0
import com.bluexml.side.clazz.generator.facetmap.ClassFacetmapGenerator

%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
	./generation/xsl/cmis2xfml.xsl

<%script type="clazz.Clazz" name="taxonomy"%>
	<%for (getAllAttributes()){%>
			<taxonomy title="<%args(0)%>-><%getLabel()%>" root-heading-title="<%args(0)%>-><%getLabel()%>" facetid="<%getFullName()%>">
				<!-- criteria -->
			    <!-- On ne prend pas en compte les différentes occurences d'un même critère car facetmap les réunit. -->
			    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:propertyString[@cmis:name='<%getFullName()%>']/cmis:value">          
			        <heading id="{current()}" title="{current()}"/>             
			    </xsl:for-each> 
			</taxonomy>
	<%}%>
	
<%script type="clazz.Clazz" name="ressource"%>
	<%for (getAllAttributes()){%>
           <map heading="{child::cmis:object/cmis:properties/cmis:propertyString[@cmis:name='<%getFullName()%>']/cmis:value}"/>
	<%}%>

<%script type="clazz.ClassPackage" name="facetmapGenerator"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com" version="2.0">
	<xsl:include href="../cmisTransformProperties.xml"/>
	 <!-- classes node -->
	<xsl:template match="classes">
	    <facetmap>
	        <resources>
	            <xsl:apply-templates/>
	        </resources>
<%for (getAllClasses()) {%><%taxonomy(getLabel())%><%}%>
	    </facetmap>
	</xsl:template>

	<!-- entry node -->
	<xsl:template match="entry">
		<xsl:variable name="ref_share" select="concat($host,$DLUrl,substring(child::id[1],10))"/>
		<xsl:variable name="ref_alfresco" select="concat($host,$ViewURL,substring(child::id[1],10),'/',child::title[1])"/>       
		<resource title="{child::title[1]}" href="{concat($ref_alfresco,'+',$ref_share)}">
<%for (getAllClasses()) {%><%ressource()%><%}%>
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
