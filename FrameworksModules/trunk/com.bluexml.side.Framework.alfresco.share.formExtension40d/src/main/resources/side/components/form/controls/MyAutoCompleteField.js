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

	var lang = YAHOO.lang, Event = YAHOO.util.Event, Dom = YAHOO.util.Dom;

	SIDE.MyAutoCompleteField = function(options, initialValue) {
		SIDE.MyAutoCompleteField.superclass.constructor.call(this, options);

		this.log("DSS initial value :" + initialValue);
	};

	YAHOO.lang.extend(SIDE.MyAutoCompleteField, inputEx.AutoComplete, {
		log : function(msg) {
			console.log("[SIDE.MyAutoCompleteField] " + msg);
		},

		/**
		 * Additional options
		 */
		setOptions : function(options) {
			SIDE.MyAutoCompleteField.superclass.setOptions.call(this, options);

			// Method to format the ddlist item labels
			this.options.returnLabel = options.returnLabel;
		},

		/**
		 * Set the value
		 * 
		 * @param {Any}
		 *            value Value to set
		 * @param {boolean}
		 *            [sendUpdatedEvt] (optional) Wether this setValue should
		 *            fire the updatedEvt or not (default is true, pass false to
		 *            NOT send the event)
		 */
		setValue : function(value, sendUpdatedEvt) {
			this.log("setValue :" + value);
			this.hiddenEl.value = value || "";
			// set corresponding style
			this.setClassFromState();

			if (sendUpdatedEvt !== false) {
				// fire update event
				this.fireUpdatedEvt();
			}
		},

		/**
		 * itemSelect handler
		 * 
		 * @param {}
		 *            sType
		 * @param {}
		 *            aArgs
		 */
		itemSelectHandler : function(sType, aArgs) {
			this.log("itemSelectHandler :" + sType + " " + aArgs[2]);
			var aData = aArgs[2];
			this.setValueAndLabel(aData);
		},

		setValueAndLabel : function(aData) {
			var value = lang.isFunction(this.options.returnValue) ? this.options.returnValue(aData) : aData[0];
			var label = lang.isFunction(this.options.returnLabel) ? this.options.returnLabel(aData) : value;

			this.el.value = label || "";
			this.setValue(value);
		},

		/**
		 * onChange event handler
		 * 
		 * @param {Event}
		 *            e The original 'change' event
		 */
		onChange : function(e) {
			this.log("onChange :" + e);
			// erase inherited version, so don't trash previous value if input
			// is empty
		},
		onBlur : function(e) {
			this.log("onBlur " + e);
			this.log("onBlur hidden " + this.hiddenEl.value);
			this.log("onBlur el.value " + this.el.value);
			if (this.hiddenEl.value != this.el.value && this.el.value != this.options.typeInvite) {
				// this.el.value = this.hiddenEl.value;
				if (this.el.value == '' && this.options.typeInvite) {
					Dom.addClass(this.divEl, "inputEx-typeInvite");
					this.el.value = this.options.typeInvite;
					this.setValue("", true);
				}
			} else if (e == 'textboxBlur' && this.el.value == '') {
				Dom.addClass(this.divEl, "inputEx-typeInvite");
				this.el.value = this.options.typeInvite;
			}
		}
	});

	inputEx.registerType("myautocomplete", SIDE.MyAutoCompleteField);

}());