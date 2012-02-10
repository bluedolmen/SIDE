const
sitePath = "/app:company_home/st:sites/cm:{site}//*";

model.filters = getfilters();

function getfilters() {
	var filters = [];
	// get service args
	var currentSite = args["site"];
	var query = args["query"];
	var dataProperty = args["dataProperty"];
	var dataType = args["dataType"];
	var filterType = args["filterType"];

	var fullQuery = "";

	if (currentSite != null && currentSite != "") {
		fullQuery += "PATH:\"" + sitePath.replace(/\{site\}/, search.ISO9075Encode(currentSite)) + "\"";
	}

	if (dataType != null && dataType != "") {
		if (fullQuery != "") {
			fullQuery += " AND ";
		}
		fullQuery += "TYPE:\"" + dataType + "\"";
	}

	if (query != null && query != "") {
		if (fullQuery != "") {
			fullQuery += " AND ";
		}
		fullQuery += query;
	}
	logger.log("getFilters request :" + fullQuery);
	var results = search.luceneSearch(fullQuery);
	for each ( var f in results) {
		var filter = null;
//		if (dataProperty == "cm:content") {
//			filter = f.content;
//		} else {
//			filter = f.properties[dataProperty];
//		}
		var obj = {
			id : filterType,
			label : f.name,
			data : "{nodeRef: \\\"" + f.nodeRef + "\\\", dataProperty: \\\"" + dataProperty + "\\\" }"

		};
		filters.push(obj);
	}
	logger.log("getFilters size :" + filters.length);
	return filters;
}
