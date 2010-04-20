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

   {id: "Key:com_bluexml_side_util_security_License", value: "License"} ,
   {id: "Key:com_bluexml_side_util_security_Partnership", value: "Partnership"} ,
   {id: "Key:com_bluexml_side_util_security_Contact", value: "Contact"} 
];
   
var contentTypes = getContentTypes().concat(availableContentTypes);   
//--END BLUEXML-PATCH [add custom content types]
model.contentTypes = contentTypes;
