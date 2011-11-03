/**
 * SIDE root namespace.
 * 
 * @namespace SIDE
 */
// Ensure Alfresco root object exists
if (typeof SIDE == "undefined" || !SIDE) {
	var SIDE = {};
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
		this.initialValue = null;
		if (initialValue) {
			this.initialValue = initialValue;
		}
	};

	YAHOO.extend(SIDE.ComboBox, Alfresco.component.Base, {
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
			maxResults : -1
		},
		load : function() {

			var myDataSource = new YAHOO.util.XHRDataSource("/share/proxy/alfresco/api/forms/picker/search/children?selectableType=" + this.options.itemType + "&searchTerm=" + this.options.filterTerm
					+ "&size=" + this.options.maxResults);
			myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
			myDataSource.responseSchema = {
				fields : [ "nodeRef", "name", "title" ],
				resultsList : "data.items"
			};

			if (this.options.multipleSelectMode) {
				
				return null;
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
					var value = params[0];
//					console.log("before value change :" + this.getValue());
					var addedField = YAHOO.util.Dom.get(me.addedFieldHtmlId);
					
					// set selected value into hidden field to add association
					var previousValue = addedField.value;
					addedField.value = value;
					
					// set association to remove
					YAHOO.util.Dom.get(me.removedFieldHtmlId).value = previousValue;
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

			console.log(this.initialValue);
			if (this.initialValue) {
				this.setValue(this.initialValue);
			}
		},
		setValue : function ComboBox_setValue(value) {
//			console.log("before setValue :" + this.getValue());
			this.DSSelectWidget.setValue(value);
//			console.log("after setValue :" + this.getValue());
		},
		getValue : function ComboBox_setValue() {
			return this.DSSelectWidget.getValue();
		}
	});
})();