<#import "/org/alfresco/webscripts.lib.html.ftl" as wsLib/>
<#import "/org/alfresco/cmis/atomentry.lib.atom.ftl" as entryLib/>
<#import "/org/alfresco/cmis/ns.lib.atom.ftl" as ns/>
<classes xmlns:cmis="<@ns.cmisNS/>" xmlns:alf="<@ns.alfNS/>" xmlns:app="<@ns.appNS/>">
<#list resultset as row>
<@entryLib.document row />
</#list>

</classes>
