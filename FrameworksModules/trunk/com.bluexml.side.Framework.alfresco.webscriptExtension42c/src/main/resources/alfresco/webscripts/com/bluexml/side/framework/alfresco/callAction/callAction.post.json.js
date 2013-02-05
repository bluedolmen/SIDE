function main() {

	// convert json JAVA Object into javascript object
	var jsObject = eval('(' + json.toString() + ')');

	// prepare param data
	var p_params = {};
	p_params.files = [ args.nodeRef ];
	p_params.actionName = args.actionName;
	p_params.jsObject = jsObject;

	model.results = runAction(p_params);
	if (model.results.length > 0) {
		model.overallSuccess = true;
		model.successCount = 1;
		model.failureCount = 0;
	} else {
		model.overallSuccess = false;
		model.successCount = 0;
		model.failureCount = 1;
	}
}

/**
 * original code from action.lib.js
 * 
 * @param p_params
 * @returns {Array}
 */
function runAction(p_params) {
	var results = [], files = p_params.files, file, fileNode, result, nodeRef, actionName = p_params.actionName, jsObject = p_params.jsObject;

	// Must have array of files
	if (!files || files.length == 0) {
		status.setCode(status.STATUS_BAD_REQUEST, "No files.");
		return;
	}
	for (file in files) {
		nodeRef = files[file];
		result = {
			nodeRef : nodeRef,
			action : "linkFile",
			success : false
		}
		try {
			fileNode = search.findNode(nodeRef);
			if (fileNode === null) {
				result.id = file;
				result.nodeRef = nodeRef;
				result.success = false;
			} else {
				result.id = fileNode.name;
				result.type = fileNode.isContainer ? "folder" : "document";
				// execute action on the node
				result.success = executeAction(actionName, jsObject, fileNode);
			}
		} catch (e) {
			result.id = file;
			result.nodeRef = nodeRef;
			result.success = false;
		}
		results.push(result);
	}

	return results;
}

/**
 * execute an action
 * 
 * @param actionName :
 *            the action identifier
 * @param jsObject :
 *            js object that contains actions parameters {paramId : {type:"",
 *            value:""}}
 * @param node :
 *            action context scriptNode
 * @returns {Boolean}
 */
function executeAction(actionName, jsObject, node) {
	// extract parameters from json stream and set action parameters
	var action = actions.create(actionName);
	for ( var key in jsObject) {
		var obj = jsObject[key];
		var type = obj.type;
		var value = obj.value;

		switch (type) {
		case "QName":
			action.parameters[key] = Packages.org.alfresco.service.namespace.QName.createQName(value);
			break;
		case "boolean":
			action.parameters[key] = (value == "true");
			break;
		case "NodeRef":
			action.parameters[key] = new Packages.org.alfresco.service.cmr.repository.NodeRef(value);
			break;
		default:
			action.parameters[key] = value;
			break;
		}
	}
	action.execute(node);
	return true;
}

main();
