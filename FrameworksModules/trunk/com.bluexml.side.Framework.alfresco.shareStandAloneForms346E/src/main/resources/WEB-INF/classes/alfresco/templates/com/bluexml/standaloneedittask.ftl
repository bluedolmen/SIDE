<#include "../../org/alfresco/include/alfresco-template.ftl" />
<#include "standalone-header.ftl" />

<@templateHeader>
   <@addStandaloneTemplateHeader/>
   
   <#if page.url.args.nodeRef??>
   <@script type="text/javascript" src="${page.url.context}/res/templates/document-details/document-details.js"></@script>
   </#if>
</@templateHeader>

<@templateBody>
   <div id="alf-hd">
      <#if page.url.args.nodeRef??>
         <@region id=referrer + "path" scope="template" protected=true />
      </#if>
   </div>
   <div id="bd">
      <div class="share-form">
         <@region id="data-header" scope="page" />
         <@region id="data-form" scope="page" />
         <@region id="data-actions" scope="page" />
      </div>
   </div>
   <#if page.url.args.nodeRef??>
   <script type="text/javascript">//<![CDATA[
   new Alfresco.DocumentDetails().setOptions(
   {
      nodeRef: new Alfresco.util.NodeRef("${page.url.args.nodeRef?js_string}"),
      siteId: "${page.url.templateArgs.site!""}",
      rootNode: "${rootNode}"
   });
   //]]></script>
   </#if>
</@>

<@templateFooter>
   <div id="alf-ft">
      <@region id="data-loader" scope="page" />
   </div>
</@>