<#macro addStandaloneTemplateHeader>
  <#if (page.url.args.css?exists) >
	  <link rel="stylesheet" type="text/css" href="/side/css/${page.url.args.css}" />
  <#else>
	  <@link rel="stylesheet" type="text/css" href="${url.context}/templates/publish/publish.css" />
  </#if>
  <#if (page.url.args.js?exists)>
	  <script type="text/javascript" href="/side/js/${page.url.args.js}"></script>
  </#if>
</#macro>

<#macro addStandaloneTemplateBody>
   <div id="alf-hd">
      
   </div>
   <div id="bd">
      <div class="share-form">
      
         					<!-- use default share components-->
         				<@region 
         						scope="template" 
         						id="formPortlet"
         					 	protected=true />
         				
      </div>
   </div>
</#macro>

<#macro addStandaloneTemplateFooter>
   <div id="alf-ft">

   </div>
</#macro>
