<%
metamodel http://www.kerblue.org/class/1.0

%>


<%--
  -- This file is a straight copy of Alfresco's default upload webscript, but it is kept
  -- separate for ease of maintainability.
  --%>


<%script type="clazz.ClassPackage" name="getBlueXmlUploadHtmlOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.paths.extension.webscripts")%>org/alfresco/repository/upload/upload.post.html.ftl<%}%>

<%script type="clazz.ClassPackage" name="blueXmlUploadHtml" file="<%getBlueXmlUploadHtmlOutputFile%>"%>
<html>
<head>
   <title>Upload success</title>
</head>
<body>
<#if (args.successCallback?exists)>
   <script type="text/javascript">
      ${args.successCallback}.call(${args.successScope},
      {
         nodeRef: "${document.nodeRef}"
      });
   </script>
</#if>
</body>
</html>