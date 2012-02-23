<#macro addNewConfig field>
{
	disabled : false,
	formconfig : {
		itemKind : "<#if field.control.params.targetItemKind??>${field.control.params.targetItemKind}<#else>type</#if>",
		itemId : "<#if field.control.params.itemType??>${field.control.params.itemType}<#else>${field.endpointType}</#if>",
		destination : "<#if field.control.params.targetDestination??>${field.control.params.targetDestination}<#else>/app:company_home</#if>",
		mode : "<#if field.control.params.targetFormMode??>${field.control.params.targetFormMode}<#else>create</#if>",
		submitType : "<#if field.control.params.targetFormSubmitType??>${field.control.params.targetFormSubmitType}<#else>json</#if>",
		formId : "<#if field.control.params.targetFormId??>${field.control.params.targetFormId}<#else></#if>"
	}
}
</#macro>