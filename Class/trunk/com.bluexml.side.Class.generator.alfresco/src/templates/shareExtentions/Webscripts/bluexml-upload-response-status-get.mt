<%
metamodel http://www.kerblue.org/class/1.0

%>


<%--
  -- This file is a straight copy of Alfresco's default upload webscript, but it is kept
  -- separate for ease of maintainability.
  --%>


<%script type="clazz.ClassPackage" name="getBlueXmlUploadStatusOutputFile"%>
<%getProperty("alf.paths.extension.webscripts")%>com/bluexml/upload/bluexml-upload.get.html.status.ftl

<%script type="clazz.ClassPackage" name="blueXmlUploadStatus" file="<%getBlueXmlUploadStatusOutputFile%>"%>
<html>
<head>
   <title>Upload failure</title>
</head>
<body>
<#if (args.failureCallback?exists)>
   <script type="text/javascript">
      ${args.failureCallback}.call(${args.failureScope}, {});
   </script>
</#if>
</body>
</html>