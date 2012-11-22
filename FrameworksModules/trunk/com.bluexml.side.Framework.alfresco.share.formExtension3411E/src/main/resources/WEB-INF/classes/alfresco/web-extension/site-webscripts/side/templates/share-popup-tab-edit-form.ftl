<#if formUI == "true">
   <@formLib.renderFormsRuntime formId=formId />
</#if>

<div id="${args.htmlid}-dialog">
   <div id="${args.htmlid}-dialogTitle" class="hd">${msg("title")}</div>
   <div class="bd">

      <div id="${formId}-container" class="form-container">

         <div class="yui-g">
            <h2 id="${args.htmlid}-dialogHeader">${msg("header")}</h2>
         </div>
   
         <#if form.showCaption?exists && form.showCaption>
            <div id="${formId}-caption" class="caption"><span class="mandatory-indicator">*</span>${msg("form.required.fields")}</div>
         </#if>
      
         <form id="${formId}" method="${form.method}" accept-charset="utf-8" enctype="${form.enctype}" action="${form.submissionUrl}">
   
         <#if form.destination??>
            <input id="${formId}-destination" name="alf_destination" type="hidden" value="${form.destination}" />
         </#if>
   
            <@formLib.renderFormContainer formId=formId>  
		   <div id="${formId}-tabview" class="yui-navset"> 
		      <ul class="yui-nav">
		         <#list form.structure as item>
		            <#if item.kind == "set">
		               <li<#if item_index == 0> class="selected"</#if>><a href="#tab_${item_index}"><em>${item.label}</em></a></li>
		            </#if>
		         </#list>
		      </ul>                
		      <div class="yui-content">
		         <#list form.structure as item>
		            <#if item.kind == "set">
		               <div id="tab_${item_index}">
		                  <@formLib.renderSet set=item />
		               </div>      
		            </#if>
		         </#list>
		      </div>
		   </div>
			</@>

			<script type="text/javascript">//<![CDATA[
			(function() 
			{
			   new YAHOO.widget.TabView('${formId}-tabview');
			})();
			//]]></script>

		<#if args.showCancelButton?exists && args.showCancelButton != "true">
		<#-- hack create a cancel button for simple-dialog when  showCancelButton == true, simple-dialog always search for cancel button even if showCancelButton == "true" -->
		<div style="display:none;"> <input id="${formId}-cancel" type="button" value="${msg("form.button.cancel.label")}" style="display:none;"/></div>
		
		</#if>
            <!--<div class="bdft">
               <input id="${formId}-submit" type="submit" value="${msg("form.button.submit.label")}" />
               &nbsp;<input id="${formId}-cancel" type="button" value="${msg("form.button.cancel.label")}" />
            </div>
      -->
         </form>

      </div>
   </div>
</div>