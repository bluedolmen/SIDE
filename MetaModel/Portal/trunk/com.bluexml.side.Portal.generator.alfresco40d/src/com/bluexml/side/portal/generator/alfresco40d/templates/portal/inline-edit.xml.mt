<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename"%>
<%if (pageSet[ID.toLowerCase().trim() == "inline-edit"].portlets.associationPortlet.nSize() > 0) {%><%getProperty("alf.share.paths.core.site-data")%>/template-instances/inline-edit.xml<%}%>

<%script type="Portal" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<%for (pageSet[ID.toLowerCase().trim() == "inline-edit"].portlets.associationPortlet){%>
<%if (name.toLowerCase().trim() == "inline-edit" && isPortletInternal != null) {%>
<?xml version='1.0' encoding='UTF-8'?>
<template-instance>
   <template-type>org/alfresco/inline-edit</template-type>
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
                  <!-- if in site: Site title -->
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

      <!-- Inline Edit Form Manager -->
      <component>
         <region-id>inline-edit-mgr</region-id>
         <url>/components/inline-edit/inline-edit-mgr</url>
      </component>

      <!-- Inline Edit Form -->
      <component>
         <region-id>inline-edit</region-id>
         <url>/components/form</url>
         <properties>
         	<%if (name.toLowerCase().trim() == "inline-edit" && isPortletInternal != null && isPortletInternal.form != null && isPortletInternal.form.name != "default"){%>
            <formId><%isPortletInternal.form.name%></formId>
            <%}%>
            <itemKind>node</itemKind>
            <itemId>{nodeRef}</itemId>
            <mode>edit</mode>
            <submitType>json</submitType>
            <showCaption>true</showCaption>
            <showCancelButton>true</showCancelButton>
         </properties>
      </component>

   </components>
</template-instance>
<%}%>
<%}%>