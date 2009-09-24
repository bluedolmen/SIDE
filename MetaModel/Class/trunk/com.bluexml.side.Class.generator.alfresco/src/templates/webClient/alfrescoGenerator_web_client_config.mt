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
import templates.webClient.alfrescoGenerator_web_client_config_actions_browseCreateMenu
import templates.webClient.alfrescoGenerator_web_client_config_actions_createAction
import templates.webClient.alfrescoGenerator_web_client_config_actions_dialogs
import templates.webClient.alfrescoGenerator_web_client_config_actions_nodeType
import templates.webClient.alfrescoGenerator_web_client_config_actions
import templates.webClient.alfrescoGenerator_web_client_config_actionWizards_aspects
import templates.webClient.alfrescoGenerator_web_client_config_advancedSearch
import templates.webClient.alfrescoGenerator_web_client_config_class_typeNames
import templates.webClient.alfrescoGenerator_web_client_config_contentWizards
import templates.webClient.alfrescoGenerator_web_client_config_simpleSearchAA
import templates.webClient.alfrescoGenerator_web_client_config_spaceWizards
import templates.webClient.alfrescoGenerator_web_client_config_dynamic
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.ClassAlfrescoGenerator

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/web-client-config-custom.xml<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='iso-8859-1'?>
<alfresco-config>
	
	<%-- GENERATE THE POSSIBILITY OF CREATE CONTENTS  --%>
	<config evaluator="string-compare" condition="Content Wizards">
		<content-types>
		<%alfrescoGenerator_contentWizards()%>
			
		</content-types>
	</config>

	<%-- GENERATE THE POSSIBILITY OF CREATE SPACES  --%>
	<config evaluator="string-compare" condition="Space Wizards">
		<folder-types>
		<%alfrescoGenerator_spaceWizards()%>
			
		</folder-types>
	</config>
		
	<config evaluator="string-compare" condition="Action Wizards">
	    <!-- The list of aspects to show in the add/remove features action -->
    	<!-- and the has-aspect condition -->	
		<aspects>
		<%alfrescoGenerator_actionWizards_aspects()%>
			
		</aspects>

		<!-- The list of types shown in the is-subtype condition -->
		<subtypes>
		<%alfrescoGenerator_class_typeNames()%>
			
		</subtypes>		

		<!-- The list of content and/or folder types shown in the specialise-type action -->
		<specialise-types>
		<%alfrescoGenerator_class_typeNames()%>
			
		</specialise-types>		

	</config>
	
	<config evaluator="string-compare" condition="Advanced Search">
		<advanced-search>
			<content-types>
			<%alfrescoGenerator_class_typeNames()%>
				
			</content-types>
			<custom-properties>
			<%alfrescoGenerator_advancedSearch()%>
				
			</custom-properties>
		</advanced-search>
	</config>
	
	<%-- Additional attributes to the Simple Search query --%>
	<config>
      <client>
         <simple-search-additional-attributes>
         <%alfrescoGenerator_simpleSearchAA()%> 
		 	
         </simple-search-additional-attributes>
      </client>
   </config>
	
	<%--LANGUAGES IN THE LOGIN BOX  --%>
	<config evaluator="string-compare" condition="Languages" replace="true">
		<languages>
			<language locale="fr_FR">French</language>	  		
	  	</languages>
  	</config>
  	
  	<%-- AVAILABLE DASHLETS --%>
  	
	<%alfrescoGenerator_dynamic()%>
	<%alfrescoGenerator_config_actions() %>
   
</alfresco-config>
