<#include "include/alfresco-template.ftl" />
<#include "include/documentlibrary.inc.ftl" />
<@templateHeader>
   <@documentLibraryJS />
   <script type="text/javascript">//<![CDATA[
      new Alfresco.widget.Resizer("DocumentLibrary");
   //]]></script>
   <@script type="text/javascript" src="${url.context}/res/modules/documentlibrary/doclib-actions.js"></@script>
</@>

<@templateBody>
   <div id="alf-hd">
      <@region id=appType + "header" scope="global" protected=true />
      <@region id=appType + doclibType + "title" scope="template" protected=true />
      <@region id=appType + doclibType + "navigation" scope="template" protected=true />
   </div>
   <div id="bd">
      <@region id=doclibType + "actions-common" scope="template" protected=true />
      <div class="yui-t1" id="alfresco-documentlibrary">
         <div id="yui-main">
            <div class="yui-b" id="alf-content">
               <@region id=doclibType + "toolbar" scope="template" protected=true />
               <@region id=doclibType + "documentlist" scope="template" protected=true />
            </div>
         </div>
         <div class="yui-b" id="alf-filters" style="display:none">            
            <@region id=doclibType + "tree" scope="template" protected=true />
         </div>
      </div>

      
   </div>
</@>

<@templateFooter>
   <div id="alf-ft">
      <@region id="footer" scope="global" protected=true />
   </div>
</@>