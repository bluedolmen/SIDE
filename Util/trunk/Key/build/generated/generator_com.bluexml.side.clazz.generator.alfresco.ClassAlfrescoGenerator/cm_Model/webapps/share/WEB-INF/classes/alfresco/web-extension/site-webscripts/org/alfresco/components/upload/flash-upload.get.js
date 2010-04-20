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

   {id: "cm:authority", value: "Alfresco Authority Abstract Type"} ,
   {id: "cm:category", value: "Category"} ,
   {id: "cm:folder", value: "Folder"} ,
   {id: "cm:mlContainer", value: "Multilingual Container"} ,
   {id: "cm:authorityContainer", value: "Alfresco Authority Type"} ,
   {id: "cm:thumbnail", value: "Thumbnail"} ,
   {id: "cm:dictionaryModel", value: "Dictionary Model"} ,
   {id: "cm:savedquery", value: "Saved Query"} ,
   {id: "cm:zone", value: "Alfresco Authentication Zone Type"} ,
   {id: "cm:systemfolder", value: "System Folder"} ,
   {id: "cm:mlRoot", value: "Multilingual Root"} ,
   {id: "cm:category_root", value: "Category Root"} ,
   {id: "cm:content", value: "Content"} ,
   {id: "cm:cmobject", value: "Object"} ,
   {id: "cm:link", value: "Link Object"} ,
   {id: "cm:person", value: "Person"} 
];
   
var contentTypes = getContentTypes().concat(availableContentTypes);   
//--END BLUEXML-PATCH [add custom content types]
model.contentTypes = contentTypes;
