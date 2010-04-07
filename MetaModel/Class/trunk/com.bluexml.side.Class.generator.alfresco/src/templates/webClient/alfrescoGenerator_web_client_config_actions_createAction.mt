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
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="clazz.ClassPackage" name="alfrescoGenerator_createAction" %>

      <%for (getAllPackages().nSort("name")){%>	      	
		 <%for (classSet.nSort("name")){%>		 	
		 <%if (metainfo[key.equalsIgnoreCase("simplifyCreation")].nSize()>0 && metainfo[key.equalsIgnoreCase("simplifyCreation")].nFirst().value.equalsIgnoreCase("true")) {%>
         <action id="create_<%getQualifiedName()%>">
            <permissions>
               <permission allow="true">CreateChildren</permission>
            </permissions>
            <label-id>create_<%getQualifiedName()%></label-id>
            <!-- <%startUserCode%> Action image of <%getQualifiedName()%> -->
             <image>/images/icons/add_item.gif</image>
             <!-- <%endUserCode%> Action image of <%getQualifiedName()%> -->
            <image>/images/icons/<%getQualifiedName()%>.gif</image>
            <action>dialog:create_<%getQualifiedName()%></action>
            <action-listener>#{DialogManager.setupParameters}</action-listener> 
            <params>
               <param name="typeContent">{<%getNameSpace()%>}<%getQualifiedName()%></param>
            </params>
   		</action>
   		<%}%>
  		<%}%>
  		<%}%>
