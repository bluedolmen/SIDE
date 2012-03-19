<#assign controlId = fieldHtmlId + "-cntrl">

<#if field.value != "" >
<#assign value = "\""+field.value+"\"">
<#else>
<#assign value = "null">
</#if>

<script type="text/javascript">//<![CDATA[


(function()
{
   var autocomplete = new SIDE.Autocomplete("${controlId}", "${fieldHtmlId}",${value});
   
   autocomplete.setOptions(
   { 
     <#if form.mode == "view" || (field.disabled && !(field.control.params.forceEditable?? && field.control.params.forceEditable == "true"))>disabled: true,</#if>
     itemType: "${field.endpointType}",
     field: "${field.name}",
     searchInSite: <#if field.control.params.searchInSite??>${field.control.params.searchInSite}<#else>true</#if>,
     multipleSelectMode: <#if field.control.params.multipleSelectMode??>${field.control.params.multipleSelectMode}<#else>${field.endpointMany?string}</#if>,
     <#if field.mandatory??>
     mandatory: ${field.mandatory?string},
     <#elseif field.endpointMandatory??>
     mandatory: ${field.endpointMandatory?string},
     </#if>
     filterTerm : <#if field.control.params.filterTerm??>"${field.control.params.filterTerm}"<#else>"*"</#if>,
	 maxResults : <#if field.control.params.maxResults??>${field.control.params.maxResults}<#else>-1</#if>,
	 selectableTypeIsAspect : <#if field.control.params.selectableTypeIsAspect??>${field.control.params.selectableTypeIsAspect}<#else>false</#if>
	 <#if field.control.params.getDataSource??>, getDataSource :${field.control.params.getDataSource}</#if>
	 <#if field.control.params.startLocation??>, startLocation : ${field.control.params.startLocation}</#if>
   });
   
   
   autocomplete.setMessages(
      ${messages}
   );
   
})();

//]]></script>

<div class="form-field">
   <#if form.mode == "view">
      <div id="${controlId}" class="viewmode-field">
         <#if field.endpointMandatory && field.value == "">
            <span class="incomplete-warning"><img src="${url.context}/res/components/form/images/warning-16.png" title="${msg("form.field.incomplete")}" /><span>
         </#if>
         <span class="viewmode-label">${field.label?html}:</span>
         <span id="${controlId}-currentValueDisplay" class="viewmode-value current-values"></span>
      </div>
   <#else>
      <label for="${controlId}">${field.label?html}:<#if field.endpointMandatory><span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></#if></label>
      
      
      <div id="${controlId}">
         <#if field.disabled == false>
         <input type="hidden" id="${fieldHtmlId}" name="-" value="${field.value?html}" />
         <input type="hidden" id="${controlId}-added" name="${field.name}_added" />
         <input type="hidden" id="${controlId}-removed" name="${field.name}_removed" />
         
         </#if>
      </div>
   </#if>
</div>
