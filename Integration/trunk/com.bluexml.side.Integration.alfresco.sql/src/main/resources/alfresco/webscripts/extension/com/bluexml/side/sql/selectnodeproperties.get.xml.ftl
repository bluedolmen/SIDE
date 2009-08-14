<?xml version="1.0" encoding="UTF-8"?>
<nodes>
<#list nodes as node>
	<node>
<#list node.properties?keys as kkk>
 <#if node.properties[kkk]?exists && kkk != "{http://www.alfresco.org/model/content/1.0}content"> 
		<property name="${kkk}"><#if node.properties[kkk]?is_date>${node.properties[kkk]?date }<#elseif node.properties[kkk]?is_boolean>${node.properties[kkk]?string}<#else>${node.properties[kkk]!"-"}</#if></property>
 </#if>
</#list>

<#list node.assocs?keys as aaa>
 <#if node.assocs[aaa]?exists>
  <#list node.assocs[aaa] as assocaaa>
	<association name="${aaa}">${assocaaa.nodeRef}</association>
  </#list>
 </#if>
</#list>

<#list node.childAssocs?keys as aaa>
 <#if node.childAssocs[aaa]?exists>
  <#list node.childAssocs[aaa] as assocaaa>
	<child-association name="${aaa}">${assocaaa.nodeRef}</child-association>
  </#list>
 </#if>
</#list>

	</node>
</#list>
</nodes>