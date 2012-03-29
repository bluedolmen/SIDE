<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename"%>
<%if (pageSet[ID.toLowerCase().trim() == "document-details" && !generate].nSize() > 0) {%><%getProperty("alf.share.paths.core.site-data")%>/template-instances/document-details.xml<%}%>

<%script type="Portal" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (pageSet[ID.toLowerCase().trim() == "document-details" && !generate].portlets.associationPortlet){%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>org/alfresco/document-details</template-type>
   <properties>
      <pageFamily>documentlibrary</pageFamily>
      <container>documentLibrary</container>
   </properties>
   <components>
      <!-- title: normal, portlet, repository & portlet+repository -->
      <component>
         <region-id>title</region-id>
         <url>/components/title/collaboration-title</url>
      </component>
      <component>
         <region-id>portlet-title</region-id>
         <url>/components/title/portlet-collaboration-title</url>
      </component>
      <component>
         <region-id>repo-title</region-id>
         <url>/components/title/simple-title</url>
         <properties>
            <title>title.repository</title>
            <subtitle>title.browser</subtitle>
         </properties>
      </component>
      <component>
         <region-id>portlet-repo-title</region-id>
         <url>/components/title/simple-title</url>
         <properties>
            <title>title.repository</title>
            <subtitle>title.browser</subtitle>
         </properties>
      </component>
      <component>
         <region-id>navigation</region-id>
         <url>/components/navigation/collaboration-navigation</url>
      </component>
      <!-- actions-common: normal & repository -->
      <component>
         <region-id>actions-common</region-id>
         <url>/components/documentlibrary/actions-common</url>
      </component>
      <component>
         <region-id>repo-actions-common</region-id>
         <url>/components/documentlibrary/actions-common</url>
      </component>
      <!-- path: normal & repository -->
      <component>
         <region-id>path</region-id>
         <url>/components/document-details/path</url>
      </component>
      <component>
         <region-id>repo-path</region-id>
         <url>/components/document-details/repo/path</url>
      </component>
      <!-- web-preview: normal & repository -->
      <component>
         <region-id>web-preview</region-id>
         <url>/components/preview/web-preview</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
            <disableI18nInputFix>false</disableI18nInputFix>
         </properties>
      </component>
      <component>
         <region-id>repo-web-preview</region-id>
         <url>/components/preview/web-preview</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
            <disableI18nInputFix>false</disableI18nInputFix>
         </properties>
      </component>
      <!-- comments: normal & repository -->
      <component>
         <region-id>comments</region-id>
         <url>/components/blog/comments</url>
         <properties>
            <editorWidth>420</editorWidth>
            <editorHeight>100</editorHeight>
         </properties>
      </component>
      <component>
         <region-id>repo-comments</region-id>
         <url>/components/blog/comments</url>
         <properties>
            <editorWidth>420</editorWidth>
            <editorHeight>100</editorHeight>
         </properties>
      </component>
      <!-- createcomment: normal & repository -->
      <component>
         <region-id>createcomment</region-id>
         <url>/components/blog/createcomment</url>
         <properties>
            <editorHeight>100</editorHeight>
            <editorWidth>420</editorWidth>
         </properties>
      </component>
      <component>
         <region-id>repo-createcomment</region-id>
         <url>/components/blog/createcomment</url>
         <properties>
            <editorHeight>100</editorHeight>
            <editorWidth>420</editorWidth>
         </properties>
      </component>
      <!-- document-metadata-header: normal & repository -->
      <component>
         <region-id>document-metadata-header</region-id>
         <url>/components/document-details/document-metadata-header</url>
      </component>
      <component>
         <region-id>repo-document-metadata-header</region-id>
         <url>/components/document-details/document-metadata-header</url>
      </component>
      <!-- document-metadata: normal & repository -->
      <component>
         <region-id>document-metadata</region-id>
         <url>/components/form</url>
         <properties>
         	<%if (name.toLowerCase().trim() == "document-details" && isPortletInternal != null && isPortletInternal.form != null && isPortletInternal.form.name != "default"){%>
            <formId><%isPortletInternal.form.name%></formId>
            <%}%>
            <itemKind>node</itemKind>
            <itemId>{nodeRef}</itemId>
            <mode>view</mode>            
         </properties>
      </component>
      <component>
         <region-id>repo-document-metadata</region-id>
         <url>/components/form</url>
         <properties>
         	<%if (name.toLowerCase().trim() == "document-details" && isPortletInternal != null && isPortletInternal.form != null && isPortletInternal.form.name != "default"){%>
            <formId><%isPortletInternal.form.name%></formId>
            <%}%>
            <itemKind>node</itemKind>
            <itemId>{nodeRef}</itemId>
            <mode>view</mode>            
         </properties>
      </component>
      <!-- document-info: normal & repository -->
      <component>
         <region-id>document-info</region-id>
         <url>/components/document-details/document-info</url>
      </component>
      <component>
         <region-id>repo-document-info</region-id>
         <url>/components/document-details/repo/document-info</url>
      </component>
      <!-- document-versions: normal & repository -->
      <component>
         <region-id>document-workflows</region-id>
         <url>/components/document-details/document-workflows</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
         </properties>
      </component>
      <component>
         <region-id>repo-document-workflows</region-id>
         <url>/components/document-details/document-workflows</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
         </properties>
      </component>
      <!-- document-versions: normal & repository -->
      <component>
         <region-id>document-versions</region-id>
         <url>/components/document-details/document-versions</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
         </properties>
      </component>
      <component>
         <region-id>repo-document-versions</region-id>
         <url>/components/document-details/document-versions</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
         </properties>
      </component>
      <!-- document-actions: normal & repository -->
      <component>
         <region-id>document-actions</region-id>
         <url>/components/document-details/document-actions</url>
      </component>
      <component>
         <region-id>repo-document-actions</region-id>
         <url>/components/document-details/repo/document-actions</url>
      </component>
      <!-- document-links: normal & repository -->
      <component>
         <region-id>document-links</region-id>
         <url>/components/document-details/document-links</url>
      </component>
      <component>
         <region-id>repo-document-links</region-id>
         <url>/components/document-details/document-links</url>
      </component>
      <component>
         <region-id>html-upload</region-id>
         <url>/components/upload/html-upload</url>
      </component>
      <component>
         <region-id>flash-upload</region-id>
         <url>/components/upload/flash-upload</url>
      </component>
      <component>
         <region-id>file-upload</region-id>
         <url>/components/upload/file-upload</url>
      </component>
   </components>
</template-instance>
<%}%>