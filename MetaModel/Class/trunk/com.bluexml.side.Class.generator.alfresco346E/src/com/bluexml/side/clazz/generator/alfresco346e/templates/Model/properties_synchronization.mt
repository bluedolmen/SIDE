<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.CommonServices

%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getConfModulePath()%>/properties-synchronization.xml<%}%>
<%script type="clazz.ClassPackage" name="generator" file="<%validatedFilename%>"%>
<propertiesSynchronization>
<%for (getAllClasses().nSort("name")){%>
<%if (metainfo[key == "autoUpdate"].nSize() > 0){%>
<%generateConfigForType(current())%>
<%for (getAllSubTypes()[metainfo[key == "autoUpdate"].nSize() == 0].filter("clazz.Clazz")){%>
<%generateConfigForType(current(1))%>
<%}%>
<%}%>
<%}%>
</propertiesSynchronization>

<%script type="clazz.Clazz" name="generateConfigForType"%>
	<type qname="<%getPrefixedNamespaceQName()%>">
<%for (args(0).metainfo[key == "autoUpdate"]){%>
		<property><%value%></property>
<%}%>
	</type>