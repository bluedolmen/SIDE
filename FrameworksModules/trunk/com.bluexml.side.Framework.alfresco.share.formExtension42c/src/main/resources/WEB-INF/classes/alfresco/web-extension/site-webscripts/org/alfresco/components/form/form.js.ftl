<#-- JavaScript Dependencies -->
<@script type="text/javascript" src="${url.context}/res/components/form/form.js" group="form"/>
<@script type="text/javascript" src="${url.context}/res/components/form/date.js" group="form"/>
<@script type="text/javascript" src="${url.context}/res/components/form/date-picker.js" group="form"/>
<@script type="text/javascript" src="${url.context}/res/components/form/period.js" group="form"/>
<@script type="text/javascript" src="${url.context}/res/components/object-finder/object-finder.js" group="form"/>
<@script type="text/javascript" src="${url.context}/res/components/form/rich-text.js" group="form"/>
<@script type="text/javascript" src="${url.context}/res/components/form/content.js" group="form"/>
<@script type="text/javascript" src="${url.context}/res/components/form/workflow/transitions.js" group="form"/>
<@script type="text/javascript" src="${url.context}/res/components/form/workflow/activiti-transitions.js" group="form"/>
<@script type="text/javascript" src="${url.context}/res/components/form/jmx/operations.js" group="form"/>

<#if config.global.forms?exists && config.global.forms.dependencies?exists && config.global.forms.dependencies.js?exists>
   <#list config.global.forms.dependencies.js as jsFile>
      <@script type="text/javascript" src="${url.context}/res${jsFile}" group="form"/>
      <#if (jsFile?starts_with("http"))>
         <@script type="text/javascript" src="${jsFile}" group="form"/>
      <#elseif (jsFile?starts_with("//"))>
         <@script type="text/javascript" src="${jsFile?replace('//','/')}" group="form"/>
      <#else>
         <@script type="text/javascript" src="${url.context}/res${jsFile}" group="form"/>
      </#if>
   </#list>
</#if>