<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="HavePortlet" name="validatedFilename"%>
<%if (associationPage.ID.toLowerCase().trim() == "inline-edit" && !associationPage.generate && associationPortlet[name.toLowerCase().trim() == "inline-edit"].nSize() > 0) {%>
<%getProperty("alf.share.paths.web-ext.components")%>template.inline-edit.inline-edit.xml
<%}%>

<%script type="HavePortlet" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<!-- Inline Edit Form -->
<component>
   <scope>template</scope>
   <source-id>inline-edit</source-id>
   <region-id>inline-edit</region-id>
   <url>/components/form</url>
   <properties>
<%if (associationPortlet.isPortletInternal != null && associationPortlet.isPortletInternal.form != null && associationPortlet.isPortletInternal.form.name != "default"){%>
      <formId><%associationPortlet.isPortletInternal.form.name%></formId>
<%}%>
  <itemKind>node</itemKind>
  <itemId>{nodeRef}</itemId>
  <mode>edit</mode>
  <submitType>json</submitType>
  <showCaption>true</showCaption>
  <showCancelButton>true</showCancelButton>
</properties>
