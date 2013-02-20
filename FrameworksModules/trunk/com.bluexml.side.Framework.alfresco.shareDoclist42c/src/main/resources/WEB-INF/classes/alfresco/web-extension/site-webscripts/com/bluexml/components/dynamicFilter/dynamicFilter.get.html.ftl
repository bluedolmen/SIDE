<#assign filterIds = "">
<div class="filter doclib-filter">
   <h2 class="alfresco-twister">${msg(headerLabelId)}</h2>
   <ul class="filterLink">
   <#list filters as filter>
      <#assign filterIds>${filterIds}"${filter.id}"<#if filter_has_next>,</#if></#assign>
      <li><span class="${filter.id}"><a class="filter-link" rel="${filter.data?html}|${msg("filter."+filter.id+".filterDisplay",msg(headerLabelId),msg(filter.label),msg(filter.description))?html}" href="#"<#if filter.description != ""> title="${filter.description}"</#if>>${msg(filter.label)}</a></span></li>
   </#list>
   </ul>
</div>

<@createWidgets group="documentlibrary"/>
<@inlineScript group="documentlibrary">
   <#-- JavaScript to be executed AFTER widget instantiation here -->
   filter.setFilterIds([${filterIds}]);
</@>
