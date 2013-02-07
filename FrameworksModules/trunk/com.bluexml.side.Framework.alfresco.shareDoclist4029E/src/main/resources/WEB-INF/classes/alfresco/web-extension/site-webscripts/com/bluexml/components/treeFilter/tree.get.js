/**
 * Main entrypoint for component webscript logic
 * 
 * @method main
 */
function main() {
	logger.log("filter componant args :" + args);
	logger.log("filter componant assoType :" + args.assoType);
	logger.log("filter componant nodeType :" + args.nodeType);
	logger.log("filter componant selectableTypeIsAspect :" + args.selectableTypeIsAspect);

	if (paramValide("rootName")) {
		// use built-in SIDE model Aspect if not provided

		model.assoType = paramValide("assoType", "buildInLibraryAlfresco:TreeFilter_AssociationTree_TreeFilter");
		model.nodeType = paramValide("nodeType", "buildInLibraryAlfresco:TreeFilter");
		model.nodeTypeDocument = paramValide("nodeTypeDocument", "buildInLibraryAlfresco:HasTreeFilter");
		model.assoTypeDocument = paramValide("assoTypeDocument", "buildInLibraryAlfresco:HasTreeFilter_hasTreeFilter_TreeFilter");
		model.selectableTypeIsAspect = paramValide("selectableTypeIsAspect", "true");
		model.rootProperty = paramValide("rootProperty", "buildInLibraryAlfresco:TreeFilter_root");
		model.rootLabelId = paramValide("headerLabelId", "portlet." + page.id + "." + args["region-id"] + ".header");
		model.documentTypeIsAspect = paramValide("documentTypeIsAspect", "false");
		model.rootName = args.rootName;

	} else {
		// error
	}

}

function paramValide(param, defaultValue) {
	return (args[param] != undefined && args[param] != null && args[param] != "") ? args[param] : defaultValue;
}
main();