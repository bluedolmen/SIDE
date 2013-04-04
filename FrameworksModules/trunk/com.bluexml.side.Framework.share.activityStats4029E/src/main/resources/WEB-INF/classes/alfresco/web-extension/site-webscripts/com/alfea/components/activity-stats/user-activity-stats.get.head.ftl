<#include "/org/alfresco/components/component.head.inc">
<!-- User Activites Stats -->
<@link rel="stylesheet" type="text/css" href="${page.url.context}/res/yui/fonts/fonts.css" />

<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<@script type="text/javascript" src="${page.url.context}/res/yui/yahoo-dom-event/yahoo-dom-event.js"></@script>
<@script type="text/javascript" src="${page.url.context}/res/yui/json/json.js"></@script>
<@script type="text/javascript" src="${page.url.context}/res/yui/element/element.js"></@script>
<@script type="text/javascript" src="${page.url.context}/res/yui/datasource/datasource.js"></@script>

<@script type="text/javascript" src="${page.url.context}/res/components/extension/alfea/activity-stats/user-activity-stats.js"></@script>
<@link rel="stylesheet" type="text/css" href="${page.url.context}/res/components/extension/alfea/activity-stats/user-activity-stats.css" />

<script type="text/javascript">
	google.load("visualization", "1", {packages:["corechart"]});
</script>