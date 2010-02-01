<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%-- Templates creation --%>
<%script type="Portal" name="createTemplates"%>
<%getProperty("alf.share.paths.core.site-webscripts")%>/com/bluexml/components/XformSearch/XformSearch.get.js
<%script type="Portal" name="alfrescoGenerator" file="<%createTemplates%>" post="trim()"%>
/**
 * Main entrypoint for component webscript logic
 *
 * @method main
 */
function main()
{
   var searchForms=eval('('+getArgument("searchForm")+')');
   if (searchForms) {
	   model.searchForms=searchForms;
   }
   
}

main();


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
