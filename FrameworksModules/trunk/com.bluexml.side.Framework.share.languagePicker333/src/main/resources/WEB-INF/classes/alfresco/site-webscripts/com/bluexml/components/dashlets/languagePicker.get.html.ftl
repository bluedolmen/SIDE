<div class="dashlet">
  <div class="title">${msg("languagePicker.title")}</div>
  <div>
  	<a class="languagePickerLink" onclick="javascript:loadShareDashboardWithLang('fr_FR');">
  		<div class="languagePickerItem">
			<img src="${page.url.context}/components/dashlets/images/fr_FR.png"/>
			${msg("languagePicker.french")}
	  	</div>
  	</a>
  	<a class="languagePickerLink" onclick="javascript:loadShareDashboardWithLang('en_US');">
		<div class="languagePickerItem">
			<img src="${page.url.context}/components/dashlets/images/en_US.png"/>
			${msg("languagePicker.english")}
		</div>
	</a>
	<!--
	============= This is a language item ================
	<a class="languagePickerLink" onclick="javascript:loadShareDashboardWithLang('es_ES');">
	  	<div class="languagePickerItem">
			<img src="${page.url.context}/components/dashlets/images/es_ES.png"/>
			${msg("languagePicker.spanish")}
		</div>
	</a>	
	
	============= This is a language item ================
	<a class="languagePickerLink" onclick="javascript:loadShareDashboardWithLang('de_DE');">
	  	<div class="languagePickerItem">
			<img src="${page.url.context}/components/dashlets/images/de_DE.png"/>
			${msg("languagePicker.german")}
		</div>
	</a>
	-->
  </div>
</div>