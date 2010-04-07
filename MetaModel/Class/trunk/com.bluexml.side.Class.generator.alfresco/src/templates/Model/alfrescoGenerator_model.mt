<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import templates.Model.alfrescoGenerator_model_aspects
import templates.Model.alfrescoGenerator_model_classes
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>


<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/model/model.xml<%}%>
<%script type="clazz.ClassPackage" name="generator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='iso-8859-1'?>

<model name="<%name%>:model" xmlns="http://www.alfresco.org/model/dictionary/1.0" xmlns:xi="http://www.w3.org/2001/XInclude">
	<!-- Definition of the model -->

	<description><%name%></description>
	<author>Alfresco Generator</author>
	<version>1.0</version>
	
	<!-- Imports are required to allow references to definitions in other models -->
	<imports>
		<!-- Import Alfresco Definitions -->
		<import uri="http://www.alfresco.org/model/dictionary/1.0" prefix="d" />
		<import uri="http://www.alfresco.org/model/application/1.0" prefix="app"/>
		<import uri="http://www.alfresco.org/model/wcmmodel/1.0" prefix="wcm"/>
      	<import uri="http://www.alfresco.org/model/forum/1.0" prefix="fm"/>
      	<import uri="http://www.alfresco.org/model/bpm/1.0" prefix="bpm" />
      	<import uri="http://www.alfresco.org/model/content/1.0" prefix="cm" />
      	<!-- Import BlueXML Definitions -->
      	<import uri="http://www.bluexml.com/model/content/1.0" prefix="bxcm" />
	</imports>
	
	<!-- Introduction of new namespaces defined by this model -->
	<namespaces>
		
		<!-- STARTMMLOOP -->
		<namespace uri="http://www.bluexml.com/model/content/<%name%>/1.0" prefix="<%name%>" />
		<!-- ENDMMLOOP -->
	</namespaces>
	
	<constraints>
		<constraint name="<%name%>:nomenclature:comparison" type="LIST">
            <parameter name="allowedValues">
                <list>
                    <value>&lt;</value>
                    <value>&gt;</value>
                    <value>=</value>
                </list>
            </parameter>
        </constraint>
        
        <constraint name="<%name%>:nomenclature:booleanoperator" type="LIST">
            <parameter name="allowedValues">
                <list>
                    <value>AND</value>
                    <value>OR</value>
                    <value>NOT</value>
                </list>
            </parameter>
        </constraint>
        
		<constraint name="<%name%>:constraint:mail" type="REGEX">
	        <parameter name="expression"><value>^(|[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]+)$</value></parameter>
	        <parameter name="requiresMatch"><value>true</value></parameter>
	    </constraint>
		<!-- STARTMMLOOP -->
	    <%for (getAllEnumerations().nSort("name")) {%>		   
	    <constraint name="<%getFolder()%>:nomenclature:<%getQualifiedName()%>" type="LIST">
            <parameter name="allowedValues">
                <list>
			<%for (literals.nSort("name")) {%>
				<%if (value != null){%>
					<value><%value%></value>
				<%}else{%>
					<value><%name%></value>
				<%}%>
			<%}%>
                </list>
            </parameter>
        </constraint>
        <%}%>
		<!-- ENDMMLOOP -->
        
	 </constraints>
	
	<types>
		<%alfrescoGenerator_Classes()%>		
	</types>
	<aspects>
		<%alfrescoGenerator_model_aspects()%>		
	</aspects> 
	
</model>