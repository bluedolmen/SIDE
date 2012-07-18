[
<#list filters as f>
	{
		id : "${f.id}",
		label : "${f.label}",
		description : "${f.description}",
		data : "${f.data}"
	}<#if f_has_next>,</#if>
 
</#list>
]