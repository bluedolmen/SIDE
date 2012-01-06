<#import "/org/alfresco/cmis/lib/ns.lib.atom.ftl" as nsLib/>
<#import "/org/alfresco/cmis/lib/links.lib.atom.ftl" as linksLib/>
<#import "/org/alfresco/cmis/lib/atomentry.lib.atom.ftl" as entryLib/>
<classes xmlns:cmis="<@nsLib.cmisNS/>" xmlns:alf="<@nsLib.alfNS/>" xmlns:app="<@nsLib.appNS/>" xmlns:cmisra="<@nsLib.cmisraNS/>">
<#list resultset as row>
<@entryLib.document row />
</#list>

</classes>