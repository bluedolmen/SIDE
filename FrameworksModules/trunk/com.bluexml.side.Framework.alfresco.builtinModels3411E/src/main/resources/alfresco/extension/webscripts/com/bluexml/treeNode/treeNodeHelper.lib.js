const treeNodeAssoQName = "buildInLibraryAlfresco:TreeFilter_AssociationTree_TreeFilter";
const treeNodeRootProperty = "buildInLibraryAlfresco:TreeFilter_root";

var TreeNodeHelper = {};

/* function to create folder */
TreeNodeHelper.createFolder = function(path, description, parent) {
	var folder = parent.childByNamePath(path);
	if (folder == null && parent.hasPermission("CreateChildren")) {
		folder = parent.createFolder(path);
		folder.properties["cm:description"] = description;
		folder.save();
	}
	return folder;
};

/* function to create simple DataListItem */
TreeNodeHelper.createItem = function createItem(type, parent, props, createdItems, existingItems) {
	var item = parent.childByNamePath(label);
	if (item != null) {
		existingItems[existingItems.length] = label;
	} else {
		item = parent.createNode(label, type, props);
		createdItems[createdItems.length] = label;
	}
	return item;
};
/**
 * create DataListItem with treeNode aspect
 */
TreeNodeHelper.createTreeNode = function (type, parent, itemParent, props, createdItems, existingItems) {
	if (!props["cm:name"]) {
		throw "missing cm:name property";
	}
	var label = props["cm:name"];
	var item = parent.childByNamePath(props["cm:name"]);
	if (item != null) {
		for (var prop in props) {
		   
		   item.properties[prop] = props[prop];
		}
	} else {
		item = parent.createNode(label, type, props);
		if (itemParent != null) {
			 item.createAssociation(itemParent, treeNodeAssoQName);
			 item.properties[treeNodeRootProperty] = false;
		} else {
			 item.properties[treeNodeRootProperty] = true;
		}	
		
	}
	item.save();
   createdItems[createdItems.length] = label;
	return item;
}

/* function to add rules */
TreeNodeHelper.addTreeNodeRules = function(node, rootName, addedRules) {
	var scriptFolder = search
			.luceneSearch('PATH:"/app:company_home/app:dictionary/app:scripts"');
	var scriptName = null;
	var createSiteCategoryRoot = null;

	/*
	 * rule that manage treeNodes without parent (except the root)
	 */
	scriptName = rootName + "MustHaveParent.js";
	createSiteCategoryRoot = scriptFolder[0].childByNamePath(scriptName);
	if (createSiteCategoryRoot == null) {
		createSiteCategoryRoot = scriptFolder[0].createFile(scriptName);
	}
	if (createSiteCategoryRoot != null) {
		// serialize addParent into js file in repository toSource()
		// method is not standard only supported by Mozilla including Rhino (the
		// js interpreter used by Alfresco)
		/**
		 * addParent rule to add parent to category
		 */
		function addParent(rootName) {
			var dataListContainer = document.parent;
			// search for Documents
			var target = dataListContainer.childByNamePath(rootName);
			if (target != null && document.name != rootName) {
				var parent = document.assocs[treeNodeAssoQName];
				if (parent == null || parent.length == 0) {
					document.createAssociation(target, treeNodeAssoQName);
				}
			}
		}

		createSiteCategoryRoot.content = "(" + addParent.toSource() + ')("'
				+ rootName + '");';
		createSiteCategoryRoot.mimetype = "application/x-javascript";
		createSiteCategoryRoot.properties["cm:title"] = "set default parent if not provided";
		createSiteCategoryRoot.properties["cm:description"] = "This script is used by rule on folder to set TreeNode parents";
		createSiteCategoryRoot.save();
		var props = new Array();
		props["app:editInline"] = true;
		createSiteCategoryRoot.addAspect("app:inlineeditable", props);
		siderule.addScriptRuleWithTypeCondition(createSiteCategoryRoot, node,
				"inbound", "Structures", SIDE_WCMQS_MODEL_URI, true, false,
				false, "add parent (inbound) " + rootName,
				"rule that add a parent for " + rootName);
		siderule.addScriptRuleWithTypeCondition(createSiteCategoryRoot, node,
				"update", "Structures", SIDE_WCMQS_MODEL_URI, true, false,
				false, "add parent (update) " + rootName,
				"rule that add a parent for " + rootName);
		addedRules[addedRules.length] = "Inbound Rule '" + scriptName + "' on "
				+ node.properties["cm:name"];
		addedRules[addedRules.length] = "Update Rule '" + scriptName + "' on "
				+ node.properties["cm:name"];
	}

};

/**
 * method to add into the rule script, not designed to be called in this
 * webscript
 */
TreeNodeHelper.createDataListForTreeNode = function(siteNode, dataListItemType,
		rootName, limitPermissions) {
	if (!siteNode) {
		siteNode = document;
	}
	var dataLists = siteNode.childByNamePath("dataLists");
	if (dataLists == null) {
		dataLists = TreeNodeHelper.createFolder("dataLists", "Data Lists", siteNode);
	}

	var categoryList = dataLists.childByNamePath(rootName);
	if (categoryList == null && dataLists.hasPermission("CreateChildren")) {
		var props = new Array(3);
		props["cm:title"] = rootName;
		props["cm:description"] = "Arbre des Structures";
		props["dl:dataListItemType"] = dataListItemType;
		categoryList = dataLists.createNode(rootName, "dl:dataList", props);
	}
	var siteName = siteNode.name;
	var trueSiteNode = siteService.getSite(siteName);

	if (limitPermissions) {
		var permissions = {};
		permissions["GROUP_EVERYONE"] = "ReadPermissions";
		permissions["GROUP_site_" + siteName + "_SiteCollaborator"] = "SiteConsumer";
		permissions["GROUP_site_" + siteName + "_SiteContributor"] = "SiteConsumer";
		permissions["GROUP_site_" + siteName + "_SiteConsumer"] = "SiteConsumer";

		trueSiteNode.setPermissions(categoryList, permissions);
		// need to remove SiteManager permission but siteAPI keep this
		// permission
		categoryList.removePermission("SiteManager", "GROUP_site_" + siteName
				+ "_SiteManager");
		categoryList.setPermission("SiteContributor", "GROUP_site_" + siteName
				+ "_SiteManager");
	}
	categoryList.save();
	return categoryList;
};

TreeNodeHelper.getIfSameName = function(a, name) {
	var i = a.length;
	while (i--) {
		if (a[i].name === name) {
			return a[i];
		}
	}
	return null;
};

TreeNodeHelper.contains = function(a, obj) {
	var i = a.length;
	while (i--) {
		if (a[i] === obj) {
			return true;
		}
	}
	return false;
};
