{<#if validity == true>
"validity":true
<#else/>
"validity":false
</#if>,
"ticket":"${ticket}",
"authorities":[
<#list authorities as x>
${x}<#if x_has_next>,</#if>
</#list>
]
}
