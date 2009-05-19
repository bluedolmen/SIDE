<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association
import com.bluexml.side.clazz.generator.alfresco.services.AttributeServices
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
%>

<%--
  -- This file is a patched copy of Alfresco Share's default file upload webscript, but it is kept
  -- separate for ease of maintainability. It should be removed when Alfresco release their own 
  -- "webscript that returns a list of content types (has been asked for on the wiki)"
  --%>

<%script type="clazz.ClassPackage" name="getFileUploadOutputFile"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/upload/file-upload.get.html.ftl<%}%>

<%script type="clazz.ClassPackage" name="addCustomSiteProfile" file="<%getFileUploadOutputFile%>"%>
<!-- Make sure that bothe the flash-upload and the html-upload component are included on the page -->
<script type="text/javascript">//<![CDATA[
  new Alfresco.getFileUploadInstance();

 /* BEGIN BLUEXML-PATCH [add custom upload URL] */
   Alfresco.getFileUploadInstance().defaultShowConfig.htmlUploadURL = "com/bluexml/api/upload";
   Alfresco.getFileUploadInstance().defaultShowConfig.flashUploadURL = "com/bluexml/api/upload";
 /* END BLUEXML-PATCH [add custom upload URL] */
//]]></script>


