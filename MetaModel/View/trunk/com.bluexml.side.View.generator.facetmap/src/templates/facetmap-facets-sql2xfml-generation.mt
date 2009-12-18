<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.view.generator.facetmap.ViewFacetmapGenerator
%>

<%script type="view.FacetMap" name="validatedFilename"%>
	./webapps/facetmap/WEB-INF/classes/alfrescoConnector/xsl/<%name%>/sql2xfml.xsl

<%script type="view.FieldElement" name="contentModelUrl"%>
{http://www.bluexml.com/model/content/com/1.0}

<%-- get the correct path to the cmis element when you got a FieldElement --%>
<%script type="view.FieldElement" name="Path"%>	
property[@name='<% contentModelUrl %><%mapTo.filter("Attribute").getFullName().replaceAll("\.","_")%>']

<%script type="view.FieldElement" name="getFieldPath"%>	
child::<% Path() %>

<%script type="view.FieldElement" name="getFieldPathWithNode"%>	
child::node/<% Path() %>

<%script type="view.FacetMap" name="taxonomy"%>
		<%for (getFields()){%>
			<taxonomy title="<%current("FacetMap").viewOf.filter("Clazz").getLabel()%>.<%mapTo.filter("Attribute").name%>" root-heading-title="<%current("FacetMap").viewOf.filter("Clazz").getLabel()%>.<%mapTo.filter("Attribute").name%>" facetid="<%mapTo.filter("Attribute").getFullName()%>">
				    <heading id="<%mapTo.filter("TitledNamedClassModelElement").getLabel()%> is null" title="<%mapTo.filter("TitledNamedClassModelElement").getLabel()%> is null"/> 
				    <xsl:for-each select="<% getFieldPathWithNode() %>">          
				        <heading id="{normalize-space(current())}" title="{normalize-space(current())}"/>             
				    </xsl:for-each> 
				</taxonomy>
		<%}%>
	
<%script type="view.FacetMap" name="ressource"%>
	<%for (getFields()){%>
		<xsl:choose>	
			<xsl:when test="<% getFieldPath() %>!= ''">
				<map heading="{normalize-space(<% getFieldPath() %>)}"/>
			</xsl:when>
			<xsl:otherwise>
				<map heading="<%mapTo.filter("TitledNamedClassModelElement").getLabel()%> is null"/>
			</xsl:otherwise>
		</xsl:choose>
	<%}%>
	
<%script type="view.FacetMap" name="sql2xfmlGenerator"  file="<%validatedFilename%>" %>
<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com" version="2.0">
	 <!-- classes node -->
	<xsl:template match="nodes">
	    <facetmap>
	        <resources>
	            <xsl:apply-templates/>
	        </resources>
		<%taxonomy()%>
	    </facetmap>
	</xsl:template>

	<!-- entry node -->
	<xsl:template match="node">
		<xsl:variable name="idDoc" select="substring(child::id[1],10)"/>  
		<%-- Use the fields of the innerview in the facetmap and te suffix and prefis to make the title --%>   
		<resource
			title="<%for (getInnerView().getFields().nSort()){%><%if (prefix !=null){%><%prefix%><%}%>{normalize-space(<% getFieldPath() %>)}<%if (suffix !=null){%><%suffix%><%}%><%}%>"
			href="{$idDoc}">
		<%-- Get all the fields of the facetmap and insert for each a piece of xsl that tests if
		the element is empty.
		if yes it inserts a standard value
		if no it inserts its value--%>
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
