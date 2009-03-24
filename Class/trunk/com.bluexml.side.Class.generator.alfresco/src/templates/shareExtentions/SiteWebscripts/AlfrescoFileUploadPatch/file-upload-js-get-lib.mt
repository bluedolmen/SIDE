<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.generator.alfresco.services.AttributeServices
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
%>

<%--
  -- This file is a patched copy of Alfresco Share's default flash upload webscript, but it is kept
  -- separate for ease of maintainability. It is common to both html and flash upload implementations.
  -- It should be removed when Alfresco release their own "webscript that returns a list of content
  -- types (has been asked for on the wiki)".
  --%>

<%script type="clazz.ClassPackage" name="getHtmlOrFlashUploadJs"%>
// A webscript that returns a list has been asked for on the wiki.
var contentTypes = [{id: "Content", value: "Content"}];
if (contentTypes === undefined || contentTypes.length < 1)
{
   status.code = 400;
   status.message = "Could not get contentTypes from the system";
   status.redirect = true;
}

//--START BLUEXML-PATCH [add custom content types]
var availableContentTypes = [
<%getCustomContentTypesAsJSON%>];
   
contentTypes = contentTypes.concat(availableContentTypes);   
//--END BLUEXML-PATCH [add custom content types]

// Prepare the model
model.contentTypes = contentTypes;


<%script type="clazz.ClassPackage" name="getCustomContentTypesAsJSON"%>
<%getAllClasses().push()%>
<%for (peek()){%>
   <%getContentTypeJSON()%> <%if (i() < peek().nSize() - 1) {%>,<%}%>
<%}%>

<%script type="clazz.Clazz" name="getContentTypeJSON"%>
{id: "<%getFolder()%>:<%getQualifiedName()%>", value: "<%getLabel()%>"}

