script: {

	var contentType = "Document";
	if (args["contentType"] && args["contentType"].indexOf(":") != -1) {
		contentType = args["contentType"].replace(":", "_");
	}

	// process paging
	var skipCount = null;
	if (args["skipCount"] && args["skipCount"].indexOf(":") != -1) {
		skipCount = parseInt(args["skipCount"]);
	}
	var pageSize = null;
	if (args["pageSize"] && args["pageSize"].indexOf(":") != -1) {
		pageSize = parseInt(args["pageSize"]);
	}

	var page = paging.createPageOrWindow(null, null, skipCount, pageSize);

	// perform query
	var paged;
	if (args["path"]) {
		model.node = findFolder(args["path"]);
		if (model.node == null) {
			status.code = 500;
			status.message = "folder not found please check your request " + args["path"];
			status.redirect = true;
			break script;
		}
		model.contentType = contentType;
		model.resultset = sidecmis.getChildren(model.node.nodeRef,
				model.contentType, page);

	} else {

	}

	if (typeof (model.resultset) == "object") {
		// bad must convert type in array
		logger.warn("CMIS :" + model.resultset.length);
	}

}

function findFolder(path) {
	var store_type = "workspace";
	var store_id = "SpacesStore";
	var reference = [ store_type, store_id ].concat(path.split("/"));
	return cmis.findNode("path", reference);
}
