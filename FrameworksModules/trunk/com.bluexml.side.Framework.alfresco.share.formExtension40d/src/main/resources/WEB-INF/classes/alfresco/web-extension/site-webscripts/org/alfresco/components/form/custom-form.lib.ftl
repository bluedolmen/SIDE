<#macro renderRule objectId rule>
    <script language="javascript" type="text/javascript">
		YAHOO.util.Event.onAvailable('${objectId}', function() {
	      	try {
	      	<#if (rule?index_of("(") != -1)>
	      		${rule}
	      	<#else>
	      		${rule}('${objectId}');
	      	</#if>
	        } catch (e) {
	      		//SIDE.custom.Logger.log("${objectId} - rule '${rule}' has thrown an exception"); 
	        }
        }, this);
      </script>
</#macro>

<#macro preRule field htmlId>
   <#assign fieldHtmlId=htmlId?js_string + "_" + field.id >
   <#if (field.control?exists)>
      <#-- SIDE pre rule-->
      <#if (field.control.params.preRule?exists)>
      		<@renderRule fieldHtmlId field.control.params.preRule/>
	  </#if>
   </#if>
</#macro>

<#macro postRule field htmlId>
   <#assign fieldHtmlId=htmlId?js_string + "_" + field.id >
   <#if (field.control?exists)>
      <#-- SIDE post rule-->
      <#if (field.control.params.postRule?exists)>
      		<@renderRule fieldHtmlId field.control.params.postRule/>
	  </#if>
   </#if>
</#macro>

<#macro postGenericRule field htmlId>
   <#if (field?exists)>
	   <#assign fieldHtmlId=htmlId?js_string + "_" + field.id >
   	   <#-- SIDE post generic rule-->
   	   <@renderRule fieldHtmlId "SIDE.custom.Controller.on"/>
   </#if>
</#macro>

<#macro postGenericButtonRule formId htmlId>
   <#assign fieldHtmlId=htmlId?js_string>
   <#-- SIDE post generic rule-->
   <@renderRule "${formId}-${htmlId}" "SIDE.custom.Controller.onButton"/>
</#macro>
