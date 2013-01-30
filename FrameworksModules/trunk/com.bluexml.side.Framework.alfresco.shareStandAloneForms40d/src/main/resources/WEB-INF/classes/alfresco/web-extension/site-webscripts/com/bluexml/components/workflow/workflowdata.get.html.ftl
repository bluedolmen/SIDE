<#assign el=args.htmlid?js_string>
<script type="text/javascript">//<![CDATA[
   new SIDE.StandAloneStartWorkflow("${el}").setOptions({
      failureMessage: "message.failure",
      submitButtonMessageKey: "button.startWorkflow",
      selectedItems: "${(page.url.args.selectedItems!"")?js_string}",
      destination: "${(page.url.args.destination!"")?js_string}"
   }).setMessages(
      ${messages}
   );
//]]></script>
