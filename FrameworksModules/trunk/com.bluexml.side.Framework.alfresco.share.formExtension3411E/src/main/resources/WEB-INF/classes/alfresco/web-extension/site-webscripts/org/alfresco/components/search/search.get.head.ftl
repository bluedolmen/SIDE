<#include "../component.head.inc">
<!-- Search -->
<@link rel="stylesheet" type="text/css" href="${page.url.context}/res/components/search/search.css" />
<@script type="text/javascript" src="${page.url.context}/res/components/search/search.js"></@script>

<#-- SIDE Custom Priority -->
<#if config.global.forms?exists && config.global.forms.dependencies?exists && config.global.forms.dependencies.css?exists>
<style type="text/css" media="screen">
<#list config.global.forms.dependencies.css as cssFile>
<#--link rel="stylesheet" type="text/css" href="${page.url.context}/res${cssFile}" /-->
<#if (cssFile?starts_with("http"))>
@import "${cssFile}";
<#else>
@import "${page.url.context}/res${cssFile}";
</#if>
</#list>
</style>
</#if>

<#if config.global.forms?exists && config.global.forms.dependencies?exists && config.global.forms.dependencies.js?exists>
<#list config.global.forms.dependencies.js as jsFile>
<#if (jsFile?starts_with("http"))>
<script type="text/javascript" src="${jsFile}"></script>
<#else>
<script type="text/javascript" src="${page.url.context}/res${jsFile}"></script>
</#if>
</#list>
</#if>