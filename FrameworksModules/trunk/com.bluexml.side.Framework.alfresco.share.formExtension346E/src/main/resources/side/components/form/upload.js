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
 * Content control component with FileUpload.
 * 
 * This component renders a file field
 * 
 * @namespace SIDE
 * @class SIDE.Upload
 */

(function() {
   /**
    * YUI Library aliases
    */
   var Dom = YAHOO.util.Dom;

   /**
    * Upload constructor.
    * 
    * @param {String}
    *           htmlId The HTML id of the parent element
    * @return {Alfresco.TextControl} The new Upload instance
    * @constructor
    */
   SIDE.Upload = function(htmlId) {
      return SIDE.Upload.superclass.constructor.call(this, htmlId, "SIDE.Upload");
   };

   YAHOO.extend(SIDE.Upload, Alfresco.RichTextControl, {
      /**
       * Object container for initialization options
       * 
       * @property options
       * @type object
       */
      options : {
         /**
          * Current Form Mode: edit or create
          * 
          * @property formMode
          * @type string
          */
         formMode : "edit",

         /**
          * NodeRef of the item the form is for
          * 
          * @property nodeRef
          * @type string
          */
         nodeRef : null,

         /**
          * The mimetype of the content being created
          * 
          * @property mimeType
          * @type string
          */
         mimeType : null,

         /**
          * Comma separated list of mime types that will be shown in a textarea
          * 
          * @property plainMimeTypes
          * @type string
          */
         plainMimeTypes : "text/plain,text/xml",

         /**
          * Comma separated list of mime types that will be shown in the TinyMCE
          * editor
          * 
          * @property richMimeTypes
          * @type string
          */
         richMimeTypes : "text/html,text/xhtml",

         /**
          * Comma separated list of mime types that will be shown using the img
          * tag and allow upload of new versions
          * 
          * @property imageMimeTypes
          * @type string
          */
         imageMimeTypes : "image/jpeg,image/jpg,image/png",

         /**
          * Whether the (plain text) editor should be forced visible, e.g.
          * mimetype unrecognized
          * 
          * @property forceEditor
          * @type boolean
          */
         forceEditor : false,

         /**
          * Whether (empty) content should be created when editor is hidden,
          * e.g. mimetype unrecognized. Note: Only relevant when forceEditor =
          * false
          * 
          * @property forceContent
          * @type boolean
          */
         forceContent : false
      },

      /**
       * Fired by YUI when parent element is available for scripting. Component
       * initialisation, including instantiation of YUI widgets and event
       * listener binding.
       * 
       * @method onReady
       */
      onReady : function Upload_onReady() {
         if (Alfresco.logger.isDebugEnabled()) {
            Alfresco.logger.debug("Rendering content control for element '" + this.id + "', value = '" + this.options.currentValue + "', nodeRef = '" + this.options.nodeRef + "', mimetype = '"
                  + this.options.mimeType + "'");
            Alfresco.logger.debug("Configured plain mimetypes for element '" + this.id + "': " + this.options.plainMimeTypes);
            Alfresco.logger.debug("Configured rich mimetypes for element '" + this.id + "': " + this.options.richMimeTypes);
            Alfresco.logger.debug("Configured image mimetypes for element '" + this.id + "': " + this.options.imageMimeTypes);
            Alfresco.logger.debug("Editor parameters for element '" + this.id + "': " + YAHOO.lang.dump(this.options.editorParameters));
         }

         var subButton = document.getElementsByName("-")[0];
         var previousAction = subButton.onclick;

         this.widgets.inputFile = Dom.get(this.id);

         // get the cm_field if any
         var cm_name_field = this._getFileNameField();
         var me = this;
         YAHOO.util.Event.addListener(this.widgets.inputFile, "change", function(p_sType, p_aArgs) {
            if (cm_name_field) {
               var inputFileValue = me.widgets.inputFile.value;
               var index = inputFileValue.lastIndexOf('/');
               var index2 = inputFileValue.lastIndexOf('\\');
               if (index != -1 || index2 != -1) {
                  index = index > index2 ? index : index2;
                  cm_name_field.value = inputFileValue.substring(index + 1, inputFileValue.length );
               } else {
                  cm_name_field.value = inputFileValue;
               }
               cm_name_field.form.formRuntime.updateSubmitElements();

            }
         });
      },

      /**
       * Hides the field, used when a content property can not be shown.
       * 
       * @method _hideField
       * @private
       */
      _hideField : function Upload__hideField() {
         if (!this.options.forceContent) {
            // change the name of the textarea so it is not submitted as new
            // content!
            Dom.get(this.id).name = "-";
         }

         // hide the whole field
         Dom.get(this.id + "-field").style.display = "none";
      },
      _getFileNameField : function Upload__getFileNameField() {
         var formEl = this.widgets.inputFile.form;
         var fileNameElement = formEl.elements['prop_cm_name'];
         return fileNameElement;
      }

   });
})();
