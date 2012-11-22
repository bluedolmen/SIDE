<import resource="classpath:/alfresco/extension/templates/webscripts/org/alfresco/slingshot/search/search.lib.js">

model.csvSeparator = ";";

model.viewName = args.viewName;

if (args.query || args.term) {
	// use search parameters
	var params = {};
	if (args.savedSearchNodeRef) {
		params = getSavedSearchQueryDef(args.savedSearchNodeRef);
	} else {
		// get search args
		params = {
			siteId : (args.site !== null) ? args.site : null,
			containerId : (args.container !== null) ? args.container : null,
			repo : (args.repo !== null) ? (args.repo == "true") : false,
			term : (args.term !== null) ? args.term : null,
			tag : (args.tag !== null) ? args.tag : null,
			query : (args.query !== null) ? args.query : null,
			sort : (args.sort !== null) ? args.sort : null,
			maxResults : -1
		};

	}
	
	if (params.query) {
		var dataType = eval('(' + params.query + ')').datatype;
		model.dataType=dataType.replace(":","_");
		model.viewTemplate =  model.dataType + "/" + model.viewName;
	}
	
	
	
	var queryDef = getSearchDef(params);
	model.records = search.query(queryDef);

} else {
	// error

}