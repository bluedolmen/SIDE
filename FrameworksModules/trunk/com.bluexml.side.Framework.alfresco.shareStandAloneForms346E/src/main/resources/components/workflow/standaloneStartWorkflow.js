
/**
 * StartWorkflow component.
 *
 * @namespace Alfresco.component
 * @class Alfresco.component.StartWorkflow
 */
(function()
{
   /**
    * YUI Library aliases
    */
   var Dom = YAHOO.util.Dom,
      Event = YAHOO.util.Event;

   /**
    * StartWorkflow constructor.
    *
    * @param {String} htmlId The HTML id of the parent element
    * @return {Alfresco.component.StartWorkflow} The new StartWorkflow instance
    * @constructor
    */
   Alfresco.component.StartWorkflow = function StartWorkflow_constructor(htmlId)
   {
      Alfresco.component.StartWorkflow.superclass.constructor.call(this, htmlId, ["button"]);

      // Re-register with our own name
      this.name = "Alfresco.component.StartWorkflow";
      Alfresco.util.ComponentManager.reregister(this);

      // Instance variables
      this.options = YAHOO.lang.merge(this.options, Alfresco.component.StartWorkflow.superclass.options);
      this.selectedItems = "";
      this.destination = "";
      this.workflowTypes = [];

      YAHOO.Bubbling.on("objectFinderReady", this.onObjectFinderReady, this);
      

      return this;
   };

   YAHOO.extend(Alfresco.component.StartWorkflow, Alfresco.component.ShareFormManager,
   {

      /**
       * Object container for initialization options
       *
       * @property options
       * @type object
       */
      options:
      {
         /**
          * The nodeRefs, separated by commas, to display in the workflow forms packageItems control.
          *
          * @property selectedItems
          * @type string
          */
         selectedItems: "",

         /**
          * A nodeRef that represents the context of the workflow
          *
          * @property destination
          * @type string
          */
         destination: "",

         /**
          * The workflow types that can be started
          *
          * @property workflowDefinitions
          * @type Array of
          *    {
          *       name: {String} The workflow name (unique)
          *       title: {String} The title of the workflow
          *       description {String} The description of the workflow
          *    }
          */
         workflowDefinitions: []
      },

      /**
       * Fired by YUI when parent element is available for scripting.
       * Template initialisation, including instantiation of YUI widgets and event listener binding.
       *
       * @method onReady
       */
      onReady: function StartWorkflow_onReady()
      {
    	 
         return Alfresco.component.StartWorkflow.superclass.onReady.call(this);
      },

      /**
       * Will populate the form packageItem's objectFinder with selectedItems when its ready
       *
       * @method onObjectFinderReady
       * @param layer {object} Event fired (unused)
       * @param args {array} Event parameters
       */
      onObjectFinderReady: function StartWorkflow_onObjectFinderReady(layer, args)
      {
         var objectFinder = args[1].eventGroup;
         if (objectFinder.options.field == "assoc_packageItems" && objectFinder.eventGroup.indexOf(this.id) == 0)
         {
            objectFinder.selectItems(this.options.selectedItems);
         }
      },

      /**
       * Called when a workflow definition has been selected
       *
       * @method onWorkflowSelectChange
       */
      onWorkflowSelectChange: function StartWorkflow_onWorkflowSelectChange(p_sType, p_aArgs)
      {
         
      },

      /**
       * Called when a workflow form has been loaded.
       * Will insert the form in the Dom.
       *
       * @method onWorkflowFormLoaded
       * @param response {Object}
       */
      onWorkflowFormLoaded: function StartWorkflow_onWorkflowFormLoaded(response)
      {
         
      },

      /**
       * Event handler called when the "formContentReady" event is received
       */
      onStartWorkflowFormContentReady: function FormManager_onStartWorkflowFormContentReady(layer, args)
      {
         
      }

   });

})();
