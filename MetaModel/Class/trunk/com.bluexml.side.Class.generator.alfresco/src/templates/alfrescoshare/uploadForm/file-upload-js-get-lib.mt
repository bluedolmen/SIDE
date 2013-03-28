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
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%--
  -- This file is a patched copy of Alfresco Share's default flash upload webscript, but it is kept
  -- separate for ease of maintainability. It is common to both html and flash upload implementations.
  -- It should be removed when Alfresco release their own "webscript that returns a list of content
  -- types (has been asked for on the wiki)".
  --%>

<%script type="clazz.ClassPackage" name="getHtmlOrFlashUploadJs"%>
/**
 * Custom content types
 */
function getContentTypes()
{
   // TODO: Data webscript call to return list of available types
   var contentTypes = [
   {
      id: "cm:content",
      value: "cm_content"
   }];

   return contentTypes;
}
//--START BLUEXML-PATCH [add custom content types]
var availableContentTypes = [
<%getCustomContentTypesAsJSON%>];
   
var contentTypes = getContentTypes().concat(availableContentTypes);   
//--END BLUEXML-PATCH [add custom content types]
model.contentTypes = contentTypes;
<%script type="clazz.ClassPackage" name="getCustomContentTypesAsJSON"%>
<%filterOnlyContent().nSort("name").push()%>
<%for (peek()){%>
   <%getContentTypeJSON()%> <%if (i() < peek().nSize() - 1) {%>,<%}%>
<%}%>

<%script type="clazz.Clazz" name="getContentTypeJSON"%>
{id: "<%getPrefixedQName()%>", value: "<%getLabel()%>"}
