<#-- CSS Dependencies -->
<@link href="${url.context}/res/yui/calendar/assets/calendar.css" group="form"/>
<@link href="${url.context}/res/components/object-finder/object-finder.css" group="form"/>
<@link href="${url.context}/res/components/form/form.css" group="form"/> 

<#if config.global.forms?exists && config.global.forms.dependencies?exists && config.global.forms.dependencies.css?exists>
   <#list config.global.forms.dependencies.css as cssFile>
      <#if (cssFile?starts_with("http"))>
         <@link href="${cssFile}" group="form"/>
      <#else>
         <@link href="${url.context}/res${cssFile}" group="form"/>
      </#if>
   </#list>
</#if>