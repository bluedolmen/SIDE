<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
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
<%getAllClasses().push()%>
<%for (peek()){%>
   <%getContentTypeJSON()%> <%if (i() < peek().nSize() - 1) {%>,<%}%>
<%}%>

<%script type="clazz.Clazz" name="getContentTypeJSON"%>
{id: "<%getFolder()%>:<%getQualifiedName()%>", value: "<%getLabel()%>"}

