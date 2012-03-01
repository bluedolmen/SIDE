<#import "pickerresults.lib.ftl" as pickerResultsLib />
<#import "/org/alfresco/repository/forms/pickertreenoderesults.lib.ftl" as pickerResultsLib2 />
<#if type?exists && type == "treeNode">
<@pickerResultsLib2.pickerTreeNodeResultsJSON results=results />
<#else>
<@pickerResultsLib.pickerResultsJSON results=results />
</#if>
