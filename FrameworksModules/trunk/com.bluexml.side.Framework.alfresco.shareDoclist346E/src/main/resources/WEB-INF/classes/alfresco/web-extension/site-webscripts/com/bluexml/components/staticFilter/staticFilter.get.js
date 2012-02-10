/**
 * Main entrypoint for component webscript logic
 * 
 * @method main
 */
function main() {
	var filters = [];

	logger.log("filter componant arg :" + args);
	logger.log("filter componant template :" + template);

	if (args.config != null && args.config != "") {
		// so filter are defined in component instance (see side portal model)
		var conf = eval('(' + unescape(args.config) + ')');

		for ( var c = 0; c < conf.length; c++) {
			var conf = conf[c];

			filters.push({
				id : conf.id,
				label : conf.label,
				data : conf.data.toSource()
			});
		}
	} else if (args.dataType != null && args.dataType != "") {
		// items to show can be gathering for a given request
		// filter configuration must be stored in cm:content property
		var connector = remote.connect("alfresco");

		var url = "/side/getFilters?site={site}&dataType={dataType}&dataProperty={dataProperty}&query={query}&filterType={filterType}";

		var site = page.url.templateArgs.site ? page.url.templateArgs.site : "";
		var dataType = paramValide("dataType", "");
		var dataProperty = paramValide("dataProperty", "cm:content");
		var query = paramValide("query", "");
		var filterType = paramValide("filterType", "");
		var headerLabelId = paramValide("headerLabelId", "");
		
		url = url.replace(/\{site\}/, encodeURIComponent(site));
		url = url.replace(/\{dataType\}/, encodeURIComponent(dataType));
		url = url.replace(/\{dataProperty\}/, encodeURIComponent(dataProperty));
		url = url.replace(/\{query\}/, encodeURIComponent(query));
		url = url.replace(/\{filterType\}/, encodeURIComponent(filterType));
		
		logger.log("staticFilter datas URL :" + url);
		var result = connector.get(url);

		if (result.status == 200) {
			logger.log("staticFilter result : " + result);
			var ss = eval('(' + result + ')');
			filters = ss;
		}
	}

	logger.log("filters :" + filters.toSource());
	model.filters = filters;
	model.headerLabelId = headerLabelId;
}

main();

function paramValide(param, defaultValue) {
	return (args[param] != undefined && args[param] != null && args[param] != "") ? args[param] : defaultValue;
}