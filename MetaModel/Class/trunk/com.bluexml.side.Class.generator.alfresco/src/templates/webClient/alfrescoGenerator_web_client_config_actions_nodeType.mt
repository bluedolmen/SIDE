<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
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

<%script type="clazz.ClassPackage" name="alfrescoGenerator_actions_nodeType"%>

  	<%for (getAllClasses().nSort("name")){%>
  	<%if (metainfo[key.equalsIgnoreCase("isContainer")].nSize()==0 && !abstract){%>
  	<config evaluator="node-type" condition="<%getPrefixedQName()%>">
      <actions>
         <action-group id="document_browse_menu">
            <show-link>false</show-link>
            <action idref="cut_node" hide="true" />
            <action idref="copy_node" hide="true" />
             <action idref="update_doc" hide="true" />
            <action idref="cancelcheckout_doc"  hide="true"/>
            <action idref="approve_doc"  hide="true"/>
            <action idref="reject_doc"  hide="true"/>
         </action-group>
   
   		<action-group id="document_browse">
            <show-link>false</show-link>
            <style-class>inlineAction</style-class>
            <action idref="edit_doc_http" hide="true" />
            <action idref="edit_doc_webdav"  hide="true"/>
            <action idref="edit_doc_cifs"  hide="true"/>
            <action idref="checkout_doc" hide="true" />
            <action idref="checkin_doc"  hide="true"/>
            <!--<action idref="edit_doc_details"/>-->
            <action idref="details_doc" />
            <action idref="delete_doc" />
         </action-group>
		</actions>
   </config>
  	<%}%>
   <%}%>
