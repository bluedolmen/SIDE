/**
 * Main entrypoint for component webscript logic
 * 
 * @method main
 */
function main() {
	logger.log("filter componant args :" + args);
	logger.log("filter componant template :" + template);
	logger.log("filter componant assoType :" + args.assoType);
	logger.log("filter componant nodeType :" + args.nodeType);
	logger.log("filter componant selectableTypeIsAspect :" + args.selectableTypeIsAspect);

	if (paramValide("rootName")) {
		// use built-in SIDE model Aspect if not provided

		model.assoType = paramValide("assoType") ? args.assoType : "sideBuildInLibraryAlfresco:TreeFilter_AssociationTree_TreeFilter";
		model.nodeType = paramValide("nodeType") ? args.nodeType : "sideBuildInLibraryAlfresco:TreeFilter";
		model.nodeTypeDocument = paramValide("nodeTypeDocument") ? args.nodeTypeDocument : "sideBuildInLibraryAlfresco:HasTreeFilter";
		model.assoTypeDocument = paramValide("assoTypeDocument") ? args.assoTypeDocument : "sideBuildInLibraryAlfresco:HasTreeFilter_hasTreeFilter_TreeFiltersearch";
		model.selectableTypeIsAspect = paramValide("selectableTypeIsAspect") ? args.selectableTypeIsAspect : "true";
		model.rootProperty = paramValide("rootProperty") ? args.rootProperty : "sideBuildInLibraryAlfresco:TreeFilter_root";
		model.rootName = args.rootName;
	} else {
		// error
	}

}

function paramValide(param) {
	return args[param] != undefined && args[param] != "";
}
main();