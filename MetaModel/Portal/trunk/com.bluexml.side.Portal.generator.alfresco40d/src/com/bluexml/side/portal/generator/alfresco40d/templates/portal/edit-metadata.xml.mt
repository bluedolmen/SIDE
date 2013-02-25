<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="HavePortlet" name="validatedFilename"%>
<%if (associationPage.ID.toLowerCase().trim() == "edit-metadata" && !associationPage.generate && associationPortlet[name.toLowerCase().trim() == "edit-metadata"].nSize() > 0) {%>
<%getProperty("alf.share.paths.web-ext.components")%>template.edit-metadata.<%eContainer("Page").ID.toLowerCase()%>.xml
<%}%>

<%script type="HavePortlet" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='UTF-8'?>
<!-- Edit Metadata Form -->
<component>
   <scope>template</scope>
   <source-id>edit-metadata</source-id>
   <region-id>edit-metadata</region-id>
   <url>/components/form</url>
   <properties>
      <itemKind>node</itemKind>
      <itemId>{nodeRef}</itemId>
<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.form != null && associationPortlet.isPortletInternal.form.name != "default"){%>
      <formId><%associationPortlet.isPortletInternal.form.name%></formId>
<%}%>
      <mode>edit</mode>
      <submitType>json</submitType>
      <showCaption>true</showCaption>
      <showCancelButton>true</showCancelButton>
   </properties>
</component>
