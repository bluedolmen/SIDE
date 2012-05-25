<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename"%>
<%if (pageSet[ID.toLowerCase().trim() == "folder-details" && !generate].portlets.associationPortlet.nSize() > 0) {%><%getProperty("alf.share.paths.core.site-data")%>/template-instances/folder-details.xml<%}%>

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

      <!-- Title -->
      <component>
         <region-id>title</region-id>
         <sub-components>
            <sub-component id="default">
               <evaluations>
                  <!-- if in site: Site title -->
                  <evaluation id="site">
                     <evaluators>
                        <evaluator type="site.component.evaluator"/>
                     </evaluators>
                     <url>/components/title/collaboration-title</url>
                  </evaluation>
                  <!-- otherwise: Repository title -->
                  <evaluation id="repo">
                     <url>/components/title/simple-title</url>
                     <properties>
                        <title>title.repository</title>
                        <subtitle>title.browser</subtitle>
                     </properties>
                  </evaluation>
               </evaluations>
            </sub-component>
         </sub-components>
      </component>

      <!-- Site Navigation -->
      <component>
         <region-id>navigation</region-id>
         <sub-components>
            <sub-component id="default">
               <evaluations>
                  <!-- if in site: Site navigation -->
                  <evaluation id="site">
                     <evaluators>
                        <evaluator type="site.component.evaluator"/>
                     </evaluators>
                     <url>/components/navigation/collaboration-navigation</url>
                  </evaluation>
               </evaluations>
            </sub-component>
         </sub-components>
      </component>

      <!-- Folder Header -->
      <component>
         <region-id>node-header</region-id>
         <sub-components>
            <sub-component id="default">
               <evaluations>
                  <!-- if in site: Site header -->
                  <evaluation id="site">
                     <evaluators>
                        <evaluator type="site.component.evaluator"/>
                     </evaluators>
                     <url>/components/node-details/node-header</url>
                     <properties>
                        <nodeRef>{nodeRef}</nodeRef>
                        <rootPage>documentlibrary</rootPage>
                        <rootLabelId>path.documents</rootLabelId>
                     </properties>
                  </evaluation>
                  <!-- otherwise: Repo header -->
                  <evaluation id="repo">
                     <url>/components/node-details/node-header</url>
                     <properties>
                        <nodeRef>{nodeRef}</nodeRef>
                        <rootPage>repository</rootPage>
                        <rootLabelId>path.repository</rootLabelId>
                     </properties>
                  </evaluation>
               </evaluations>
            </sub-component>
         </sub-components>
      </component>

      <!-- Comments -->
      <component>
         <region-id>comments</region-id>
         <url>/components/comments/list</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
            <activityType>folder</activityType>
         </properties>
      </component>

      <!-- Folder Actions (incl commons) -->
      <component>
         <region-id>folder-actions</region-id>
         <url>/components/folder-details/folder-actions</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
         </properties>
      </component>
      <component>
         <region-id>actions-common</region-id>
         <url>/components/documentlibrary/actions-common</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
         </properties>
      </component>

      <!-- Folder Tags -->
      <component>
         <region-id>folder-tags</region-id>
         <sub-components>
            <sub-component id="default">
               <evaluations>
                  <!-- if in site: Site tags -->
                  <evaluation id="site">
                     <evaluators>
                        <evaluator type="site.component.evaluator"/>
                     </evaluators>
                     <url>/components/folder-details/folder-tags</url>
                     <properties>
                        <nodeRef>{nodeRef}</nodeRef>
                     </properties>
                  </evaluation>
               </evaluations>
            </sub-component>
         </sub-components>
      </component>

      <!-- Folder Links -->
      <component>
         <region-id>folder-links</region-id>
         <url>/components/folder-details/folder-links</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
         </properties>
      </component>

      <!-- Folder Metadata -->
      <component>
         <region-id>folder-metadata</region-id>
         <url>/components/folder-details/folder-metadata</url>
         <properties>
            <nodeRef>{nodeRef}</nodeRef>
            <%if (name.toLowerCase().trim() == "folder-details" && isPortletInternal != null && isPortletInternal.form != null && isPortletInternal.form.name != "default"){%>
            <formId><%isPortletInternal.form.name%></formId>
            <%}%>
         </properties>
      </component>

      <!-- Folder Permissions -->
      <component>
         <region-id>folder-permissions</region-id>
         <sub-components>
            <sub-component id="default">
               <evaluations>
                  <!-- if in site: Permissions -->
                  <evaluation id="site">
                     <evaluators>
                        <evaluator type="site.component.evaluator"/>
                     </evaluators>
                     <url>/components/folder-details/folder-permissions</url>
                     <properties>
                        <nodeRef>{nodeRef}</nodeRef>
                     </properties>
                  </evaluation>
               </evaluations>
            </sub-component>
         </sub-components>
      </component>

      <!-- Custom Code -->
      <component>
         <region-id>doclib-custom</region-id>
         <url>/components/documentlibrary/doclib-custom</url>
      </component>

   </components>
</template-instance>

<%}%>