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
	 * @class SIDE.MyDSCheckFields
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
	SIDE.MyDSCheckFields = function(options, initialValue) {
		SIDE.MyDSCheckFields.superclass.constructor.call(this, options);
		this.initialValue = initialValue;
		this.checkFields = [];
		this.value = [];
		this.log("DSS initial value :" + initialValue);
	};

	YAHOO.lang.extend(SIDE.MyDSCheckFields, inputEx.Field, {
		log : function(msg) {
			console.log("[SIDE.MyDSCheckFields] " + msg);
		},
		/**
		 * Setup the additional options for selectfield
		 * 
		 * @param {Object}
		 *            options Options object as passed to the constructor
		 */
		setOptions : function(options) {
			this.log("setOptions :" + options);
			SIDE.MyDSCheckFields.superclass.setOptions.call(this, options);

			this.options.choices = lang.isArray(options.choices) ? options.choices : [];

			this.options.valueKey = options.valueKey || "value";
			this.options.labelKey = options.labelKey || "label";

			this.options.datasource = options.datasource;

		},

		/**
		 * Build a select tag with options
		 */
		renderComponent : function() {
			this.log("renderComponent");
			var choices, length, i, sep;

			this.choicesList = [];

			choices = this.options.choices;

			for (i = 0, length = choices.length; i < length; i += 1) {

				this.addChoice(choices[i]);

			}

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
			/*
			 * while (this.el.childNodes.length > 0) {
			 * this.el.removeChild(this.el.childNodes[0]); }
			 */
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

			this.log("dataloaded");
			if (this.initialValue) {
				this.log("dataloaded init old: (value setted by populateSelect)" + this.getValue());
				this.setValue(this.initialValue);
				this.log("dataloaded init new:" + this.getValue());
			} else {
				this.setValue('');
			}
			this.log("force previousState to 'valid'");
			this.previousState = 'valid';
		},
		/**
		 * choice methods
		 */
		createChoiceNode : function(choice) {
			this.log("createChoiceNode");
			var div, radioId, radioNode, labelNode;

			div = inputEx.cn('div', {
				className : 'inputEx-RadioField-choice'
			});

			var check = new inputEx.CheckBox({
				sentValues : [ choice.value, '' ],
				rightLabel : choice.label,
				parentEl : div,
				value : this.initialValue.indexOf(choice.value) != -1 ? choice.value : ''
			});
			var me = this;
			check.updatedEvt.subscribe(function(e, params) {
				me.log("update Called :" + params);
				// update value
				var values = [];
				for ( var x = 0; x < me.checkFields.length; x++) {
					var v = me.checkFields[x].getValue();
					if (v != '') {
						values.push(v);
					}
				}
				me.log("new value :" + values.toString());
				me.setValue(values.toString(), true);
			});
			this.log("createChoiceNode out");
			this.checkFields.push(check);
			return div;

		},

		removeChoiceNode : function(node) {

			// remove from selector
			// 
			// -> style.display = 'none' would work only on FF (when node is an
			// <option>)
			// -> other browsers (IE, Chrome...) require to remove <option> node
			// from DOM
			//
			this.fieldContainer.removeChild(node);

		},

		disableChoiceNode : function(node) {

			// node.firstChild.disabled = "disabled";
			node.firstChild.disabled = true;
		},

		enableChoiceNode : function(node) {

			// node.firstChild.removeAttribute("disabled");
			node.firstChild.disabled = false;

		},

		/**
		 * Attach an <option> node to the <select> at the specified position
		 * 
		 * @param {HTMLElement}
		 *            node The <option> node to attach to the <select>
		 * @param {Int}
		 *            position The position of the choice in choicesList (may
		 *            not be the "real" position in DOM)
		 */
		appendChoiceNode : function(node, position) {

			var domPosition, i;

			// Compute real DOM position (since previous choices in choicesList
			// may be hidden)
			domPosition = 0;

			for (i = 0; i < position; i += 1) {

				if (this.choicesList[i].visible) {

					domPosition += 1;

				}

			}

			// Insert in DOM
			if (domPosition < this.fieldContainer.childNodes.length) {

				YAHOO.util.Dom.insertBefore(node, this.fieldContainer.childNodes[domPosition]);

			} else {

				this.fieldContainer.appendChild(node);

			}
		},
		/**
		 * Function to set the value
		 * 
		 * @param {Any}
		 *            value The new value
		 * @param {boolean}
		 *            [sendUpdatedEvt] (optional) Wether this setValue should
		 *            fire the updatedEvt or not (default is true, pass false to
		 *            NOT send the event)
		 */
		setValue : function(value, sendUpdatedEvt) {
			this.value = value.split(',');

			if (sendUpdatedEvt !== false) {
				// fire update event
				this.fireUpdatedEvt();
			}

		},
		/**
		 * Return the value of the input
		 * 
		 * @return {Any} value of the field
		 */
		getValue : function() {
			return this.value;
		}
	});

	lang.augmentObject(SIDE.MyDSCheckFields.prototype, inputEx.mixin.choice);
	// Register this class as "sidedsselect" type
	inputEx.registerType("sidedscheck", SIDE.MyDSCheckFields);

}());