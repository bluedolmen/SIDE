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
	/**
	 * Create a select field from a datasource
	 * 
	 * @class SIDE.MyDSRadioFields
	 * @extends inputEx.SelectField
	 * @constructor
	 * @param {Object}
	 *            options Added options:
	 *            <ul>
	 *            <li>options: list of option elements configurations</li>
	 *            <li>datasource: the datasource</li>
	 *            <li>valueKey: value key</li>
	 *            <li>labelKey: label key</li>
	 *            </ul>
	 * @param {String}
	 *            initialValue value to select on widget creation
	 * 
	 */
	SIDE.MyDSRadioFields = function(options, initialValue) {
		SIDE.MyDSRadioFields.superclass.constructor.call(this, options);
		this.initialValue = initialValue;
		this.log("DSS initial value :" + initialValue);
	};

	YAHOO.lang.extend(SIDE.MyDSRadioFields, inputEx.RadioField, {
		log : function(msg) {
			console.log("[SIDE.MyDSRadioFields] " + msg);
		},
		/**
		 * Setup the additional options for selectfield
		 * 
		 * @param {Object}
		 *            options Options object as passed to the constructor
		 */
		setOptions : function(options) {
			options.choices = lang.isArray(options.choices) ? options.choices : [];
			SIDE.MyDSRadioFields.superclass.setOptions.call(this, options);

			this.options.valueKey = options.valueKey || "value";
			this.options.labelKey = options.labelKey || "label";
			this.options.mandatory = options.mandatory || false;
			this.options.datasource = options.datasource;

		},
		/**
		 * Send the datasource request for reload and preserve selected value
		 */
		reload : function(mode, addedValue) {
			// modes ::[add|replace|keep|cancel]
			var newValue = '';
			if (mode == "add" || mode == "replace") {
				newValue = addedValue[0];
			} else if (mode == "keep") {
				newValue = this.getValue();
			} else if (mode == "cancel" && this.initialValue) {
				newValue = this.initialValue;
			}

			this.reloadData = {
				added : newValue,
				mode : mode ? mode : 'keep'
			};
			this.sendDataRequest(null);
		},
		/**
		 * Build a select tag with options
		 */
		renderComponent : function() {

			SIDE.MyDSRadioFields.superclass.renderComponent.call(this);

			// Send the data request
			this.sendDataRequest(null); // TODO: configurable default request ?
		},

		/**
		 * Send the datasource request
		 */
		sendDataRequest : function(oRequest) {
			if (!!this.options.datasource) {
				this.options.datasource.sendRequest(oRequest, {
					success : this.onDatasourceSuccess,
					failure : this.onDatasourceFailure,
					scope : this
				});
			}
		},

		/**
		 * Insert the options
		 */
		populateSelect : function(items) {

			var i, length;
			this.log("populate start");
			this.log("populate remove previous");
			// remove previous <option>s nodes
			while (this.choicesList.length > 0) {
				this.removeChoice({
					value : this.choicesList[0].value
				});
			}
			this.log("populate add new options");
			// add new options
			for (i = 0, length = items.length; i < length; i += 1) {
				this.addChoice({
					value : items[i][this.options.valueKey],
					label : items[i][this.options.labelKey]
				});
			}
			this.log("populate end");
		},

		/**
		 * Callback for request failure
		 */
		onDatasourceFailure : function(oRequest, oParsedResponse, oPayload) {
			// TODO
			this.el.innerHTML = "<label>error</label>";
		},
		/**
		 * Callback for request success
		 */
		onDatasourceSuccess : function(oRequest, oParsedResponse, oPayload) {
			this.populateSelect(oParsedResponse.results);
			if (!this.options.mandatory) {
				this.addChoice({
					value : '',
					label : 'no value',
					position : 0,
					selected : false
				});
			}
			var value = '';
			if (this.reloadData) {
				value = this.reloadData.added;
				this.reloadData = null;
			} else if (this.initialValue) {
				value = this.initialValue;
			}

			this.log("dataloaded init old: (value setted by populateSelect)" + this.getValue());
			this.setValue(value);
			this.log("dataloaded init new:" + this.getValue());
			this.log("force previousState to 'valid'");
			this.previousState = 'valid';
		}
	});

	// Register this class as "sidedsselect" type
	inputEx.registerType("sidedsradio", SIDE.MyDSRadioFields);

}());