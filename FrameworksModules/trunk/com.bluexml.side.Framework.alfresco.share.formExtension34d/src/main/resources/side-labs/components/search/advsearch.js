/**
 * Copyright (C) 2005-2010 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Advanced Search component.
 * 
 * @namespace Alfresco
 * @class Alfresco.AdvancedSearch
 */
(function() {
   /**
    * YUI Library aliases
    */
   var Dom = YAHOO.util.Dom, Event = YAHOO.util.Event;

   /**
    * Alfresco Slingshot aliases
    */
   var $html = Alfresco.util.encodeHTML;

   /**
    * Advanced Search constructor.
    * 
    * @param {String}
    *           htmlId The HTML id of the parent element
    * @return {Alfresco.AdvancedSearch} The new AdvancedSearch instance
    * @constructor
    */
   Alfresco.AdvancedSearch = function(htmlId) {
      Alfresco.AdvancedSearch.superclass.constructor.call(this, "Alfresco.AdvancedSearch", htmlId, [ "button", "container" ]);

      YAHOO.Bubbling.on("beforeFormRuntimeInit", this.onBeforeFormRuntimeInit, this);

      return this;
   };

   YAHOO.extend(Alfresco.AdvancedSearch, Alfresco.component.Base, {
      /**
       * Object container for initialization options
       * 
       * @property options
       * @type object
       */
      options : {
         /**
          * Current siteId
          * 
          * @property siteId
          * @type string
          */
         siteId : "",

         /**
          * Search Form objects, for example: { id: "advanced-search", type:
          * "cm:content", label: "Content", description: "All types of content" }
          * 
          * @property searchForms
          * @type Array
          */
         searchForms : [],

         /**
          * Previously saved query, if any
          * 
          * @property savedQuery
          * @type string
          */
         savedQuery : ""

      },

      /**
       * Currently visible Search Form object
       */
      currentForm : null,

      /**
       * Fired by YUI when parent element is available for scripting. Component
       * initialisation, including instantiation of YUI widgets and event
       * listener binding.
       * 
       * @method onReady
       */
      onReady : function ADVSearch_onReady() {
         var me = this, domId = this.id + "-form-list", elList = Dom.get(domId);

         // see if a saved query json string is provided
         var defaultForm = this.options.searchForms[0];
         if (this.options.savedQuery.length !== 0) {
            var savedQuery = YAHOO.lang.JSON.parse(this.options.savedQuery);
            if (savedQuery.datatype) {
               for ( var f in this.options.searchForms) {
                  var form = this.options.searchForms[f];
                  if (form.type === savedQuery.datatype) {
                     // found previous form datatype - use as first form to
                     // display
                     defaultForm = form;
                     break;
                  }
               }
            }
         }

         // search YUI button and menus
         this.widgets.searchButton1 = Alfresco.util.createYUIButton(this, "search-button-1", this.onSearchClick);
         this.widgets.searchButton2 = Alfresco.util.createYUIButton(this, "search-button-2", this.onSearchClick);
         this.widgets.formButton = new YAHOO.widget.Button(this.id + "-selected-form-button", {
            type : "menu",
            menu : this.id + "-selected-form-list"
         });
         this.widgets.formButton.set("label", defaultForm.label);
         this.widgets.formButton.set("title", defaultForm.description);

         // event handler for form menu
         this.widgets.formButton.getMenu().subscribe("click", function(p_sType, p_aArgs) {

            me.saveCurrentFieldValues();
            // update selected item menu button label
            var form = me.options.searchForms[p_aArgs[1].index];
            me.widgets.formButton.set("label", form.label);
            me.widgets.formButton.set("title", form.description);

            var isCached = form.htmlid != undefined && form.htmlid != null && form.htmlid != "";
            // render the appropriate form template
            me.renderFormTemplate(form, true);

            // Repopulate current form from url query data?
            if (isCached) {
               me.currentForm.repopulate = false;
               me.repopulateCurrentForm();
            }
         });

         // render initial form template
         // form = {description: '', id: '', label:'', type:
         // 'Kinedoc:org_kinedoc_ActeDeCongres'}
         this.renderFormTemplate(defaultForm, true);

         // register the "enter" event on the search text field
         var queryInput = Dom.get(this.id + "-search-text");
         this.widgets.enterListener = new YAHOO.util.KeyListener(queryInput, {
            keys : YAHOO.util.KeyListener.KEY.ENTER
         }, {
            fn : me._searchEnterHandler,
            scope : this,
            correctScope : true
         }, "keydown").enable();

         // Finally show the component body here to prevent UI artifacts on YUI
         // button decoration
         Dom.setStyle(this.id + "-body", "visibility", "visible");
      },

      /**
       * SIDE add on enable history between forms based on field.name
       */
      saveCurrentFieldValues : function ADVSearch_saveCurrentFieldValues() {
         // get the current forms, search fields values and sore them in a map
         // field.name -> field.value
         // so if some forms share some fields we can populate them with
         // previous values
         if (this.currentForm) {
            var formData = this.currentForm.runtime.getFormData();
            this.options.savedQuery = YAHOO.lang.JSON.stringify(formData);
            // console.log("save "+this.options.savedQuery);
         }
      },

      /**
       * DEFAULT ACTION EVENT HANDLERS Handlers for standard events fired from
       * YUI widgets, e.g. "click"
       */

      /**
       * Loads or retrieves from cache the Form template for a given content
       * type
       * 
       * @method renderFormTemplate
       * @param form
       *           {Object} Form descriptor to render template for
       * @param repopulate
       *           {boolean} If true, repopulate form instance based on supplied
       *           data
       */
      renderFormTemplate : function ADVSearch_renderFormTemplate(form, repopulate) {
         // update current form state
         this.currentForm = form;
         this.currentForm.repopulate = repopulate;

         var containerDiv = Dom.get(this.id + "-forms");

         var visibleFormFn = function() {
            // hide visible form if any
            for ( var i = 0, c = containerDiv.children; i < c.length; i++) {
               if (!Dom.hasClass(c[i], "hidden")) {
                  Dom.addClass(c[i], "hidden");
                  break;
               }
            }

            // display cached form element
            Dom.removeClass(form.htmlid, "hidden");

            // reset focus to search input textbox
            Dom.get(this.id + "-search-text").focus();
         };

         if (!form.htmlid) {
            // generate child container div for this form
            // var htmlid = this.id + "_" + containerDiv.children.length;
            var htmlid = form.id + '-' + form.type.replace(/:/, '_');

            var formDiv = document.createElement("div");
            formDiv.id = htmlid;
            Dom.addClass(formDiv, "hidden");
            Dom.addClass(formDiv, "share-form");
            // Dom.addClass(formDiv, form.id + '-' + form.type.replace(/:/,
            // '_'))

            // cache htmlid so we know the form is present on the form
            form.htmlid = htmlid;

            // load the form component for the appropriate type
            var formUrl = YAHOO.lang.substitute(Alfresco.constants.URL_SERVICECONTEXT
                  + "components/form?itemKind=type&itemId={itemId}&formId={formId}&mode=edit&showSubmitButton=false&showCancelButton=false", {
               itemId : form.type,
               formId : form.id
            });
            var formData = {
               htmlid : htmlid
            };

            /*
             * containerDiv.appendChild(formDiv); YAHOO.plugin.Dispatcher.init
             * ({relative: true}); YAHOO.plugin.Dispatcher.fetch (htmlid,
             * formUrl + '&htmlid=' + htmlid, {action: 'add'});
             * visibleFormFn.call(this);
             */
            Alfresco.util.Ajax.request({
               url : formUrl,
               dataObj : formData,
               successCallback : {
                  fn : function ADVSearch_onFormTemplateLoaded(response) {
                     // Inject the template from the XHR request into the child
                     // container div
                     formDiv.innerHTML = response.serverResponse.responseText;
                     containerDiv.appendChild(formDiv);
                     visibleFormFn.call(this);
                  },
                  scope : this
               },
               failureMessage : "Could not load form component '" + formUrl + "'.",
               scope : this,
               execScripts : true
            });
         } else {
            visibleFormFn.call(this);
         }
      },

      /**
       * Repopulate currently displayed Form fields based on saved query data
       * 
       * @method repopulateCurrentForm
       */
      repopulateCurrentForm : function ADVSearch_repopulateCurrentForm() {
         if (this.options.savedQuery.length !== 0) {
            var savedQuery = YAHOO.lang.JSON.parse(this.options.savedQuery);
            var elForm = Dom.get(this.currentForm.runtime.formId);
            console.log("load from " + this.options.savedQuery);
            for ( var i = 0, j = elForm.elements.length; i < j; i++) {
               var element = elForm.elements[i];
               var name = element.name;

               if (name != undefined && name !== "-") {

                  var savedValue = savedQuery[name];
                  if (savedValue !== undefined) {

                     // search if some widget exists for this field
                     if (name.indexOf("assoc_") == 0) {
                        // remove last segment '_added' or '_remove'
                        var hiddenFieldId = element.id.substring(0, element.id.lastIndexOf("-cntrl-added"));

                        var hiddenField = Dom.get(hiddenFieldId);
                        if (hiddenField != null) {
                           if (hiddenField.widget != undefined) {
                              console.log("name " + name);
                              console.log("id " + element.id);
                              console.log("hiddenFieldId " + hiddenFieldId);
                              console.log("savedValue " + savedValue);
                              console.log("widget name :" + hiddenField.widget.name);
                              console.log("call reload mode replace value :" + savedValue.split(","));
                              // we set initial value so widget can
                              if (savedValue != "") {
                                 hiddenField.widget.reload("replace", savedValue.split(","));
                              } else {
                                 hiddenField.widget.reload("replace", []);
                              }
                           } else {
                              // the widget is not ready so need to reload after
                              // 'onInitialized' event
                              var local_savedValue = savedValue;
                              YAHOO.Bubbling.on("/side-labs/onInitialized/" + hiddenFieldId, function(e, ob1, context) {
                                 var widget = ob1[1];
                                 if (local_savedValue != "") {
                                    widget.reload("replace", savedValue.split(","));
                                 } else {
                                    widget.reload("replace", []);
                                 }
                              }, this);
                           }

                        }

                     } else if (element.type === "checkbox" || element.type === "radio") {
                        element.checked = (savedValue === "true");
                     } else {
                        element.value = savedValue;
                     }
                  }
               } else {

               }
            }
         }
      },

      /**
       * Event handler that gets fired when user clicks the Search button.
       * 
       * @method onSearchClick
       * @param e
       *           {object} DomEvent
       * @param obj
       *           {object} Object passed back from addListener method
       */
      onSearchClick : function ADVSearch_onSearchClick(e, obj) {
         // retrieve form data structure directly from the runtime
         var formData = this.currentForm.runtime.getFormData();

         // add DD type to form data structure
         formData.datatype = this.currentForm.type;

         // build and execute url for search page
         var url = '';
         if (window.location.search.substring(1).indexOf('redirect') != -1) {
            var qs = window.location.search.substring(1);
            qs = qs.split("+").join(" ");
            var params = {};
            var tokens;
            var pattern = /[?&]?([^=]+)=([^&]*)/g;

            while (tokens = pattern.exec(qs)) {
               params[decodeURIComponent(tokens[1])] = decodeURIComponent(tokens[2]);
            }

            url = YAHOO.lang.substitute(params['redirect'] + "?site=kinedoc&term={terms}&query={query}", {
               terms : encodeURIComponent(Dom.get(this.id + "-search-text").value),
               query : encodeURIComponent(YAHOO.lang.JSON.stringify(formData))
            });
         } else {
            // var url =
            // YAHOO.lang.substitute(Alfresco.constants.URL_PAGECONTEXT +
            // "{site}search?t={terms}&q={query}",
            url = YAHOO.lang.substitute(Alfresco.constants.URL_PAGECONTEXT + "{site}search?t={terms}&q={query}", {
               site : (this.options.siteId.length !== 0 ? ("site/" + this.options.siteId + "/") : ""),
               terms : encodeURIComponent(Dom.get(this.id + "-search-text").value),
               query : encodeURIComponent(YAHOO.lang.JSON.stringify(formData))
            });
         }

         window.location.href = url;
      },

      /**
       * Event handler called when the "beforeFormRuntimeInit" event is received
       */
      onBeforeFormRuntimeInit : function ADVSearch_onBeforeFormRuntimeInit(layer, args) {
         // extract the current form runtime - so we can reference it later
         this.currentForm.runtime = args[1].runtime;

         // Repopulate current form from url query data?
         if (this.currentForm.repopulate) {
            this.currentForm.repopulate = false;
            this.repopulateCurrentForm();
         }
      },

      /**
       * Search text box ENTER key event handler
       * 
       * @method _searchEnterHandler
       */
      _searchEnterHandler : function ADVSearch__searchEnterHandler(e, args) {
         this.onSearchClick(e, args);
      }
   });
})();