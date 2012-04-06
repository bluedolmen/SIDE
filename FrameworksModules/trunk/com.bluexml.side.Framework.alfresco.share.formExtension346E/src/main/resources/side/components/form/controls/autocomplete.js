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
 * Autocomplete component.
 * 
 * @namespace SIDE
 * @class SIDE.Autocomplete
 */
(function() {
	SIDE.Autocomplete = function(htmlId, currentValueHtmlId, initialValue) {
		SIDE.Autocomplete.superclass.constructor.call(this, "SIDE.Autocomplete", htmlId, [ "button", "menu", "container", "resize", "datasource", "datatable" ]);
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

		YAHOO.Bubbling.on("/side-labs/onCreateNewItem/" + this.currentValueHtmlId, this.reloadBehavior, this);
	};

	YAHOO.extend(SIDE.Autocomplete, Alfresco.component.Base, {

		log : function(msg) {
			console.log("[SIDE.Autocomplete] " + msg);
		},
		/**
		 * Object container for initialization options
		 * 
		 * @property options
		 * @type object
		 */
		options : {
			disabled : false,
			itemType : "",
			multipleSelectMode : false,
			mandatory : false,
			filterTerm : "*",
			maxResults : -1,
			selectableTypeIsAspect : false,
			searchInSite : true
		},
		setOptions : function(options) {
			this.log("setOptions :" + options);
			SIDE.Autocomplete.superclass.setOptions.call(this, options);
			if (options.getDataSource) {
				this.options.getDataSource = options.getDataSource;
			} else {
				var me = this;
				this.options.getDataSource = function _getDataSource(me) {
					var url = Alfresco.constants.PROXY_URI + "api/forms/picker/search/children?selectableType=" + me.options.itemType + "&size=" + me.options.maxResults + "&selectableTypeIsAspect="
							+ me.options.selectableTypeIsAspect;
					if (me.options.searchInSite && Alfresco.constants.SITE != "" && Alfresco.constants.SITE != undefined) {
						url += "&site=" + Alfresco.constants.SITE;
					}
					if (me.options.startLocation) {
                  url += "&xpath=" + YAHOO.lang.substitute(me.options.startLocation, me.options);
               }
               url = encodeURI(url);
					var myDataSource = new YAHOO.util.XHRDataSource(url);

					myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
					myDataSource.responseSchema = {
						fields : [ "nodeRef", "name", "title" ],
						resultsList : "data.items"
					};

					return myDataSource;
				};
			}
		},
		load : function() {
			var me = this;

			myDataSource = this.options.getDataSource(this);
			if (this.options.disabled) {
				// use object-piker instance
				return new Alfresco.ObjectFinder(this.htmlid, this.currentValueHtmlId).setOptions({
					disabled : true,
					field : this.options.field,
					compactMode : true,
					currentValue : this.initialValue,
				});
			} else if (this.options.multipleSelectMode) {
				// cardinality n-n
				// compute query to remove from result selected items
				var gen_req = function(sQuery) {
					var notClause = "";

					var values = null;
					if (me.options.multipleSelectMode) {
						values = me.getValue();
					} else {
						values = [ me.getValue() ];
					}

					if (values.length > 0) {
						notClause = "&advancedQuery=NOT ( ";
						for ( var x = 0; x < values.length; x++) {
							var val = values[x];
							notClause += "sys:node-uuid=" + val.substring("workspace://SpacesStore/".length);

							if (x != values.length - 1) {
								notClause += " OR ";
							}
						}
						notClause += " )";
					}

					me.log("sQuery :" + sQuery);
					me.log(" notClause :" + notClause);
					return notClause + "&searchTerm=" + sQuery;
				};

				var mymultiautocomplete = inputEx({
					name : "-",
					type : "mymultiautocomplete",
					typeInvite : "enter target name",
					parentEl : this.htmlid,
					datasource : myDataSource,

					// Format the hidden value (value returned by the form)
					returnValue : function(oResultItem) {
						return oResultItem[0];
					},
					returnLabel : function(oResultItem) {
						return oResultItem[1];
					},
					generateRequest : gen_req,
					autoComp : {
						queryQuestionMark : false,
						forceSelection : true,
						minQueryLength : 2,
						maxResultsDisplayed : 50,
						formatResult : function(oResultItem, sQuery) {
							var sMarkup = oResultItem[1];
							return sMarkup;
						}
					}
				});

				mymultiautocomplete.updatedEvt.subscribe(function(e, params) {
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
					YAHOO.util.Dom.get(me.currentValueHtmlId).value = values.toString();
					if (me.options.mandatory) {
						YAHOO.Bubbling.fire("mandatoryControlValueUpdated", me);
					}
				});
				return mymultiautocomplete;
			} else {
				// cardinality n-1
				// tell to myDataSource.generateRequest to use searchTerm as
				// query parameter to filter
				myDataSource.scriptQueryParam = "&searchTerm";

				var myautocomplete = inputEx({
					name : "-",
					type : "myautocomplete",
					parentEl : this.htmlid,
					datasource : myDataSource,
					typeInvite : "enter target name",
					// Format the hidden value (value returned by the form)
					returnValue : function(oResultItem) {
						return oResultItem[0];
					},
					returnLabel : function(oResultItem) {
						return oResultItem[1];
					},

					autoComp : {
						queryQuestionMark : false,
						forceSelection : true,
						minQueryLength : 2,
						maxResultsDisplayed : 50,
						formatResult : function(oResultItem, sQuery) {
							var sMarkup = oResultItem[1];
							return sMarkup;
						}
					}
				});
				var me = this;
				myautocomplete.updatedEvt.subscribe(function(e, params) {
					me.log("updatedEvt :" + e);
					me.log("state :" + params[1].previousState);
					var value = params[0];
					me.log("value :" + value);
					if (params[1].previousState == 'valid' || params[1].previousState == null && value == '') {
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
						YAHOO.util.Dom.get(me.currentValueHtmlId).value = value.toString();
						if (me.options.mandatory) {
							YAHOO.Bubbling.fire("mandatoryControlValueUpdated", me);
						}
					}

				});
				return myautocomplete;
			}

		},
		/**
		 * Fired by YUI when parent element is available for scripting
		 * 
		 * @method onReady
		 */
		onReady : function Autocomplete_onReady() {
			YAHOO.Bubbling.fire("/side-labs/onReady/" + this.currentValueHtmlId, this);
			this.DSSelectWidget = this.load();
			YAHOO.Bubbling.fire("/side-labs/onLoaded/" + this.currentValueHtmlId, this);

			if (!this.options.disabled && this.initialValue) {
				this.setValue(this.initialValue);
			}
			YAHOO.Bubbling.fire("/side-labs/onInitialized/" + this.currentValueHtmlId, this);
			if (!this.options.disabled) {
				// add widget reference on html element
				var el = document.getElementById(this.currentValueHtmlId);
				el.widget = this;
			}
		},
		setValue : function Autocomplete_setValue(value) {
			this.log("before setValue :" + this.getValue());
			var values = value.split(",");
			if (values.length > 0) {
				var me = this;
				var onSuccess = function ObjectFinder__loadSelectedItems_onSuccess(response) {
					var items = response.json.data.items, item;
					this.selectedItems = {};
					var oDatas = [];
					for ( var i = 0, il = items.length; i < il; i++) {
						item = items[i];
						oDatas.push([ item.nodeRef, item.name ]);
					}

					if (me.options.multipleSelectMode) {
						me.DSSelectWidget.setValuesAndLabels(oDatas);
					} else {
						me.DSSelectWidget.setValueAndLabel(oDatas[0]);
					}

				};
				var onFailure = function ObjectFinder__loadSelectedItems_onFailure(response) {

				};
				// call service to get nodes properties
				Alfresco.util.Ajax.jsonRequest({
					url : Alfresco.constants.PROXY_URI + "api/forms/picker/items",
					method : "POST",
					dataObj : {
						items : values,
						itemValueType : "nodeRef"
					},
					successCallback : {
						fn : onSuccess,
						scope : this
					},
					failureCallback : {
						fn : onFailure,
						scope : this
					}
				});
			}

		},

		getValue : function Autocomplete_setValue() {
			return this.DSSelectWidget.getValue();
		},
		/**
		 * reload the list can make selection changes : mode
		 * :[add|replace|keep|cancel] use keep to only reload the list cancel
		 * restore values to initial values
		 */
		reload : function Autocomplete_addNew(mode, addNodesToSelection) {
			this.log("mode :" + mode + " addNodesToSelection :" + addNodesToSelection);
			var newValues = [];

			var values = this.getValue();
			if (mode === 'add') {
				// add the given values to selectList
				if (this.options.multipleSelectMode) {
					newValues = newValues.concat(values);
				}
				newValues = newValues.concat(addNodesToSelection);
				this.log("mode add");
			} else if (mode === 'replace' && addNodesToSelection != null) {
				newValues = newValues.concat(addNodesToSelection);
				this.log("mode replace");
			} else if (mode === 'cancel' && this.initialValue != null) {
				var initArray = this.initialValue.split(',');
				newValues = newValues.concat(initArray);
				this.log("mode cancel");
			}

			this.setValue(newValues.toString());
		},
		/**
		 * this method is used to define a behavior on event fired to reload the
		 * widget.
		 */
		reloadBehavior : function ComboBox_reloadBehavior(event, obj, scope) {
			this.log("event :" + event);
			this.log("obj :" + obj);
			this.log("scope :" + scope);
			var context = obj[1];
			this.log("obj[1] :" + context);
			this.reload(context.mode, context.values);
		},
		/**
		 * example how to call reload and set parameters
		 */
		fireReload : function ComboBox_fireReload() {
			YAHOO.Bubbling.fire("/side-labs/onCreateNewItem/" + this.currentValueHtmlId, {
				mode : "add",
				values : [ "workspace://SpacesStore/7eca31e0-7b33-4f73-b3d3-86e1d9e6fbb2" ]
			});
		}
	});
})();