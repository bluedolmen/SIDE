<%
metamodel http://www.kerblue.org/class/1.0

%>


<%--
  -- This file is a straight copy of Alfresco's default upload webscript, but it is kept
  -- separate for ease of maintainability.
  --%>


<%script type="clazz.ClassPackage" name="getBlueXmlUploadJsonOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.webscripts")%>org/alfresco/repository/upload/upload.post.json.ftl<%}%>

<%script type="clazz.ClassPackage" name="blueXmlUploadJson" file="<%getBlueXmlUploadJsonOutputFile%>"%>
{
   "nodeRef": "${document.nodeRef}",
   "status":
   {
      "code": 200,
      "name": "OK",
      "description" : "File uploaded successfully"
   }
}