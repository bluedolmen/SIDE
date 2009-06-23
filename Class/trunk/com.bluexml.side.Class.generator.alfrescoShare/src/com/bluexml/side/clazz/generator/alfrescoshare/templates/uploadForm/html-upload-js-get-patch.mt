<%
metamodel http://www.kerblue.org/class/1.0

import com.bluexml.side.clazz.generator.alfrescoshare.templates.uploadForm.file-upload-js-get-lib
%>

<%--
  -- This file is a patched copy of Alfresco Share's default html upload webscript, but it is kept
  -- separate for ease of maintainability. It should be removed when Alfresco release their own 
  -- "webscript that returns a list of content types (has been asked for on the wiki)"
  --%>
  
<%script type="clazz.ClassPackage" name="getHtmlUploadJsOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/upload/html-upload.get.js<%}%>

<%script type="clazz.ClassPackage" name="htmlUploadJs" file="<%getHtmlUploadJsOutputFile%>"%>
<%getHtmlOrFlashUploadJs()%>