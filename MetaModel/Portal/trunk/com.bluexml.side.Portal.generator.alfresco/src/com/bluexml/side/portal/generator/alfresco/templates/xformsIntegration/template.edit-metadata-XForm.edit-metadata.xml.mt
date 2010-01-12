<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="Portlet" name="createTemplates"%>
<%if (name.toLowerCase() =="edit-metadata"){%>
<%getProperty("alf.share.paths.core.site-data")%>/components/template.edit-metadata-XForm.edit-metadata.xml
<%}%>

<%script type="Portlet" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
<?xml version='1.0' encoding='UTF-8'?>
<component>
   <scope>template</scope>
   <region-id>edit-metadata-XForm</region-id>
   <source-id>edit-metadata</source-id>
   <url>/components/Xform</url>
   <properties>
      <itemKind>node</itemKind>
      <itemId>{nodeRef}</itemId>
      <mode>edit</mode>
      <submitType>json</submitType>
      <showCaption>true</showCaption>
      <showCancelButton>true</showCancelButton>
   </properties>
</component>