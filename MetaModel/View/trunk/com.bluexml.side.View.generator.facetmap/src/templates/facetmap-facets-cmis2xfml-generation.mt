<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./facets/WEB-INF/xsl/cmis2xfml.xsl

<%script type="view.FacetMap" name="taxonomy"%>
		<%for (getFields()){%>
			<taxonomy title="<%current("FacetMap").viewOf.filter("Clazz").getLabel()%>.<%mapTo.filter("Attribute").name%>" root-heading-title="<%current("FacetMap").viewOf.filter("Clazz").getLabel()%>.<%mapTo.filter("Attribute").name%>" facetid="<%mapTo.filter("Attribute").getFullName()%>">
					<!-- criteria -->
				    <!-- On ne prend pas en compte les diff�rentes occurences d'un m�me crit�re car facetmap les r�unit. -->
				    <xsl:for-each select="child::entry/cmis:object/cmis:properties/cmis:property<%if mapTo.filter("Attribute").typ!="int"{%><%mapTo.filter("Attribute").typ%><%}else{%>Integer<%}%>[@cmis:name='<%mapTo.filter("Attribute").getFullName()%>']/cmis:value">          
				        <heading id="{current()}" title="{current()}"/>             
				    </xsl:for-each> 
				</taxonomy>
		<%}%>
	
<%script type="view.FacetMap" name="ressource"%>
	<%for (getFields()){%>
		<map heading="{child::cmis:object/cmis:properties/cmis:property<%if mapTo.filter("Attribute").typ!="int"{%><%mapTo.filter("Attribute").typ%><%}else{%>Integer<%}%>[@cmis:name='<%mapTo.filter("Attribute").getFullName()%>']/cmis:value}"/>
	<%}%>
	

<%script type="view.FacetMap" name="cmis2xfmlGenerator"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com" version="2.0">
	<xsl:include href="../cmisTransformProperties.xml"/>
	 <!-- classes node -->
	<xsl:template match="classes">
	    <facetmap>
	        <resources>
	            <xsl:apply-templates/>
	        </resources>
		<%taxonomy()%>
	    </facetmap>
	</xsl:template>

	<!-- entry node -->
	<xsl:template match="entry">
		<xsl:variable name="idDoc" select="substring(child::id[1],10)"/>     
		<resource
			title="<%current("FacetMap").viewOf.filter("Clazz").getLabel()%> :<%for (getInnerView().getFields()){%> {child::cmis:object/cmis:properties/cmis:property<%if mapTo.filter("Attribute").typ!="int"{%><%mapTo.filter("Attribute").typ%><%}else{%>Integer<%}%>[@cmis:name='<%mapTo.filter("Attribute").getFullName()%>']/cmis:value}<%}%>"
			idDoc="{$idDoc}">
		<%ressource()%>
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
