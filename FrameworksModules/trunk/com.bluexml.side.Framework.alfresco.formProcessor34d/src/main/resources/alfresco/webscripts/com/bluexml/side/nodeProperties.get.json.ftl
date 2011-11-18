<#assign keys = node.properties?keys>
item : {
<#list keys as key>
${key} : "${node.properties[key]?string}"
<#if key_has_next>,</#if>
</#list>
}
