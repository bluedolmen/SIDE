<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="HavePortlet" name="validatedFilename"%>
<%if (associationPage.ID.toLowerCase().trim() == "folder-details" && !associationPage.generate && associationPortlet[name.toLowerCase().trim() == "folder-metadata"].nSize() > 0) {%>
<%getProperty("alf.share.paths.web-ext.components")%>template.folder-metadata.<%eContainer("Page").ID.toLowerCase()%>.xml
<%}%>

<%script type="HavePortlet" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='UTF-8'?>
<!-- Folder Metadata -->
<component>
   <scope>template</scope>
   <source-id>folder-details</source-id>
   <region-id>folder-metadata</region-id>
   <url>/components/folder-details/folder-metadata</url>
   <properties>
      <nodeRef>{nodeRef}</nodeRef>
<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.form != null && associationPortlet.isPortletInternal.form.name != "default"){%>
      <formId><%associationPortlet.isPortletInternal.form.name%></formId>
<%}%>
   </properties>
</component>
