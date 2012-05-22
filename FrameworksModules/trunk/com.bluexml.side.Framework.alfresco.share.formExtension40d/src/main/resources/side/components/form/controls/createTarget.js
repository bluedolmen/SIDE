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

/**
 * ComboBox component.
 * 
 * @namespace SIDE
 * @class SIDE.ComboBox
 */
(function() {

   var lang = YAHOO.lang, Event = YAHOO.util.Event, Dom = YAHOO.util.Dom;

   SIDE.CreateTarget = function(htmlId, currentValueHtmlId) {
      SIDE.CreateTarget.superclass.constructor.call(this, "SIDE.CreateTarget", htmlId, [ "button", "menu", "container", "resize", "datasource", "datatable" ]);
      this.currentValueHtmlId = currentValueHtmlId;

      YAHOO.Bubbling.on("/side-labs/CreateTarget/openDialog" + this.id, this.onNewItem, this);
   };
   YAHOO.extend(SIDE.CreateTarget, Alfresco.component.Base, {

      log : function(msg) {
         console.log("[SIDE.CreateTarget] " + msg);
      },
      /**
       * Object container for initialization options
       * 
       * @property options
       * @type object
       */
      options : {
         formconfig : {
            itemKind : null, /* node, type */
            itemId : null, /* cm:content, cm:folder ... or nodeRef to edit */
            destination : null, /*
                                  * container noderef only needed in create mode
                                  */
            mode : "create", /* create | edit | view */
            submitType : "multipart", /* json | urlencoded | multipart */
            formId : "" /* the formId to use */
         }
      },

      computeRedirectUrl : function CreateTarget__computeRedirectUrl() {
         var scope = "window.parent.Alfresco.util.ComponentManager.get('" + this.id + "')";
         var successCallback = scope + ".onSuccess";

         var failureCallback = scope + ".onFailure";

         var params = {
            successCallback : successCallback,
            successScope : scope,
            failureCallback : failureCallback,
            failureScope : scope
         }

         var url = Alfresco.constants.URL_SERVICECONTEXT + "side/api/multipartcallback?";
         url += "params=" + lang.JSON.stringify(params);

         return url;
      },

      /**
       * Fired by YUI when parent element is available for scripting
       * 
       * @method onReady
       */
      onReady : function CreateTarget_onReady() {
         this.log("onReady");
      },

      /**
       * New Folder button click handler
       * 
       * @method onNewFolder
       * @param e
       *           {object} DomEvent
       * @param p_obj
       *           {object} Object passed back from addListener method
       */
      onNewItem : function DLTB_onNewFolder(e, p_obj) {

         // Intercept before dialog show
         var doBeforeDialogShow = function DLTB_onNewFolder_doBeforeDialogShow(p_form, p_dialog) {
            // Dom.get(p_dialog.id + "-dialogTitle").innerHTML =
            // this.msg("label.new-folder.title");
            // Dom.get(p_dialog.id + "-dialogHeader").innerHTML =
            // this.msg("label.new-folder.header");
         };

         var templateUrl = Alfresco.constants.URL_SERVICECONTEXT
               + "components/form?itemKind={itemKind}&itemId={itemId}&destination={destination}&mode={mode}&submitType={submitType}&formId={formId}&showCancelButton={showCancelButton}";
         templateUrl += "&redirect=" + this.computeRedirectUrl();

         templateUrl = YAHOO.lang.substitute(templateUrl, this.options.formconfig);

         templateUrl = YAHOO.lang.substitute(templateUrl, {
            site : "cm:" + Alfresco.constants.SITE
         });

         // Using Forms Service, so always create new instance
         this.widgets.createFolder = new Alfresco.module.SimpleDialog(this.id + "-dialog-" + Alfresco.util.generateDomId());
         var me = this;
         this.widgets.createFolder.setOptions({
            width : "33em",
            templateUrl : templateUrl,
            actionUrl : null,
            destroyOnHide : true,
            doBeforeDialogShow : {
               fn : doBeforeDialogShow,
               scope : this
            },
            doBeforeFormSubmit : {
               fn : this.doBeforeSubmit,
               obj : null,
               scope : this
            },
            onSuccess : {
               fn : this.onSuccess,
               scope : this
            },
            onFailure : {
               fn : this.onFailure,
               scope : this
            }
         }).show();
      },

      doBeforeSubmit : function DLTB_onBeforeSubmit(ob) {
         this.widgets.feedbackMessage = Alfresco.util.PopupManager.displayMessage({
            text : Alfresco.util.message("form.control.upload.uploading", this.name),
            spanClass : "wait",
            displayTime : 0
         });
      },
      /**
       * 
       */
      onSuccess : function DLTB_onNewFolder_success(response) {
         this.widgets.feedbackMessage.destroy();
         this.widgets.createFolder.hide();
         var label = this.options.formconfig.mode == "create" ? this.options.formconfig.mode + "-new" : this.options.formconfig.mode;
         Alfresco.util.PopupManager.displayMessage({
            text : this.msg("form.control.object-picker." + label + ".success", "")
         });

         if (this.options.formconfig.mode == "create") {
            YAHOO.Bubbling.fire("/side-labs/onCreateNewItem/" + this.currentValueHtmlId, {
               mode : "add",
               values : [ response.nodeRef ]
            });
         } else if (this.options.formconfig.mode == "edit") {

            YAHOO.Bubbling.fire("/side-labs/onCreateNewItem/" + this.currentValueHtmlId, {
               mode : "keep",
               values : []
            });
         }
      },

      onFailure : function DLTB_onNewFolder_failure(response) {
         this.widgets.feedbackMessage.destroy();
         this.widgets.createFolder.hide();
         var label = this.options.formconfig.mode == "create" ? this.options.formconfig.mode + "-new" : this.options.formconfig.mode;
         Alfresco.util.PopupManager.displayMessage({
            text : this.msg("form.control.object-picker." + label + ".failure", response.message)
         });
      }
   });
})();