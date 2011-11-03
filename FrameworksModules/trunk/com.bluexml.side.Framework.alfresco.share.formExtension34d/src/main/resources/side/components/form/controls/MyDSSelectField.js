/**
 * SIDE root namespace.
 * 
 * @namespace SIDE
 */
// Ensure Alfresco root object exists
if (typeof SIDE == "undefined" || !SIDE) {
	var SIDE = {};
}

(function () {
	
	/**
	 * Create a select field from a datasource
	 * @class SIDE.MyDSSelectField
	 * @extends inputEx.SelectField
	 * @constructor
	 * @param {Object} options Added options:
	 * <ul>
	 *	   <li>options: list of option elements configurations</li>
	 *    <li>datasource: the datasource</li>
	 *    <li>valueKey: value key</li>
	 *    <li>labelKey: label key</li>
	 * </ul>
	 * @param {String} initialValue value to select on widget creation
	 * 
	 */
	SIDE.MyDSSelectField = function (options, initialValue) {
		SIDE.MyDSSelectField.superclass.constructor.call(this, options);
		this.initialValue = initialValue;
	};
	
	YAHOO.lang.extend(SIDE.MyDSSelectField, inputEx.DSSelectField, {
		
		/**
		 * Callback for request success 
		 */
		onDatasourceSuccess: function (oRequest, oParsedResponse, oPayload) {
			this.populateSelect(oParsedResponse.results);
			console.log("dataloaded");
			if (this.initialValue) {
				console.log("dataloaded init old:"+this.getValue());
				this.setValue(this.initialValue);
				console.log("dataloaded init new:"+this.getValue());
			}
		}
	});
	
	// Register this class as "sidedsselect" type
	inputEx.registerType("sidedsselect", SIDE.MyDSSelectField);

}());