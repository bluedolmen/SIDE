<#assign p = treenode.parent>
<#escape x as jsonUtils.encodeJSONString(x)>
{
   "totalResults": ${treenode.items?size?c},
   "resultsTrimmed": ${treenode.resultsTrimmed?string},
   "parent":
   {
      "nodeRef": "${p.nodeRef}",
      "userAccess":
      {
         "create": ${p.hasPermission("CreateChildren")?string},
         "edit": ${p.hasPermission("Write")?string},
         "delete": ${p.hasPermission("Delete")?string}
      }
   },
   "items":
   [
   <#list treenode.items as item>
      <#assign t = item.node>
      <#if (t.properties.description?exists && t.properties.description?length > 0)>
      	<#assign description = t.properties.description>
      <#else>
      	<#assign description = t.name>
      </#if> 
      {
         "nodeRef": "${t.nodeRef}",
         "name": "${t.name}",
         "description": "${description}",
         "hasChildren": ${item.hasSubfolders?string},
         "userAccess":
         {
            "create": ${t.hasPermission("CreateChildren")?string},
            "edit": ${t.hasPermission("Write")?string},
            "delete": ${t.hasPermission("Delete")?string}
         }
      }<#if item_has_next>,</#if>
   </#list>
   ]
}
</#escape>
