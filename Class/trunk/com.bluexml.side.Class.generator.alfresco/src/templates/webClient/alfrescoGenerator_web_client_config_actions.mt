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
import templates.webClient.alfrescoGenerator_web_client_config_actions_nodeType
import templates.webClient.alfrescoGenerator_web_client_config_actions_browseCreateMenu
import templates.webClient.alfrescoGenerator_web_client_config_actions_createAction
import templates.webClient.alfrescoGenerator_web_client_config_actions_dialogs
import com.bluexml.side.clazz.generator.alfresco.services.AttributeServices
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
%>

<%script type="clazz.ClassPackage" name="alfrescoGenerator_config_actions" %>

	<config xmlns:xi="http://www.w3.org/2001/XInclude">
      <actions>
      <%alfrescoGenerator_createAction()%>

<!--
   		<action id="edit_doc_details">
            <permissions>
               <permission allow="true">Write</permission>
            </permissions>
            <label-id>edit</label-id>
            <image>/images/icons/edit_icon.gif</image>
            <action-listener>#{CreateTypeDialog.editProperties}</action-listener>
            <params>
               <param name="id">#{actionContext.id}</param>
            </params>
         </action>
-->   		  		
   		<action-group id="browse_create_menu">
   			<%alfrescoGenerator_browseCreateMenu()%>      		
        </action-group>  
     </actions>
     
     <dialogs>
     	<%alfrescoGenerator_actions_dialogs()%>     	
     </dialogs>
  	</config>
  	<%alfrescoGenerator_actions_nodeType()%>
