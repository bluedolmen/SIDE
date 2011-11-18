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

// This prototype is provided by the Mozilla foundation and
// is distributed under the MIT license.
// http://www.ibiblio.org/pub/Linux/LICENSES/mit.license

if (!Array.prototype.indexOf) {
	Array.prototype.indexOf = function(elt /* , from */) {
		var len = this.length;

		var from = Number(arguments[1]) || 0;
		from = (from < 0) ? Math.ceil(from) : Math.floor(from);
		if (from < 0)
			from += len;

		for (; from < len; from++) {
			if (from in this && this[from] === elt)
				return from;
		}
		return -1;
	};
}
/**
 * ComboBox component.
 * 
 * @namespace SIDE
 * @class SIDE.ComboBox
 */
(function() {
	SIDE.ComboBox = function(htmlId, currentValueHtmlId, initialValue) {
		SIDE.ComboBox.superclass.constructor.call(this, "SIDE.ComboBox", htmlId, [ "button", "menu", "container", "resize", "datasource", "datatable" ]);
		this.htmlid = htmlId;
		this.currentValueHtmlId = currentValueHtmlId;
		this.addedFieldHtmlId = htmlId + "-added";
		this.removedFieldHtmlId = htmlId + "-removed";
		this.DSSelectWidget = null;
		this.initialValue = "";
		if (initialValue) {
			this.initialValue = initialValue;
			console.log("field :" + htmlId + " initialValue :" + initialValue);
		}

	};

	YAHOO.extend(SIDE.ComboBox, Alfresco.component.Base, {

		log : function(msg) {
			console.log("[SIDE.ComboBox] " + msg);
		},
		/**
		 * Object container for initialization options
		 * 
		 * @property options
		 * @type object
		 */
		options : {
			itemType : "",
			multipleSelectMode : false,
			filterTerm : "*",
			advancedQuery : "",
			maxResults : -1
		},
		load : function() {

			var myDataSource = new YAHOO.util.XHRDataSource("/share/proxy/alfresco/api/forms/picker/search/children?selectableType=" + this.options.itemType + "&searchTerm=" + this.options.filterTerm
					+ "&size=" + this.options.maxResults + "&advancedQuery=" + this.options.advancedQuery);
			myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
			myDataSource.responseSchema = {
				fields : [ "nodeRef", "name", "title" ],
				resultsList : "data.items"
			};

			if (this.options.multipleSelectMode) {
				// cardinality n-n
				var multiselect = new SIDE.MyDSMultiSelectField({
					name : "-",
					datasource : myDataSource,
					valueKey : "nodeRef",
					labelKey : "name",
					parentEl : this.htmlid
				}, this.initialValue);
				var me = this;
				multiselect.updatedEvt.subscribe(function(e, params) {
					var values = params[0];
					var toAdd = [];
					var toremove = [];
					var initialValues = null;
					if (me.initialValue) {
						initialValues = me.initialValue.split(",");
					}
					if (initialValues) {
						// compute real nodes to remove
						for ( var c = 0; c < initialValues.length; c++) {
							var oneValue = initialValues[c];
							// search this value in the new list
							if (values.indexOf(oneValue) == -1) {
								toremove.push(oneValue);
							}
						}
						// compute real nodes to add
						for ( var c = 0; c < values.length; c++) {
							var oneValue = values[c];
							// search this value in the initial list
							if (initialValues.indexOf(oneValue) == -1) {
								toAdd.push(oneValue);
							}
						}
					} else {
						toAdd = values;
					}

					me.log("values changed to add :" + toAdd.toString());
					me.log("values changed to remove :" + toremove.toString());
					YAHOO.util.Dom.get(me.addedFieldHtmlId).value = toAdd.toString();
					YAHOO.util.Dom.get(me.removedFieldHtmlId).value = toremove.toString();
				});
				return multiselect;
			} else {
				// cardinality n-1
				var DSSelectWidget = new SIDE.MyDSSelectField({
					name : "-",
					datasource : myDataSource,
					valueKey : "nodeRef",
					labelKey : "name",
					parentEl : this.htmlid
				}, this.initialValue);

				var me = this;
				DSSelectWidget.updatedEvt.subscribe(function(e, params) {
					me.log("updatedEvt :" + e);
					me.log("state :" + params[1].previousState);
					var value = params[0];
					me.log("value :" + value);
					if (params[1].previousState == 'valid') {
						me.log("value changed to :" + value.toString());
						me.log("initialValue :" + me.initialValue);
						if (value != me.initialValue) {
							// real change

							// user create or replace association
							// set selected value into hidden field to add
							// association
							me.log("value changed Add :" + value.toString());
							YAHOO.util.Dom.get(me.addedFieldHtmlId).value = value;

							if (me.initialValue != "") {
								// set association to remove
								me.log("value changed Remove :" + me.initialValue.toString());
								YAHOO.util.Dom.get(me.removedFieldHtmlId).value = me.initialValue;
							}
						} else {
							me.log("cancel change ...");
							// cancel change
							YAHOO.util.Dom.get(me.addedFieldHtmlId).value = "";
							YAHOO.util.Dom.get(me.removedFieldHtmlId).value = "";
						}
					}

				});
				return DSSelectWidget;
			}

		},
		/**
		 * Fired by YUI when parent element is available for scripting
		 * 
		 * @method onReady
		 */
		onReady : function ComboBox_onReady() {
			this.DSSelectWidget = this.load();
			if (this.initialValue) {
				this.setValue(this.initialValue);
			}
		},
		setValue : function ComboBox_setValue(value) {
			this.log("before setValue :" + this.getValue());
			this.DSSelectWidget.setValue(value);
			this.log("after setValue :" + this.getValue());
		},
		getValue : function ComboBox_setValue() {
			return this.DSSelectWidget.getValue();
		}
	});
})();