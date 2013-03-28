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
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.client.webscripts")%>/org/alfresco/slingshot/documentlibrary/doclist.get.desc.xml<%}%>
<%script type="clazz.ClassPackage" name="doclist_get_desc_xml" file="<%validatedFilename%>"%>
<webscript>
  <shortname>doclist</shortname>
  <description>Document List Component - doclist data webscript</description>
  <url>/slingshot/doclib/doclist/node/{store_type}/{store_id}/{id}</url>
  <url>/slingshot/doclib/doclist/{type}/site/{site}/{container}/{path}</url>
  <url>/slingshot/doclib/doclist/{type}/site/{site}/{container}</url>
  <url>/slingshot/doclib/doclist/{type}/node/{store_type}/{store_id}/{id}/{path}</url>
  <url>/slingshot/doclib/doclist/{type}/node/{store_type}/{store_id}/{id}</url>
  <format default="json">argument</format>
  <authentication>user</authentication>
  <transaction>required</transaction>
</webscript>
