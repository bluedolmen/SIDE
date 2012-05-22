<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%--
we replace ":" by escaped "\:" because java Properties files use '=' and ':' as key/value separator
--%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/association-synchronization.properties<%}%>
<%script type="clazz.ClassPackage" name="generator" file="<%validatedFilename%>"%>

<%for (getAllAssociations().nSort("name")){%>
<%if (firstEnd.navigable && secondEnd.navigable){%>
<%getPrefixedAssociationQName(firstEnd).replaceFirst(":", "\\\\:")%>=<%getPrefixedAssociationQName(secondEnd)%>
<%getPrefixedAssociationQName(secondEnd).replaceFirst(":", "\\\\:")%>=<%getPrefixedAssociationQName(firstEnd)%>
<%}%>


<%for (metainfo[key == "synchro-association"]){%>
<%if (current("Association").firstEnd.navigable && current("Association").secondEnd.navigable){%>
# syncho-association on two way association is not Implemented ! (<%current("Association")%>)
<%}else{%>
<%current("Association").getPrefixedAssociationQNameForSource().replaceFirst(":", "\\\\:")%>=<%EObjectValue.filter("Association").getPrefixedAssociationQNameForTarget()%>
<%current("Association").getPrefixedAssociationQNameForTarget().replaceFirst(":", "\\\\:")%>=<%EObjectValue.filter("Association").getPrefixedAssociationQNameForSource()%>
<%}%>

<%}%>

<%}%>

