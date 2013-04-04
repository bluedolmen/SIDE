{
	"charts":
	[
		<#list charts as jsonChart>
		${jsonChart}
		<#if jsonChart_has_next>,</#if>
		</#list>
	]
}