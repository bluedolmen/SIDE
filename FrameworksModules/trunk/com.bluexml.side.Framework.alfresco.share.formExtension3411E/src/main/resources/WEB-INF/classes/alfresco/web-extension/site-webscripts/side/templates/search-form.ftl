<#import "/side/templates/search.lib.ftl" as formLib />

<#if formUI == "true">
   <@formLib.renderFormsRuntime formId=formId />
</#if>

<@formLib.renderFormContainer formId=formId>
   <#--div id="${formId}-tabview" class="yui-navset"-->
   <div id="${formId}">
   
   	<div id="search-parameters">
      <div class="form-field search-path">
      	 <#-- set variable for the picker -->
      	 <#assign field={'name':'path','disabled':false,'value':'','label':msg('form.search.path'),'endpointType':'node','endpointMany':false,'endpointType':'cm:folder','control':{'params':{'compactMode':false,'valueType':'dataListTest:org_mydatalist_Clazz3'}}} />
      	 <#assign controlId=formId+"-searchPath" />
      	 <#assign fieldHtmlId=formId+"-searchPath" />
      	 <#-- include node picker to select a folder -->
      	 <#include "/org/alfresco/components/form/controls/association.ftl" />
      	 <input type="checkbox" name="path_subdirectories" tabindex="0"/>
      	 <label for="path_subdirectories" class="checkbox">${msg('form.search.path.subdirectories')}</label>      	       
      </div>
      <div id="main-search-operator" class="form-field">
         <label for="operator">${msg("form.control.search.operator.default")}:</label>
         <@formLib.renderOperator id=formId onchange="" name="default" operators=[['OR','form.control.search.operator.OR',true],['AND','form.control.search.operator.AND',false]]/>
      </div>
    </div>
      <#-- ul class="yui-nav">
         <#list form.structure as item>
            <#if item.kind == "set">
               <li<#if item_index == 0> class="selected"</#if>><a href="#tab_${item_index}"><em>${item.label}</em></a></li>
            </#if>
         </#list>
      </ul-->                
      <div class="yui-content">
         <#list form.structure as item>
            <#if item.kind == "set">
               <#--div id="tab_${item_index}"-->
                  <@formLib.renderSet set=item />
               <#--/div-->      
            </#if>
         </#list>
      </div>
   </div>
</@>

<#--
<script type="text/javascript">//<![CDATA[
(function() 
{
   new YAHOO.widget.TabView('${formId}-tabview');
})();
//]]></script>
-->
