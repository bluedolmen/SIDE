const
SITES_SPACE_QNAME_PATH = "/app:company_home/st:sites/";

function getTreeNodeChidren(params) {
	var currentNode = null;
	var parentNode = null;
	var root = getRootNode(params)[0];
	
	if (params.nodeRef == null || params.nodeRef == "" || params.nodeRef == root.nodeRef) {
		currentNode = root;
	} else {		
		currentNode = search.findNode(params.nodeRef);
	}

	return {
		children : currentNode.sourceAssocs[params.assoType],
		parent : currentNode,
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
	return search.luceneSearch(rootQuery, "@cm\\:name", false);
}
/* Sort the results by case-insensitive name */
function sortByName(a, b) {
	return (b.node.name.toLowerCase() > a.node.name.toLowerCase() ? -1 : 1);
}