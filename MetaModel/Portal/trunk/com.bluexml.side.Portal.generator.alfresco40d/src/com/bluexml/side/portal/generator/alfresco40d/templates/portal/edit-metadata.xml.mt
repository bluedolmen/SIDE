<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename"%>
<%if (pageSet[ID.toLowerCase().trim() == "edit-metadata"].portlets.associationPortlet.nSize() > 0) {%><%getProperty("alf.share.paths.core.site-data")%>/template-instances/edit-metadata.xml<%}%>

<%script type="Portal" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (pageSet[ID.toLowerCase().trim() == "edit-metadata"].portlets.associationPortlet){%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>org/alfresco/edit-metadata</template-type>
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

      <!-- Edit Metadata Form Manager -->
      <component>
         <region-id>edit-metadata-mgr</region-id>
         <url>/components/edit-metadata/edit-metadata-mgr</url>
      </component>

      <!-- Edit Metadata Form -->
      <component>
         <region-id>edit-metadata</region-id>
         <url>/components/form</url>
         <properties>
            <itemKind>node</itemKind>
            <itemId>{nodeRef}</itemId>
            <%if (name.toLowerCase().trim() == "edit-metadata" && isPortletInternal != null && isPortletInternal.form != null && isPortletInternal.form.name != "default"){%>
            <formId><%isPortletInternal.form.name%></formId>
            <%}%>
            <mode>edit</mode>
            <submitType>json</submitType>
            <showCaption>true</showCaption>
            <showCancelButton>true</showCancelButton>
         </properties>
      </component>

   </components>
</template-instance>
<%}%>