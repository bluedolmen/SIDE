/**
 * Main entrypoint for component webscript logic
 * 
 * @method main
 */
function main() {
	var filters = [];

	logger.log("dynamicFilter componant arg :" + args);
	logger.log("dynamicFilter componant template :" + template);

	if (args.dataType != null && args.dataType != "") {
		// items to show can be gathering for a given request
		// filter configuration must be stored in cm:content property
		var connector = remote.connect("alfresco");

		var url = "/side/getFilters?site={site}&dataType={dataType}&dataTemplate={dataTemplate}&query={query}&filterType={filterType}";

		var site = page.url.templateArgs.site ? page.url.templateArgs.site : "";
		var dataType = paramValide("dataType", "");
		var dataTemplate = paramValide("dataTemplate", escape("{nodeRef}"));
		var query = paramValide("query", "");
		var filterType = paramValide("filterType", "");
		var headerLabelId = paramValide("headerLabelId", "portlet." + page.id + "." + args["region-id"]+".header");
		
		url = url.replace(/\{site\}/, encodeURIComponent(site));
		url = url.replace(/\{dataType\}/, encodeURIComponent(dataType));
		// dataTemplate is already escaped by generator since Alfresco component.properties are interpreted ( '\{.*\}' string are replaced ) 		
		url = url.replace(/\{dataTemplate\}/, encodeURIComponent(decodeURIComponent(dataTemplate)));
		url = url.replace(/\{query\}/, encodeURIComponent(query));
		url = url.replace(/\{filterType\}/, encodeURIComponent(filterType));
		
		logger.log("dynamicFilter datas URL :" + url);
		var result = connector.get(url);

		if (result.status == 200) {
			logger.log("dynamicFilter result : " + result);
			var ss = eval('(' + result + ')');
			filters = ss;
		}
	}

	logger.log("dynamicFilters :" + filters.toSource());
	model.filters = filters;
	model.headerLabelId = headerLabelId;
}

main();

function paramValide(param, defaultValue) {
	return (args[param] != undefined && args[param] != null && args[param] != "") ? args[param] : defaultValue;
}