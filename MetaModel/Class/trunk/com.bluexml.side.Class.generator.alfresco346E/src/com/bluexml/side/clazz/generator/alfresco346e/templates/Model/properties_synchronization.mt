<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.CommonServices

%>

<%script type="clazz.ClassPackage" name="validatedFilename" post="trim()" %>
<%if (eContainer() == null) {%><%getConfModulePath()%>/properties-synchronization.xml<%}%>
<%script type="clazz.ClassPackage" name="generator" file="<%validatedFilename%>" post="trim()" %>
<propertiesSynchronization>
<%for (getAllClasses().nSort("name")){%>
<%if (metainfo[key == "autoUpdate"].nSize() > 0 || getAllAttributes().metainfo[key == "autoUpdate"].nSize() > 0 || getInheritedClasses().metainfo[key == "autoUpdate"].nSize() > 0){%>
	<type qname="<%getPrefixedNamespaceQName()%>">
	<%generateConfigForClass()%>
	<%for (getInheritedClasses()){%>
	<%filter("Clazz").generateConfigForClass()%>
	<%}%>
	<%generateConfigForAttribute()%>
	</type>
<%}%>		
<%}%>
</propertiesSynchronization>
<%script type="clazz.Clazz" name="generateConfigForAttribute" post="trim()" %>
<%-- value : text ${<prefixedQName2>} text --%>
<%for (getAllAttributes()){%>
<%if (metainfo[key == "autoUpdate"].nSize() == 1){%>
		<property qname="<%getPrefixedQualifiedName()%>"><%metainfo[key == "autoUpdate"].value%></property>
<%}%>
<%}%>
<%script type="clazz.Clazz" name="generateConfigForClass" post="trim()" %>
<%-- value : <prefixedQName1>=text ${<prefixedQName2>} text --%>
<%for (metainfo[key == "autoUpdate"]){%>
		<property qname="<%value.substring(0, value.indexOf("="))%>"><%value.substring(value.indexOf("=")+1)%></property>
<%}%>
