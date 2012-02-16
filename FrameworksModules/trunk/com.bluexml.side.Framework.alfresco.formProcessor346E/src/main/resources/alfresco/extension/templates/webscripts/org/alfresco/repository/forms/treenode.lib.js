const
SITES_SPACE_QNAME_PATH = "/app:company_home/st:sites/";

function getTreeNodeChidren(params) {
	var currentNode = null;
	var parentNode = null;
	var children = null;
	var isRoot = false;
	var root = getRootNode(params);

	// set currentNode
	if (params.nodeRef == null || params.nodeRef == "") {
		isRoot = true;
		currentNode = root;
	} else {
		currentNode = search.findNode(params.nodeRef);
	}

	// set parentNode and children
	if (isRoot && params.selectableRoot) {
		parentNode = null;
		children = [ currentNode ];
	} else {
		parentNode = currentNode;
		children = currentNode.sourceAssocs[params.assoType];
	}

	return {
		children : children,
		parent : parentNode,
		root : root
	};

}

function getRootNode(params) {
	var rootQuery = "";
	if (params.selectableTypeIsAspect && params.selectableTypeIsAspect == "true") {
		rootQuery = "ASPECT:\"" + params.nodeType + "\"";
	} else {
		rootQuery = "TYPE:\"" + params.nodeType + "\"";
	}
	rootQuery += " AND ";

	var path = null;
	if (params.site) {
		path = SITES_SPACE_QNAME_PATH;
		if (params.site !== null && params.site.length > 0) {
			path += "cm:" + search.ISO9075Encode(params.site) + "//*";
		}
	} else if (params.path) {
		path = params.path;
	}

	if (path != null) {
		path = "PATH:\"" + path + "\"";
		rootQuery += path + " AND ";
	}

	rootQuery += " @" + params.rootProperty.replace(":", "\\:") + ":true";
	rootQuery += " AND ";
	rootQuery += " @cm\\:name:\"" + params.rootName + "\"";
	var results = search.luceneSearch(rootQuery, "@cm\\:name", false);
	if (results.length > 0) {
		return results[0];
	} else {
		// error
		throw {
			message : "No Root Found please create Root with name=" + params.rootName
		};
	}
}

/* Sort the results by case-insensitive name */
function sortByName(a, b) {
	return (b.node.name.toLowerCase() > a.node.name.toLowerCase() ? -1 : 1);
}