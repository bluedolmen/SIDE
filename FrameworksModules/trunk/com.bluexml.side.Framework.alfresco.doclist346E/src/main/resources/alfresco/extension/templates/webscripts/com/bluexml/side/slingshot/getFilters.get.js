const
sitePath = "/app:company_home/st:sites/cm:{site}//*";

model.filters = getfilters();

function getfilters() {
	var filters = [];
	// get service args
	var site = paramValide("site","");
	var query = paramValide("query","");
	var dataTemplate = unescape(paramValide("dataTemplate","{nodeRef}"));
	var dataType = paramValide("dataType","");
	var filterType = paramValide("filterType","");

	var fullQuery = "";

	if (site != "") {
		fullQuery += "PATH:\"" + sitePath.replace(/\{site\}/, search.ISO9075Encode(site)) + "\"";
	}

	if (dataType != "") {
		if (fullQuery != "") {
			fullQuery += " AND ";
		}
		fullQuery += "TYPE:\"" + dataType + "\"";
	}

	if (query != "") {
		if (fullQuery != "") {
			fullQuery += " AND ";
		}
		fullQuery += query;
	}
	logger.log("getFilters request :" + fullQuery);
	var results = search.luceneSearch(fullQuery);
	for each ( var f in results) {
		var filter = null;
		
		var data = dataTemplate;
		
		// apply dataTemplate
		data = data.replace(/\{site\}/, encodeURIComponent(site));
		data = data.replace(/\{dataType\}/, encodeURIComponent(dataType));
		data = data.replace(/\{query\}/, encodeURIComponent(query));
		data = data.replace(/\{filterType\}/, encodeURIComponent(filterType));
		data = data.replace(/\{nodeRef\}/, encodeURIComponent(f.nodeRef));
		
		if (f.content) {
			data = data.replace(/\{cm:content\}/, encodeURIComponent(f.content.content));			
		}
		logger.log("getFilter data :" + data);		
		var obj = {
			id : filterType,
			label : (f.properties["cm:title"] ? f.properties["cm:title"] : f.name ),
			description : (f.properties["cm:description"] ? f.properties["cm:description"] : "" ),
			data : data
		};
		filters.push(obj);
	}
	logger.log("getFilters size :" + filters.length);
	return filters;
}

function paramValide(param, defaultValue) {
	return (args[param] != undefined && args[param] != null && args[param] != "") ? args[param] : defaultValue;
}