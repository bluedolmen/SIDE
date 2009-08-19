<#import "/org/alfresco/webscripts.lib.html.ftl" as wsLib/>
<#import "/org/alfresco/cmis/atomentry.lib.atom.ftl" as entryLib/>
<classes xmlns:cmis="http://www.cmis.org/2008/05" xmlns:alf="http://www.alfresco.com">
 <#assign rs = cmisresultset(resultset)>
<#list rs.rows as row>
<@entryLib.row row/>
</#list>
</classes>