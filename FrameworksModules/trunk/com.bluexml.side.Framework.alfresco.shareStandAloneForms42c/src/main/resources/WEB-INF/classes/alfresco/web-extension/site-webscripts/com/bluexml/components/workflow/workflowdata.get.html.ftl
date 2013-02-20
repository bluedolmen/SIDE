<@markup id="css" >
   <@link rel="stylesheet" type="text/css" href="${page.url.context}/res/components/workflow/start-workflow.css" group="workflowdata" />
</@>

<@markup id="js">
   <@script type="text/javascript" src="${page.url.context}/res/components/workflow/start-workflow.js" group="workflowdata" />
   <@script type="text/javascript" src="${page.url.context}/res/components/workflow/standaloneStartWorkflow.js" group="workflowdata" />
</@>

<@markup id="widgets">
   <@createWidgets group="workflow"/>
</@>


<@markup id="html">
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
</@>