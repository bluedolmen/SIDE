<%
metamodel http://www.kerblue.org/class/1.0

import templates.shareExtentions.SiteWebscripts.AlfrescoFileUploadPatch.file-upload-js-get-lib
%>

<%--
  -- This file is a patched copy of Alfresco Share's default flash upload webscript, but it is kept
  -- separate for ease of maintainability. It should be removed when Alfresco release their own 
  -- "webscript that returns a list of content types (has been asked for on the wiki)"
  --%>


<%script type="clazz.ClassPackage" name="getFlashUploadJsOutputFile"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/upload/flash-upload.get.js

<%script type="clazz.ClassPackage" name="flashUploadJs" file="<%getFlashUploadJsOutputFile%>"%>
<%getHtmlOrFlashUploadJs()%>

