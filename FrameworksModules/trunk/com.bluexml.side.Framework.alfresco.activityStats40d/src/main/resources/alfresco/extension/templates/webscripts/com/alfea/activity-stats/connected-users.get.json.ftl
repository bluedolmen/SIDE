<#assign names = connectedUsers?keys>

{
	"connectedUsersCount":"${connectedUsersCount}",
	"connectedUsers":
	[
		<#list names as name>
		{
			"name":	"${name}",
			"email":	"${connectedUsers[name]}"
		}
		<#if name_has_next>,</#if>
		</#list>
	]
}