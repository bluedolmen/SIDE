
function main() {
	var nodeRef = args["nodeRef"];
	if (nodeRef != null) {

		var node = search.findNode(nodeRef);
		model.node = node;
		
	} else {
		// error
	}

}

main();