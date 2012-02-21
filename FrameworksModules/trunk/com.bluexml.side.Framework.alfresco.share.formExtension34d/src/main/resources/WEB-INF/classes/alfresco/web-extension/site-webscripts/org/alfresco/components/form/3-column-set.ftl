<#list set.children as item>
	  <#if (item_index % 3) == 0>
      <div class="yui-gb"><div class="yui-u first">
      <#else>
      <div class="yui-u">
      </#if>
	  <#if item.kind != "set">
      <@formLib.renderField field=form.fields[item.id] />
      <#else>
      <@renderSet set=item />
      </#if>
      </div>
      <#if ((item_index % 3) == 2) || !item_has_next></div></#if>
</#list>


