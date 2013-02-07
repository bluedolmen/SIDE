/**
 * Custom content types
 */
function getDefaultContentTypes() {
	var contentTypes = [ {
		id : "cm:content",
		value : "cm_content"
	} ];

	return contentTypes;
}

var availableContentTypes = [];
// get list from component instance configuration
if (args.uploadableTypes && args.uploadableTypes.length) {
	for ( var c = 0; c < args.uploadableTypes.length; c++) {
		var item = args.uploadableTypes[c];
		availableContentTypes.push({
			id : item.id,
			value : item.value
		});
	}
}

// get list from xml conf only if instance configuration is null
if (availableContentTypes.length == 0) {
	var myConfig = new XML(config.script);

	for each (var xmlActionSet in myConfig..type) {
		var qname = xmlActionSet.@qname.toString();
		var labelId = xmlActionSet.@labelId.toString();
		availableContentTypes.push({
			id : qname,
			value : labelId
		});
	}	
}

var contentTypes = [];
if (availableContentTypes.length == 0) {
	contentTypes = getDefaultContentTypes();
} else {
	contentTypes = availableContentTypes;
}

model.contentTypes = contentTypes;
