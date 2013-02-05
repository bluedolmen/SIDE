<import resource="classpath:/alfresco/extension/templates/webscripts/org/alfresco/repository/forms/treenode.lib.js">
/**
 * Document List Component: treenode
 */
model.treenode = getTreeNode();

/* Create collection of folders in the given space */
function getTreeNode() {
	try {
		var params = {};
		params.path = url.templateArgs.path || "";
		params.site = url.templateArgs.site;
		
		params.evalChildFolders = args["children"] !== "false";
		params.argMax = parseInt(args["max"], 10);
		params.maxItems = isNaN(argMax) ? -1 : argMax;
		params.assoType = args["assoType"];
		params.selectableTypeIsAspect = args["selectableTypeIsAspect"];
		params.nodeType = args["nodeType"];
		params.rootProperty = args["rootProperty"];
		params.rootName = args["rootName"];
		params.nodeRef = args["nodeRef"];
		params.selectableRoot = args["selectableRoot"] == "true";
		
		var items = new Array();		
		var resultsTrimmed = false;
		var argMax = parseInt(args["max"], 10);
		var maxItems = isNaN(argMax) ? -1 : argMax;
		var result = getTreeNodeChidren(params);
		var children = result.children;
		
		if (children) {
			// get Nodes
			for ( var c = 0; c < children.length; c++) {
				var hasSubfolders = true;
				var item = children[c];
				if (params.evalChildFolders) {
					if (item.sourceAssocs[params.assoType]) {
						hasSubfolders = item.sourceAssocs[params.assoType].length > 0;
					} else {
						// this happen if no target have been selected
						hasSubfolders = false;
					}
				}

				items.push({
					node : item,
					hasSubfolders : hasSubfolders
				});

				if (params.maxItems !== -1 && items.length > params.maxItems) {
					items.pop();
					resultsTrimmed = true;
					break;
				}
			}
		}
		items.sort(sortByName);

		return ({
			parent : result.parent,
			resultsTrimmed : resultsTrimmed,
			items : items
		});
	} catch (e) {
		status.setCode(status.STATUS_INTERNAL_SERVER_ERROR, e.toString());
		return;
	}
}