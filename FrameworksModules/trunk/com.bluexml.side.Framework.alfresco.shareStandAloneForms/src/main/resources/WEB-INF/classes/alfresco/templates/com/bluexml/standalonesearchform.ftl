<#include "../../org/alfresco/include/alfresco-template.ftl" />
<#include "standalone-header.ftl" />

<@templateHeader>
	<@addStandaloneTemplateHeader/>
</@>

<@templateBody>
   <div id="alf-hd">
      
   </div>
   <div id="bd">
      <div class="share-form">
      
         					<!-- use default share components-->
         				<@region 
         						scope="template" 
         						id="standaloneSearchFormPortlet"
         					 	protected=true />
         				
      </div>
   </div>
</@>

<@templateFooter>
	<@addStandaloneTemplateFooter/>
</@>
