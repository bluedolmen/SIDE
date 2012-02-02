<div class="dashlet">
  <div class="title">${msg("languagePicker.title")}</div>
  <div>
  	<#list languages as l>
  	<a class="languagePickerLink" onclick="javascript:loadShareDashboardWithLang('${l.code}');">
  		<div class="languagePickerItem">
			<img src="${page.url.context}/components/dashlets/images/${l.icon}"/>
			${msg(l.label)}
	  	</div>
  	</a>
  	</#list>
  </div>
</div>