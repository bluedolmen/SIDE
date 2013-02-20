<@markup id="css" >
   <#-- CSS Dependencies -->
   <@link href="${url.context}/components/dashlets/languagePicker.css" group="dashlet-languagePicker"/>
</@>

<@markup id="js">
   <#-- JavaScript Dependencies -->
   <@script src="${url.context}/components/dashlets/languagePicker.js" group="dashlet-languagePicker"/>
</@>

<@markup id="html">
<div class="dashlet">
  <div class="title">${msg("languagePicker.title")}</div>
  <div>
  	<#list languages as l>
  	<a class="languagePickerLink" onclick="javascript:SIDE.languagePicker.changeLanguage('${l.code}');">
  		<div class="languagePickerItem">
			<img src="${url.context}/components/dashlets/images/${l.icon}"/>
			${msg(l.label)}
	  	</div>
  	</a>
  	</#list>
  </div>
</div>
</@>