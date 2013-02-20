<import resource="classpath:alfresco/web-extension/site-webscripts/org/alfresco/components/upload/flash-upload.get.js">

function main2()
{
   // Widget instantiation metadata...
   var htmlUpload = {
      id : "HtmlUpload", 
      name : "Alfresco.HtmlUpload"
   };
   model.widgets = [htmlUpload];
}

main2();