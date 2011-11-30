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
			filters.push(conf[c]);
		}
	}

	model.filters = filters;
}

main();