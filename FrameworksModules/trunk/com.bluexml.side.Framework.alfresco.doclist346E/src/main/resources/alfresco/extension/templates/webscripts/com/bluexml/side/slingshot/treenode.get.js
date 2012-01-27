/**
 * Document List Component: treenode
 */
model.treenode = getTreeNode();

/* Create collection of folders in the given space */
function getTreeNode() {
	try {
		var items = new Array();
		hasSubfolders = true;
		evalChildFolders = args["children"] !== "false";
		resultsTrimmed = false;
		argMax = parseInt(args["max"], 10);
		maxItems = isNaN(argMax) ? -1 : argMax;

		var assoType = args["assoType"];
		var selectableTypeIsAspect = args["selectableTypeIsAspect"];
		var nodeType = args["nodeType"];
		var rootProperty = args["rootProperty"];
		var rootName = args["rootName"];
		var path = url.templateArgs.path || "";
		var nodeRef = args["nodeRef"];

		var parentNode = null;
		if (nodeRef == null || nodeRef == "") {
			// we must get roots nodes, roots are node without parent
			var xpathLocation = ""; // where to search for root nodes
			rootQuery = "";
			if (selectableTypeIsAspect && selectableTypeIsAspect == "true") {
				rootQuery = "ASPECT:\"" + nodeType + "\"";
			} else {
				rootQuery = "TYPE:\"" + nodeType + "\"";
			}
			rootQuery +=" AND ";
			// rootQuery += "+PATH:" + xpathLocation;
			rootQuery += " @" + rootProperty.replace(":", "\\:") + ":true";
			rootQuery +=" AND ";
			rootQuery += " @cm\\:name:\"" + rootName + "\"";
			var root = search.luceneSearch(rootQuery, "@cm\\:name", false);
			parentNode = root[0];
		} else {
			parentNode = search.findNode(nodeRef);
		}

		var children = parentNode.assocs[assoType];
		if (children) {

			// get Nodes
			for ( var c = 0; c < children.length; c++) {
				var item = children[c];
				if (evalChildFolders) {
					if (item.assocs[assoType]) {
						hasSubfolders = item.assocs[assoType].length > 0;
					}
				}

				items.push({
					node : item,
					hasSubfolders : hasSubfolders
				});

				if (maxItems !== -1 && items.length > maxItems) {
					items.pop();
					resultsTrimmed = true;
					break;
				}
			}
		}
		items.sort(sortByName);

		return ({
			parent : parentNode,
			resultsTrimmed : resultsTrimmed,
			items : items
		});
	} catch (e) {
		status.setCode(status.STATUS_INTERNAL_SERVER_ERROR, e.toString());
		return;
	}
}

/* Sort the results by case-insensitive name */
function sortByName(a, b) {
	return (b.node.name.toLowerCase() > a.node.name.toLowerCase() ? -1 : 1);
}