<#list set.children as item>
      <#if (item_index % 2) == 0>
      <div class="yui-g"><div class="yui-u first">
      <#else>
      <div class="yui-u">
      </#if>
	  <#if item.kind != "set">
      <@formLib.renderField field=form.fields[item.id] />
      <#else>
      <@renderSet set=item />
      </#if>
      </div>
      <#if ((item_index % 2) != 0) || !item_has_next></div></#if>
</#list>

