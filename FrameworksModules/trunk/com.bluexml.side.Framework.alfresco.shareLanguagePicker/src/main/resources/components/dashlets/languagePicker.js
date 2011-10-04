//Fonction qui change le langage sur action à partir de la dashlet languagePicker
function loadShareDashboardWithLang(lang)
{	
	var params = window.location.search;
	var searchString = "";
	
	if (params.length > 0)
	{
		var position = params.indexOf("shareLang=", 0);
		if (position == -1)
			searchString = params + "&shareLang=" + lang
		else
		{
			var end = params.indexOf("&", position);
			
			if (end == -1)
				searchString = params.substring(0, position + 10) + lang;
			else
				searchString = params.substring(0, position + 10) + lang + params.substring (end);
		}
	}
	else
		searchString = "?shareLang=" + lang
	
	window.location.href = window.location.protocol + "//" + window.location.host + window.location.pathname + searchString;
}

function changeLanguage(lang) {
    var preferences = new Alfresco.service.Preferences();
    var responseConfig = 
         {
            failureCallback: {
            
               fn: function DL_oFD_failure(event, p_oRecord) {
                  Alfresco.util.PopupManager.displayPrompt(
                  {
                     title: "Error",
                     text: "Fail to change user language"
                  });
               },
               scope: this
            },
            successCallback : {
                fn: function (response, obj) {
                    loadShareDashboardWithLang(lang);
                },
                scope: this
            }
         };
    preferences.set("user_language", lang ,responseConfig);

}