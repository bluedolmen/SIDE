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
<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename"%>
<%if (pageSet[ID.toLowerCase().trim() == "inline-edit"].portlets.associationPortlet.nSize() > 0) {%><%getProperty("alf.share.paths.core.site-data")%>/template-instances/inline-edit.xml<%}%>

<%script type="Portal" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (pageSet[ID.toLowerCase().trim() == "inline-edit"].portlets.associationPortlet){%>
<%if (name.toLowerCase().trim() == "inline-edit" && isPortletInternal != null) {%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>org/alfresco/inline-edit</template-type>
   <properties>
      <pageFamily>documentlibrary</pageFamily>
      <container>documentLibrary</container>
   </properties>
   <components>
      <!-- title: normal, portlet, repository & portlet+repository -->
      <component>
         <region-id>title</region-id>
         <url>/components/title/collaboration-title</url>
      </component>
      <component>
         <region-id>portlet-title</region-id>
         <url>/components/title/portlet-collaboration-title</url>
      </component>
      <component>
         <region-id>repo-title</region-id>
         <url>/components/title/simple-title</url>
         <properties>
            <title>title.repository</title>
            <subtitle>title.browser</subtitle>
         </properties>
      </component>
      <component>
         <region-id>portlet-repo-title</region-id>
         <url>/components/title/simple-title</url>
         <properties>
            <title>title.repository</title>
            <subtitle>title.browser</subtitle>
         </properties>
      </component>
      <component>
         <region-id>navigation</region-id>
         <url>/components/navigation/collaboration-navigation</url>
      </component>
      <!-- inline-edit-mgr: normal & repository -->
      <component>
         <region-id>inline-edit-mgr</region-id>
         <url>/components/inline-edit/inline-edit-mgr</url>
      </component>
      <component>
         <region-id>repo-inline-edit-mgr</region-id>
         <url>/components/inline-edit/inline-edit-mgr</url>
      </component>
      <!-- inline-edit: normal & repository -->
      <component>
         <region-id>inline-edit</region-id>
         <url>/components/form</url>
         <properties>
            <%if (name.toLowerCase().trim() == "inline-edit" && isPortletInternal != null && isPortletInternal.form != null && isPortletInternal.form.name != "default"){%>
            <formId><%isPortletInternal.form.name%></formId>
            <%}%>
            <itemKind>node</itemKind>
            <itemId>{nodeRef}</itemId>
            <mode>edit</mode>
            <submitType>json</submitType>
            <showCaption>true</showCaption>
            <showCancelButton>true</showCancelButton>
         </properties>
      </component>
      <component>
         <region-id>repo-inline-edit</region-id>
         <url>/components/form</url>
         <properties>
            <%if (name.toLowerCase().trim() == "inline-edit" && isPortletInternal != null && isPortletInternal.form != null && isPortletInternal.form.name != "default"){%>
            <formId><%isPortletInternal.form.name%></formId>
            <%}%>
            <itemKind>node</itemKind>
            <itemId>{nodeRef}</itemId>
            <mode>edit</mode>
            <submitType>json</submitType>
            <showCaption>true</showCaption>
            <showCancelButton>true</showCancelButton>
         </properties>
      </component>      
   </components>
</template-instance>
<%}%>
<%}%>
