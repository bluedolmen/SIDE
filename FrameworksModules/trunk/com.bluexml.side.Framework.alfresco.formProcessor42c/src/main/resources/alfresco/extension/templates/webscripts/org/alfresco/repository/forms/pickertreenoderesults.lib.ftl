<#macro renderParentTreeNode node indent="   ">
	<#escape x as jsonUtils.encodeJSONString(x)>
	${indent}"parent":
	${indent}{
	<#if (node != rootNode) && node.assocs[sourceAsso][0]??>
		<@renderParentTreeNode node.assocs[sourceAsso][0] indent+"   " />
	</#if>
		${indent}"type": "${node.typeShort}",
		${indent}"isContainer": ${node.isContainer?string},
		${indent}"name": "${node.properties.name!""}",
		${indent}"title": "${node.properties.title!""}",
		${indent}"description": "${node.properties.description!""}",
		<#if node.properties.modified??>${indent}"modified": "${xmldate(node.properties.modified)}",</#if>
		<#if node.properties.modifier??>${indent}"modifier": "${node.properties.modifier}",</#if>
		${indent}"displayPath": "${node.displayPath!""}",
		${indent}"nodeRef": "${node.nodeRef}"
	${indent}},
	</#escape>
</#macro>

<#macro pickerTreeNodeResultsJSON results>
	<#escape x as jsonUtils.encodeJSONString(x)>
{
	"data":
	{
<#if parent??>
	<@renderParentTreeNode parent />
</#if>
		"items":
		[
		<#list results as row>
			{
				"type": "${row.item.typeShort}",
				"isContainer": ${row.item.isContainer?string},
				"name": "${row.item.properties.name!""}",
				"title": "${row.item.properties.title!""}",
				"description": "${row.item.properties.description!""}",
				<#if row.item.properties.modified??>"modified": "${xmldate(row.item.properties.modified)}",</#if>
				<#if row.item.properties.modifier??>"modifier": "${row.item.properties.modifier}",</#if>
				<#if row.item.siteShortName??>"site": "${row.item.siteShortName}",</#if>
				"displayPath": "${row.item.displayPath!""}",
				"nodeRef": "${row.item.nodeRef}"<#if row.selectable?exists>,
				"selectable" : ${row.selectable?string}</#if>
			}<#if row_has_next>,</#if>
		</#list>
		]
	}
}
	</#escape>
</#macro>