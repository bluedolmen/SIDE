<script type="text/javascript">//<![CDATA[
   new Alfresco.CreateContentMgr("${args.htmlid?js_string}").setOptions(
   {
      siteId: "${page.url.templateArgs.site!""}",
      nodeType: "${nodetype?js_string}"
   }).setMessages(
      ${messages}
   );
//]]></script>

<div class="create-content-mgr">
   <div class="heading">${msg("create-content-mgr.heading")}</div>
</div>