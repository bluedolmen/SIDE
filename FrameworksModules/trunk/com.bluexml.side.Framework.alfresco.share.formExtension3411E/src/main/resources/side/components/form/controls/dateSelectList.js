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
    * @return {SIDE.DateWidget} The new DatePicker instance
    * @constructor
    */
   SIDE.DateWidget = function(htmlId, currentValueHtmlId) {
      // Mandatory properties
      this.name = "SIDE.DateWidget";
      this.id = htmlId;
      this.currentValueHtmlId = currentValueHtmlId;

      /* Register this component */
      Alfresco.util.ComponentManager.register(this);

      /* Load YUI Components */
      Alfresco.util.YUILoaderHelper.require([ "button", "calendar" ], this.onComponentsLoaded, this);

      this.widgets = {};

      return this;
   };

   SIDE.DateWidget.prototype = {

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
       * @return {SIDE.DateWidget} returns 'this' for method chaining
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
       * @return {SIDE.DateWidget} returns 'this' for method chaining
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
         console.log("[SIDE.DateWidget] " + msg);
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
         this.widgets.day = Dom.get(this.currentValueHtmlId + "-day");
         this.widgets.month = Dom.get(this.currentValueHtmlId + "-month");
         this.widgets.year = Dom.get(this.currentValueHtmlId + "-year");

         YAHOO.util.Event.addListener([ this.currentValueHtmlId + "-day", this.currentValueHtmlId + "-month", this.currentValueHtmlId + "-year" ], "change", this.updateHiddenDate, this);

         var minDate = this._getDefaultDate(this.options.minDate);
         var maxDate = this._getDefaultDate(this.options.maxDate);

         for ( var c = maxDate.getFullYear(); c >= minDate.getFullYear(); c--) {
            var option = document.createElement("option");
            option.text = c;
            option.value = c;
            this._addOption(this.widgets.year, option, null);
         }

         // manage mandatory day and month
         if (!this.options.mandatory) {
            var option = document.createElement("option");
            option.text = "-";
            option.value = "-";
            this._addOption(this.widgets.year, option, 0);
         }
         if (!this.options.mandatoryDay) {
            var option = document.createElement("option");
            option.text = "-";
            option.value = "-";
            this._addOption(this.widgets.day, option, 0);
         }

         if (!this.options.mandatoryMonth) {
            var option = document.createElement("option");
            option.text = "-";
            option.value = "-";
            this._addOption(this.widgets.month, option, 0);
         }

         this.loadCurrentValue();

         this.log("fire event :" + "/side-labs/onInitialized/" + this.currentValueHtmlId);
         YAHOO.Bubbling.fire("/side-labs/onInitialized/" + this.currentValueHtmlId, this);
      },
      updateHiddenDate : function DatePiker_updateHiddenDate(event, context) {
         var isoValue = "";
         if (context.widgets.year.value != "-") {
            var newDate = new Date(context.widgets.year.value, Number(context._getMonthDayValue(context.widgets.month.value)) - 1, context._getMonthDayValue(context.widgets.day.value));
            isoValue = Alfresco.util.toISO8601(newDate, {
               "milliseconds" : true
            });
         }

         Dom.get(context.currentValueHtmlId).value = isoValue;
         YAHOO.Bubbling.fire("/side-labs/onDateSelectListChanged/" + this.currentValueHtmlId, context);
      },

      loadCurrentValue : function DatePicker_loadCurrentValue() {
         var currentDate = null;

         var hasCurrentValue = this.options.currentValue !== null && this.options.currentValue !== "";
         // calculate current date
         if (hasCurrentValue) {
            currentDate = Alfresco.util.fromISO8601(this.options.currentValue);
         } else {
            currentDate = new Date();
         }

         this._selectValues(currentDate);

      },

      /**
       * @method setValue
       * @param date :
       *           js Date Object or ISO8601 date string
       */
      setValue : function DatePicker_setValue(date) {
         var parsedDate = null;
         if (typeof date == "object") {
            parsedDate = date;
         } else {
            parsedDate = Alfresco.util.fromISO8601(date);
         }
         this._selectValues(parsedDate);

      },

      /**
       * @method _selectValues
       * @param date :
       *           js Date Object
       */
      _selectValues : function DatePicker__selectValues(date) {
         var day = date.getDate();
         var month = date.getMonth() + 1;
         var year = date.getFullYear();

         this._selectValueFor(this.widgets.day, day);
         this._selectValueFor(this.widgets.month, month);
         this._selectValueFor(this.widgets.year, year);
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
         return Alfresco.util.message.call(this, messageId, "SIDE.DateWidget", Array.prototype.slice.call(arguments).slice(1));
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
