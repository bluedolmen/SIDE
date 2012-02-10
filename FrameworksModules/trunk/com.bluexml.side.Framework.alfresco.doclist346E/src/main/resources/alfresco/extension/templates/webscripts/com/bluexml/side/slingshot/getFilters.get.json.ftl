[
<#list filters as f>
	{
		id : "${f.id}",
		label : "${f.label}",
		data : "${f.data}"
	}<#if f_has_next>,</#if>
 
</#list>
]