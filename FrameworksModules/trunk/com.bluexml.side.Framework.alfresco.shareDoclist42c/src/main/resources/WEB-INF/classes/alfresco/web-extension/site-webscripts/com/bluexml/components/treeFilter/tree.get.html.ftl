<#assign treeConfig = config.scoped["DocumentLibrary"]["tree"]!>
<#if treeConfig.getChildValue??>
   <#assign evaluateChildFolders = treeConfig.getChildValue("evaluate-child-folders")!"true">
   <#assign maximumFolderCount = treeConfig.getChildValue("maximum-folder-count")!"-1">
</#if>
<script type="text/javascript">//<![CDATA[
   new SIDE.DocListTree("${args.htmlid}").setOptions(
   {
      siteId: "${page.url.templateArgs.site!""}",
      containerId: "${template.properties.container!"documentLibrary"}",
      evaluateChildFolders: ${evaluateChildFolders!"false"},
      maximumFolderCount: ${maximumFolderCount!"-1"},
      nodeType: "${nodeType}",
      selectableTypeIsAspect: "${selectableTypeIsAspect}",
      assoType: "${assoType}",
      nodeTypeDocument: "${nodeTypeDocument}",
      assoTypeDocument: "${assoTypeDocument}",
      documentTypeIsAspect : "${documentTypeIsAspect}",
      rootProperty: "${rootProperty}",
      rootName: "${rootName}",
      rootLabel: "${msg(rootLabelId)}"
   }).setMessages(
      ${messages}
   );
//]]></script>
<div class="treeview filter">
   <h2 id="${args.htmlid}-h2">${msg(rootLabelId)}</h2>
   <div id="${args.htmlid}-treeview" class="tree"></div>
</div>