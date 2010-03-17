<#import "/org/alfresco/webscripts.lib.html.ftl" as wsLib/>
<#import "/org/alfresco/cmis/lib/links.lib.atom.ftl" as linksLib/>
<#import "/org/alfresco/cmis/lib/atomentry.lib.atom.ftl" as entryLib/>
<#import "/org/alfresco/cmis/lib/ns.lib.atom.ftl" as ns/>
<classes xmlns:cmisra="<@ns.cmisraNS/>" xmlns:cmis="<@ns.cmisNS/>" xmlns:alf="<@ns.alfNS/>" xmlns:app="<@ns.appNS/>">
<#list resultset as row>
<@entryLib.document row />
</#list>

</classes>
