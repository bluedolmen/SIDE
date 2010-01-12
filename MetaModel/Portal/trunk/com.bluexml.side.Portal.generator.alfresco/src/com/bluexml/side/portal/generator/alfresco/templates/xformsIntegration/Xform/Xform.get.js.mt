<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/com/bluexml/components/Xform/Xform.get.js

<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
/* caches */
var propertyFields = null;
var associationFields = null;
var defaultControls = null;
var defaultConstraintHandlers = null;
var formUIConstraints = null;
         
/* constants */
const PROP_PREFIX = "prop:"
const ASSOC_PREFIX = "assoc:";

/**
 * Main entrypoint for component webscript logic
 *
 * @method main
 */
function main()
{
   var formUIModel = null;
   
   var itemKind = getArgument("itemKind");
   var itemId = getArgument("itemId");
   
   if (itemKind != null && itemKind.length > 0 && itemId != null && itemId.length > 0)
   {
      // determine what mode we are in from the arguments
      var mode = getArgument("mode", "edit");
      
      // determine if a form with a specific form is required
      var formId = getArgument("formId");

      if (logger.isLoggingEnabled())
      {
         logger.log("Showing form (id=" + formId + ") for item: [" + itemKind + "]" + itemId);
      }
      
      // get the config for the form
      var formConfig = getFormConfig(itemId, formId);
      
      // get the configured visible fields
      var visibleFields = getVisibleFields(mode, formConfig);
      
      // build the JSON object to send to the server
      var postBody = createPostBody(itemKind, itemId, visibleFields, formConfig);
         
      // make remote call to service
      var connector = remote.connect("alfresco");
      var json = connector.post("/api/formdefinitions", 
            jsonUtils.toJSONString(postBody), "application/json");
      
      if (logger.isLoggingEnabled())
      {
         logger.log("json = " + json);
      }
      
      var formModel = eval('(' + json + ')');
      
      // if we got a successful response attempt to render the form
      if (json.status == 200)
      {
         
      }
      else
      {
         model.error = formModel.message;
      }
   }
   
   // log the model
   //dumpFormUIModel(formUIModel);
   
   // pass form ui model to FTL
   model.form = formModel.data;
}

/**
 * Retrieves the value of the given named argument, looks in the 
 * URL arguments and the component binding properties
 *
 * @method getArgument
 * @param argName The name of the argument to locate
 * @param defValue The default value to use if the argument could not be found
 * @return The value or null if not found
 */
function getArgument(argName, defValue)
{
   var result = null;
   
   var argValue = null;
   try
   {
      argValue = instance.properties[argName];
   }
   catch (e)
   {
      argValue = null;
   }
   if (argValue !== null)
   {
      if (argValue.length > 0)
      {
         // check for parameterised values i.e. {xyz}
         // if found leave result resolution to 'args' map
         // as the value will have been resolved
         if (argValue.charAt(0) !== "{" || 
             argValue.charAt(argValue.length-1) !== "}")
         {
            result = argValue;
         }
      }
      else
      {
         result = "";
      }
   }
   
   // if result is null try the 'context.properties' map
   if (result === null)
   {
      argValue = context.properties[argName];
      if (argValue !== null)
      {
         if (argValue.length > 0)
         {
            // check for parameterised values i.e. {xyz}
            // if found leave result resolution to 'args' map
            // as the value will have been resolved
            if (argValue.charAt(0) !== "{" || 
                argValue.charAt(argValue.length-1) !== "}")
            {
               result = argValue;
            }
         }
         else
         {
            result = "";
         }
      }
   }
   
   // if result is still null try the 'args' map
   if (result === null)
   {
      argValue = args[argName];
      if (argValue !== null)
      {
   	   result = argValue;
      }
   }
   
   // if we still don't have a result and a default has been
   // defined, return that instead
   if (result === null && typeof defValue !== "undefined")
   {
      result = defValue;
   }
   
   if (logger.isLoggingEnabled())
   {
      logger.log("Returning \"" + result + "\" from getArgument for \"" + argName + "\"");
   }
   
   return result;
}


/**
 * Finds the configuration for the given item id, if
 * there isn't any configuration for the item null is
 * returned.
 *
 * @method getFormConfig
 * @param itemId The id of the item to retrieve for config for
 * @param formId The id of the specific form to lookup or null
 *               to get the default form
 * @return Object representing the configuration or null
 */
function getFormConfig(itemId, formId)
{
   var formConfig = null;
   
   // query for configuration for item
   var nodeConfig = config.scoped[itemId];
   
   if (nodeConfig !== null)
   {
      // get the forms configuration
      var formsConfig = nodeConfig.forms;

      if (formsConfig !== null)
      {
         if (formId !== null && formId.length > 0)
         {
            // look up the specific form
            formConfig = formsConfig.getForm(formId);
         }
         else
         {
            // look up the default form
            formConfig = formsConfig.defaultForm;
         }
      }
   }
   
   return formConfig;
}

/**
 * Returns the list of fields configured to be visible for the 
 * given mode. If this method returns null or an empty list the
 * component should attempt to display ALL known data for the item, 
 * unless there are fields configured to be hidden.
 *
 * @method getVisibleFields
 * @param mode The mode the form is rendering, 'view', 'edit' or 'create'
 * @param formConfig The form configuration, maybe null
 * @return Array of field names or null
 */
function getVisibleFields(mode, formConfig)
{
   var visibleFields = null;
   
   if (formConfig != null)
   {
      // get visible fields for the current mode
      switch (mode)
      {
         case "view":
            visibleFields = formConfig.visibleViewFieldNames;
            break;
         case "edit":
            visibleFields = formConfig.visibleEditFieldNames;
            break;
         case "create":
            visibleFields = formConfig.visibleCreateFieldNames;
            break;
         default:
            visibleFields = formConfig.visibleViewFieldNames;
            break;
      }
   }
   
   if (logger.isLoggingEnabled())
   {
      logger.log("Fields configured to be visible for " + mode + " mode = " + visibleFields);
   }
         
   return visibleFields;
}


/**
 * Creates an Object to represent the body of the POST request
 * to send to the form service.
 *
 * @method createPostBody
 * @param itemKind The kind of item
 * @param itemId The id of the item
 * @param visibleFields List of fields to get data for
 * @param formConfig The form configuration object
 * @return Object representing the POST body
 */
function createPostBody(itemKind, itemId, visibleFields, formConfig)
{
   var postBody = {};
   
   postBody.itemKind = itemKind;
   postBody.itemId = itemId.replace(":/", "");
   
   if (visibleFields !== null)
   {
      // TODO: find a way to return/make a native JS array, for now
      //       convert the Java List to a JS array checking force as we go
      var postBodyFields = [];
      var postBodyForcedFields = [];
      var fieldId = null;
      for (var f = 0; f < visibleFields.length; f++)
      {
         fieldId = visibleFields[f]
         postBodyFields.push(fieldId);
         if (formConfig.isFieldForced(fieldId))
         {
            postBodyForcedFields.push(fieldId);
         }
      }
      
      postBody.fields = postBodyFields;
      if (postBodyForcedFields.length > 0)
      {
         postBody.force = postBodyForcedFields;
      }
   }
   
   if (logger.isLoggingEnabled())
   {
      logger.log("postBody = " + jsonUtils.toJSONString(postBody));
   }
      
   return postBody;
}

main();
