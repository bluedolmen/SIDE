/**
 * SIDE root namespace.
 * 
 * @namespace SIDE
 */
// Ensure SIDE root object exists
if (typeof SIDE == "undefined" || !SIDE) {
	var SIDE = {};
}
if (console == undefined) {
	// create a fake console object to avoid error (console is provided by
	// firebug)
	var console = {
		log : function(msg) {
		}
	};
}

(function() {

	var Event = YAHOO.util.Event, lang = YAHOO.lang;
	
	SIDE.MyMultiAutoCompleteField = function(options, initialValue) {
		SIDE.MyMultiAutoCompleteField.superclass.constructor.call(this, options);
		
		this.log("DSS initial value :" + initialValue);
	};

	YAHOO.lang.extend(SIDE.MyMultiAutoCompleteField, inputEx.MultiAutoComplete, {
		log : function(msg) {
			console.log("[SIDE.MyMultiAutoCompleteField] " + msg);
		}
	});
		
	inputEx.registerType("mymultiautocomplete", SIDE.MyMultiAutoCompleteField);

}());