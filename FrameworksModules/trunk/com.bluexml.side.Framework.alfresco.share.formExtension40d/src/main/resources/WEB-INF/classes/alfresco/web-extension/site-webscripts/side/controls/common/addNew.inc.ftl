<#macro addNewConfig field>
{
	disabled : false,
	formconfig : {
		itemKind : "type",
		parentId : "<#if args.itemId??>${args.itemId}</#if>",
		itemId : "<#if field.control.params.itemType??>${field.control.params.itemType}<#else>${field.endpointType}</#if>",
		destination : "<#if field.control.params.targetDestination??>${field.control.params.targetDestination}<#else>/app:company_home</#if>",
		mode : "create",
		submitType : "<#if field.control.params.targetFormSubmitType??>${field.control.params.targetFormSubmitType}<#else>multipart</#if>",
		formId : "<#if field.control.params.targetFormId??>${field.control.params.targetFormId}<#else></#if>",
		showCancelButton : "<#if field.control.params.showCancelButton??>${field.control.params.showCancelButton}<#else>true</#if>"
	}
}
</#macro>

<#macro editConfig field>
{
	disabled : false,
	formconfig : {
		itemKind : "node",
		itemId : "{nodeRef}",
		mode : "edit",
		submitType : "<#if field.control.params.targetFormSubmitType??>${field.control.params.targetFormSubmitType}<#else>multipart</#if>",
		formId : "<#if field.control.params.targetFormId??>${field.control.params.targetFormId}<#else></#if>",
		showCancelButton : "<#if field.control.params.showCancelButton??>${field.control.params.showCancelButton}<#else>true</#if>"
	}
}
</#macro>

