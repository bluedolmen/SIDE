{
	"sites":
	[
	<#list sites as site>
		{
			"name":			"${site.shortName}",
			"displayName":	"${site.title}"
		}
		<#if site_has_next>,</#if>
	</#list>
	]
}