/**
 * Custom content types
 */
function getContentTypes()
{
   // TODO: Data webscript call to return list of available types
   var contentTypes = [
   {
      id: "cm:content",
      value: "cm_content"
   }];

   return contentTypes;
}
//--START BLUEXML-PATCH [add custom content types]
var availableContentTypes = [

   {id: "Ifremer:WorkflowAdvancedStartTask", value: "WorkflowAdvancedStartTask"} ,
   {id: "Ifremer:WorkflowSimpleStartTask", value: "WorkflowSimpleStartTask"} 
];
   
var contentTypes = getContentTypes().concat(availableContentTypes);   
//--END BLUEXML-PATCH [add custom content types]
model.contentTypes = contentTypes;
