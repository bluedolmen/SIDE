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
<%if (getAllAttributes().metainfo[key == "autoUpdate"].nSize() > 0){%>
	<%generateConfigForType()%>
<%}%>
<%}%>
</propertiesSynchronization>

<%script type="clazz.Clazz" name="generateConfigForType"%>
	<type qname="<%getPrefixedNamespaceQName()%>">
<%for (getAllAttributes()){%>
<%if (metainfo[key == "autoUpdate"].nSize() == 1){%>
		<property qname="<%getPrefixedQualifiedName()%>"><%metainfo[key == "autoUpdate"].value%></property>
<%}%>
<%}%>
	</type>