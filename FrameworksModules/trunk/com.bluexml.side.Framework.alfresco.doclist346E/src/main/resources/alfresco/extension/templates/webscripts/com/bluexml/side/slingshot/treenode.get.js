<import resource="classpath:/alfresco/extension/templates/webscripts/com/bluexml/side/slingshot/treenode.lib.js">
/**
 * Document List Component: treenode
 */
model.treenode = getTreeNode();

/* Create collection of folders in the given space */
function getTreeNode() {
	try {
		var params = {};
		
		params.evalChildFolders = args["children"] !== "false";
		params.argMax = parseInt(args["max"], 10);
		params.maxItems = isNaN(argMax) ? -1 : argMax;
		params.assoType = args["assoType"];
		params.selectableTypeIsAspect = args["selectableTypeIsAspect"];
		params.nodeType = args["nodeType"];
		params.rootProperty = args["rootProperty"];
		params.rootName = args["rootName"];
		params.path = url.templateArgs.path || "";
		params.nodeRef = args["nodeRef"];

		var evalChildFolders = args["children"] !== "false";
		
		var items = new Array();
		var hasSubfolders = true;
		var resultsTrimmed = false;
		var argMax = parseInt(args["max"], 10);
		var maxItems = isNaN(argMax) ? -1 : argMax;
		
		
		var result = getTreeNodeChidren(params);
		var children = result.children;
		if (children) {
			// get Nodes
			for ( var c = 0; c < children.length; c++) {
				var item = children[c];
				if (evalChildFolders) {
					if (item.assocs[params.assoType]) {
						hasSubfolders = item.assocs[params.assoType].length > 0;
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
