script: {
	
	var contentType = "Document";
	if (args["contentType"] && args["contentType"].indexOf(":") !=-1) {
		contentType = args["contentType"].replace(":", "_");
	}
	// extract query statement
	model.statement = "SELECT * FROM " + contentType;
	if (model.statement == null || model.statement.length == 0) {
		//status.setCode(status.STATUS_BAD_REQUEST, "Query statement must be provided");
		break script;
	}

	// process paging
	var skipCount = parseInt("0");
	if (args["skipCount"] && args["skipCount"].indexOf(":") !=-1) {
		contentType = parseInt(args["skipCount"]);
	}
	var pageSize = parseInt("5");
	if (args["pageSize"] && args["pageSize"].indexOf(":") !=-1) {
		contentType = parseInt(args["pageSize"]);
	}
	
	var page = paging.createPageOrWindow(null, null, skipCount, pageSize);

	// perform query
	var paged = cmis.query(model.statement, page);
	model.resultset = paged.result;
	model.cursor = paged.cursor;
	
//	logger.warn("CMIS :");
//	logger.warn("query :" + model.statement);
//	logger.warn("results number:" + model.resultset.getLength());

}