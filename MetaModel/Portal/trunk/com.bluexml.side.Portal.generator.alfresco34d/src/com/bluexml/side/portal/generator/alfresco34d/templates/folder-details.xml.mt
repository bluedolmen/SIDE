<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename"%>
<%if (pageSet[ID.toLowerCase().trim() == "folder-details" && !generate].nSize() > 0) {%><%getProperty("alf.share.paths.core.site-data")%>/template-instances/folder-details.xml<%}%>

<%script type="Portal" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (pageSet[ID.toLowerCase().trim() == "folder-details" && !generate].portlets.associationPortlet){%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>org/alfresco/folder-details</template-type>
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
         <url>/components/folder-details/path</url>
      </component>            
      <component>
         <region-id>repo-path</region-id>
         <url>/components/folder-details/repo/path</url>
      </component>
      <!-- folder-metadata-header: normal & repository -->
      <component>
         <region-id>folder-metadata-header</region-id>
         <url>/components/folder-details/folder-metadata-header</url>
      </component>
      <component>
         <region-id>repo-folder-metadata-header</region-id>
         <url>/components/folder-details/folder-metadata-header</url>
      </component>
      <!-- folder-metadata: normal & repository -->
      <component>
         <region-id>folder-metadata</region-id>
         <url>/components/form</url>
         <properties>
         	<%if (name.toLowerCase().trim() == "folder-details" && isPortletInternal != null && isPortletInternal.form != null && isPortletInternal.form.name != "default"){%>
            <formId><%isPortletInternal.form.name%></formId>
            <%}%>
            <itemKind>node</itemKind>
            <itemId>{nodeRef}</itemId>
            <mode>view</mode>
         </properties>
      </component>
      <component>
         <region-id>repo-folder-metadata</region-id>
         <url>/components/form</url>
         <properties>
         	<%if (name.toLowerCase().trim() == "folder-details" && isPortletInternal != null && isPortletInternal.form != null && isPortletInternal.form.name != "default"){%>
            <formId><%isPortletInternal.form.name%></formId>
            <%}%>
            <itemKind>node</itemKind>
            <itemId>{nodeRef}</itemId>
            <mode>view</mode>
         </properties>
      </component>
      <!-- folder-info: normal & repository -->
      <component>
         <region-id>folder-info</region-id>
         <url>/components/folder-details/folder-info</url>
      </component>
      <component>
         <region-id>repo-folder-info</region-id>
         <url>/components/folder-details/repo/folder-info</url>
      </component>
      <!-- folder-actions: normal & repository -->
      <component>
         <region-id>folder-actions</region-id>
         <url>/components/folder-details/folder-actions</url>
      </component>
      <component>
         <region-id>repo-folder-actions</region-id>
         <url>/components/folder-details/repo/folder-actions</url>
      </component>
      <!-- folder-links: normal & repository -->
      <component>
         <region-id>folder-links</region-id>
         <url>/components/folder-details/folder-links</url>
      </component>
      <component>
         <region-id>repo-folder-links</region-id>
         <url>/components/folder-details/folder-links</url>
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
   </components>
</template-instance>
<%}%>