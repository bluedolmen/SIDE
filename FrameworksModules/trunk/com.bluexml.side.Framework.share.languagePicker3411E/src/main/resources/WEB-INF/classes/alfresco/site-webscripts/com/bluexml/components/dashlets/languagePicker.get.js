function loadConfiguration() {
	var languages = [];

	var myConfig = new XML(config.script);
	
	for each (var language in myConfig..language)
    {
		var code = language.@code.toString();
		var icon = language.@icon.toString();
		var label = language.@label.toString();
		languages.push({
			'code' : code,
			'icon' : icon,
			'label' : label
		});
	}
	return languages;
}

model.languages = loadConfiguration();