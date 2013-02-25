<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="HavePortlet" name="validatedFilename"%>
<%if (associationPage.ID.toLowerCase().trim() == "document-details" && !associationPage.generate && associationPortlet[name.toLowerCase().trim() == "document-details" || name.toLowerCase().trim() == "document-metadata"].nSize() > 0) {%>
<%getProperty("alf.share.paths.web-ext.components")%>template.document-metadata.<%eContainer("Page").ID.toLowerCase()%>.xml
<%}%>

<%script type="HavePortlet" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='UTF-8'?>
<!-- Document Metadata -->
<component>
   <scope>template</scope>
   <source-id>document-details</source-id>
   <region-id>document-metadata</region-id>
   <url>/components/document-details/document-metadata</url>
   <properties>
      <nodeRef>{nodeRef}</nodeRef>
	<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.form != null && associationPortlet.isPortletInternal.form.name != "default"){%>
      <formId><%associationPortlet.isPortletInternal.form.name%></formId>
	<%}%>
   </properties>
</component>