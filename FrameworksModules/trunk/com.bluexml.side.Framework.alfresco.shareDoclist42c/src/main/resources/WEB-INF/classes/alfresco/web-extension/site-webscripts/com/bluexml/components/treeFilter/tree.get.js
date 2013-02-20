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

	
	var evaluateChildFolders = "true",
   maximumFolderCount = "-1";
   var docLibConfig = config.scoped["DocumentLibrary"];
   if (docLibConfig != null)
   {
     var tree = docLibConfig["tree"];
     if (tree != null)
     {
        var tmp = tree.getChildValue("evaluate-child-folders");
        evaluateChildFolders = tmp != null ? tmp : "true";
        tmp = tree.getChildValue("maximum-folder-count");
        maximumFolderCount = tmp != null ? tmp : "-1";
     }
   }
   
   if (paramValide("rootName")) {
      throw "PLease check parameters rootName is Missing";
   }
   
   var docListTree = {
     id : "DocListTree", 
     name : "Alfresco.DocListTree",
     options : {
        siteId : (page.url.templateArgs.site != null) ? page.url.templateArgs.site : "",
        containerId : template.properties.container != null ? template.properties.container : "documentLibrary",
        evaluateChildFolders : (evaluateChildFolders == "true"),
        maximumFolderCount : parseInt(maximumFolderCount),
        setDropTargets : true,
        assoType : paramValide("assoType", "buildInLibraryAlfresco:TreeFilter_AssociationTree_TreeFilter"),
        nodeType : paramValide("nodeType", "buildInLibraryAlfresco:TreeFilter"),
        nodeTypeDocument : paramValide("nodeTypeDocument", "buildInLibraryAlfresco:HasTreeFilter"),
        assoTypeDocument : paramValide("assoTypeDocument", "buildInLibraryAlfresco:HasTreeFilter_hasTreeFilter_TreeFilter"),
        selectableTypeIsAspect : paramValide("selectableTypeIsAspect", "true"),
        rootProperty : paramValide("rootProperty", "buildInLibraryAlfresco:TreeFilter_root"),
        rootLabelId : paramValide("headerLabelId", "portlet." + page.id + "." + args["region-id"] + ".header"),
        documentTypeIsAspect : paramValide("documentTypeIsAspect", "false"),
        rootName : args.rootName,
        rootPath : paramValide("rootPath", "")
     }
   };
   model.widgets = [docListTree];
	
}

function paramValide(param, defaultValue) {
	return (args[param] != undefined && args[param] != null && args[param] != "") ? args[param] : defaultValue;
}
main();