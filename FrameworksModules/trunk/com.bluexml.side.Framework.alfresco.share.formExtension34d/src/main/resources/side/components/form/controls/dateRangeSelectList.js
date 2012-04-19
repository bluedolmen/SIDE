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
   /**
    * YUI Library aliases
    */
   var Dom = YAHOO.util.Dom, Event = YAHOO.util.Event, KeyListener = YAHOO.util.KeyListener;

   /**
    * Alfresco Slingshot aliases
    */
   var $html = Alfresco.util.encodeHTML;

   /**
    * DatePicker constructor.
    * 
    * @param {String}
    *           htmlId The HTML id of the parent element
    * @param {String}
    *           currentValueHtmlId The HTML id of the parent element
    * @return {SIDE.DateRangeWidget} The new DatePicker instance
    * @constructor
    */
   SIDE.DateRangeWidget = function(htmlId, currentValueHtmlId) {
      // Mandatory properties
      this.name = "SIDE.DateRangeWidget";
      this.id = htmlId;
      this.currentValueHtmlId = currentValueHtmlId;

      /* Register this component */
      Alfresco.util.ComponentManager.register(this);

      /* Load YUI Components */
      Alfresco.util.YUILoaderHelper.require([ "button", "calendar" ], this.onComponentsLoaded, this);

      this.widgets = {};

      return this;
   };

   SIDE.DateRangeWidget.prototype = {

      widgets : {},

      /**
       * Object container for initialization options
       * 
       * @property options
       * @type object
       */
      options : {
         /**
          * The current value
          * 
          * @property currentValue
          * @type string
          */
         currentValue : "",
         /**
          * Flag to indicate whether the field is mandatory
          * 
          * @property mandatory
          * @type boolean
          * @default false
          */
         mandatory : false,
         minDate : '1900/1/1',
         maxDate : 'now',
         mandatoryDay : true,
         mandatoryMonth : true
      },
      /**
       * Set multiple initialization options at once.
       * 
       * @method setOptions
       * @param obj
       *           {object} Object literal specifying a set of options
       * @return {SIDE.DateRangeWidget} returns 'this' for method chaining
       */
      setOptions : function DatePicker_setOptions(obj) {
         this.options = YAHOO.lang.merge(this.options, obj);
         return this;
      },

      /**
       * Set messages for this component.
       * 
       * @method setMessages
       * @param obj
       *           {object} Object literal specifying a set of messages
       * @return {SIDE.DateRangeWidget} returns 'this' for method chaining
       */
      setMessages : function DatePicker_setMessages(obj) {
         Alfresco.util.addMessages(obj, this.name);
         return this;
      },
      /**
       * Fired by YUILoaderHelper when required component script files have been
       * loaded into the browser.
       * 
       * @method onComponentsLoaded
       */
      onComponentsLoaded : function DatePicker_onComponentsLoaded() {
         Event.onContentReady(this.id, this.onReady, this, true);
      },

      log : function(msg) {
         console.log("[SIDE.DateRangeWidget] " + msg);
      },
      /**
       * Fired by YUI when parent element is available for scripting. Component
       * initialisation, including instantiation of YUI widgets and event
       * listener binding.
       * 
       * @method onReady
       */
      onReady : function DatePicker_onReady() {
         Dom.get(this.currentValueHtmlId).widget = this;

         this.initMinDate();
         this.initMaxDate();

         this.loadCurrentValue();

         this.log("fire event :" + "/side-labs/onInitialized/" + this.currentValueHtmlId);
         YAHOO.Bubbling.fire("/side-labs/onInitialized/" + this.currentValueHtmlId, this);
      },

      initMinDate : function() {
         this.widgets.dayMin = Dom.get("min-" + this.currentValueHtmlId + "-day");
         this.widgets.monthMin = Dom.get("min-" + this.currentValueHtmlId + "-month");
         this.widgets.yearMin = Dom.get("min-" + this.currentValueHtmlId + "-year");

         YAHOO.util.Event.addListener([ "min-" + this.currentValueHtmlId + "-day", "min-" + this.currentValueHtmlId + "-month", "min-" + this.currentValueHtmlId + "-year" ], "change",
               this.updateHiddenDate, this);

         var minDate = this._getDefaultDate(this.options.minDate);
         var maxDate = this._getDefaultDate(this.options.maxDate);

         for ( var c = maxDate.getFullYear(); c >= minDate.getFullYear(); c--) {
            var option = document.createElement("option");
            option.text = c;
            option.value = c;
            this._addOption(this.widgets.yearMin, option, null);
         }

         // manage mandatory day and month
         if (!this.options.mandatory) {
            var option = document.createElement("option");
            option.text = "-";
            option.value = "-";
            this._addOption(this.widgets.yearMin, option, 0);
         }
         if (!this.options.mandatoryDay) {
            var option = document.createElement("option");
            option.text = "-";
            option.value = "-";
            this._addOption(this.widgets.dayMin, option, 0);
         }

         if (!this.options.mandatoryMonth) {
            var option = document.createElement("option");
            option.text = "-";
            option.value = "-";
            this._addOption(this.widgets.monthMin, option, 0);
         }

      },

      initMaxDate : function() {
         this.widgets.dayMax = Dom.get("max-" + this.currentValueHtmlId + "-day");
         this.widgets.monthMax = Dom.get("max-" + this.currentValueHtmlId + "-month");
         this.widgets.yearMax = Dom.get("max-" + this.currentValueHtmlId + "-year");

         YAHOO.util.Event.addListener([ "max-" + this.currentValueHtmlId + "-day", "max-" + this.currentValueHtmlId + "-month", "max-" + this.currentValueHtmlId + "-year" ], "change",
               this.updateHiddenDate, this);

         var minDate = this._getDefaultDate(this.options.minDate);
         var maxDate = this._getDefaultDate(this.options.maxDate);

         for ( var c = maxDate.getFullYear(); c >= minDate.getFullYear(); c--) {
            var option = document.createElement("option");
            option.text = c;
            option.value = c;
            this._addOption(this.widgets.yearMax, option, null);
         }

         // manage mandatory day and month
         if (!this.options.mandatory) {
            var option = document.createElement("option");
            option.text = "-";
            option.value = "-";
            this._addOption(this.widgets.yearMax, option, 0);
         }
         if (!this.options.mandatoryDay) {
            var option = document.createElement("option");
            option.text = "-";
            option.value = "-";
            this._addOption(this.widgets.dayMax, option, 0);
         }

         if (!this.options.mandatoryMonth) {
            var option = document.createElement("option");
            option.text = "-";
            option.value = "-";
            this._addOption(this.widgets.monthMax, option, 0);
         }

      },
      updateHiddenDate : function DatePiker_updateHiddenDate(event, context) {
         var isoValueMin = "";
         var isoValueMax = "";
         if (context.widgets.yearMin.value != "-") {
            var newDate = new Date(context.widgets.yearMin.value, Number(context._getMonthDayValue(context.widgets.monthMin.value)) - 1, context._getMonthDayValue(context.widgets.dayMin.value));
            isoValueMin = Alfresco.util.toISO8601(newDate, {
               "milliseconds" : true
            });
         }

         if (context.widgets.yearMax.value != "-") {
            var newDate = new Date(context.widgets.yearMax.value, Number(context._getMonthDayValue(context.widgets.monthMax.value)) - 1, context._getMonthDayValue(context.widgets.dayMax.value));
            isoValueMax = Alfresco.util.toISO8601(newDate, {
               "milliseconds" : true
            });
         }

         Dom.get(context.currentValueHtmlId).value = isoValueMin + "|" + isoValueMax;

         YAHOO.Bubbling.fire("/side-labs/onDateSelectListChanged/" + this.currentValueHtmlId, context);
      },

      loadCurrentValue : function DatePicker_loadCurrentValue() {
         var currentDateMin = null;
         var currentDateMax = null;

         var hasCurrentValue = this.options.currentValue !== null && this.options.currentValue !== "";
         // calculate current date
         if (hasCurrentValue) {
            var dateMin = this.options.currentValue.split('|')[0];
            var dateMax = this.options.currentValue.split('|')[1];
            currentDateMin = Alfresco.util.fromISO8601(dateMin);
            currentDateMax = Alfresco.util.fromISO8601(dateMax);
         } else {
            currentDateMin = this._getDefaultDate(this.options.minDate);
            currentDateMax = new Date();
         }

         this._selectValues(currentDateMin, "min");
         this._selectValues(currentDateMax, "max");

         this.updateHiddenDate("", this);
      },

      /**
       * @method setValue
       * @param date :
       *           js ISO8601 date string
       */
      setValue : function DatePicker_setValue(date) {
         var parsedDateMin = null;
         var parsedDateMax = null;
         var dateMin = date.split('|')[0];
         var dateMax = date.split('|')[1];
         parsedDateMin = Alfresco.util.fromISO8601(dateMin);
         parsedDateMax = Alfresco.util.fromISO8601(dateMax);

         this._selectValues(parsedDateMin, "min");
         this._selectValues(parsedDateMax, "max");

      },

      /**
       * @method _selectValues
       * @param date :
       *           js Date Object
       */
      _selectValues : function DatePicker__selectValues(date, rangeId) {
         var day = date.getDate();
         var month = date.getMonth() + 1;
         var year = date.getFullYear();
         if (rangeId == "min") {
            this._selectValueFor(this.widgets.dayMin, day);
            this._selectValueFor(this.widgets.monthMin, month);
            this._selectValueFor(this.widgets.yearMin, year);
         } else {
            this._selectValueFor(this.widgets.dayMax, day);
            this._selectValueFor(this.widgets.monthMax, month);
            this._selectValueFor(this.widgets.yearMax, year);
         }

      },

      /**
       * Gets a custom message
       * 
       * @method _msg
       * @param messageId
       *           {string} The messageId to retrieve
       * @return {string} The custom message
       * @private
       */
      _msg : function DatePicker__msg(messageId) {
         return Alfresco.util.message.call(this, messageId, "SIDE.DateRangeWidget", Array.prototype.slice.call(arguments).slice(1));
      },

      /**
       * 
       */
      _addOption : function DatePicker__addOption(select, option, position) {
         try {
            // for IE earlier than version 8
            select.add(option, select.options[position]);
         } catch (e) {
            select.add(option, position);
         }
      },

      /**
       * 
       */
      _getMonthDayValue : function DatePicker__getMonthDayValue(value) {
         return value == "-" ? 1 : value;
      },

      /**
       * 
       */
      _getDefaultDate : function DatePicker__getDefaultDate(dateString) {
         if (dateString == 'now') {
            return new Date();
         } else {
            var dateA = dateString.split("/");
            var date = new Date();
            date.setFullYear(dateA[0], Number(dateA[1]) - 1, dateA[2]);
            return date;
         }
      },

      /**
       * 
       */
      _selectValueFor : function DatePicker__selectValueFor(select, value) {
         for ( var i = 0; i < select.options.length; i++) {
            var option = select.options[i];
            var v = option.value;
            if (v == value) {
               option.selected = true;
               break;
            }
         }
      }
   };
})();
