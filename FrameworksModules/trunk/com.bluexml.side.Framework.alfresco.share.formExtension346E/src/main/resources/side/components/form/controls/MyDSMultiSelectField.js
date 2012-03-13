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
	/**
	 * Create a multi select field
	 * 
	 * @class SIDE.MyDSMultiSelectField
	 * @extends inputEx.SelectField
	 * @constructor
	 * @param {Object}
	 *            options Added options:
	 *            <ul>
	 *            <li>choices: contains the list of choices configs
	 *            ([{value:'usa'}, {value:'fr', label:'France'}])</li>
	 *            </ul>
	 */
	SIDE.MyDSMultiSelectField = function(options, initialValue) {
		this.initialValue = initialValue;

		SIDE.MyDSMultiSelectField.superclass.constructor.call(this, options);

		this.log("initialValue DSMulti :" + initialValue);
	};

	YAHOO.lang.extend(SIDE.MyDSMultiSelectField, inputEx.DSSelectField, {
		log : function(msg) {
			console.log("[SIDE.MyDSMultiSelectField] " + msg);
		},
		setOptions : function(options) {
			SIDE.MyDSMultiSelectField.superclass.setOptions.call(this, options);
			this.options.editConfig = options.editConfig;
		},
		/**
		 * Build the DDList
		 */
		renderComponent : function() {

			SIDE.MyDSMultiSelectField.superclass.renderComponent.call(this);

			this.ddlist = new SIDE.MyDDList({
				parentEl : this.fieldContainer,
				editConfig : this.options.editConfig
			});

		},

		/**
		 * Register the "change" event
		 */
		initEvents : function() {
			YAHOO.util.Event.addListener(this.el, "change", this.onAddNewItem, this, true);
			this.ddlist.itemRemovedEvt.subscribe(this.onItemRemoved, this, true);
			this.ddlist.listReorderedEvt.subscribe(this.fireUpdatedEvt, this, true);
		},

		/**
		 * Re-enable the option element when an item is removed by the user
		 */
		onItemRemoved : function(e, params) {

			this.showChoice({
				value : params[0]
			});
			this.el.selectedIndex = 0;

			this.fireUpdatedEvt();

		},

		/**
		 * Add an item to the list when the select changed
		 */
		onAddNewItem : function() {

			var value, position, choice;

			if (this.el.selectedIndex !== 0) {

				// Get the selector value
				value = SIDE.MyDSMultiSelectField.superclass.getValue.call(this);

				position = this.getChoicePosition({
					value : value
				});
				choice = this.choicesList[position];

				this.ddlist.addItem({
					value : value,
					label : choice.label
				});

				// hide choice that has just been selected (+ select first
				// choice)
				this.hideChoice({
					position : position
				});
				this.el.selectedIndex = 0;

				this.fireUpdatedEvt();

			}
		},

		/**
		 * Set the value of the list
		 * 
		 * @param {String}
		 *            value The value to set
		 * @param {boolean}
		 *            [sendUpdatedEvt] (optional) Wether this setValue should
		 *            fire the updatedEvt or not (default is true, pass false to
		 *            NOT send the event)
		 */
		setValue : function(value, sendUpdatedEvt) {

			var i, length, position, choice, ddlistValue = [];

			if (!YAHOO.lang.isArray(value)) {
				return;
			}

			// Re-show all choices
			for (i = 0, length = this.choicesList.length; i < length; i += 1) {
				this.showChoice({
					position : i
				});
			}

			// Hide selected choices and fill ddlist value
			for (i = 0, length = value.length; i < length; i += 1) {

				position = this.getChoicePosition({
					value : value[i]
				});
				choice = this.choicesList[position];

				ddlistValue.push({
					value : choice.value,
					label : choice.label
				});

				this.hideChoice({
					position : position
				});
			}

			// set ddlist value
			this.ddlist.setValue(ddlistValue);

			// reset select to first choice
			this.el.selectedIndex = 0;

			if (sendUpdatedEvt !== false) {
				// fire update event
				this.fireUpdatedEvt();
			}
		},

		/**
		 * Return the value
		 * 
		 * @return {Any} an array of selected values
		 */
		getValue : function() {
			return this.ddlist.getValue();
		},
		/**
		 * Send the datasource request for reload
		 */
		reload : function(mode, addedValues) {
			var newValue = [];
			if (mode == "add") {
				newValue = newValue.concat(this.getValue());
				newValue = newValue.concat(addedValues);
			} else if (mode == "replace") {
				newValue = addedValues;
			} else if (mode == "keep") {
				newValue = this.getValue();
			} else if (mode == "cancel" && this.initialValue) {
				newValue = this.initialValue.split(',');
			}

			this.reloadData = {
				added : newValue,
				mode : mode
			};
			this.sendDataRequest();
		},
		/**
		 * Callback for request success
		 */
		onDatasourceSuccess : function(oRequest, oParsedResponse, oPayload) {
			this.choicesList = [];
			this.populateSelect(oParsedResponse.results);
			this.addChoice({
				value : '',
				label : '',
				position : 0
			});
			this.el.selectedIndex = 0;
			this.log("dataloaded");

			var values = [];

			if (this.reloadData) {
				values = this.reloadData.added;
				this.reloadData = null;
			} else if (this.initialValue) {
				values = this.initialValue.split(",");
			}
			this.log("dataloaded init old:" + this.getValue());
			this.setValue(values);
			this.log("dataloaded init new:" + this.getValue());
		}

	});

	// Register this class as "multiselect" type
	inputEx.registerType("dsmultiselect", SIDE.MyDSMultiSelectField);

}());

