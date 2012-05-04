<#include "/org/alfresco/components/form/controls/common/utils.inc.ftl" />
<div class="form-field">
   <#if form.mode == "view" || (form.mode == "edit" && field.disabled)>
      <div class="viewmode-field">
         <span class="viewmode-label">${field.label?html}:</span>
         <span class="viewmode-value">
         <#if field.value?string == "Not Yet Started">${msg("status.notyetstarted")}
         <#elseif field.value?string == "In Progress">${msg("status.inprogress")}
         <#elseif field.value?string == "On Hold">${msg("status.onhold")}
         <#elseif field.value?string == "Cancelled">${msg("status.canceled")}
         <#elseif field.value?string == "Completed">${msg("status.completed")}
         <#else>${field.value?html}</#if>
         </span>
      </div>
   <#else>
      <label for="${fieldHtmlId}">${field.label?html}:<#if field.mandatory><span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></#if></label>
      <select id="${fieldHtmlId}" name="${field.name}" tabindex="0" size="1"
            <#if field.description??>title="${field.description}"</#if>
            <#if field.control.params.styleClass??>class="${field.control.params.styleClass}"</#if>
            <#if field.control.params.style??>style="${field.control.params.style}"</#if>
            <#if field.disabled>disabled="true"</#if>>
            <option value="1"<#if field.value?string == "Not Yet Started"> selected="selected"</#if>>${msg("status.notyetstarted")}</option>
            <option value="2"<#if field.value?string == "In Progress"> selected="selected"</#if>>${msg("status.inprogress")}</option>
            <option value="3"<#if field.value?string == "On Hold"> selected="selected"</#if>>${msg("status.onhold")}</option>
            <option value="3"<#if field.value?string == "Cancelled"> selected="selected"</#if>>${msg("status.canceled")}</option>
            <option value="3"<#if field.value?string == "Completed"> selected="selected"</#if>>${msg("status.completed")}</option>
      </select>
   </#if>
</div>