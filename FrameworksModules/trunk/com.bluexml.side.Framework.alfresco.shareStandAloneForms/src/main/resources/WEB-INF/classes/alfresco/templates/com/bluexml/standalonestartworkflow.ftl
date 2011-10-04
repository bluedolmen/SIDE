<#include "../../org/alfresco/include/alfresco-template.ftl" />
<@templateHeader>
  <@link rel="stylesheet" type="text/css" href="${url.context}/templates/publish/publish.css" />
</@>

<@templateBody>
   <div id="alf-hd">
      
   </div>
   <div id="bd">
      <div class="share-form">
      
         					<!-- use default share components-->
         				<@region 
         						scope="template" 
         						id="formPortlet"
         					 	protected=true />
         					<!-- use default share components-->
         				<@region 
         						scope="template" 
         						id="workflowdata"
         					 	protected=true />
         
      
      </div>
   </div>
</@>

<@templateFooter>
   <div id="alf-ft">

   </div>
</@>
