/**
 * Main entrypoint for component webscript logic
 * 
 * @method main
 */
function main() {
	var filters = [];

	logger.log("filter componant arg :" + args);
	logger.log("filter componant template :" + template);

	if (args.config != null) {
		var conf = eval('(' + unescape(args.config) + ')');

		for ( var c = 0; c < conf.length; c++) {
			var conf = conf[c];

			filters.push({
				id : conf.id,
				label : conf.label,
				data : conf.data.toSource()
			});
		}
	}

	logger.log("filters :" + filters.toSource());
	model.filters = filters;
}

main();