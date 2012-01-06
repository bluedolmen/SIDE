<#include "/org/alfresco/components/form/form.lib.ftl" />

<#macro renderField field>
    <#assign fieldHtmlId=args.htmlid?js_string + "_" + field.id >
    <div id="${fieldHtmlId}-search-parameters">

	    <#if field.control?? && field.control.template??>
		    <#if field.control.params.operators??>
			    <@renderOperator id=fieldHtmlId name=field.name operators=field.control.params.operators?eval/>
		    <#elseif field.dataType == "boolean">
			    <@renderOperator id=fieldHtmlId name=field.name operators=[['OR','form.control.search.operator.OR',false],['AND','form.control.search.operator.AND',false],['IGNORE','form.control.search.operator.IGNORE',true]]/>
		    <#else>
		    	<@renderOperator id=fieldHtmlId name=field.name />
		    </#if>

			<#-- SIDE -->      
			<#--renderField field/-->
			<@customizedRenderField field=field/>      
			  
			<#--include "${field.control.template}" /-->
	    </#if>

    </div>
</#macro>

<#macro renderOperator id operators=[['OR','form.control.search.operator.OR',true],['AND','form.control.search.operator.AND',false],['NOT','form.control.search.operator.NOT',false]] name=id onchange="">
<select name="operator-${name}" id="operator-${id}" class="default-search-operator" onchange="${onchange}">
	<#list operators as op>
	<option value="${op[0]}" id="${op[0]}"<#if op[2]> selected="true"</#if>>${msg(op[1])}</option>
	</#list>
</select>
</#macro>