(function() {

	var lang = YAHOO.lang, Event = YAHOO.util.Event, Dom = YAHOO.util.Dom;
	/**
	 * DDList extension to add edit action
	 */
	SIDE.MyDDList = function(options) {
		this.editLinksTarget = [];
		SIDE.MyDDList.superclass.constructor.call(this, options);
	};
	YAHOO.lang.extend(SIDE.MyDDList, inputEx.widget.DDList, {
		log : function(msg) {
			console.log("[SIDE.MyDDList] " + msg);
		},
		setOptions : function(options) {
			SIDE.MyDDList.superclass.setOptions.call(this, options);
			this.options.editConfig = options.editConfig;
			this.options.currentValueHtmlId = options.currentValueHtmlId;
		},
		/**
		 * Add an item to the list
		 * 
		 * @param {String|Object}
		 *            item Either a string with the given value or an object
		 *            with "label" and "value" attributes
		 */
		addItem : function(item) {
			var li = inputEx.cn('li', {
				className : 'inputEx-DDList-item'
			});
			li.appendChild(inputEx.cn('span', null, null, (typeof item == "object") ? item.label : item));

			// Option for the "remove" link (default: true)
			if (!!this.options.allowDelete) {
				var removeLink = inputEx.cn('a', null, null, "remove");
				li.appendChild(removeLink);
				Event.addListener(removeLink, 'click', function(e) {
					var a = Event.getTarget(e);
					var li = a.parentNode;
					this.removeItem(inputEx.indexOf(li, this.ul.childNodes));
				}, this, true);
			}

			if (!lang.isUndefined(this.options.editConfig) && !this.options.editConfig.disabled) {
				var editLink = inputEx.cn('a', null, null, "edit");
				li.appendChild(editLink);
				var me = this;
				Event.addListener(editLink, 'click', function(e) {
					var editTarget = new SIDE.CreateTarget(me.id + "-dialog", me.options.currentValueHtmlId);
					editTarget.setOptions(me.options.editConfig);
					editTarget.options.formconfig.itemId = item.value;
					editTarget.onNewItem(e, me);
				}, this, true);
			}
			var dditem = new inputEx.widget.DDListItem(li);
			dditem._list = this;

			this.items.push((typeof item == "object") ? item.value : item);

			this.ul.appendChild(li);
		}
	});
}());